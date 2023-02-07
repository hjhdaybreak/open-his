package com.bee.openhis.mapper;

import com.bee.openhis.domain.Scheduling;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @author 19235
* @description 针对表【his_scheduling(排班信息表)】的数据库操作Mapper
* @createDate 2023-01-27 14:47:32
* @Entity com.bee.openhis.domain.Scheduling
*/
public interface SchedulingMapper extends BaseMapper<Scheduling> {

    List<Long> queryHasSchedulingDeptIds(@Param("deptId") Long deptId,
                                         @Param("schedulingDay") String schedulingDay,
                                         @Param("schedulingType") String schedulingType,
                                         @Param("subsectionType") String subsectionType);
}




