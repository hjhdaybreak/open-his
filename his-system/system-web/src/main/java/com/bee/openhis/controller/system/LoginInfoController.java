package com.bee.openhis.controller.system;

import com.bee.openhis.dto.LoginInfoDto;
import com.bee.openhis.service.LoginInfoService;
import com.bee.openhis.vo.AjaxResult;
import com.bee.openhis.vo.DataGridView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("system/loginInfo")
public class LoginInfoController {

    @Autowired
    private LoginInfoService loginInfoService;

    @GetMapping("listForPage")
    public AjaxResult listForPage(LoginInfoDto loginInfoDto) {
        DataGridView dataGridView = loginInfoService.listForPage(loginInfoDto);
        return AjaxResult.success("查询成功", dataGridView.getData(), dataGridView.getTotal());
    }

    @DeleteMapping("deleteLoginInfoByIds/{infoIds}")
    public AjaxResult deleteLoginInfoByIds(@PathVariable Long[] infoIds) {
        return AjaxResult.toAjax(loginInfoService.deleteLoginInfoByIds(infoIds));
    }

    @DeleteMapping("clearLoginInfo")
    public AjaxResult clearLoginInfo() {
        return AjaxResult.toAjax(loginInfoService.clearLoginInfo());
    }
}
