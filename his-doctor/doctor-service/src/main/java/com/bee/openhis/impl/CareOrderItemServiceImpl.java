package com.bee.openhis.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bee.openhis.domain.CareOrderItem;
import com.bee.openhis.service.CareOrderItemService;
import com.bee.openhis.mapper.CareOrderItemMapper;
import org.springframework.stereotype.Service;

/**
* @author 19235
* @description 针对表【his_care_order_item(开诊细表)】的数据库操作Service实现
* @createDate 2023-01-27 14:47:32
*/
@Service
public class CareOrderItemServiceImpl extends ServiceImpl<CareOrderItemMapper, CareOrderItem>
    implements CareOrderItemService{

}




