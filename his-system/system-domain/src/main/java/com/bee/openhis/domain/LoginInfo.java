package com.bee.openhis.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 系统访问记录
 * @TableName sys_login_info
 */
@TableName(value ="sys_login_info")
@Data
public class LoginInfo extends BaseEntity implements Serializable {
    /**
     * 访问ID
     */
    @TableId(value = "info_id", type = IdType.AUTO)
    private Long infoId;

    /**
     * 用户名称
     */
    @TableField(value = "user_name")
    private String userName;

    /**
     * 登陆账号
     */
    @TableField(value = "login_account")
    private String loginAccount;

    /**
     * 登录IP地址
     */
    @TableField(value = "ip_addr")
    private String ipAddr;

    /**
     * 登录地点
     */
    @TableField(value = "login_location")
    private String loginLocation;

    /**
     * 浏览器类型
     */
    @TableField(value = "browser")
    private String browser;

    /**
     * 操作系统
     */
    @TableField(value = "os")
    private String os;

    /**
     * 登录状态（0成功 1失败）字典表
     */
    @TableField(value = "login_status")
    private String loginStatus;

    /**
     * 登陆类型0系统用户1患者用户 字典表
     */
    @TableField(value = "login_type")
    private String loginType;

    /**
     * 提示消息
     */
    @TableField(value = "msg")
    private String msg;

    /**
     * 访问时间
     */
    @TableField(value = "login_time")
    private Date loginTime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}