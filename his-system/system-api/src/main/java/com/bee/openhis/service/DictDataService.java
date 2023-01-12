package com.bee.openhis.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bee.openhis.domain.DictData;
import com.bee.openhis.dto.DictDataDto;
import com.bee.openhis.vo.DataGridView;

/**
* @author 19235
* @description 针对表【sys_dict_data(字典数据表)】的数据库操作Service
* @createDate 2023-01-12 21:29:43
*/
public interface DictDataService extends IService<DictData> {

    DataGridView listForPage(DictDataDto dictDataDto);

}
