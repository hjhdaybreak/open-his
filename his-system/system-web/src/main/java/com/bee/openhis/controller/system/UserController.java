package com.bee.openhis.controller.system;


import com.bee.openhis.dto.UserDto;
import com.bee.openhis.service.UserService;
import com.bee.openhis.utils.ShiroSecurityUtils;
import com.bee.openhis.vo.AjaxResult;
import com.bee.openhis.vo.DataGridView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotEmpty;

@RestController
@RequestMapping("system/user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 分页查询用户
     *
     * @param userDto
     * @return
     */
    @GetMapping("listUserForPage")
    public AjaxResult listUserForPage(UserDto userDto) {
        DataGridView dataGridView = userService.listUserForPage(userDto);
        return AjaxResult.success("查询成功", dataGridView.getData(), dataGridView.getTotal());
    }


    @PostMapping("addUser")
    public AjaxResult addUser(@Validated UserDto userDto) {
        userDto.setSimpleUser(ShiroSecurityUtils.getCurrentSimpleUser());
        return AjaxResult.toAjax(userService.addUser(userDto));
    }

    /**
     * 更新用户
     *
     * @param userDto
     * @return
     */
    @PutMapping("updateUser")
    public AjaxResult updateUser(@Validated UserDto userDto) {
        userDto.setSimpleUser(ShiroSecurityUtils.getCurrentSimpleUser());
        return AjaxResult.toAjax(userService.updateUser(userDto));
    }

    /**
     * 批量删除用户
     *
     * @param userIds
     * @return
     */
    @DeleteMapping("deleteUserByIds/{userIds}")
    public AjaxResult deleteUserByIds(@PathVariable @Validated
                                      @NotEmpty(message = "要删除的id不能为空") Long[] userIds) {
        return AjaxResult.toAjax(userService.deleteUserByIds(userIds));
    }

    /**
     * 查询所有可用的用户
     */
    @GetMapping("selectAllUser")
    public AjaxResult selectAllUser() {
        return AjaxResult.success(userService.getAllUsers());
    }

    @PostMapping("resetPwd/{userIds}")
    public AjaxResult resetPwd(@PathVariable Long[] userIds) {
        if (userIds.length > 0) {
            userService.resetPassword(userIds);
            return AjaxResult.success("重置成功");
        }
        return AjaxResult.fail("重置失败,没有选择用户");
    }

}
