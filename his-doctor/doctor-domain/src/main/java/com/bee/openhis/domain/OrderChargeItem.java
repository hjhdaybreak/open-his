package com.bee.openhis.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.math.BigDecimal;
import lombok.Data;

/**
 * 支付订单详情表
 * @TableName his_order_charge_item
 */
@TableName(value ="his_order_charge_item")
@Data
public class OrderChargeItem extends BaseEntity implements Serializable {
    /**
     * 详情ID和his_care_order_*表里面的ID一样
     */
    @TableId(value = "item_id")
    private String itemId;

    /**
     * 处方ID【备用】
     */
    @TableField(value = "co_id")
    private String coId;

    /**
     * 项目名称
     */
    @TableField(value = "item_name")
    private String itemName;

    /**
     * 项目价格
     */
    @TableField(value = "item_price")
    private BigDecimal itemPrice;

    /**
     * 项目数量
     */
    @TableField(value = "item_num")
    private Integer itemNum;

    /**
     * 小计
     */
    @TableField(value = "item_amount")
    private Integer itemAmount;

    /**
     * 订单IDhis_oder_charge主键
     */
    @TableField(value = "order_id")
    private String orderId;

    /**
     * 项目类型0药品  还是1检查项
     */
    @TableField(value = "item_type")
    private String itemType;

    /**
     * 状态，0未支付，1已支付，2，已退费  3，已完成 字典表 his_order_details_status
     */
    @TableField(value = "status")
    private String status;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}