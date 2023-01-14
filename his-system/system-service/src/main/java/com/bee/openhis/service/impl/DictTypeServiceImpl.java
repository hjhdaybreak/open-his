package com.bee.openhis.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bee.openhis.constants.Constants;
import com.bee.openhis.domain.DictData;
import com.bee.openhis.domain.DictType;
import com.bee.openhis.dto.DictTypeDto;
import com.bee.openhis.mapper.DictDataMapper;
import com.bee.openhis.mapper.DictTypeMapper;
import com.bee.openhis.service.DictTypeService;
import com.bee.openhis.vo.DataGridView;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.util.List;

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


    @Autowired
    private DictDataMapper dictDataMapper;

    @Autowired
    private StringRedisTemplate redisTemplate;

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


    public void dictCacheAsync() {
        QueryWrapper<DictType> dictTypeQueryWrapper = new QueryWrapper<>();
        dictTypeQueryWrapper.eq(DictType.COL_STATUS, Constants.STATUS_TRUE);
        List<DictType> dictTypes = dictTypeMapper.selectList(dictTypeQueryWrapper);
        for (DictType dictType : dictTypes) {
            QueryWrapper<DictData> dictDataWrapper = new QueryWrapper<>();
            dictDataWrapper.eq(DictData.COL_STATUS, Constants.STATUS_TRUE);
            dictDataWrapper.eq(DictData.COL_DICT_TYPE, dictType.getDictType());
            dictDataWrapper.orderByAsc(DictData.COL_DICT_SORT);
            List<DictData> dataList = dictDataMapper.selectList(dictDataWrapper);
            String string = JSON.toJSONString(dataList);
            ValueOperations<String, String> value = redisTemplate.opsForValue();
            value.set(Constants.DICT_REDIS_PREFIX + dictType.getDictType(), string);
        }
    }
}




