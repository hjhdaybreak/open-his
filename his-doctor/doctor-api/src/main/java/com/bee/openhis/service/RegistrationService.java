package com.bee.openhis.service;

import com.bee.openhis.domain.Registration;
import com.baomidou.mybatisplus.extension.service.IService;
import com.bee.openhis.dto.RegistrationDto;
import com.bee.openhis.vo.DataGridView;

import java.util.List;

/**
* @author 19235
* @description 针对表【his_registration】的数据库操作Service
* @createDate 2023-01-27 14:47:32
*/
public interface RegistrationService extends IService<Registration> {

    void addRegistration(RegistrationDto registrationDto);

    Registration queryRegistrationByRegId(String regId);

    int updateRegistrationByRegId(Registration registration);

    DataGridView queryRegistrationForPage(RegistrationDto registrationDto);

    List<Registration> queryRegistration(Long deptId, String subSectionType, String schedulingType, String regStatus, Long userId);
}
