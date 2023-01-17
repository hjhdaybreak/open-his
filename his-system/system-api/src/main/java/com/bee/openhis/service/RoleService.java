package com.bee.openhis.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bee.openhis.domain.Role;
import com.bee.openhis.dto.RoleDto;
import com.bee.openhis.vo.DataGridView;

import java.util.List;

/**
* @author 19235
* @description 针对表【sys_role(角色信息表)】的数据库操作Service
* @createDate 2023-01-16 21:05:18
*/
public interface RoleService extends IService<Role> {

    int saveRoleUser(Long userId, Long[] roleIds);

    List<Role> listAllRoles();

    List<Long> getRoleIdsByUserId(Long userId);

    DataGridView listRoleForPage(RoleDto roleDto);

    int addRole(RoleDto roleDto);

    int updateRole(RoleDto roleDto);

    Role getOne(Long roleId);

    int deleteRoleByIds(Long[] roleIds);

    void saveRoleMenu(Long roleId, Long[] menuIds);

}
