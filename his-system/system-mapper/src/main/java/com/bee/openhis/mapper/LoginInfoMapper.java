package com.bee.openhis.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bee.openhis.domain.LoginInfo;
import org.springframework.stereotype.Repository;

/**
 * @author 19235
 * @description 针对表【sys_login_info(系统访问记录)】的数据库操作Mapper
 * @createDate 2023-01-10 17:05:40
 * @Entity com.bee.openhis.domain.LoginInfo
 */
@Repository
public interface LoginInfoMapper extends BaseMapper<LoginInfo> {

}




