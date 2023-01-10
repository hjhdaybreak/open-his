package com.bee.openhis.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bee.openhis.constants.Constants;
import com.bee.openhis.domain.Menu;
import com.bee.openhis.mapper.MenuMapper;
import com.bee.openhis.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 19235
 * @description 针对表【sys_menu(菜单权限表)】的数据库操作Service实现
 * @createDate 2023-01-10 17:05:40
 */
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu>
        implements MenuService {

    @Autowired
    private MenuMapper menuMapper;


    @Override
    public List<Menu> selectMenuTree(boolean isAdmin) {
        QueryWrapper<Menu> wrapper = new QueryWrapper<>();
        wrapper.eq(Menu.COL_STATUS, Constants.STATUS_TRUE);//能正常使用
        wrapper.in(Menu.COL_MENU_TYPE, Constants.MENU_TYPE_C, Constants.MENU_TYPE_F, Constants.MENU_TYPE_M);
        wrapper.orderByAsc(Menu.COL_PARENT_ID);
        if (isAdmin) {
            return menuMapper.selectList(wrapper);
        } else {
            return menuMapper.selectList(wrapper);
        }
    }
}




