package com.bee.openhis.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bee.openhis.domain.DictType;
import com.bee.openhis.dto.DictTypeDto;
import com.bee.openhis.vo.DataGridView;

/**
* @author 19235
* @description 针对表【sys_dict_type(字典类型表)】的数据库操作Service
* @createDate 2023-01-12 21:29:43
*/
public interface DictTypeService extends IService<DictType> {

    DataGridView listForPage(DictTypeDto dictTypeDto);
}
