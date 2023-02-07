package com.bee.openhis.service;

import com.bee.openhis.domain.Patient;
import com.baomidou.mybatisplus.extension.service.IService;
import com.bee.openhis.domain.PatientFile;
import com.bee.openhis.dto.PatientDto;
import com.bee.openhis.vo.DataGridView;

/**
* @author 19235
* @description 针对表【his_patient(患者信息表)】的数据库操作Service
* @createDate 2023-01-27 14:47:32
*/
public interface PatientService extends IService<Patient> {

    DataGridView listPatientForPage(PatientDto patientDto);

    Patient getPatientById(String patientId);

    PatientFile getPatientFileById(String patientId);

    Patient getPatientByIdCard(String idCard);

    Patient addPatient(PatientDto patientDto);
}
