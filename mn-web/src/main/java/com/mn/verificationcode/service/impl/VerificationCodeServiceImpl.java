package com.mn.verificationcode.service.impl;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.LineCaptcha;
import com.mn.module.redis.RedisUtil;
import com.mn.verificationcode.entity.vo.VerificationCodeVo;
import com.mn.verificationcode.service.VerificationCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * @Description: (描述)
 * @Author:Mloong
 * @Date :create in 2020/9/9-23:54
 * @Version 1.0
 * @Modified By:
 */
@Service("verificationCodeService")
public class VerificationCodeServiceImpl implements VerificationCodeService {
    private final String prex = "MN_VERCODE:";

    @Autowired
    private RedisUtil redisUtil;

    @Override
    public VerificationCodeVo create() {
        LineCaptcha lineCaptcha = null;
        String verCode = null;
        String[] ignoreCharArray = {"i","l","o","0","1"};
        boolean haveFlg = false;
        for(int i=0;i<10;i++){//重复10次，直到验证码去掉不易区分的字母和数字
            lineCaptcha = CaptchaUtil.createLineCaptcha(98, 27,5,80);
            verCode = lineCaptcha.getCode();
            for(String ignoreChar : ignoreCharArray){
                if(verCode.contains(ignoreChar)){
                    haveFlg = true;
                    break;
                }
            }
            if(haveFlg){
                continue;
            }
            break;
        }
        String codeId = UUID.randomUUID().toString().replace("-","");
        redisUtil.set(prex + codeId,verCode,300);
        VerificationCodeVo vo = new VerificationCodeVo();
        vo.setCodeId(codeId);
        vo.setDataImg("data:image/png;base64,"+lineCaptcha.getImageBase64());
        return vo;
    }

    @Override
    public boolean check(String codeId,String code) {
        Object dbCode = redisUtil.get(prex + codeId);
        if(dbCode !=null){
            redisUtil.del(prex + codeId);
            return code.equals(dbCode);
        }
        return false;
    }
}


