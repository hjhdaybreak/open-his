package com.bee.openhis.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 
 * @TableName stock_purchase
 */
@TableName(value ="stock_purchase")
@Data
public class Purchase extends BaseEntity implements Serializable {
    /**
     * 制单号ID 全局ID雪花算法
     */
    @TableId(value = "purchase_id")
    private String purchaseId;

    /**
     * 供应商ID
     */
    @TableField(value = "provider_id")
    private String providerId;

    /**
     * 采购批发总额
     */
    @TableField(value = "purchase_trade_total_amount")
    private BigDecimal purchaseTradeTotalAmount;

    /**
     * 单据状态； 1未提交2待审核 3审核通过 4审核失败 5作废 6入库成功 字典表 his_order_status
     */
    @TableField(value = "status")
    private String status;

    /**
     * 申请人ID
     */
    @TableField(value = "apply_user_id")
    private Long applyUserId;

    /**
     * 申请人名称
     */
    @TableField(value = "apply_user_name")
    private String applyUserName;

    /**
     * 入库操作人
     */
    @TableField(value = "storage_opt_user")
    private String storageOptUser;

    /**
     * 入库操作时间
     */
    @TableField(value = "storage_opt_time")
    private Date storageOptTime;

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

    /**
     * 审核信息
     */
    @TableField(value = "examine")
    private String examine;

    /**
     * 审核信息
     */
    @TableField(value = "audit_msg")
    private String auditMsg;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}