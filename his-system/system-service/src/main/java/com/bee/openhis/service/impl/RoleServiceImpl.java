package com.bee.openhis.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bee.openhis.constants.Constants;
import com.bee.openhis.domain.Role;
import com.bee.openhis.dto.RoleDto;
import com.bee.openhis.mapper.RoleMapper;
import com.bee.openhis.service.RoleService;
import com.bee.openhis.vo.DataGridView;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * @author 19235
 * @description 针对表【sys_role(角色信息表)】的数据库操作Service实现
 * @createDate 2023-01-16 21:05:18
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role>
        implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public int saveRoleUser(Long userId, Long[] roleIds) {
        roleMapper.deleteRoleUserByUserIds(Arrays.asList(userId));
        for (Long roleId : roleIds) {
            roleMapper.saveRoleUser(userId, roleId);
        }
        return 0;
    }

    @Override
    public List<Role> listAllRoles() {
        QueryWrapper<Role> wrapper = new QueryWrapper<>();
        wrapper.eq(Role.COL_STATUS, Constants.STATUS_TRUE);
        wrapper.orderByAsc(Role.COL_ROLE_SORT);
        return roleMapper.selectList(wrapper);
    }

    @Override
    public List<Long> getRoleIdsByUserId(Long userId) {
        return roleMapper.selectRoleIdsByUserId(userId);
    }

}




