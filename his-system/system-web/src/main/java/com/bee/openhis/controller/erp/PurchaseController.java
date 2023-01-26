package com.bee.openhis.controller.erp;

import com.bee.openhis.constants.Constants;
import com.bee.openhis.domain.Purchase;
import com.bee.openhis.domain.PurchaseItem;
import com.bee.openhis.dto.PurchaseDto;
import com.bee.openhis.dto.PurchaseFormDto;
import com.bee.openhis.service.PurchaseService;
import com.bee.openhis.utils.IdGeneratorSnowflake;
import com.bee.openhis.utils.ShiroSecurityUtils;
import com.bee.openhis.vo.AjaxResult;
import com.bee.openhis.vo.DataGridView;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

/**
 * 采购
 */
@RestController
@RequestMapping("erp/purchase")
public class PurchaseController {

    @Reference
    private PurchaseService purchaseService;

    /**
     * 分页查询采购入库列表
     *
     * @param purchaseDto
     * @return
     */
    @GetMapping("listPurchaseForPage")
    public AjaxResult listPurchaseForPage(PurchaseDto purchaseDto) {
        DataGridView dataGridView = purchaseService.listPurchaseForPage(purchaseDto);
        return AjaxResult.success("查询成功", dataGridView.getData(), dataGridView.getTotal());
    }

    /**
     * 提交审核
     *
     * @param purchaseId
     * @return
     */
    @PostMapping("doAudit/{purchaseId}")
    public AjaxResult doAudit(@PathVariable String purchaseId) {
        Purchase purchase = purchaseService.getPurchaseById(purchaseId);
        //判断采购单的状态 1--未提交 4--审核失败 这两种状态可以提交
        if (purchase.getStatus().equals(Constants.STOCK_PURCHASE_STATUS_1)
                || purchase.getStatus().equals(Constants.STOCK_PURCHASE_STATUS_4)) {
            return AjaxResult.toAjax(purchaseService.doAudit(purchaseId, ShiroSecurityUtils.getCurrentSimpleUser()));
        } else {
            return AjaxResult.fail("当前单据状态不是未提交或审核失败,不能提交");
        }
    }

    /**
     * 提交作废
     *
     * @param purchaseId
     * @return
     */
    @PostMapping("doInvalid/{purchaseId}")
    public AjaxResult doInvalid(@PathVariable String purchaseId) {
        Purchase purchase = purchaseService.getPurchaseById(purchaseId);
        //判断采购单的状态 1-- 未提交 4-- 审核失败 这两种状态可以提交
        if (purchase.getStatus().equals(Constants.STOCK_PURCHASE_STATUS_1)
                || purchase.getStatus().equals(Constants.STOCK_PURCHASE_STATUS_4)) {
            //提交作废
            return AjaxResult.toAjax(purchaseService.doInvalid(purchaseId, ShiroSecurityUtils.getCurrentSimpleUser()));
        } else {
            return AjaxResult.fail("当前单据状态不是未提交或审核失败,不能作废");
        }
    }

    /**
     * 生成采购单 ID
     *
     * @return
     */
    @GetMapping("generatePurchaseId")
    public AjaxResult generatePurchaseId() {
        return AjaxResult.success(IdGeneratorSnowflake.generatorIdWithPrefix(Constants.ID_PROFIX_CG));
    }

    /**
     * 保存并提交审核
     *
     * @param purchaseFormDto
     * @return
     */
    @PostMapping("addPurchaseToAudit")
    public AjaxResult addPurchaseToAudit(@RequestBody PurchaseFormDto purchaseFormDto) {
        if (!checkPurchase(purchaseFormDto)) {
            return AjaxResult.fail("当前单据状态不是未提交或审核失败,不能提交审核");
        } else {
            purchaseFormDto.getPurchaseDto().setSimpleUser(ShiroSecurityUtils.getCurrentSimpleUser());
            return AjaxResult.toAjax(purchaseService.addPurchaseAndItemToAudit(purchaseFormDto));
        }
    }

    /**
     * 公共验证方法
     *
     * @param purchaseFormDto
     * @return
     */
    private boolean checkPurchase(PurchaseFormDto purchaseFormDto) {
        String purchaseId = purchaseFormDto.getPurchaseDto().getPurchaseId();
        Purchase purchase = purchaseService.getPurchaseById(purchaseId);
        if (purchase == null) {
            return true;
        }
        return purchase.getStatus().equals(Constants.STOCK_PURCHASE_STATUS_1)
                || purchase.getStatus().equals(Constants.STOCK_PURCHASE_STATUS_4);
    }

