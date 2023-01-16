package com.bee.openhis.controller.system;


import com.bee.openhis.service.RoleService;
import com.bee.openhis.vo.AjaxResult;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("system/role")
public class RoleController {
    @Autowired
    private RoleService roleService;

    @PostMapping("saveRoleUser/{userId}/{roleIds}")
    public AjaxResult saveRoleUser(@PathVariable Long userId, @PathVariable Long[] roleIds) {
        if (roleIds.length == 1 && roleIds[0].equals(-1L)) {
            roleIds = new Long[]{};
        }
        roleService.saveRoleUser(userId, roleIds);
        return AjaxResult.success();
    }

    /**
     * 查询所有角色
     *
     * @return
     */
    @GetMapping("selectAllRole")
    public AjaxResult selectAllRole() {
        return AjaxResult.success(roleService.listAllRoles());
    }

    /**
     * 根据用户id查询他的角色
     *
     * @param userId
     * @return
     */
    @GetMapping("getRoleIdsByUserId/{userId}")
    public AjaxResult getRoleIdsByUserId(@PathVariable Long userId) {
        List<Long> roleIds = roleService.getRoleIdsByUserId(userId);
        return AjaxResult.success(roleIds);
    }

}
