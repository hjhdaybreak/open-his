package com.bee.openhis.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bee.openhis.domain.DictData;
import com.bee.openhis.dto.DictDataDto;
import com.bee.openhis.mapper.DictDataMapper;
import com.bee.openhis.service.DictDataService;
import com.bee.openhis.vo.DataGridView;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * @author 19235
 * @description 针对表【sys_dict_data(字典数据表)】的数据库操作Service实现
 * @createDate 2023-01-12 21:29:43
 */
@Service
public class DictDataServiceImpl extends ServiceImpl<DictDataMapper, DictData>
        implements DictDataService {

    @Autowired
    private DictDataMapper dictDataMapper;

    @Override
    public DataGridView listForPage(DictDataDto dictDataDto) {
        Page<DictData> page = new Page<>(dictDataDto.getPageNum(), dictDataDto.getPageSize());
        QueryWrapper<DictData> wrapper = new QueryWrapper<>();

        wrapper.eq(StringUtils.isNotBlank(dictDataDto.getDictType()),
                DictData.COL_DICT_TYPE, dictDataDto.getDictType());
        wrapper.like(StringUtils.isNotBlank(dictDataDto.getDictLabel()),
                DictData.COL_DICT_LABEL, dictDataDto.getDictLabel());
        wrapper.eq(StringUtils.isNotBlank(dictDataDto.getStatus()),
                DictData.COL_STATUS, dictDataDto.getStatus());

        dictDataMapper.selectPage(page, wrapper);
        return new DataGridView(page.getTotal(), page.getRecords());
    }

    @Override
    public DictData selectDictDataById(Long dictId) {
        return dictDataMapper.selectById(dictId);
    }

    @Override
    public int insert(DictDataDto dictDataDto) {
        DictData dictData = new DictData();
        BeanUtils.copyProperties(dictDataDto, dictData);
        dictData.setCreateBy(dictDataDto.getSimpleUser().getUserName());
        dictData.setCreateTime(DateUtil.date());
        return dictDataMapper.insert(dictData);
    }

    @Override
    public int update(DictDataDto dictDataDto) {
        DictData dictData = new DictData();
        BeanUtil.copyProperties(dictDataDto, dictData);
        dictData.setUpdateBy(dictDataDto.getSimpleUser().getUserName());
        return dictDataMapper.updateById(dictData);
    }

    @Override
    public int deleteDictData(Long[] dictIds) {
        List<Long> ids = Arrays.asList(dictIds);
        if (ids.size() > 0) {
            return dictDataMapper.deleteBatchIds(ids);
        } else {
            return 0;
        }
    }
}




