package com.mn.verificationcode.entity.vo;

import java.io.Serializable;

/**
 * @Description: (描述)
 * @Author:Mloong
 * @Date :create in 2020/9/9-23:58
 * @Version 1.0
 * @Modified By:
 */
public class VerificationCodeVo implements Serializable {
    /**
     * 验证码标志
     */
    private String codeId;
    /**
     * 验证码图片base64
     */
    private String dataImg;

    public String getCodeId() {
        return codeId;
    }

    public void setCodeId(String codeId) {
        this.codeId = codeId;
    }

    public String getDataImg() {
        return dataImg;
    }

    public void setDataImg(String dataImg) {
        this.dataImg = dataImg;
    }
}
