package com.bee.openhis.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bee.openhis.domain.OrderBackfee;
import com.bee.openhis.service.OrderBackfeeService;
import com.bee.openhis.mapper.OrderBackfeeMapper;
import org.springframework.stereotype.Service;

/**
* @author 19235
* @description 针对表【his_order_backfee(退费主表)】的数据库操作Service实现
* @createDate 2023-01-27 14:47:32
*/
@Service
public class OrderBackfeeServiceImpl extends ServiceImpl<OrderBackfeeMapper, OrderBackfee>
    implements OrderBackfeeService{

}




