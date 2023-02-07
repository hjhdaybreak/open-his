package com.bee.openhis.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bee.openhis.domain.CareOrder;
import com.bee.openhis.service.CareOrderService;
import com.bee.openhis.mapper.CareOrderMapper;
import org.springframework.stereotype.Service;

/**
* @author 19235
* @description 针对表【his_care_order(药用处方表)】的数据库操作Service实现
* @createDate 2023-01-27 14:47:32
*/
@Service
public class CareOrderServiceImpl extends ServiceImpl<CareOrderMapper, CareOrder>
    implements CareOrderService{

}




