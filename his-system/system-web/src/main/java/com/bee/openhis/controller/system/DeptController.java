package com.bee.openhis.controller.system;


import com.bee.openhis.dto.DeptDto;
import com.bee.openhis.service.DeptService;
import com.bee.openhis.utils.ShiroSecurityUtils;
import com.bee.openhis.vo.AjaxResult;
import com.bee.openhis.vo.DataGridView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotEmpty;


@RestController
@RequestMapping("system/dept")
public class DeptController {

    @Autowired
    private DeptService deptService;


    /**
     * 分页查询科室
     *
     * @param deptDto
     * @return
     */
    @GetMapping("listDeptForPage")
    public AjaxResult listDeptForPage(DeptDto deptDto) {
        DataGridView dataGridView = deptService.listDeptForPage(deptDto);
        return AjaxResult.success("查询成功", dataGridView.getData(), dataGridView.getTotal());
    }

    /**
     * 添加科室
     *
     * @param deptDto
     * @return
     */
    @PostMapping("addDept")
    public AjaxResult addDept(@Validated DeptDto deptDto) {
        deptDto.setSimpleUser(ShiroSecurityUtils.getCurrentSimpleUser());
        return AjaxResult.toAjax(deptService.addDept(deptDto));
    }

    /**
     * 修改科室
     *
     * @param deptDto
     * @return
     */
    @PutMapping("updateDept")
    public AjaxResult updateDept(@Validated DeptDto deptDto) {
        deptDto.setSimpleUser(ShiroSecurityUtils.getCurrentSimpleUser());
        return AjaxResult.toAjax(deptService.updateDept(deptDto));
    }

    /**
     * 批量删除部门
     *
     * @param deptIds
     * @return
     */
    @DeleteMapping("deleteDeptByIds/{deptIds}")
    public AjaxResult deleteDeptByIds(@PathVariable @Validated
                                      @NotEmpty(message = "要删除的id不能为空") Long[] deptIds) {
        return AjaxResult.toAjax(deptService.deleteDeptByIds(deptIds));
    }
}