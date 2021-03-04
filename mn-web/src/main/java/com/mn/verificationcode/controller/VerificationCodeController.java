package com.mn.verificationcode.controller;

import com.mn.commonbean.restful.Message;
import com.mn.mnutil.MessageUtil;
import com.mn.verificationcode.entity.vo.VerificationCodeVo;
import com.mn.verificationcode.service.VerificationCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description: 验证码Controller
 * @Author:Mloong
 * @Date :create in 2020/9/9-23:37
 * @Version 1.0
 * @Modified By:
 */
@RestController
@RequestMapping("/api/verifyCode")
public class VerificationCodeController {
    @Autowired
    private VerificationCodeService verificationCodeService;

    @GetMapping("/create")
    public Message verifyCode(){
        VerificationCodeVo vo = verificationCodeService.create();
        return MessageUtil.successMsg(vo);
    }



}
