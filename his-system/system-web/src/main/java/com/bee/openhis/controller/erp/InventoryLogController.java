package com.bee.openhis.controller.erp;


import com.bee.openhis.dto.InventoryLogDto;
import com.bee.openhis.service.InventoryLogService;
import com.bee.openhis.vo.AjaxResult;
import com.bee.openhis.vo.DataGridView;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("erp/inventoryLog")
public class InventoryLogController {

    @Reference
    private InventoryLogService inventoryLogService;

    /**
     * 分页查询批次库存及价格
     * @param inventoryLogDto
     * @return
     */
    @GetMapping("listInventoryLogForPage")
    public AjaxResult listInventoriesForPage(InventoryLogDto inventoryLogDto){
        DataGridView dataGridView = inventoryLogService.listInventoryLogForPage(inventoryLogDto);
        return AjaxResult.success("查询成功",dataGridView.getData(),dataGridView.getTotal());
    }
}
