package com.bee.openhis.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bee.openhis.domain.RegisteredItem;
import com.bee.openhis.dto.RegisteredItemDto;
import com.bee.openhis.vo.DataGridView;

import java.util.List;

/**
 * @author 19235
 * @description 针对表【sys_registered_item】的数据库操作Service
 * @createDate 2023-01-18 10:57:16
 */
public interface RegisteredItemService extends IService<RegisteredItem> {

    DataGridView listRegisteredItemForPage(RegisteredItemDto registeredItemDto);

    int addRegisteredItem(RegisteredItemDto registeredItemDto);

    int updateRegisteredItem(RegisteredItemDto registeredItemDto);

    RegisteredItem getOne(Long registeredItemId);

    int deleteRegisteredItemByIds(Long[] registeredItemIds);

    List<RegisteredItem> selectAllRegisteredItem();


}
