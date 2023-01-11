package com.bee.openhis.controller.sms;


import com.bee.openhis.service.VerificationCodeService;
import com.bee.openhis.vo.AjaxResult;
import io.swagger.models.auth.In;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("system/sms")
public class SmsSendController {

    @Reference
    private VerificationCodeService verificationCodeService;


    /**
     * 获取验证码
     *
     * @param phoneNumber
     * @return
     */
    @PostMapping("sendSms/{phoneNumber}")
    public AjaxResult acquireVerifyCode(@PathVariable String phoneNumber) {
        return AjaxResult.toAjax(verificationCodeService.sendSms(phoneNumber));
    }

    @GetMapping("checkCode/{phoneNumber}/{code}")
    public AjaxResult checkCode(@PathVariable String phoneNumber, @PathVariable Integer code) {
        return AjaxResult.success(verificationCodeService.checkCode(phoneNumber, code));
    }
}
