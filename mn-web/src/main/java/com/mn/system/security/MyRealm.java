package com.mn.system.security;

import com.mn.mnutil.JwtUtil;
import com.mn.permission.entity.po.SysUser;
import com.mn.permission.service.SysUserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Set;

public class MyRealm  extends AuthorizingRealm{
    @Autowired
    private SysUserService sysUserService;

    /**
     * 必须重写此方法，不然Shiro会报错
     * 重写 Realm 的 supports() 方法是通过 JWT 进行登录判断的关键
     * 因为前文中创建了 JWTToken 用于替换 Shiro 原生 token
     * 所以必须在此方法中显式的进行替换，否则在进行判断时会一直失败
     */
    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JwtToken;
    }

    /**
     * 只有当需要检测用户权限的时候才会调用此方法，例如checkRole,checkPermission之类的
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        String username = JwtUtil.getUsername(principals.toString());
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        Set<String> set = sysUserService.getUrlPermitByUserName(username);
        //添加权限信息
        simpleAuthorizationInfo.addStringPermissions(set);
        return simpleAuthorizationInfo;
    }

    /**
     * 默认使用此方法进行用户名正确与否验证，错误抛出异常即可。
     * 登录的合法验证通常包括 token 是否有效 、用户名是否存在 、密码是否正确
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken auth) throws AuthenticationException {
        String token = (String) auth.getCredentials();
        // 解密获得username，用于和数据库进行对比
        String username = JwtUtil.getUsername(token);
        if (username == null) {
            throw new AuthenticationException("token无效");
        }

        SysUser userBean = sysUserService.findSysUserByUserName(username);
        if (userBean == null) {
            throw new UnknownAccountException("用户不存在!");
        }
        if(userBean.getIslock() == 1){
            throw new DisabledAccountException("账号已锁定!");
        }

        //这里其实是校验的token是否无效，这里没有取用户传递过来的密码；
        if (!JwtUtil.verify(token, username, userBean.getPassword())) {
            throw new AuthenticationException("用户名或密码错误");
        }

        return new SimpleAuthenticationInfo(token, token, getName());
    }
}
