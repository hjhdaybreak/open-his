package com.bee.openhis.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bee.openhis.domain.InventoryLog;
import com.bee.openhis.dto.InventoryLogDto;
import com.bee.openhis.mapper.InventoryLogMapper;
import com.bee.openhis.service.InventoryLogService;
import com.bee.openhis.vo.DataGridView;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.apache.dubbo.config.annotation.Service;

/**
* @author 19235
* @description 针对表【stock_inventory_log】的数据库操作Service实现
* @createDate 2023-01-18 16:31:53
*/
@Service
public class InventoryLogServiceImpl extends ServiceImpl<InventoryLogMapper, InventoryLog>
    implements InventoryLogService{

    @Autowired
    private InventoryLogMapper inventoryLogMapper;

    @Override
    public DataGridView listInventoryLogForPage(InventoryLogDto inventoryLogDto) {
        Page<InventoryLog> page = new Page<>(inventoryLogDto.getPageNum(), inventoryLogDto.getPageSize());
        QueryWrapper<InventoryLog> wrapper = new QueryWrapper<>();
        wrapper.like(StringUtils.isNotBlank(inventoryLogDto.getPurchaseId()),InventoryLog.COL_PURCHASE_ID,
                inventoryLogDto.getPurchaseId());
        wrapper.like(StringUtils.isNotBlank(inventoryLogDto.getMedicinesName()),InventoryLog.COL_MEDICINES_NAME,
                inventoryLogDto.getMedicinesName());
        wrapper.eq(StringUtils.isNotBlank(inventoryLogDto.getMedicinesType()),InventoryLog.COL_MEDICINES_TYPE,
                inventoryLogDto.getMedicinesType());
        wrapper.eq(StringUtils.isNotBlank(inventoryLogDto.getProducterId()),InventoryLog.COL_PRODUCTER_ID,
                inventoryLogDto.getProducterId());
        wrapper.eq(StringUtils.isNotBlank(inventoryLogDto.getPrescriptionType()),InventoryLog.COL_PRESCRIPTION_TYPE,
                inventoryLogDto.getPrescriptionType());
        wrapper.ge(inventoryLogDto.getBeginTime() != null,InventoryLog.COL_CREATE_TIME,
                inventoryLogDto.getBeginTime());
        wrapper.le(inventoryLogDto.getEndTime() != null,InventoryLog.COL_CREATE_TIME,
                inventoryLogDto.getEndTime());
        wrapper.orderByDesc(InventoryLog.COL_CREATE_TIME);
        inventoryLogMapper.selectPage(page,wrapper);
        return new DataGridView(page.getTotal(),page.getRecords());
    }
}




