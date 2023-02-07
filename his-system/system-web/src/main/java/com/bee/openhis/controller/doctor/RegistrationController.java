package com.bee.openhis.controller.doctor;

import cn.hutool.core.util.StrUtil;
import com.bee.openhis.constants.Constants;
import com.bee.openhis.domain.Dept;
import com.bee.openhis.domain.Patient;
import com.bee.openhis.domain.Registration;
import com.bee.openhis.dto.PatientDto;
import com.bee.openhis.dto.RegistrationDto;
import com.bee.openhis.dto.RegistrationFormDto;
import com.bee.openhis.dto.RegistrationQueryDto;
import com.bee.openhis.service.DeptService;
import com.bee.openhis.service.PatientService;
import com.bee.openhis.service.RegistrationService;
import com.bee.openhis.service.SchedulingService;
import com.bee.openhis.utils.IdGeneratorSnowflake;
import com.bee.openhis.utils.ShiroSecurityUtils;
import com.bee.openhis.vo.AjaxResult;
import com.bee.openhis.vo.DataGridView;
import org.apache.commons.lang.StringUtils;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;


@RestController
@RequestMapping("doctor/registration")
public class RegistrationController {


    /**
     * 科室
     */
    @Autowired
    private DeptService deptService;

    /**
     * 患者
     */
    @Reference
    private PatientService patientService;


    @Reference
    private SchedulingService schedulingService;

    @Reference
    private RegistrationService registrationService;

    /**
     * 1、从his_scheduling表中查出所有参与排班的deptId集合
     * 2、根据deptId集合到his_dept表查询部门信息
     */
    @GetMapping("listDeptForScheduling")
    public AjaxResult listDeptForScheduling(@Validated RegistrationQueryDto registrationQueryDto) {
        Long deptId = registrationQueryDto.getDeptId();
        String subsectionType = registrationQueryDto.getSubsectionType();   //时段
        String schedulingType = registrationQueryDto.getSchedulingType();   //排班类型 门诊、急诊
        String schedulingDay = registrationQueryDto.getSchedulingDay().substring(0, 10); //日期

        //查询需要排班的部门 ID 集合
        List<Long> deptIds = schedulingService.queryHasSchedulingDeptIds(deptId, schedulingDay, schedulingType, subsectionType);
        if (deptIds == null || deptIds.size() == 0) {
            return AjaxResult.success(Collections.EMPTY_LIST);
        } else {
            //部门表
            List<Dept> list = deptService.listDeptByDeptIds(deptIds);
            return AjaxResult.success(list);
        }
    }

    /**
     * 根据身份证号查询患者信息
     *
     * @param idCard
     * @return
     */
    @GetMapping("getPatientByIdCard/{idCard}")
    public AjaxResult getPatientByIdCard(@PathVariable String idCard) {
        Patient patient = patientService.getPatientByIdCard(idCard);
        if (patient != null) {
            return AjaxResult.success(patient);
        } else {
            return AjaxResult.fail("【" + idCard + "】对应的患者不存在，请在下面新建患者信息");
        }
    }


    /**
     * 挂号
     *
     * @param registrationFormDto
     * @return
     */
    @PostMapping("addRegistration")
    public AjaxResult addRegistration(@RequestBody RegistrationFormDto registrationFormDto) {
        PatientDto patientDto = registrationFormDto.getPatientDto();
        RegistrationDto registrationDto = registrationFormDto.getRegistrationDto();
        Patient patient = null;
        if (StringUtils.isBlank(patientDto.getPatientId())) { //要加载患者信息
            patientDto.setPatientId(IdGeneratorSnowflake.generatorIdWithPrefix(Constants.ID_PROFIX_HZ));
            patientDto.setSimpleUser(ShiroSecurityUtils.getCurrentSimpleUser());
            patient = patientService.addPatient(patientDto);
        } else {
            patient = patientService.getPatientById(patientDto.getPatientId());
        }
        if (patient == null) {
            return AjaxResult.fail("当前患者id不存在, 请确认后提交");
        }

        //查询部门信息
        Dept dept = deptService.getOne(registrationDto.getDeptId()); //能得到排到什么号了 dept.getRegNumber()
        //保存患者信息
        registrationDto.setSimpleUser(ShiroSecurityUtils.getCurrentSimpleUser());
        registrationDto.setRegId(IdGeneratorSnowflake.generatorIdWithPrefix(Constants.ID_PROFIX_GH));
        registrationDto.setPatientId(patient.getPatientId());
        registrationDto.setPatientName(patient.getName());
        registrationDto.setRegNumber(dept.getRegNumber() + 1);
        registrationService.addRegistration(registrationDto);
        //更新当天的科室挂号编号
        deptService.updateDeptRegNumber(dept.getDeptId(), dept.getRegNumber() + 1);
        //返回挂号编号给前端
        return AjaxResult.success("", registrationDto.getRegId());
    }

    /**
     * 收挂号费
     *
     * @param regId
     * @return
     */
    @PostMapping("collectFee/{regId}")
    public AjaxResult collectFee(@PathVariable String regId) {
        //根据regId查询挂号单
        Registration registration = registrationService.queryRegistrationByRegId(regId);
        if (registration == null) {
            return AjaxResult.fail("挂号单【" + regId + "】不存在, 不能收费");
        }
        //如果挂号单存在，判断它的状态是不是未收费
        if (!registration.getRegistrationStatus().equals(Constants.REG_STATUS_0)) {
            return AjaxResult.fail("挂号单【" + regId + "】的状态不是未收费，不能收费");
        }
        //收费，更新挂号单状态
        registration.setRegistrationStatus(Constants.REG_STATUS_1);
        return AjaxResult.toAjax(registrationService.updateRegistrationByRegId(registration));
    }


    /**
     * 分页查询挂号数据
     *
     * @param registrationDto
     * @return
     */
    @GetMapping("queryRegistrationForPage")
    public AjaxResult queryRegistrationForPage(RegistrationDto registrationDto) {
        DataGridView dataGridView = registrationService.queryRegistrationForPage(registrationDto);
        return AjaxResult.success("查询成功", dataGridView.getData(), dataGridView.getTotal());
    }

    /**
     * 作废
     *
     * @param regId
     * @return
     */
    @PostMapping("doInvalid/{regId}")
    public AjaxResult doInvalid(@PathVariable String regId) {
        Registration registration = registrationService.queryRegistrationByRegId(regId);
        if (registration == null) {
            return AjaxResult.fail("当前挂号单【" + regId + "】不存在，不能作废");
        }
        //如果挂号单的状态不是未收费，不能作废
        if (!registration.getRegistrationStatus().equals(Constants.REG_STATUS_0)) {
            return AjaxResult.fail("当前挂号单【" + regId + "】不是未收费状态，不能作废");
        }
        //更新挂号单状态
        registration.setRegistrationStatus(Constants.REG_STATUS_5);  //5--作废
        return AjaxResult.toAjax(registrationService.updateRegistrationByRegId(registration));
    }
}
