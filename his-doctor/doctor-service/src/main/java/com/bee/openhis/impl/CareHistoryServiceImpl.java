package com.bee.openhis.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bee.openhis.constants.Constants;
import com.bee.openhis.domain.CareHistory;
import com.bee.openhis.domain.CareOrder;
import com.bee.openhis.domain.CareOrderItem;
import com.bee.openhis.domain.Registration;
import com.bee.openhis.dto.CareHistoryDto;
import com.bee.openhis.dto.CareOrderFormDto;
import com.bee.openhis.dto.CareOrderItemDto;
import com.bee.openhis.mapper.CareOrderItemMapper;
import com.bee.openhis.mapper.CareOrderMapper;
import com.bee.openhis.mapper.RegistrationMapper;
import com.bee.openhis.service.CareHistoryService;
import com.bee.openhis.mapper.CareHistoryMapper;
import com.bee.openhis.utils.IdGeneratorSnowflake;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
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


    @Autowired
    private RegistrationMapper registrationMapper;

    @Override
    public List<CareHistory> queryCareHistoryByPatientId(String patientId) {
        QueryWrapper<CareHistory> wrapper = new QueryWrapper<>();
        wrapper.eq(CareHistory.COL_PATIENT_ID, patientId);
        return careHistoryMapper.selectList(wrapper);
    }

    @Override
    public List<CareOrder> queryCareOrdersByChId(String chId) {
        QueryWrapper<CareOrder> wrapper = new QueryWrapper<>();
        wrapper.eq(CareOrder.COL_CH_ID, chId);
        return careOrderMapper.selectList(wrapper);
    }

    @Override
    public List<CareOrderItem> queryCareOrderItemsByCoId(String coId, String status) {
        QueryWrapper<CareOrderItem> wrapper = new QueryWrapper<>();
        wrapper.eq(CareOrderItem.COL_CO_ID, coId);
        wrapper.eq(StringUtils.isNotBlank(status), CareOrderItem.COL_STATUS, status);
        return careOrderItemMapper.selectList(wrapper);
    }

    @Override
    public CareHistory saveOrUpdateCareHistory(CareHistoryDto careHistoryDto) {
        CareHistory careHistory = new CareHistory();
        BeanUtil.copyProperties(careHistoryDto, careHistory);
        if (StringUtils.isNotBlank(careHistory.getChId())) {
            careHistoryMapper.updateById(careHistory);  //更新
        } else {
            //添加
            careHistory.setChId(IdGeneratorSnowflake.generatorIdWithPrefix(Constants.ID_PROFIX_CH));
            careHistoryMapper.insert(careHistory);
        }
        return careHistory;
    }

    @Override
    public CareHistory queryCareHistoryByRegId(String regId) {
        QueryWrapper<CareHistory> wrapper = new QueryWrapper<>();
        wrapper.eq(CareHistory.COL_REG_ID, regId);
        return careHistoryMapper.selectOne(wrapper);
    }

    @Override
    public CareHistory queryCareHistoryByChId(String chId) {
        return careHistoryMapper.selectById(chId);
    }

    @Override
    public int saveCareOrderItem(CareOrderFormDto careOrderFormDto) {
        CareOrder careOrder = new CareOrder();
        BeanUtil.copyProperties(careOrderFormDto.getCareOrder(), careOrder);
        careOrder.setCreateBy(careOrderFormDto.getCareOrder().getSimpleUser().getUserName());
        careOrder.setCreateTime(DateUtil.date());
        int i = careOrderMapper.insert(careOrder);
        //保存详情表
        List<CareOrderItemDto> items = careOrderFormDto.getCareOrderItems();
        for (CareOrderItemDto item : items) {
            CareOrderItem careOrderItem = new CareOrderItem();
            BeanUtil.copyProperties(item, careOrderItem);
            careOrderItem.setCoId(careOrder.getCoId());
            careOrderItem.setCreateTime(DateUtil.date());
            careOrderItem.setStatus(Constants.ORDER_STATUS_0);
            careOrderItem.setItemId(IdGeneratorSnowflake.generatorIdWithPrefix(Constants.ID_PROFIX_ITEM));
            careOrderItemMapper.insert(careOrderItem);
        }
        return i;
    }

    @Override
    public CareOrderItem queryCareOrderItemsByItemId(String itemId) {
        return careOrderItemMapper.selectById(itemId);
    }

    @Override
    public int deleteCareOrderItemById(String itemId) {
        CareOrderItem careOrderItem = careOrderItemMapper.selectById(itemId);
        String coId = careOrderItem.getCoId();
        //删除
        int i = careOrderItemMapper.deleteById(itemId);
        QueryWrapper<CareOrderItem> wrapper = new QueryWrapper<>();
        wrapper.eq(CareOrderItem.COL_CO_ID,coId);
        List<CareOrderItem> careOrderItems = careOrderItemMapper.selectList(wrapper);
        if(careOrderItems != null && careOrderItems.size() > 0){
            //重新计算处方价格
            BigDecimal allAmount = new BigDecimal("0");
            for (CareOrderItem orderItem : careOrderItems) {
                allAmount = allAmount.add(orderItem.getAmount());
            }
            //根据coId查询处方表
            CareOrder careOrder = careOrderMapper.selectById(coId);
            careOrder.setAllAmount(allAmount);
            careOrderMapper.updateById(careOrder);
        }else{
            careOrderMapper.deleteById(coId);
        }
        return i;
    }

    @Override
    public int visitComplete(String regId) {
        Registration registration = new Registration();
        registration.setRegistrationId(regId);
        registration.setRegistrationStatus(Constants.REG_STATUS_3);
        return registrationMapper.updateById(registration);
    }
}




