package com.bee.openhis.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bee.openhis.domain.Menu;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author 19235
 * @description 针对表【sys_menu(菜单权限表)】的数据库操作Mapper
 * @createDate 2023-01-10 17:05:40
 * @Entity com.bee.openhis.domain.Menu
 */
public interface MenuMapper extends BaseMapper<Menu> {

    List<Long> queryMenuIdsByRoleId(@Param("roleId") Long roleId);

    Long queryChildCountByMenuId(@Param("menuId") Long menuId);
}




