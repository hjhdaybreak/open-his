package com.bee.openhis.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bee.openhis.domain.Purchase;
import com.bee.openhis.domain.PurchaseItem;
import com.bee.openhis.domain.SimpleUser;
import com.bee.openhis.dto.PurchaseDto;
import com.bee.openhis.dto.PurchaseFormDto;
import com.bee.openhis.vo.DataGridView;

import java.util.List;

/**
* @author 19235
* @description 针对表【stock_purchase】的数据库操作Service
* @createDate 2023-01-18 16:31:53
*/
public interface PurchaseService extends IService<Purchase> {

    DataGridView listPurchaseForPage(PurchaseDto purchaseDto);

    Purchase getPurchaseById(String purchaseId);

    int doAudit(String purchaseId, SimpleUser currentSimpleUser);

    int doInvalid(String purchaseId, SimpleUser currentSimpleUser);

    int addPurchaseAndItemToAudit(PurchaseFormDto purchaseFormDto);

    int addPurchaseAndItem(PurchaseFormDto purchaseFormDto);

    List<PurchaseItem> getPurchaseItemById(String purchaseId);

    int auditPass(String purchaseId);

    int auditNoPass(String purchaseId, String auditMsg);

    int doInventory(String purchaseId, SimpleUser currentSimpleUser);
}
