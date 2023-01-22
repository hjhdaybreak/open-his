package com.bee.openhis.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bee.openhis.domain.Provider;
import com.bee.openhis.dto.ProviderDto;
import com.bee.openhis.vo.DataGridView;

import java.util.List;

/**
* @author 19235
* @description 针对表【stock_provider(供应商信息表)】的数据库操作Service
* @createDate 2023-01-18 16:31:53
*/
public interface ProviderService extends IService<Provider> {

    DataGridView listProviderForPage(ProviderDto providerDto);

    int addProvider(ProviderDto providerDto);

    int updateProvider(ProviderDto providerDto);

    Provider getOne(Long providerId);

    int deleteProviderByIds(Long[] providerIds);

    /**
     * 查询所有可用供应商
     */
    List<Provider> selectAllProvider();

}
