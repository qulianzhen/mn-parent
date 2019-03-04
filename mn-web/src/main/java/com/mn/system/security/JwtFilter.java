package com.mn.system.security;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.BasicHttpAuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 创建 JWTFilter 实现前端请求统一拦截及处理
 */
public class JwtFilter extends BasicHttpAuthenticationFilter {

    // 登录标识
    // LOGIN_SIGN 是前端放置在 headers 头文件中的登录标识，如果用户发起的请求是需要登录才能正常返回的，
    // 那么头文件中就必须存在该标识并携带有效值,即，Header中存放了：Authorization=加密后的token值
    private static String LOGIN_SIGN = "Authorization";

    /**
     * 检测用户是否登录
     * 检测header里面是否包含Authorization字段即可
     *
     * @param request
     * @param response
     * @return
     */
    @Override
    protected boolean isLoginAttempt(ServletRequest request, ServletResponse response) {
        HttpServletRequest req = (HttpServletRequest) request;
        String authorization = req.getHeader(LOGIN_SIGN);
        return authorization != null;

    }
    /**
     * 执行登录认证
     *
     * @param request
     * @param response
     * @param mappedValue
     * @return
     */
    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        try {
            executeLogin(request, response);
            return true;
        } catch (Exception e) {
            //throw new TSharkException("登录权限不足！", e);
            return false;
        }
    }

    /**
     * 始终返回 true 的原因是因为具体的是否登录成功的判断，需要在 Realm 中手动实现，此处不做统一判断
     * 方法中的 getSubject(request, response).login(token) 就是触发 Shiro Realm 自身的登录控制，具体内容需要手动实现
     */
    @Override
    protected boolean executeLogin(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        String token = httpServletRequest.getHeader(LOGIN_SIGN);

        JwtToken jwtToken = new JwtToken(token);
        // 提交给realm进行登入，如果错误他会抛出异常并被捕获
        getSubject(request, response).login(jwtToken);
        // 如果没有抛出异常则代表登入成功，返回true
        return true;
    }

    /**
     * 对跨域提供支持
     */
    @Override
    protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        httpServletResponse.setHeader("Access-control-Allow-Origin", httpServletRequest.getHeader("Origin"));
        httpServletResponse.setHeader("Access-Control-Allow-Methods", "GET,POST,OPTIONS,PUT,DELETE");
        httpServletResponse.setHeader("Access-Control-Allow-Headers", httpServletRequest.getHeader("Access-Control-Request-Headers"));
        // 跨域时会首先发送一个option请求，这里我们给option请求直接返回正常状态
        if (httpServletRequest.getMethod().equals(RequestMethod.OPTIONS.name())) {
            httpServletResponse.setStatus(HttpStatus.OK.value());
            return false;
        }
        return super.preHandle(request, response);
    }


    /**
     * 验证权限失败后会执行此方法,即执行完上面的isAccessAllowed方法， 得到false后， 会进入此方法， 执行后续操作
     */
    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response)
            throws IOException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        Subject subject = getSubject(request, response);
        if(subject.getPrincipal() == null){
            saveRequestAndRedirectToLogin(request, response);
        }else{
            String unauthorizedUrl = getUnauthorizedUrl();
            //如果请求为ajax请求或者没有配置无权限页面，则不跳转到无权限页面，而是返回错误状态码401，让前台知道是无权限错误
            if(StringUtils.hasText(unauthorizedUrl) && httpRequest.getHeader("x-requested-with") == null){
                WebUtils.issueRedirect(request, response, unauthorizedUrl);
            }else{
                WebUtils.toHttp(response).sendError(401);
            }
        }
        return false;
    }


}