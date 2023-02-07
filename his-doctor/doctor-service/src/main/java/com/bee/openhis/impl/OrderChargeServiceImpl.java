package com.bee.openhis.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bee.openhis.domain.OrderCharge;
import com.bee.openhis.service.OrderChargeService;
import com.bee.openhis.mapper.OrderChargeMapper;
import org.springframework.stereotype.Service;

/**
* @author 19235
* @description 针对表【his_order_charge(收费表)】的数据库操作Service实现
* @createDate 2023-01-27 14:47:32
*/
@Service
public class OrderChargeServiceImpl extends ServiceImpl<OrderChargeMapper, OrderCharge>
    implements OrderChargeService{

}




