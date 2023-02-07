package com.bee.openhis.controller.doctor;

import com.bee.openhis.constants.Constants;
import com.bee.openhis.domain.Registration;
import com.bee.openhis.service.PatientService;
import com.bee.openhis.service.RegistrationService;
import com.bee.openhis.utils.HisDateUtils;
import com.bee.openhis.utils.ShiroSecurityUtils;
import com.bee.openhis.vo.AjaxResult;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("doctor/care")
public class CareController {


    @Reference
    private PatientService patientService;

    @Reference
    private RegistrationService registrationService;

    /**
     * 查询待就诊挂号信息
     *
     * @param schedulingType
     * @return
     */
    @GetMapping("queryToBeSeenRegistration/{schedulingType}")
    public AjaxResult queryToBeSeenRegistration(@PathVariable String schedulingType) {
        //获取部门id
        Long deptId = ShiroSecurityUtils.getCurrentActiveUser().getUser().getDeptId();
        //设置挂号信息的状态
        String regStatus = Constants.REG_STATUS_1;
        //计算时间段
        String subSectionType = HisDateUtils.getCurrentTimeType();
        //查询
        Long userId = null;
        List<Registration> list = registrationService.queryRegistration(deptId, subSectionType, schedulingType, regStatus, userId);
        return AjaxResult.success(list);
    }

    /**
     * 查询就诊中挂号信息
     *
     * @param schedulingType
     * @return
     */
    @GetMapping("queryVisitingRegistration/{schedulingType}")
    public AjaxResult queryVisitingRegistration(@PathVariable String schedulingType) {
        //获取部门id
        Long deptId = ShiroSecurityUtils.getCurrentActiveUser().getUser().getDeptId();
        //设置挂号信息的状态
        String regStatus = Constants.REG_STATUS_2;
        //查询
        Long userId = ShiroSecurityUtils.getCurrentActiveUser().getUser().getUserId();
        List<Registration> list = registrationService.queryRegistration(deptId, null, schedulingType, regStatus, userId);
        return AjaxResult.success(list);
    }

    /**
     * 查询就诊完成挂号信息
     *
     * @param schedulingType
     * @return
     */
    @GetMapping("queryVisitCompletedRegistration/{schedulingType}")
    public AjaxResult queryVisitCompletedRegistration(@PathVariable String schedulingType) {
        //获取部门id
        Long deptId = ShiroSecurityUtils.getCurrentActiveUser().getUser().getDeptId();
        //设置挂号信息的状态
        String regStatus = Constants.REG_STATUS_3;
        //查询
        Long userId = ShiroSecurityUtils.getCurrentActiveUser().getUser().getUserId();
        List<Registration> list = registrationService.queryRegistration(deptId, null, schedulingType, regStatus, userId);
        return AjaxResult.success(list);
    }

    /**
     * 接诊
     *
     * @param regId
     * @return
     */
    @PostMapping("receivePatient/{regId}")
    public AjaxResult receivePatient(@PathVariable String regId) {
        Registration registration = registrationService.queryRegistrationByRegId(regId);
        if (registration == null) {
            return AjaxResult.fail("【" + regId + "】挂号单不存在, 不能接诊");
        }
        //判断挂号单的状态,必须是待就诊才能接诊
        if (registration.getRegistrationStatus().equals(Constants.REG_STATUS_1)) {
            registration.setRegistrationStatus(Constants.REG_STATUS_2);
            registration.setUserId(ShiroSecurityUtils.getCurrentActiveUser().getUser().getUserId());
            registration.setDoctorName(ShiroSecurityUtils.getCurrentActiveUser().getUser().getUserName());
            return AjaxResult.toAjax(registrationService.updateRegistrationByRegId(registration));
        } else {
            return AjaxResult.fail("【" + regId + "】挂号单的状态不是待就诊，不能接诊");
        }
    }
}
