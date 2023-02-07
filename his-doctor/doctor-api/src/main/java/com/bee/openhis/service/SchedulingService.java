package com.bee.openhis.service;

import com.bee.openhis.domain.Scheduling;
import com.baomidou.mybatisplus.extension.service.IService;
import com.bee.openhis.dto.SchedulingFormDto;
import com.bee.openhis.dto.SchedulingQueryDto;

import java.util.List;

/**
 * @author 19235
 * @description 针对表【his_scheduling(排班信息表)】的数据库操作Service
 * @createDate 2023-01-27 14:47:32
 */
public interface SchedulingService extends IService<Scheduling> {

    List<Scheduling> queryScheduling(SchedulingQueryDto schedulingQueryDto);

    int saveScheduling(SchedulingFormDto schedulingFormDto);

    List<Long> queryHasSchedulingDeptIds(Long deptId, String schedulingDay, String schedulingType, String subsectionType);
}
