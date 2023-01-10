package com.bee.openhis.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 部门/科室表
 * @TableName sys_dept
 */
@TableName(value ="sys_dept")
@Data
public class Dept extends BaseEntity implements Serializable {
    /**
     * 部门科室id
     */
    @TableId(value = "dept_id", type = IdType.AUTO)
    private Long dept_id;

    /**
     * 部门名称
     */
    @TableField(value = "dept_name")
    private String dept_name;

    /**
     * 挂号编号
     */
    @TableField(value = "reg_number")
    private Integer reg_number;

    /**
     * 科室编号
     */
    @TableField(value = "dept_number")
    private String dept_number;

    /**
     * 显示顺序
     */
    @TableField(value = "order_num")
    private Integer order_num;

    /**
     * 负责人
     */
    @TableField(value = "dept_leader")
    private String dept_leader;

    /**
     * 联系电话
     */
    @TableField(value = "leader_phone")
    private String leader_phone;

    /**
     * 部门状态（0正常 1停用）
     */
    @TableField(value = "status")
    private String status;

    /**
     * 删除标志（0正常 1删除）
     */
    @TableField(value = "del_flag")
    private String del_flag;

    /**
     * 创建时间
     */
    @TableField(value = "create_time")
    private Date create_time;

    /**
     * 更新时间
     */
    @TableField(value = "update_time")
    private Date update_time;

    /**
     * 创建者
     */
    @TableField(value = "create_by")
    private String create_by;

    /**
     * 更新者
     */
    @TableField(value = "update_by")
    private String update_by;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}