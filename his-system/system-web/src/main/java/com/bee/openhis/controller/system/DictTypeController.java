package com.bee.openhis.controller.system;

import com.bee.openhis.dto.DictTypeDto;
import com.bee.openhis.service.DictTypeService;
import com.bee.openhis.vo.AjaxResult;
import com.bee.openhis.vo.DataGridView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("system/dict/type")
public class DictTypeController {

    @Autowired
    private DictTypeService dictTypeService;

    /**
     * 分页查询字典类型
     *
     * @param dictTypeDto
     * @return
     */
    @GetMapping("listForPage")
    private AjaxResult listForPage(DictTypeDto dictTypeDto) {
        DataGridView dataGridView = dictTypeService.listForPage(dictTypeDto);
        return AjaxResult.success("查询成功", dataGridView.getData(), dataGridView.getTotal());
    }

}
