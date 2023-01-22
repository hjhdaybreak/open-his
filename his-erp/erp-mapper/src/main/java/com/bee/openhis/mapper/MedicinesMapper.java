package com.bee.openhis.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bee.openhis.domain.Medicines;
import org.springframework.stereotype.Repository;

/**
* @author 19235
* @description 针对表【stock_medicines(药品信息表)】的数据库操作Mapper
* @createDate 2023-01-18 16:31:53
* @Entity com.bee.openhis.domain.Medicines
*/
@Repository
public interface MedicinesMapper extends BaseMapper<Medicines> {

}




