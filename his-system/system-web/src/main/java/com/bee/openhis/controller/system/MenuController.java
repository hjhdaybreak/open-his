package com.bee.openhis.controller.system;

import com.bee.openhis.constants.Constants;
import com.bee.openhis.domain.Menu;
import com.bee.openhis.dto.MenuDto;
import com.bee.openhis.service.MenuService;
import com.bee.openhis.utils.ShiroSecurityUtils;
import com.bee.openhis.vo.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 菜单(权限)
 */
@RestController
@RequestMapping("system/menu")
public class MenuController {

    @Autowired
    private MenuService menuService;

    /**
     * 查询所有菜单
     *
     * @param menuDto
     * @return
     */
    @GetMapping("listAllMenus")
    public AjaxResult listAllMenus(MenuDto menuDto) {
        List<Menu> list = menuService.listAllMenus(menuDto);
        return AjaxResult.success(list);
    }

    /**
     * 根据角色 ID 查询已分配菜单 ID (只查子菜单 不查父菜单)
     *
     * @param roleId
     * @return
     */
    @GetMapping("getMenuIdsByRoleId/{roleId}")
    public AjaxResult getMenuIdsByRoleId(@PathVariable Long roleId) {
        List<Long> ids = menuService.getMenuIdsByRoleId(roleId);
        return AjaxResult.success(ids);
    }

    /**
     * 查询下拉菜单树
     *
     * @return
     */
    @GetMapping("selectMenuTree")
    public AjaxResult selectMenuTree() {
        MenuDto menuDto = new MenuDto();
        menuDto.setStatus(Constants.STATUS_TRUE);
        return AjaxResult.success(menuService.listAllMenus(menuDto));
    }

    /**
     * 根据菜单 ID 查询菜单权限
     *
     * @param menuId
     * @return
     */
    @GetMapping("getMenuById/{menuId}")
    public AjaxResult getMenuById(@PathVariable Long menuId) {
        Menu menu = menuService.getOne(menuId);
        return AjaxResult.success(menu);
    }

    /**
     * 添加菜单
     *
     * @param menuDto
     * @return
     */
    @PostMapping("addMenu")
    public AjaxResult addMenu(@Validated MenuDto menuDto) {
        menuDto.setSimpleUser(ShiroSecurityUtils.getCurrentSimpleUser());
        return AjaxResult.toAjax(menuService.addMenu(menuDto));
    }

    /**
     * 修改菜单
     *
     * @param menuDto
     * @return
     */
    @PutMapping("updateMenu")
    public AjaxResult updateMenu(@Validated MenuDto menuDto) {
        menuDto.setSimpleUser(ShiroSecurityUtils.getCurrentSimpleUser());
        return AjaxResult.toAjax(menuService.updateMenu(menuDto));
    }

    /**
     * 删除菜单
     *
     * @param menuId
     * @return
     */
    @DeleteMapping("deleteMenuById/{menuId}")
    public AjaxResult deleteMenuById(@PathVariable Long menuId) {
        if (menuService.hasChildByMenuId(menuId)) {
            return AjaxResult.fail("当前要删除的菜单有子节点,请先删除子节点");
        }
        return AjaxResult.toAjax(menuService.deleteMenuById(menuId));
    }
}
