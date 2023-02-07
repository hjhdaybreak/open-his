package com.bee.openhis.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 退费主表
 * @TableName his_order_backfee
 */
@TableName(value ="his_order_backfee")
@Data
public class OrderBackfee extends BaseEntity implements Serializable {
    /**
     * 退费ID
     */
    @TableId(value = "back_id")
    private String backId;

    /**
     * 总费用
     */
    @TableField(value = "back_amount")
    private Long backAmount;

    /**
     * 病历ID
     */
    @TableField(value = "ch_id")
    private String chId;

    /**
     * 挂号单
     */
    @TableField(value = "reg_id")
    private String regId;

    /**
     * 患者名称
     */
    @TableField(value = "patient_name")
    private String patientName;

    /**
     * 订单状态0未退费  1 退费成功 2退费失败  字典表his_backfee_status
     */
    @TableField(value = "back_status")
    private String backStatus;

    /**
     * 退费类型0现金1支付宝  字典表his_pay_type_status
     */
    @TableField(value = "back_type")
    private String backType;

    /**
     * 关联订单ID his_order_charge  
     */
    @TableField(value = "order_id")
    private String orderId;

    /**
     * 退费交易ID
     */
    @TableField(value = "back_platform_id")
    private String backPlatformId;

    /**
     * 退费交易时间
     */
    @TableField(value = "back_time")
    private Date backTime;

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

    /**
     * 创建者
     */
    @TableField(value = "create_by")
    private String createBy;

    /**
     * 更新者
     */
    @TableField(value = "update_by")
    private String updateBy;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}