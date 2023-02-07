package com.bee.openhis.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bee.openhis.domain.Patient;
import com.bee.openhis.domain.PatientFile;
import com.bee.openhis.dto.PatientDto;
import com.bee.openhis.mapper.PatientFileMapper;
import com.bee.openhis.service.PatientService;
import com.bee.openhis.mapper.PatientMapper;
import com.bee.openhis.vo.DataGridView;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.apache.dubbo.config.annotation.Service;

/**
 * @author 19235
 * @description 针对表【his_patient(患者信息表)】的数据库操作Service实现
 * @createDate 2023-01-27 14:47:32
 */
@Service
public class PatientServiceImpl extends ServiceImpl<PatientMapper, Patient>
        implements PatientService {

    @Autowired
    private PatientMapper patientMapper;

    @Autowired
    private PatientFileMapper patientFileMapper;

    @Override
    public DataGridView listPatientForPage(PatientDto patientDto) {
        Page<Patient> page = new Page<>(patientDto.getPageNum(), patientDto.getPageSize());
        QueryWrapper<Patient> wrapper = new QueryWrapper<>();
        wrapper.like(StringUtils.isNotBlank(patientDto.getName()), Patient.COL_NAME,
                patientDto.getName());
        wrapper.like(StringUtils.isNotBlank(patientDto.getPhone()), Patient.COL_PHONE,
                patientDto.getPhone());
        wrapper.like(StringUtils.isNotBlank(patientDto.getIdCard()), Patient.COL_ID_CARD,
                patientDto.getIdCard());
        patientMapper.selectPage(page, wrapper);
        return new DataGridView(page.getTotal(), page.getRecords());
    }

    @Override
    public Patient getPatientById(String patientId) {
        return patientMapper.selectById(patientId);
    }

    @Override
    public PatientFile getPatientFileById(String patientId) {
        return patientFileMapper.selectById(patientId);
    }

    @Override
    public Patient getPatientByIdCard(String idCard) {
        QueryWrapper<Patient> wrapper = new QueryWrapper<>();
        wrapper.eq(Patient.COL_ID_CARD,idCard);
        return patientMapper.selectOne(wrapper);
    }

    @Override
    public Patient addPatient(PatientDto patientDto) {
        return null;
    }
}




