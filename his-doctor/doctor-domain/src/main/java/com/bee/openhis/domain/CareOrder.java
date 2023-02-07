package com.bee.openhis.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import lombok.Data;

/**
 * 药用处方表
 * @TableName his_care_order
 */
@TableName(value ="his_care_order")
@Data
public class CareOrder extends BaseEntity implements Serializable {
    /**
     * 处方ID
     */
    @TableId(value = "co_id")
    private String coId;

    /**
     * 处方类型0药用处方1检查处方
     */
    @TableField(value = "co_type")
    private String coType;

    /**
     * 医生id
     */
    @TableField(value = "user_id")
    private Long userId;

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
     * 关联病历id
     */
    @TableField(value = "ch_id")
    private String chId;

    /**
     * 处方全额
     */
    @TableField(value = "all_amount")
    private BigDecimal allAmount;

    /**
     * 创建者
     */
    @TableField(value = "create_by")
    private String createBy;

    /**
     * 创建时间
     */
    @TableField(value = "create_time")
    private Date createTime;

    /**
     * 更新者
     */
    @TableField(value = "update_by")
    private String updateBy;

    /**
     * 更新时间
     */
    @TableField(value = "update_time")
    private Date updateTime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    public static final String COL_CO_ID = "co_id";

    public static final String COL_CO_TYPE = "co_type";

    public static final String COL_USER_ID = "user_id";

    public static final String COL_PATIENT_ID = "patient_id";

    public static final String COL_PATIENT_NAME = "patient_name";

    public static final String COL_CH_ID = "ch_id";

    public static final String COL_ALL_AMOUNT = "all_amount";

    public static final String COL_CREATE_BY = "create_by";

    public static final String COL_CREATE_TIME = "create_time";

    public static final String COL_UPDATE_BY = "update_by";

    public static final String COL_UPDATE_TIME = "update_time";
}