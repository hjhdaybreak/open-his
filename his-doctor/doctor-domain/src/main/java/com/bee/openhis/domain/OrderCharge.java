package com.bee.openhis.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 收费表
 * @TableName his_order_charge
 */
@TableName(value ="his_order_charge")
@Data
public class OrderCharge extends BaseEntity implements Serializable {
    /**
     * 收费ID
     */
    @TableId(value = "order_id")
    private String orderId;

    /**
     * 总费用
     */
    @TableField(value = "order_amount")
    private Long orderAmount;

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
     * 订单状态0未支付  1 支付成功  2支付超时 3支付失败 字典表his_order_charge_status
     */
    @TableField(value = "order_status")
    private String orderStatus;

    /**
     * 支付ID
     */
    @TableField(value = "pay_platform_id")
    private String payPlatformId;

    /**
     * 支付时间
     */
    @TableField(value = "pay_time")
    private Date payTime;

    /**
     * 支付类型0现金1支付宝  字典表his_pay_type_status
     */
    @TableField(value = "pay_type")
    private String payType;

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