package com.bee.openhis.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bee.openhis.domain.Menu;

import java.util.List;

/**
 * @author 19235
 * @description 针对表【sys_menu(菜单权限表)】的数据库操作Service
 * @createDate 2023-01-10 17:05:40
 */
public interface MenuService extends IService<Menu> {
    List<Menu> selectMenuTree(boolean isAdmin);
}
