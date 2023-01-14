package com.bee.openhis.utils;

import com.bee.openhis.config.vo.ActiverUser;

import com.bee.openhis.domain.SimpleUser;
import com.bee.openhis.domain.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

public class ShiroSecurityUtils {

    /**
     * 获取当前登录用户 返回ActiverUser
     *
     * @return
     */
    public static ActiverUser getCurrentActiveUser() {
        Subject subject = SecurityUtils.getSubject();
        ActiverUser activerUser = (ActiverUser) subject.getPrincipal();
        return activerUser;
    }

    /**
     * 获取当前登录用户 返回SimpleUser
     *
     * @return
     */
    public static SimpleUser getCurrentSimpleUser() {
        Subject subject = SecurityUtils.getSubject();
        ActiverUser principal = (ActiverUser) subject.getPrincipal();
        User user = principal.getUser();
        return new SimpleUser(Long.toString(user.getUserId()), user.getUserName());
    }
}
