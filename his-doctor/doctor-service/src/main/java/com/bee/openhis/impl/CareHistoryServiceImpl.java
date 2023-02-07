package com.bee.openhis.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bee.openhis.domain.CareHistory;
import com.bee.openhis.domain.CareOrder;
import com.bee.openhis.domain.CareOrderItem;
import com.bee.openhis.mapper.CareOrderItemMapper;
import com.bee.openhis.mapper.CareOrderMapper;
import com.bee.openhis.service.CareHistoryService;
import com.bee.openhis.mapper.CareHistoryMapper;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author 19235
 * @description 针对表【his_care_history(病例表)】的数据库操作Service实现
 * @createDate 2023-01-27 14:47:32
 */
@Service
public class CareHistoryServiceImpl extends ServiceImpl<CareHistoryMapper, CareHistory>
        implements CareHistoryService {

    @Autowired
    private CareHistoryMapper careHistoryMapper;

    @Autowired
    private CareOrderMapper careOrderMapper;

    @Autowired
    private CareOrderItemMapper careOrderItemMapper;

    @Override
    public List<CareHistory> queryCareHistoryByPatientId(String patientId) {
        QueryWrapper<CareHistory> wrapper = new QueryWrapper<>();
        wrapper.eq(CareHistory.COL_PATIENT_ID, patientId);
        return careHistoryMapper.selectList(wrapper);
    }

    @Override
    public List<CareOrder> queryCareOrdersByChId(String chId) {
        QueryWrapper<CareOrder> wrapper = new QueryWrapper<>();
        wrapper.eq(CareOrder.COL_CH_ID,chId);
        return careOrderMapper.selectList(wrapper);
    }

    @Override
    public List<CareOrderItem> queryCareOrderItemsByCoId(String coId, String status) {
        QueryWrapper<CareOrderItem> wrapper = new QueryWrapper<>();
        wrapper.eq(CareOrderItem.COL_CO_ID,coId);
        wrapper.eq(StringUtils.isNotBlank(status),CareOrderItem.COL_STATUS,status);
        return careOrderItemMapper.selectList(wrapper);
    }
}




