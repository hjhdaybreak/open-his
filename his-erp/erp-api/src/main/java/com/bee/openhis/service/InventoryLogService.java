package com.bee.openhis.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bee.openhis.domain.InventoryLog;
import com.bee.openhis.dto.InventoryLogDto;
import com.bee.openhis.vo.DataGridView;

/**
* @author 19235
* @description 针对表【stock_inventory_log】的数据库操作Service
* @createDate 2023-01-18 16:31:53
*/
public interface InventoryLogService extends IService<InventoryLog> {

    DataGridView listInventoryLogForPage(InventoryLogDto inventoryLogDto);
}
