package com.bee.openhis.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bee.openhis.domain.User;
import com.bee.openhis.service.UserService;
import com.bee.openhis.mapper.UserMapper;
import org.springframework.stereotype.Service;

/**
 * @author 19235
 * @description 针对表【sys_user(用户信息表)】的数据库操作Service实现
 * @createDate 2023-01-09 11:05:21
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
        implements UserService {

}




