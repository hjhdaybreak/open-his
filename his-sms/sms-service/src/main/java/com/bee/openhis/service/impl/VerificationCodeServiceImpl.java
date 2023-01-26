package com.bee.openhis.service.impl;

import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bee.openhis.config.SmsConfig;
import com.bee.openhis.domain.VerificationCode;
import com.bee.openhis.mapper.VerificationCodeMapper;
import com.bee.openhis.service.VerificationCodeService;
import com.bee.openhis.utils.HttpUtils;
import com.bee.openhis.utils.IdGeneratorSnowflake;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.Method;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;


import java.net.URLEncoder;

/**
 * @author 19235
 * @description 针对表【his_verification_code】的数据库操作Service实现
 * @createDate 2023-01-11 16:57:45
 */
@Service(timeout = 5000, methods = {@Method(name = "sendSms", retries = 0)})
public class VerificationCodeServiceImpl extends ServiceImpl<VerificationCodeMapper, VerificationCode>
        implements VerificationCodeService {

    @Autowired
    private VerificationCodeMapper verificationCodeMapper;

    @Autowired
    private SmsConfig smsConfig;

    @Override
    public int sendSms(String phoneNumber) {
        Integer code = IdGeneratorSnowflake.generateVerificationCode();
        try {
            String result = this.execute(phoneNumber, code);
            JSONObject jsonObject = (JSONObject) JSON.parse(result);
            if (jsonObject != null && StringUtils.isNotEmpty(jsonObject.getString("respCode"))) {
                if (jsonObject.getString("respCode").equals("0000")) {
                    return saveVerification(phoneNumber, code);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

    @Override
    public VerificationCode checkCode(String phoneNumber, Integer code) {
        QueryWrapper<VerificationCode> wrapper = new QueryWrapper<>();
        wrapper.eq(VerificationCode.PHONE_NUMBER, phoneNumber);
        wrapper.eq(VerificationCode.VERIFICATION_CODE, code);
        wrapper.eq(VerificationCode.IS_CHECK, false);
        return verificationCodeMapper.selectOne(wrapper);
    }


    private int saveVerification(String phoneNumber, Integer code) {
        VerificationCode verificationCode = new VerificationCode();
        verificationCode.setPhoneNumber(phoneNumber);
        verificationCode.setVerificationCode(code);
        verificationCode.setCreateTime(DateUtil.date());
        verificationCode.setIsCheck(false);
        return verificationCodeMapper.insert(verificationCode);
    }

    /**
     * 短信发送(验证码通知,会员营销)
     * 接口文档地址：http://www.danmi.com/developer.html#smsSend
     */
    public String execute(String phoneNumber, Integer code) throws Exception {
        String sb = "accountSid" + "=" + smsConfig.getACCOUNT_SID() +  //客户id
                "&to" + "=" + phoneNumber +
                "&templateid" + "=" + smsConfig.getTEMPLATE_ID() +     //模板id
                "&param" + "=" + URLEncoder.encode(code.toString(), "UTF-8");
        String body = sb + smsConfig.createCommonParam(smsConfig.getACCOUNT_SID(), smsConfig.getAUTH_TOKEN());
        return HttpUtils.post(smsConfig.getBASE_URL(), body);
    }

}




