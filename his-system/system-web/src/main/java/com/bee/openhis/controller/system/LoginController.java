package com.bee.openhis.controller.system;

import com.bee.openhis.config.vo.ActiverUser;
import com.bee.openhis.constants.Constants;
import com.bee.openhis.constants.HttpStatus;
import com.bee.openhis.domain.Menu;
import com.bee.openhis.dto.LoginBodyDto;
import com.bee.openhis.service.MenuService;
import com.bee.openhis.vo.AjaxResult;
import com.bee.openhis.vo.MenuTreeVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@RestController
@Slf4j
public class LoginController {

    @Autowired
    private MenuService menuService;




    @PostMapping("login/doLogin")
    public AjaxResult login(@RequestBody @Validated LoginBodyDto loginBodyDto, HttpServletRequest httpServletRequest) {
        AjaxResult ajaxResult = AjaxResult.success();
        String username = loginBodyDto.getUsername();
        String password = loginBodyDto.getPassword();

        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        Subject currentUser = SecurityUtils.getSubject();

        try {
            currentUser.login(token);
            //封装loginInfo对象 todo
            Serializable id = currentUser.getSession().getId();

        } catch (AuthenticationException e) {
            log.error("username or secrete error");
            ajaxResult = AjaxResult.error(HttpStatus.ERROR, "username or secrete error");
        }
        return ajaxResult;
    }

    @GetMapping("login/getInfo")
    public AjaxResult getIndo() {
        Subject subject = SecurityUtils.getSubject();
        ActiverUser activerUser = (ActiverUser) subject.getPrincipal();
        if (activerUser == null) {
            return AjaxResult.fail("plz login first!");
        }
        AjaxResult ajaxResult = AjaxResult.success();
        ajaxResult.put("username", activerUser.getUser().getUserName());
        ajaxResult.put("picture", activerUser.getUser().getPicture());
        ajaxResult.put("roles", activerUser.getRoles());
        ajaxResult.put("permissions", activerUser.getPermissions());
        return ajaxResult;
    }

    @GetMapping("login/getMenus")
    public AjaxResult getMenus() {
        Subject subject = SecurityUtils.getSubject();
        ActiverUser activerUser = (ActiverUser) subject.getPrincipal();
        if (activerUser == null) {
            return AjaxResult.fail("plz login first!");
        }
        boolean isAdmin = activerUser.getUser().getUserType().equals(Constants.USER_ADMIN);
        List<Menu> menus = menuService.selectMenuTree(isAdmin);
        ArrayList<MenuTreeVo> menuVos = new ArrayList<>();
        for (Menu menu : menus) {
            menuVos.add(new MenuTreeVo(menu.getMenuId().toString(), menu.getPath(), true));
        }
        return AjaxResult.success(menuVos);
    }

    @PostMapping("login/logout")
    public AjaxResult logout() {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return AjaxResult.success("user logout");
    }

}
