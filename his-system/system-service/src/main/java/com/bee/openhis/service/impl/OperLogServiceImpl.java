package com.bee.openhis.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bee.openhis.domain.OperLog;


import com.bee.openhis.dto.OperLogDto;
import com.bee.openhis.mapper.OperLogMapper;
import com.bee.openhis.service.OperLogService;
import com.bee.openhis.vo.DataGridView;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
* @author 19235
* @description 针对表【sys_oper_log(操作日志记录)】的数据库操作Service实现
* @createDate 2023-01-15 20:25:18
*/
@Service
public class OperLogServiceImpl extends ServiceImpl<OperLogMapper, OperLog>
    implements OperLogService {


    @Autowired
    private OperLogMapper operLogMapper;

    @Override
    public DataGridView listForPage(OperLogDto operLogDto) {
        Page<OperLog> page = new Page<>(operLogDto.getPageNum(), operLogDto.getPageSize());
        QueryWrapper<OperLog> wrapper = new QueryWrapper<>();
        wrapper.like(StringUtils.isNotBlank(operLogDto.getOperName()),
                OperLog.COL_OPER_NAME,operLogDto.getOperName());
        wrapper.like(StringUtils.isNotBlank(operLogDto.getTitle()),
                OperLog.COL_TITLE,operLogDto.getTitle());
        wrapper.eq(StringUtils.isNotBlank(operLogDto.getBusinessType()),
                OperLog.COL_BUSINESS_TYPE,operLogDto.getBusinessType());
        wrapper.eq(StringUtils.isNotBlank(operLogDto.getStatus()),
                OperLog.COL_STATUS,operLogDto.getStatus());
        wrapper.ge(operLogDto.getBeginTime() != null, OperLog.COL_OPER_TIME,
                operLogDto.getBeginTime());
        wrapper.le(operLogDto.getEndTime() != null,OperLog.COL_OPER_TIME,
                operLogDto.getEndTime());
        wrapper.orderByDesc(OperLog.COL_OPER_TIME);
        this.operLogMapper.selectPage(page,wrapper);
        return new DataGridView(page.getTotal(),page.getRecords());
    }

    @Override
    public int deleteOperLogByIds(Long[] infoIds) {
        List<Long> ids = Arrays.asList(infoIds);
        if(ids.size() > 0){
            return operLogMapper.deleteBatchIds(ids);
        }
        return 0;
    }

    @Override
    public int clearAllOperLog() {
        return 0;
    }


}




