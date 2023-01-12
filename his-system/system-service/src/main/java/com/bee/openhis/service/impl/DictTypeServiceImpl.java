package com.bee.openhis.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bee.openhis.domain.DictType;
import com.bee.openhis.dto.DictTypeDto;
import com.bee.openhis.mapper.DictTypeMapper;
import com.bee.openhis.service.DictTypeService;
import com.bee.openhis.vo.DataGridView;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 19235
 * @description 针对表【sys_dict_type(字典类型表)】的数据库操作Service实现
 * @createDate 2023-01-12 21:29:43
 */
@Service
public class DictTypeServiceImpl extends ServiceImpl<DictTypeMapper, DictType>
        implements DictTypeService {

    @Autowired
    private DictTypeMapper dictTypeMapper;

    @Override
    public DataGridView listForPage(DictTypeDto dictTypeDto) {
        Page<DictType> page = new Page<>(dictTypeDto.getPageNum(), dictTypeDto.getPageSize());
        QueryWrapper<DictType> wrapper = new QueryWrapper<>();
        wrapper.like(StringUtils.isNotBlank(dictTypeDto.getDictName()),
                DictType.COL_DICT_NAME, dictTypeDto.getDictName());
        wrapper.like(StringUtils.isNotBlank(dictTypeDto.getDictType()),
                DictType.COL_DICT_TYPE, dictTypeDto.getDictType());
        wrapper.eq(StringUtils.isNotBlank(dictTypeDto.getStatus()),
                DictType.COL_STATUS, dictTypeDto.getStatus());
        wrapper.ge(dictTypeDto.getBeginTime() != null,
                DictType.COL_CREATE_TIME, dictTypeDto.getBeginTime());
        wrapper.le(dictTypeDto.getEndTime() != null,
                DictType.COL_CREATE_TIME, dictTypeDto.getEndTime());
        dictTypeMapper.selectPage(page, wrapper);
        return new DataGridView(page.getTotal(), page.getRecords());
    }
}




