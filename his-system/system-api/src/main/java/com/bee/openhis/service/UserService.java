package com.bee.openhis.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bee.openhis.domain.User;
import com.bee.openhis.dto.UserDto;
import com.bee.openhis.vo.DataGridView;

import java.util.List;

/**
 * @author 19235
 * @description 针对表【sys_user(用户信息表)】的数据库操作Service
 * @createDate 2023-01-09 11:05:21
 */
public interface UserService extends IService<User> {

    User queryUserByPhone(String phone);

    DataGridView listUserForPage(UserDto userDto);

    int addUser(UserDto userDto);

    int updateUser(UserDto userDto);

    int deleteUserByIds(Long[] userIds);

    List<User> getAllUsers();

    void resetPassword(Long[] userIds);
}
