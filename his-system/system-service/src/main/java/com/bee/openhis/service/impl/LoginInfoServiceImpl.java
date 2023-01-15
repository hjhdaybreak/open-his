package com.bee.openhis.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bee.openhis.domain.LoginInfo;
import com.bee.openhis.dto.LoginInfoDto;
import com.bee.openhis.mapper.LoginInfoMapper;

import com.bee.openhis.service.LoginInfoService;
import com.bee.openhis.vo.DataGridView;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * @author
 * @description 针对表【sys_login_info(系统访问记录)】的数据库操作Service实现
 */
@Service
public class LoginInfoServiceImpl extends ServiceImpl<LoginInfoMapper, LoginInfo> implements LoginInfoService {

    @Autowired
    private LoginInfoMapper loginInfoMapper;

    @Override
    public void insertLoginInfo(LoginInfo loginInfo) {
        loginInfoMapper.insert(loginInfo);
    }

    @Override
    public DataGridView listForPage(LoginInfoDto loginInfoDto) {
        Page<LoginInfo> page = new Page<>(loginInfoDto.getPageNum(), loginInfoDto.getPageSize());
        QueryWrapper<LoginInfo> wrapper = new QueryWrapper<>();
        wrapper.like(StringUtils.isNotBlank(loginInfoDto.getUserName()),
                LoginInfo.COL_USER_NAME, loginInfoDto.getUserName());
        wrapper.like(StringUtils.isNotBlank(loginInfoDto.getIpAddr()),
                LoginInfo.COL_IP_ADDR, loginInfoDto.getIpAddr());
        wrapper.like(StringUtils.isNotBlank(loginInfoDto.getLoginAccount()),
                LoginInfo.COL_LOGIN_ACCOUNT, loginInfoDto.getLoginAccount());
        wrapper.eq(StringUtils.isNotBlank(loginInfoDto.getLoginStatus()),
                LoginInfo.COL_LOGIN_STATUS, loginInfoDto.getLoginStatus());
        wrapper.eq(StringUtils.isNotBlank(loginInfoDto.getLoginType()),
                LoginInfo.COL_LOGIN_TYPE, loginInfoDto.getLoginType());
        wrapper.ge(loginInfoDto.getBeginTime() != null, LoginInfo.COL_LOGIN_TIME,
                loginInfoDto.getBeginTime());
        wrapper.le(loginInfoDto.getEndTime() != null, LoginInfo.COL_LOGIN_TIME,
                loginInfoDto.getEndTime());
        wrapper.orderByDesc(LoginInfo.COL_LOGIN_TIME);

        wrapper.orderByDesc(LoginInfo.COL_LOGIN_TIME);
        loginInfoMapper.selectPage(page, wrapper);
        return new DataGridView(page.getTotal(), page.getRecords());

    }

    @Override
    public int deleteLoginInfoByIds(Long[] infoIds) {
        List<Long> ids = Arrays.asList(infoIds);
        if (ids.size() > 0) {
            return this.loginInfoMapper.deleteBatchIds(ids);
        } else {
            return 0;
        }
    }

    @Override
    public int clearLoginInfo() {
        return loginInfoMapper.delete(null);
    }
}




