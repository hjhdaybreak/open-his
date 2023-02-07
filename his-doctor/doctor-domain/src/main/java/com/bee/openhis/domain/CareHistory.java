package com.bee.openhis.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 病例表
 * @TableName his_care_history
 */
@TableName(value ="his_care_history")
@Data
public class CareHistory extends BaseEntity implements Serializable {
    /**
     * 病历ID
     */
    @TableId(value = "ch_id")
    private String chId;

    /**
     * 医生id
     */
    @TableField(value = "user_id")
    private Long userId;

    /**
     * 医生姓名
     */
    @TableField(value = "user_name")
    private String userName;

    /**
     * 患者id
     */
    @TableField(value = "patient_id")
    private String patientId;

    /**
     * 患者姓名
     */
    @TableField(value = "patient_name")
    private String patientName;

    /**
     * 科室id
     */
    @TableField(value = "dept_id")
    private Long deptId;

    /**
     * 科室名称
     */
    @TableField(value = "dept_name")
    private String deptName;

    /**
     * 
     */
    @TableField(value = "receive_type")
    private String receiveType;

    /**
     * 是否传染，0否，1是 字典表属性his_contagious_status
     */
    @TableField(value = "is_contagious")
    private String isContagious;

    /**
     * 就诊时间
     */
    @TableField(value = "care_time")
    private Date careTime;

    /**
     * 发病日期
     */
    @TableField(value = "case_date")
    private String caseDate;

    /**
     * 挂号单号
     */
    @TableField(value = "reg_id")
    private String regId;

    /**
     * 主诉
     */
    @TableField(value = "case_title")
    private String caseTitle;

    /**
     * 诊断信息
     */
    @TableField(value = "case_result")
    private String caseResult;

    /**
     * 医生建议
     */
    @TableField(value = "doctor_tips")
    private String doctorTips;

    /**
     * 备注
     */
    @TableField(value = "remark")
    private String remark;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    public static final String COL_CH_ID = "ch_id";

    public static final String COL_USER_ID = "user_id";

    public static final String COL_USER_NAME = "user_name";

    public static final String COL_PATIENT_ID = "patient_id";

    public static final String COL_PATIENT_NAME = "patient_name";

    public static final String COL_DEPT_ID = "dept_id";

    public static final String COL_DEPT_NAME = "dept_name";

    public static final String COL_RECEIVE_TYPE = "receive_type";

    public static final String COL_IS_CONTAGIOUS = "is_contagious";

    public static final String COL_CARE_DATE = "care_date";

    public static final String COL_CASE_DATE = "case_date";

    public static final String COL_REG_ID = "reg_id";

    public static final String COL_CASE_TITLE = "case_title";

    public static final String COL_CASE_RESULT = "case_result";

    public static final String COL_DOCTOR_TIPS = "doctor_tips";

    public static final String COL_REMARK = "remark";
}