package com.bee.openhis.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bee.openhis.domain.OrderChargeItem;
import com.bee.openhis.service.OrderChargeItemService;
import com.bee.openhis.mapper.OrderChargeItemMapper;
import org.springframework.stereotype.Service;

/**
* @author 19235
* @description 针对表【his_order_charge_item(支付订单详情表)】的数据库操作Service实现
* @createDate 2023-01-27 14:47:32
*/
@Service
public class OrderChargeItemServiceImpl extends ServiceImpl<OrderChargeItemMapper, OrderChargeItem>
    implements OrderChargeItemService{

}




