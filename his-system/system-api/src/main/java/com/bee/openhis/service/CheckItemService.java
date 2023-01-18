package com.bee.openhis.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bee.openhis.domain.CheckItem;
import com.bee.openhis.dto.CheckItemDto;
import com.bee.openhis.vo.DataGridView;

import java.util.List;

/**
* @author 19235
* @description 针对表【sys_check_item(检查费用表)】的数据库操作Service
* @createDate 2023-01-18 10:29:20
*/
public interface CheckItemService extends IService<CheckItem> {

    DataGridView listCheckItemForPage(CheckItemDto checkItemDto);

    int addCheckItem(CheckItemDto checkItemDto);

    int updateCheckItem(CheckItemDto checkItemDto);

    CheckItem getOne(Long checkItemId);

    int deleteCheckItemByIds(Long[] checkItemIds);

    List<CheckItem> selectAllCheckItem();

}
