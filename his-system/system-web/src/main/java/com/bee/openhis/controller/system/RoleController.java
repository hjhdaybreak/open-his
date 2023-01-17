package com.bee.openhis.controller.system;


import com.bee.openhis.dto.RoleDto;
import com.bee.openhis.service.RoleService;
import com.bee.openhis.utils.ShiroSecurityUtils;
import com.bee.openhis.vo.AjaxResult;

import com.bee.openhis.vo.DataGridView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping("system/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @GetMapping("listRoleForPage")
    public AjaxResult listRoleForPage(RoleDto roleDto) {
        DataGridView dataGridView = roleService.listRoleForPage(roleDto);
        return AjaxResult.success("查询成功", dataGridView.getData(), dataGridView.getTotal());
    }


    /**
     * 添加角色
     *
     * @param roleDto
     * @return
     */
    @PostMapping("addRole")
    public AjaxResult addRole(@Validated RoleDto roleDto) {
        roleDto.setSimpleUser(ShiroSecurityUtils.getCurrentSimpleUser());
        return AjaxResult.toAjax(roleService.addRole(roleDto));
    }


    @PutMapping("updateRole")
    public AjaxResult updateRole(@Validated RoleDto roleDto) {
        roleDto.setSimpleUser(ShiroSecurityUtils.getCurrentSimpleUser());
        return AjaxResult.toAjax(roleService.updateRole(roleDto));
    }


    /**
     * 根据角色 id 查询角色
     *
     * @param roleId
     * @return
     */
    @GetMapping("getRoleById/{roleId}")
    public AjaxResult getRoleById(@PathVariable @Validated @NotNull(message = "角色ID不能为空") Long roleId) {
        return AjaxResult.success(roleService.getOne(roleId));
    }

    /**
     * 批量删除角色
     *
     * @param roleIds
     * @return
     */
    @DeleteMapping("deleteRoleByIds/{roleIds}")
    public AjaxResult deleteRoleByIds(@PathVariable @Validated
                                      @NotEmpty(message = "要删除的id不能为空") Long[] roleIds) {
        return AjaxResult.toAjax(roleService.deleteRoleByIds(roleIds));
    }


    /**
     * 保存角色和菜单之间的关系
     *
     * @param roleId
     * @param menuIds
     * @return
     */
    @PostMapping("saveRoleMenu/{roleId}/{menuIds}")
    public AjaxResult saveRoleMenu(@PathVariable Long roleId, @PathVariable Long[] menuIds) {
        if (menuIds.length == 1 && menuIds[0].equals(-1L)) {
            menuIds = new Long[]{};
        }
        roleService.saveRoleMenu(roleId, menuIds);
        return AjaxResult.success();
    }

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
