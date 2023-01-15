package com.bee.openhis.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bee.openhis.domain.OperLog;
import org.springframework.stereotype.Repository;

/**
* @author 19235
* @description 针对表【sys_oper_log(操作日志记录)】的数据库操作Mapper
* @createDate 2023-01-15 20:25:18
* @Entity com.bee.openhis.domain.OperLog
*/
@Repository
public interface OperLogMapper extends BaseMapper<OperLog> {

}




