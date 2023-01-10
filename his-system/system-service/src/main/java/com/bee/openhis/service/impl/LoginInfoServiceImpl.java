package com.bee.openhis.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bee.openhis.domain.LoginInfo;
import com.bee.openhis.mapper.LoginInfoMapper;
import com.bee.openhis.service.LoginInfoService;
import org.springframework.stereotype.Service;

/**
 * @author 19235
 * @description 针对表【sys_login_info(系统访问记录)】的数据库操作Service实现
 * @createDate 2023-01-10 17:05:40
 */
@Service
public class LoginInfoServiceImpl extends ServiceImpl<LoginInfoMapper, LoginInfo>
        implements LoginInfoService {

}




