package com.bee.openhis.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bee.openhis.domain.Dept;
import com.bee.openhis.service.DeptService;
import com.bee.openhis.mapper.DeptMapper;
import org.springframework.stereotype.Service;

/**
* @author 19235
* @description 针对表【sys_dept(部门/科室表)】的数据库操作Service实现
* @createDate 2023-01-10 22:34:09
*/
@Service
public class DeptServiceImpl extends ServiceImpl<DeptMapper, Dept>
    implements DeptService{

}