    /**
     * 暂存采购单
     *
     * @param purchaseFormDto
     * @return
     */
    @PostMapping("addPurchase")
    public AjaxResult addPurchase(@RequestBody PurchaseFormDto purchaseFormDto) {
        if (!checkPurchase(purchaseFormDto)) {
            return AjaxResult.fail("当前单据状态不是未提交或审核失败, 不能提交审核");
        }
        purchaseFormDto.getPurchaseDto().setSimpleUser(ShiroSecurityUtils.getCurrentSimpleUser());
        return AjaxResult.toAjax(purchaseService.addPurchaseAndItem(purchaseFormDto));
    }

    /**
     * 根据采购单 ID 查询详情(某个供应商,下面订购了哪些药)
     *
     * @param purchaseId
     * @return
     */
    @GetMapping("queryPurchaseAndItemByPurchaseId/{purchaseId}")
    public AjaxResult queryPurchaseAndItemByPurchaseId(@PathVariable String purchaseId) {
        Purchase purchase = purchaseService.getPurchaseById(purchaseId);
        if (purchase == null) {
            return AjaxResult.fail("单据号【" + purchaseId + "】不存在");
        } else {
            List<PurchaseItem> items = purchaseService.getPurchaseItemById(purchaseId);
            HashMap<String, Object> hashMap = new HashMap<>();
            hashMap.put("purchase", purchase);
            hashMap.put("items", items);
            return AjaxResult.success(hashMap);
        }
    }

    /**
     * 分页查询待审核的单据
     *
     * @param purchaseDto
     * @return
     */
    @GetMapping("listPurchasePendingForPage")
    public AjaxResult listPurchasePendingForPage(PurchaseDto purchaseDto) {
        purchaseDto.setStatus(Constants.STOCK_PURCHASE_STATUS_2);
        DataGridView dataGridView = purchaseService.listPurchaseForPage(purchaseDto);
        return AjaxResult.success("查询成功", dataGridView.getData(), dataGridView.getTotal());
    }

    /**
     * 审核通过
     *
     * @param purchaseId
     * @return
     */
    @PostMapping("auditPass/{purchaseId}")
    public AjaxResult auditPass(@PathVariable String purchaseId) {
        Purchase purchase = purchaseService.getPurchaseById(purchaseId);
        if (purchase.getStatus().equals(Constants.STOCK_PURCHASE_STATUS_2)) {
            int i = purchaseService.auditPass(purchaseId);
            return AjaxResult.toAjax(i);
        } else {
            return AjaxResult.fail("当前单据状态不是【待审核】，不能审核");
        }
    }

    /**
     * 审核不通过
     *
     * @param purchaseId
     * @return
     */
    @PostMapping("auditNoPass/{purchaseId}/{auditMsg}")
    public AjaxResult auditNoPass(@PathVariable String purchaseId, @PathVariable String auditMsg) {
        Purchase purchase = purchaseService.getPurchaseById(purchaseId);
        if (purchase.getStatus().equals(Constants.STOCK_PURCHASE_STATUS_2)) {
            int i = purchaseService.auditNoPass(purchaseId, auditMsg);
            return AjaxResult.toAjax(i);
        } else {
            return AjaxResult.fail("当前单据状态不是【待审核】, 不能审核");
        }
    }

    /**
     * 根据 ID 查询采购信息详情
     *
     * @param purchaseId
     * @return
     */
    @GetMapping("getPurchaseItemById/{purchaseId}")
    public AjaxResult getPurchaseItemById(@PathVariable String purchaseId) {
        List<PurchaseItem> items = purchaseService.getPurchaseItemById(purchaseId);
        return AjaxResult.success(items);
    }

    /**
     * 提交入库
     *
     * @param purchaseId
     * @return
     */
    @PostMapping("doInventory/{purchaseId}")
    public AjaxResult doInventory(@PathVariable String purchaseId) {
        Purchase purchase = purchaseService.getPurchaseById(purchaseId);
        if (purchase.getStatus().equals(Constants.STOCK_PURCHASE_STATUS_3)) {
            //进行入库
            return AjaxResult.toAjax(purchaseService.doInventory(purchaseId, ShiroSecurityUtils.getCurrentSimpleUser()));
        } else if (purchase.getStatus().equals(Constants.STOCK_PURCHASE_STATUS_6)) {
            return AjaxResult.fail("采购单【" + purchaseId + "】已入库，不能重复入库");
        } else {
            return AjaxResult.fail("采购单【" + purchaseId + "】没有审核通过，不能入库");
        }
    }
}
