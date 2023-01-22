package com.bee.openhis.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bee.openhis.domain.Medicines;
import com.bee.openhis.dto.MedicinesDto;
import com.bee.openhis.vo.DataGridView;

/**
* @author 19235
* @description 针对表【stock_medicines(药品信息表)】的数据库操作Service
* @createDate 2023-01-18 16:31:53
*/
public interface MedicinesService extends IService<Medicines> {

    DataGridView listMedicinesForPage(MedicinesDto medicinesDto);

    int addMedicines(MedicinesDto medicinesDto);

    int updateMedicines(MedicinesDto medicinesDto);

    Medicines getOne(Long medicinesId);

    int deleteMedicinesByIds(Long[] medicinesIds);

    int updateMedicinesStockNum(Long medicinesId, Integer stockNum);
}
