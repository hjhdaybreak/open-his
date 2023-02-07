package com.bee.openhis.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 患者信息表
 * @TableName his_patient
 */
@TableName(value ="his_patient")
@Data
public class Patient extends BaseEntity implements Serializable {
    /**
     * 主键
     */
    @TableId(value = "patient_id")
    private String patientId;

    /**
     * 患者姓名
     */
    @TableField(value = "name")
    private String name;

    /**
     * 患者电话
     */
    @TableField(value = "phone")
    private String phone;

    /**
     * 患者性别0男1女 2未知字典表 sys_user_sex
     */
    @TableField(value = "sex")
    private String sex;

    /**
     * 出生年月
     */
    @TableField(value = "birthday")
    private String birthday;

    /**
     * 身份证号[认证ID]
     */
    @TableField(value = "id_card")
    private String idCard;

    /**
     * 地址信息
     */
    @TableField(value = "address")
    private String address;

    /**
     * 过敏信息
     */
    @TableField(value = "allergy_info")
    private String allergyInfo;

    /**
     * 是否完善信息，0未完善1已完善 字典表 his_patient_msg_final
     */
    @TableField(value = "is_final")
    private String isFinal;

    /**
     * 登录密码
     */
    @TableField(value = "password")
    private String password;

    /**
     * 微信openid
     */
    @TableField(value = "openid")
    private String openid;

    /**
     * 最后登录ip
     */
    @TableField(value = "last_login_ip")
    private String lastLoginIp;

    /**
     * 最后登录时间
     */
    @TableField(value = "last_login_time")
    private Date lastLoginTime;

    /**
     * 创建时间
     */
    @TableField(value = "create_time")
    private Date createTime;

    /**
     * 更新时间
     */
    @TableField(value = "update_time")
    private Date updateTime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    public static final String COL_PATIENT_ID = "patient_id";

    public static final String COL_NAME = "name";

    public static final String COL_PHONE = "phone";

    public static final String COL_SEX = "sex";

    public static final String COL_BIRTHDAY = "birthday";

    public static final String COL_ID_CARD = "id_card";

    public static final String COL_ADDRESS = "address";

    public static final String COL_ALLERGY_INFO = "allergy_info";

    public static final String COL_IS_FINAL = "is_final";

    public static final String COL_PASSWORD = "password";

    public static final String COL_OPENID = "openid";

    public static final String COL_LAST_LOGIN_IP = "last_login_ip";

    public static final String COL_LAST_LOGIN_TIME = "last_login_time";

    public static final String COL_CREATE_TIME = "create_time";

    public static final String COL_UPDATE_TIME = "update_time";
}