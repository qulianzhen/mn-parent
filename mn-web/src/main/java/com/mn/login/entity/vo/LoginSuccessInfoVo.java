package com.mn.login.entity.vo;

/**
 * @Description: (描述)
 * @Author:Mloong
 * @Date :create in 2020/5/13-22:10
 * @Version 1.0
 * @Modified By:
 */
public class LoginSuccessInfoVo {
    /**
     * 用户身份标志
     */
    private String subject;
    /**
     * 用户登录token
     */
    private String token;

    /**
     * 昵称
     */
    private String nickName;


    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }
}
