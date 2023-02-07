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
 * @TableName his_patient_file
 */
@TableName(value ="his_patient_file")
@Data
public class PatientFile extends BaseEntity implements Serializable {
    /**
     * 患者id
     */
    @TableId(value = "patient_id")
    private String patientId;

    /**
     * 紧急联系人
     */
    @TableField(value = "emergency_contact_name")
    private String emergencyContactName;

    /**
     * 紧急联系人电话
     */
    @TableField(value = "emergency_contact_phone")
    private String emergencyContactPhone;

    /**
     * 爸爸,妈妈,儿子,女儿,亲戚,朋友
     */
    @TableField(value = "emergency_contact_relation")
    private String emergencyContactRelation;

    /**
     * 左耳听力 正常  耳聋
     */
    @TableField(value = "left_ear_hearing")
    private String leftEarHearing;

    /**
     * 右耳听力 正常  耳聋
     */
    @TableField(value = "right_ear_hearing")
    private String rightEarHearing;

    /**
     * 左眼视力
     */
    @TableField(value = "left_vision")
    private BigDecimal leftVision;

    /**
     * 右眼视力
     */
    @TableField(value = "right_vision")
    private BigDecimal rightVision;

    /**
     * 身高
     */
    @TableField(value = "height")
    private BigDecimal height;

    /**
     * 体重
     */
    @TableField(value = "weight")
    private BigDecimal weight;

    /**
     * 血型 A  B  AB  O    R-阴 RH-阳
     */
    @TableField(value = "blood_type")
    private String bloodType;

    /**
     * 个人史
     */
    @TableField(value = "personal_info")
    private String personalInfo;

    /**
     * 家族史
     */
    @TableField(value = "family_info")
    private String familyInfo;

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

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}