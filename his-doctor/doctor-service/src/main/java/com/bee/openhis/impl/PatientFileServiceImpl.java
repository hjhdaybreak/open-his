package com.bee.openhis.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bee.openhis.domain.PatientFile;
import com.bee.openhis.service.PatientFileService;
import com.bee.openhis.mapper.PatientFileMapper;
import org.springframework.stereotype.Service;

/**
* @author 19235
* @description 针对表【his_patient_file】的数据库操作Service实现
* @createDate 2023-01-27 14:47:32
*/
@Service
public class PatientFileServiceImpl extends ServiceImpl<PatientFileMapper, PatientFile>
    implements PatientFileService{

}




