package com.bee.openhis.mapper;

import com.bee.openhis.domain.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

/**
* @author 19235
* @description 针对表【sys_user(用户信息表)】的数据库操作Mapper
* @createDate 2023-01-09 11:05:21
* @Entity com.bee.openhis.domain.User
*/

@Repository
public interface UserMapper extends BaseMapper<User> {

}




