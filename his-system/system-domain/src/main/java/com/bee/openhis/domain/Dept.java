package com.bee.openhis.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

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
    private Long deptId;

    /**
     * 部门名称
     */
    @TableField(value = "dept_name")
    private String deptName;

    /**
     * 挂号编号
     */
    @TableField(value = "reg_number")
    private Integer regNumber;

    /**
     * 科室编号
     */
    @TableField(value = "dept_number")
    private String deptNumber;

    /**
     * 显示顺序
     */
    @TableField(value = "order_num")
    private Integer orderNum;

    /**
     * 负责人
     */
    @TableField(value = "dept_leader")
    private String deptLeader;

    /**
     * 联系电话
     */
    @TableField(value = "leader_phone")
    private String leaderPhone;

    /**
     * 部门状态（0正常 1停用）
     */
    @TableField(value = "status")
    private String status;

    /**
     * 删除标志（0正常 1删除）
     */
    @TableField(value = "del_flag")
    private String delFlag;

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

    public static final String COL_DEPT_ID = "dept_id";

    public static final String COL_DEPT_NAME = "dept_name";

    public static final String COL_REG_NUMBER = "reg_number";

    public static final String COL_DEPT_NUMBER = "dept_number";

    public static final String COL_ORDER_NUM = "order_num";

    public static final String COL_DEPT_LEADER = "dept_leader";

    public static final String COL_LEADER_PHONE = "leader_phone";

    public static final String COL_STATUS = "status";

    public static final String COL_CREATE_TIME = "create_time";

    public static final String COL_UPDATE_TIME = "update_time";

    public static final String COL_CREATE_BY = "create_by";

    public static final String COL_UPDATE_BY = "update_by";
}