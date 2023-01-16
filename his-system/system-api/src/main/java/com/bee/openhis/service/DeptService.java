package com.bee.openhis.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bee.openhis.domain.Dept;
import com.bee.openhis.dto.DeptDto;
import com.bee.openhis.vo.DataGridView;

/**
* @author 19235
* @description 针对表【sys_dept(部门/科室表)】的数据库操作Service
* @createDate 2023-01-16 15:23:14
*/
public interface DeptService extends IService<Dept> {

    DataGridView listDeptForPage(DeptDto deptDto);

    int addDept(DeptDto deptDto);

    int updateDept(DeptDto deptDto);

    int deleteDeptByIds(Long[] deptIds);
}
