package com.bee.openhis.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bee.openhis.domain.LoginInfo;
import com.bee.openhis.dto.LoginInfoDto;
import com.bee.openhis.vo.DataGridView;

/**
 * @description 针对表【sys_login_info(系统访问记录)】的数据库操作Service
 */
public interface LoginInfoService extends IService<LoginInfo> {

    void insertLoginInfo(LoginInfo loginInfo);

    DataGridView listForPage(LoginInfoDto loginInfoDto);

    int deleteLoginInfoByIds(Long[] infoIds);


    int clearLoginInfo();

}
