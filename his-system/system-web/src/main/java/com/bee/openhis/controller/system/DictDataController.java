package com.bee.openhis.controller.system;


import com.bee.openhis.dto.DictDataDto;
import com.bee.openhis.service.DictDataService;
import com.bee.openhis.utils.ShiroSecurityUtils;
import com.bee.openhis.vo.AjaxResult;
import com.bee.openhis.vo.DataGridView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("system/dict/data")
public class DictDataController {

    @Autowired
    private DictDataService dataService;

    /**
     * 查询字典数据列表
     *
     * @param dictDataDto
     * @return
     */
    @GetMapping("listForPage")
    public AjaxResult listForPage(DictDataDto dictDataDto) {
        DataGridView dataGridView = dataService.listForPage(dictDataDto);
        return AjaxResult.success("查询成功", dataGridView.getData(), dataGridView.getTotal());
    }

    @GetMapping("getOne/{dictId}")
    public AjaxResult getDictData(@PathVariable @Validated @NotNull @NotNull(message = "字典数据ID不能为空") Long dictId) {
        return AjaxResult.success(dataService.selectDictDataById(dictId));
    }

    @PostMapping("addDictData")
    public AjaxResult addDictData(@Validated DictDataDto dictDataDto) {
        dictDataDto.setSimpleUser(ShiroSecurityUtils.getCurrentSimpleUser());
        return AjaxResult.toAjax(dataService.insert(dictDataDto));
    }


    @PutMapping("updateDictData")
    public AjaxResult updateDictData(@Validated DictDataDto dictDataDto) {
        dictDataDto.setSimpleUser(ShiroSecurityUtils.getCurrentSimpleUser());
        return AjaxResult.toAjax(dataService.update(dictDataDto));
    }

    @DeleteMapping("deleteDictDataByIds/{dictIds}")
    public AjaxResult deleteDictData(@PathVariable @Validated
                                     @NotEmpty(message = "要删除的字典id不能为空") Long[] dictIds) {
        return AjaxResult.toAjax(dataService.deleteDictData(dictIds));
    }
}
