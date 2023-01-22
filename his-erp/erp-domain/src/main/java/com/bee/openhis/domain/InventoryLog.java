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
 * @TableName stock_inventory_log
 */
@TableName(value ="stock_inventory_log")
@Data
public class InventoryLog extends BaseEntity implements Serializable {
    /**
     * 入库ID
     */
    @TableId(value = "inventory_log_id")
    private String inventoryLogId;

    /**
     * 采购单据ID
     */
    @TableField(value = "purchase_id")
    private String purchaseId;

    /**
     * 药品ID
     */
    @TableField(value = "medicines_id")
    private String medicinesId;

    /**
     * 入库数量
     */
    @TableField(value = "inventory_log_num")
    private Integer inventoryLogNum;

    /**
     * 批发价
     */
    @TableField(value = "trade_price")
    private BigDecimal tradePrice;

    /**
     * 处方价
     */
    @TableField(value = "prescription_price")
    private BigDecimal prescriptionPrice;

    /**
     * 批发额
     */
    @TableField(value = "trade_total_amount")
    private BigDecimal tradeTotalAmount;

    /**
     * 处方额
     */
    @TableField(value = "prescription_total_amount")
    private BigDecimal prescriptionTotalAmount;

    /**
     * 药品生产批次号
     */
    @TableField(value = "batch_number")
    private String batchNumber;

    /**
     * 药品名称
     */
    @TableField(value = "medicines_name")
    private String medicinesName;

    /**
     * 药品分类 sys_dict_data表his_medicines_type
     */
    @TableField(value = "medicines_type")
    private String medicinesType;

    /**
     * 处方类型 sys_dict_data表his_prescription_type
     */
    @TableField(value = "prescription_type")
    private String prescriptionType;

    /**
     * 生产厂家ID
     */
    @TableField(value = "producter_id")
    private String producterId;

    /**
     * 换算量
     */
    @TableField(value = "conversion")
    private Integer conversion;

    /**
     * 单位（g/条）
     */
    @TableField(value = "unit")
    private String unit;

    /**
     * 供应商ID
     */
    @TableField(value = "provider_id")
    private String providerId;

    /**
     * 创建时间
     */
    @TableField(value = "create_time")
    private Date createTime;

    /**
     * 创建者
     */
    @TableField(value = "create_by")
    private String createBy;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}