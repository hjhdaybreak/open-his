package com.bee.openhis.mapper;

import com.bee.openhis.domain.Menu;

/**
* @author 19235
* @description 针对表【sys_menu(菜单权限表)】的数据库操作Mapper
* @createDate 2023-01-10 22:33:37
* @Entity com.bee.openhis.domain.Menu
*/
public interface MenuMapper {

    int deleteByPrimaryKey(Long id);

    int insert(Menu record);

    int insertSelective(Menu record);

    Menu selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Menu record);

    int updateByPrimaryKey(Menu record);

}
