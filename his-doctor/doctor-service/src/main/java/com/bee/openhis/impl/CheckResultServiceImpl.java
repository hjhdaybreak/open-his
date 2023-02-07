package com.bee.openhis.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bee.openhis.domain.CheckResult;
import com.bee.openhis.service.CheckResultService;
import com.bee.openhis.mapper.CheckResultMapper;
import org.springframework.stereotype.Service;

/**
* @author 19235
* @description 针对表【his_check_result】的数据库操作Service实现
* @createDate 2023-01-27 14:47:32
*/
@Service
public class CheckResultServiceImpl extends ServiceImpl<CheckResultMapper, CheckResult>
    implements CheckResultService{

}




