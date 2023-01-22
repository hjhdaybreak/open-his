package com.bee.openhis.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bee.openhis.domain.Purchase;
import com.bee.openhis.mapper.PurchaseMapper;
import com.bee.openhis.service.PurchaseService;
import org.springframework.stereotype.Service;

/**
* @author 19235
* @description 针对表【stock_purchase】的数据库操作Service实现
* @createDate 2023-01-18 16:31:53
*/
@Service
public class PurchaseServiceImpl extends ServiceImpl<PurchaseMapper, Purchase>
    implements PurchaseService{

}




