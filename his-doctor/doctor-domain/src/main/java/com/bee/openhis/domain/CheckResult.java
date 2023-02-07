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
 * 
 * @TableName his_check_result
 */
@TableName(value ="his_check_result")
@Data
public class CheckResult extends BaseEntity implements Serializable {
    /**
     * 处方检查项ID
     */
    @TableId(value = "coc_id")
    private String cocId;

    /**
     * 检查项目ID
     */
    @TableField(value = "check_item_id")
    private Integer checkItemId;

    /**
     * 检查项目名称
     */
    @TableField(value = "check_item_name")
    private String checkItemName;

    /**
     * 价格
     */
    @TableField(value = "price")
    private BigDecimal price;

    /**
     * 挂号id
     */
    @TableField(value = "reg_id")
    private String regId;

    /**
     * 检查结果描述
     */
    @TableField(value = "result_msg")
    private String resultMsg;

    /**
     * 检查结果扫描附件[json表示]
     */
    @TableField(value = "result_img")
    private Object resultImg;

    /**
     * 患者ID
     */
    @TableField(value = "patient_id")
    private String patientId;

    /**
     * 患者姓名
     */
    @TableField(value = "patient_name")
    private String patientName;

    /**
     * 是否录入检查结果0 检测中  1 检测完成  字典表 his_check_result_status
     */
    @TableField(value = "result_status")
    private String resultStatus;

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