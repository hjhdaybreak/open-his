package com.bee.openhis.controller.system;

import com.bee.openhis.dto.CheckItemDto;
import com.bee.openhis.service.CheckItemService;
import com.bee.openhis.utils.ShiroSecurityUtils;
import com.bee.openhis.vo.AjaxResult;
import com.bee.openhis.vo.DataGridView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * 9. 检查费用
 */
@RestController
@RequestMapping("system/checkItem")
public class CheckItemController {

    @Autowired
    private CheckItemService checkItemService;

    /**
     * 分页查询检查项目
     *
     * @param checkItemDto
     * @return
     */
    @GetMapping("listCheckItemForPage")
    public AjaxResult listCheckItemForPage(CheckItemDto checkItemDto) {
        DataGridView dataGridView = checkItemService.listCheckItemForPage(checkItemDto);
        return AjaxResult.success("查询成功", dataGridView.getData(), dataGridView.getTotal());
    }

    /**
     * 添加检查项目
     *
     * @param checkItemDto
     * @return
     */
    @PostMapping("addCheckItem")
    public AjaxResult addCheckItem(@Validated CheckItemDto checkItemDto) {
        checkItemDto.setSimpleUser(ShiroSecurityUtils.getCurrentSimpleUser());
        return AjaxResult.toAjax(checkItemService.addCheckItem(checkItemDto));
    }

    /**
     * 修改检查项目
     *
     * @param checkItemDto
     * @return
     */
    @PutMapping("updateCheckItem")
    public AjaxResult updateCheckItem(@Validated CheckItemDto checkItemDto) {
        checkItemDto.setSimpleUser(ShiroSecurityUtils.getCurrentSimpleUser());
        return AjaxResult.toAjax(checkItemService.updateCheckItem(checkItemDto));
    }

    /**
     * 根据 id 查询一个检查项目
     *
     * @param checkItemId
     * @return
     */
    @GetMapping("getCheckItemById/{checkItemId}")
    public AjaxResult getCheckItemById(@PathVariable @Validated
                                       @NotNull(message = "检查项目id不能为空") Long checkItemId) {
        return AjaxResult.success(checkItemService.getOne(checkItemId));
    }


    /**
     * 批量删除检查项目
     * @param checkItemIds
     * @return
     */
    @DeleteMapping("deleteCheckItemByIds/{checkItemIds}")
    public AjaxResult deleteCheckItemByIds(@PathVariable @Validated
                                           @NotEmpty(message = "要删除的id不能为空") Long[] checkItemIds){
        return AjaxResult.toAjax(checkItemService.deleteCheckItemByIds(checkItemIds));
    }

    /**
     * 查询所有可用的检查项目
     * @return
     */
    @GetMapping("selectAllCheckItem")
    public AjaxResult selectAllCheckItem(){
        return AjaxResult.success(checkItemService.selectAllCheckItem());
    }
}
