package com.bee.openhis.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bee.openhis.domain.Producer;
import com.bee.openhis.dto.ProducerDto;
import com.bee.openhis.vo.DataGridView;

import java.util.List;

/**
* @author 19235
* @description 针对表【stock_producer(生产厂家表)】的数据库操作Service
* @createDate 2023-01-18 16:31:53
*/
public interface ProducerService extends IService<Producer> {


    DataGridView listProducerForPage(ProducerDto producerDto);

    int addProducer(ProducerDto producerDto);

    int updateProducer(ProducerDto producerDto);

    Producer getOne(Long producerId);

    int deleteProducterByIds(Long[] producerIds);

    /**
     * 查询所有可用生产厂家
     */
    List<Producer> selectAllProducter();

}
