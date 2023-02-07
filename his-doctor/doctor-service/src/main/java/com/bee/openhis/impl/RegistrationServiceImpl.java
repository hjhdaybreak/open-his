package com.bee.openhis.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bee.openhis.constants.Constants;
import com.bee.openhis.domain.Registration;
import com.bee.openhis.dto.RegistrationDto;
import com.bee.openhis.service.RegistrationService;
import com.bee.openhis.mapper.RegistrationMapper;
import com.bee.openhis.vo.DataGridView;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author 19235
 * @description 针对表【his_registration】的数据库操作Service实现
 * @createDate 2023-01-27 14:47:32
 */
@Service
public class RegistrationServiceImpl extends ServiceImpl<RegistrationMapper, Registration>
        implements RegistrationService {

    @Autowired
    private RegistrationMapper registrationMapper;

    @Override
    public void addRegistration(RegistrationDto registrationDto) {
        Registration registration = new Registration();
        BeanUtil.copyProperties(registrationDto, registration);
        registration.setRegistrationStatus(Constants.REG_STATUS_0);
        registration.setCreateBy(registrationDto.getSimpleUser().getUserName());
        registration.setCreateTime(DateUtil.date());
        registrationMapper.insert(registration);
    }

    @Override
    public Registration queryRegistrationByRegId(String regId) {
        return registrationMapper.selectById(regId);
    }

    @Override
    public int updateRegistrationByRegId(Registration registration) {
        return registrationMapper.updateById(registration);
    }

    @Override
    public DataGridView queryRegistrationForPage(RegistrationDto registrationDto) {
        Page<Registration> page = new Page<>(registrationDto.getPageNum(), registrationDto.getPageSize());
        QueryWrapper<Registration> wrapper = new QueryWrapper<>();
        wrapper.eq(registrationDto.getDeptId() != null, Registration.COL_DEPT_ID,
                registrationDto.getDeptId());
        wrapper.like(StringUtils.isNotBlank(registrationDto.getPatientName()), Registration.COL_PATIENT_NAME,
                registrationDto.getPatientName());
        wrapper.eq(StringUtils.isNotBlank(registrationDto.getSchedulingType()), Registration.COL_SCHEDULING_TYPE,
                registrationDto.getSchedulingType());
        wrapper.eq(StringUtils.isNotBlank(registrationDto.getSubsectionType()), Registration.COL_SUBSECTION_TYPE,
                registrationDto.getSubsectionType());
        wrapper.eq(StringUtils.isNotBlank(registrationDto.getRegStatus()), Registration.COL_REG_STATUS,
                registrationDto.getRegStatus());
        wrapper.eq(StringUtils.isNotBlank(registrationDto.getVisitDate()), Registration.COL_VISIT_DATE,
                registrationDto.getVisitDate());
        wrapper.orderByDesc(Registration.COL_CREATE_TIME);
        registrationMapper.selectPage(page, wrapper);
        return new DataGridView(page.getTotal(), page.getRecords());
    }

    @Override
    public List<Registration> queryRegistration(Long deptId, String subSectionType, String schedulingType, String regStatus, Long userId) {
        QueryWrapper<Registration> wrapper = new QueryWrapper<>();
        wrapper.eq(Registration.COL_DEPT_ID,deptId);
        wrapper.eq(StringUtils.isNotBlank(subSectionType),Registration.COL_SUBSECTION_TYPE,subSectionType);
        wrapper.eq(Registration.COL_SCHEDULING_TYPE,schedulingType);
        wrapper.eq(Registration.COL_REG_STATUS,regStatus);
        wrapper.eq(Registration.COL_VISIT_DATE,DateUtil.format(DateUtil.date(),"yyyy-MM-dd"));
        wrapper.eq(userId != null,Registration.COL_USER_ID,userId);
        wrapper.orderByAsc(Registration.COL_REG_NUMBER);
        return registrationMapper.selectList(wrapper);
    }

}




