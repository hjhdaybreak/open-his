package com.bee.openhis.controller.system;

import cn.hutool.core.date.DateUtil;
import com.bee.openhis.aspectj.Log;
import com.bee.openhis.aspectj.enums.BusinessType;
import com.bee.openhis.config.vo.ActiverUser;
import com.bee.openhis.constants.Constants;
import com.bee.openhis.constants.HttpStatus;
import com.bee.openhis.domain.LoginInfo;
import com.bee.openhis.domain.Menu;
import com.bee.openhis.dto.LoginBodyDto;
import com.bee.openhis.service.LoginInfoService;
import com.bee.openhis.service.MenuService;
import com.bee.openhis.utils.AddressUtils;
import com.bee.openhis.utils.IpUtils;
import com.bee.openhis.utils.ShiroSecurityUtils;
import com.bee.openhis.vo.AjaxResult;
import com.bee.openhis.vo.MenuTreeVo;
import eu.bitwalker.useragentutils.UserAgent;
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

    @Autowired
    private LoginInfoService loginInfoService;

    @PostMapping("login/doLogin")
    @Log(title = "用户登录", businessType = BusinessType.INSERT)
    public AjaxResult login(@RequestBody @Validated LoginBodyDto loginBodyDto, HttpServletRequest httpServletRequest) {
        AjaxResult ajaxResult = AjaxResult.success();
        String username = loginBodyDto.getUsername();
        String password = loginBodyDto.getPassword();

        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        Subject currentUser = SecurityUtils.getSubject();

        LoginInfo loginInfo = createLoginInfo(httpServletRequest);
        loginInfo.setLoginAccount(loginBodyDto.getUsername());

        try {

            currentUser.login(token);
            Serializable webToken = currentUser.getSession().getId();
            ajaxResult.put(Constants.TOKEN, webToken);
            loginInfo.setMsg("登录成功");
            loginInfo.setLoginStatus(Constants.LOGIN_SUCCESS);
            loginInfo.setUserName(ShiroSecurityUtils.getCurrentSimpleUser().getUserName());

        } catch (AuthenticationException e) {
            log.error("用户名或密码不正确");
            ajaxResult = AjaxResult.error(HttpStatus.ERROR, "用户名或密码不正确");
            loginInfo.setMsg("用户名或密码不正确");
            loginInfo.setLoginStatus(Constants.LOGIN_ERROR);
        }
        //保存登录信息到数据库
        loginInfoService.insertLoginInfo(loginInfo);

        return ajaxResult;
    }

    private LoginInfo createLoginInfo(HttpServletRequest httpServletRequest) {
        LoginInfo loginInfo = new LoginInfo();
        UserAgent userAgent = UserAgent.parseUserAgentString(httpServletRequest.getHeader("User-Agent"));
        //获取IP地址
        String ipAddr = IpUtils.getIpAddr(httpServletRequest);
        //获取操作系统
        String osName = userAgent.getOperatingSystem().getName();
        //获取浏览器类型
        String browser = userAgent.getBrowser().getName();
        //获取登录地址
        String location = AddressUtils.getRealAddressByIP(ipAddr);

        loginInfo.setIpAddr(ipAddr);
        loginInfo.setLoginLocation(location);
        loginInfo.setOs(osName);
        loginInfo.setBrowser(browser);
        loginInfo.setLoginTime(DateUtil.date());
        loginInfo.setLoginType(Constants.LOGIN_TYPE_SYSTEM);
        return loginInfo;
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
