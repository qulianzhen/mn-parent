package com.mn.verificationcode.service;

import com.mn.verificationcode.entity.vo.VerificationCodeVo;

/**
 * @Description: (描述)
 * @Author:Mloong
 * @Date :create in 2020/9/9-23:54
 * @Version 1.0
 * @Modified By:
 */
public interface VerificationCodeService {
    /**
     * 生成验证码
     * @return
     */
    VerificationCodeVo create();

    /**
     * 校验验证码
     * @param codeId 验证码标志
     * @param code 验证码
     * @return
     */
    boolean check(String codeId,String code);
}
