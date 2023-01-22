package com.bee.openhis.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 
 * @TableName stock_purchase_item
 */
@TableName(value ="stock_purchase_item")
@Data
public class PurchaseItem extends BaseEntity implements Serializable {
    /**
     * 详情ID
     */
    @TableId(value = "item_id")
    private String itemId;

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
     * 采购数量
     */
    @TableField(value = "purchase_number")
    private Integer purchaseNumber;

    /**
     * 批发价
     */
    @TableField(value = "trade_price")
    private BigDecimal tradePrice;

    /**
     * 批发额
     */
    @TableField(value = "trade_total_amount")
    private BigDecimal tradeTotalAmount;

    /**
     * 药品生产批次号
     */
    @TableField(value = "batch_number")
    private String batchNumber;

    /**
     * 备注
     */
    @TableField(value = "remark")
    private String remark;

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
     * 关键字
     */
    @TableField(value = "keywords")
    private String keywords;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}