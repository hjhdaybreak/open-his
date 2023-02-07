package com.bee.openhis.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bee.openhis.domain.OrderBackfeeItem;
import com.bee.openhis.service.OrderBackfeeItemService;
import com.bee.openhis.mapper.OrderBackfeeItemMapper;
import org.springframework.stereotype.Service;

/**
* @author 19235
* @description 针对表【his_order_backfee_item(退费订单详情表)】的数据库操作Service实现
* @createDate 2023-01-27 14:47:32
*/
@Service
public class OrderBackfeeItemServiceImpl extends ServiceImpl<OrderBackfeeItemMapper, OrderBackfeeItem>
    implements OrderBackfeeItemService{

}




