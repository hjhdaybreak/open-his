package com.bee.openhis.controller.system;


import com.bee.openhis.dto.DictDataDto;
import com.bee.openhis.service.DictDataService;
import com.bee.openhis.vo.AjaxResult;
import com.bee.openhis.vo.DataGridView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("system/dict/data")
public class DictDataController {

    @Autowired
    private DictDataService dictDataService;

    /**
     * 查询字典数据列表
     *
     * @param dictDataDto
     * @return
     */
    @GetMapping("listForPage")
    public AjaxResult listForPage(DictDataDto dictDataDto) {
        DataGridView dataGridView = dictDataService.listForPage(dictDataDto);
        return AjaxResult.success("查询成功", dataGridView.getData(), dataGridView.getTotal());
    }
}
