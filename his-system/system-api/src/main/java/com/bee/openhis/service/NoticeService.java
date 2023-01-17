package com.bee.openhis.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bee.openhis.domain.Notice;
import com.bee.openhis.dto.NoticeDto;
import com.bee.openhis.vo.DataGridView;

/**
* @author 19235
* @description 针对表【sys_notice(通知公告表)】的数据库操作Service
* @createDate 2023-01-17 21:32:36
*/
public interface NoticeService extends IService<Notice> {

    DataGridView listNoticeForPage(NoticeDto noticeDto);

    int addNotice(NoticeDto noticeDto);

    int updateNotice(NoticeDto noticeDto);

    int deleteNoticeByIds(Long[] noticeIds);

    Notice getOne(Long noticeId);
}
