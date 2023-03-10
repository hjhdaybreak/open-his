package com.bee.openhis.service;

import com.bee.openhis.domain.CareHistory;
import com.baomidou.mybatisplus.extension.service.IService;
import com.bee.openhis.domain.CareOrder;
import com.bee.openhis.domain.CareOrderItem;
import com.bee.openhis.dto.CareHistoryDto;
import com.bee.openhis.dto.CareOrderFormDto;

import java.util.List;

/**
* @author 19235
* @description 针对表【his_care_history(病例表)】的数据库操作Service
* @createDate 2023-01-27 14:47:32
*/
public interface CareHistoryService extends IService<CareHistory> {

    List<CareHistory> queryCareHistoryByPatientId(String patientId);

    List<CareOrder> queryCareOrdersByChId(String chId);

    List<CareOrderItem> queryCareOrderItemsByCoId(String coId,String detailStatus);

    CareHistory saveOrUpdateCareHistory(CareHistoryDto careHistoryDto);

    CareHistory queryCareHistoryByRegId(String regId);

    CareHistory queryCareHistoryByChId(String chId);

    int saveCareOrderItem(CareOrderFormDto careOrderFormDto);

    CareOrderItem queryCareOrderItemsByItemId(String itemId);

    int deleteCareOrderItemById(String itemId);

    int visitComplete(String regId);
}
