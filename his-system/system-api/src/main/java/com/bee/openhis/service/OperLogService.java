package com.bee.openhis.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bee.openhis.domain.OperLog;
import com.bee.openhis.dto.OperLogDto;
import com.bee.openhis.vo.DataGridView;

/**
* @author 19235
* @description 针对表【sys_oper_log(操作日志记录)】的数据库操作Service
* @createDate 2023-01-15 20:25:18
*/

public interface OperLogService extends IService<OperLog> {

    DataGridView listForPage(OperLogDto operLogDto);

    int deleteOperLogByIds(Long[] infoIds);

    int clearAllOperLog();
}
