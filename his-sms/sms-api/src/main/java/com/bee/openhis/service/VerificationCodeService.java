package com.bee.openhis.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bee.openhis.domain.VerificationCode;

/**
 * @author 19235
 * @description 针对表【his_verification_code】的数据库操作Service
 * @createDate 2023-01-11 16:57:45
 */
public interface VerificationCodeService extends IService<VerificationCode> {

    int sendSms(String phoneNumber);

    VerificationCode checkCode(String phoneNumber, Integer code);
}
