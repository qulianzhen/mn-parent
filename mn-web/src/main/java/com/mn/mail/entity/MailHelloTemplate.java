package com.mn.mail.entity;

/**
 * @Description: "问好"模板的实体类
 * @Author:Mloong
 * @Date :create in 2020/9/21-0:22
 * @Version 1.0
 * @Modified By:
 */
public class MailHelloTemplate {
    private String messageCode;

    private String messageStatus;

    private String cause;

    public String getMessageCode() {
        return messageCode;
    }

    public void setMessageCode(String messageCode) {
        this.messageCode = messageCode;
    }

    public String getMessageStatus() {
        return messageStatus;
    }

    public void setMessageStatus(String messageStatus) {
        this.messageStatus = messageStatus;
    }

    public String getCause() {
        return cause;
    }

    public void setCause(String cause) {
        this.cause = cause;
    }
}
