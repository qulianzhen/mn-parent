package com.mn.config;

import com.alibaba.fastjson.JSONObject;
import com.mn.commonbean.restful.Message;
import com.mn.mnutil.MessageUtil;
import com.mn.module.authentication.AuthenConstants;
import com.mn.module.authorization.AuthorConfig;
import com.mn.module.redis.RedisUtil;
import com.mn.sysuser.entity.po.SysUser;
import com.mn.sysuser.service.SysUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.AntPathMatcher;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Set;

/**
 * @version 1.0
 * @desc 描述  登录Filter
 * @auth qulianzhen
 * @date 2020-04-22 21:27
 */
public class MnAccessFilter implements Filter {
    private static final Logger LOGGER = LoggerFactory.getLogger(MnAccessFilter.class);
    private AntPathMatcher matcher = new AntPathMatcher();
    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private AuthorConfig authorConfig;
    @Value("${server.servlet.context-path}")
    private String contextPath;


    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest)servletRequest;
        String loginName = req.getHeader(TokenConfig.userflg);
        String requestUrl = req.getRequestURI().replace(contextPath,"");//获取访问url
        //ant风格的url解析，解析两个*或者一个*所匹配的url
        int matchResult = 0;//0未匹配  1只有认证  3 认证+鉴权
        for(String anonPattern:authorConfig.getAccessAnonList()){
            if(matcher.match(anonPattern,requestUrl)){
                matchResult = 1;//无认证+无鉴权
                break;
            }
        }
        if(matchResult == 0){
            for(String anonPattern:authorConfig.getAccessAuthcList()){
                if(matcher.match(anonPattern,requestUrl)){
                    matchResult = 2;//只有认证
                    break;
                }
            }
        }

        if(matchResult == 0){
            for(String anonPattern:authorConfig.getAccessPermsList()){
                if(matcher.match(anonPattern,requestUrl)){
                    matchResult = 3;//认证+鉴权
                    break;
                }
            }
        }

        if(matchResult == 2 || matchResult == 3){
            /*认证逻辑*/
            String token = req.getHeader(TokenConfig.tokenName);
            if(token == null || loginName == null){
                createErrorMsg(MessageUtil.codeMsg(401),servletResponse);
                return;
            }
            //从缓存中获取
            String userToken = (String) redisUtil.get(AuthenConstants.USERTOKENPREFIX + loginName);
            if(userToken == null || !userToken.equals(token)){
                createErrorMsg(MessageUtil.codeMsg(401),servletResponse);
                return;
            }else{//刷新token过期时间
                redisUtil.expire(AuthenConstants.USERTOKENPREFIX + loginName,TokenConfig.timeout*60);
            }
            /*鉴权逻辑*/
            if(matchResult == 3 && !AuthenConstants.SUPERADMIN.equals(loginName)){
                //判断是否有访问该url的权限
                //这个方法需要配合缓存注解，会先从缓存中取，取不到时，再从数据库取
                SysUser sysUser = sysUserService.get(loginName);
                Set<String> userPermitSet =  sysUserService.getOnlyUrlPermitByUserId(sysUser.getId());
                if(userPermitSet == null || !userPermitSet.contains(requestUrl)){
                    createErrorMsg(MessageUtil.codeMsg(403),servletResponse);
                    return;
                }
            }
        }


        filterChain.doFilter(servletRequest,servletResponse);

    }

    private void createErrorMsg(Message codeMsg,ServletResponse servletResponse) {
        servletResponse.setCharacterEncoding("UTF-8");
        servletResponse.setContentType("application/json; charset=utf-8");
        PrintWriter writer = null;
        OutputStreamWriter osw = null;
        try {
            osw = new OutputStreamWriter(servletResponse.getOutputStream() , "UTF-8");
            writer = new PrintWriter(osw, true);
            String jsonStr = JSONObject.toJSONString(codeMsg);
            writer.write(jsonStr);
            writer.flush();
            writer.close();
            osw.close();
        }catch(Exception e){
           LOGGER.error("createErrorMsg,error:",e);
        }finally{
            if (null != writer) {
                writer.close();
            }
            if(null != osw){
                try {
                    osw.close();
                } catch (IOException e2) {
                    LOGGER.error("createErrorMsg,osw.close() error:",e2);
                }
            }
        }
    }

    @Override
    public void destroy() {

    }
}
