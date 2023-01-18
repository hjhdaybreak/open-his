package com.bee.openhis.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 检查费用表
 *
 * @TableName sys_check_item
 */
@TableName(value = "sys_check_item")
@Data
public class CheckItem extends BaseEntity implements Serializable {
    /**
     * 项目费用ID
     */
    @TableId(value = "check_item_id", type = IdType.AUTO)
    private Long checkItemId;

    /**
     * 项目名称
     */
    @TableField(value = "check_item_name")
    private String checkItemName;

    /**
     * 关键字【查询用】
     */
    @TableField(value = "keywords")
    private String keywords;

    /**
     * 项目单价
     */
    @TableField(value = "unit_price")
    private BigDecimal unitPrice;

    /**
     * 项目成本
     */
    @TableField(value = "cost")
    private BigDecimal cost;

    /**
     * 单位
     */
    @TableField(value = "unit")
    private String unit;

    /**
     * 项目类别IDs  sys_dict_type
     */
    @TableField(value = "type_id")
    private String typeId;

    /**
     * 状态0正常1停用 sys_dict_type
     */
    @TableField(value = "status")
    private String status;

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

    public static final String COL_CHECK_ITEM_ID = "check_item_id";

    public static final String COL_CHECK_ITEM_NAME = "check_item_name";

    public static final String COL_KEYWORDS = "keywords";

    public static final String COL_UNIT_PRICE = "unit_price";

    public static final String COL_COST = "cost";

    public static final String COL_UNIT = "unit";

    public static final String COL_TYPE_ID = "type_id";

    public static final String COL_STATUS = "status";

    public static final String COL_CREATE_TIME = "create_time";

    public static final String COL_UPDATE_TIME = "update_time";

    public static final String COL_CREATE_BY = "create_by";

    public static final String COL_UPDATE_BY = "update_by";

}