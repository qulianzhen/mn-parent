package com.mn.config;

import com.alibaba.fastjson.JSONObject;
import com.mn.commonbean.restful.Message;
import com.mn.mnutil.MessageUtil;
import com.mn.permission.service.SysUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

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

    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private RedisTemplate redisTemplate;


    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest)servletRequest;

        String requestUrl = req.getRequestURI();//获取访问url
        //这里应该是ant风格的url解析，解析两个*或者一个*所匹配的url，暂时写成eq
        if(MnSystemConfig.accessAnonList.contains(requestUrl)){

        }
        if(MnSystemConfig.accessAuthcList.contains(requestUrl)){
            String token = req.getHeader(TokenConfig.tokenName);
            String loginName = req.getHeader(TokenConfig.userflg);
            if(token == null || loginName == null){
                createErrorMsg(MessageUtil.codeMsg(401),servletResponse);
                return;
            }

            //从缓存中获取
            String seesionUser = (String) redisTemplate.opsForValue().get("session"+loginName);
            if(seesionUser == null || !seesionUser.equals(token)){
                createErrorMsg(MessageUtil.codeMsg(401),servletResponse);
                return;
            }
        }
        if(MnSystemConfig.accessPermsList.contains(requestUrl)){
            String token = req.getHeader(TokenConfig.tokenName);
            String loginName = req.getHeader(TokenConfig.userflg);
            if(token == null || loginName == null){
                createErrorMsg(MessageUtil.codeMsg(401),servletResponse);
                return;
            }

            //从缓存中获取--已经登录的用户，如果没有登录㢧，或者token值不同，则允许访问
            String seesionUser = (String) redisTemplate.opsForValue().get("session"+loginName);
            if(seesionUser == null || !seesionUser.equals(token)){
                createErrorMsg(MessageUtil.codeMsg(401),servletResponse);
                return;
            }

            //判断是否有访问该url的权限
            //这个方法加了缓存注解，会先从缓存中取，取不到时，再从数据库取
            Set<String> userPermitSet =  sysUserService.getUrlPermitByUserName(loginName);
            if(userPermitSet == null || userPermitSet.contains(requestUrl)){
                createErrorMsg(MessageUtil.codeMsg(403),servletResponse);
                return;
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
