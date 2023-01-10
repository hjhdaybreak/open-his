package com.bee.openhis.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 菜单权限表
 * @TableName sys_menu
 */
@TableName(value ="sys_menu")
@Data
public class Menu extends BaseEntity implements Serializable {
    /**
     * 菜单ID
     */
    @TableId(value = "menu_id", type = IdType.AUTO)
    private Long menu_id;

    /**
     * 父菜单ID
     */
    @TableField(value = "parent_id")
    private Long parent_id;

    /**
     * 父节点ID集合
     */
    @TableField(value = "parent_ids")
    private String parent_ids;

    /**
     * 菜单名称
     */
    @TableField(value = "menu_name")
    private String menu_name;

    /**
     * 菜单类型（M目录 C菜单 F按钮）
     */
    @TableField(value = "menu_type")
    private String menu_type;

    /**
     * 权限标识
     */
    @TableField(value = "percode")
    private String percode;

    /**
     * 路由地址
     */
    @TableField(value = "path")
    private String path;

    /**
     * 备注
     */
    @TableField(value = "remark")
    private String remark;

    /**
     * 菜单状态（0正常 1停用）
     */
    @TableField(value = "status")
    private String status;

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