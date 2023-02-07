package com.bee.openhis.controller.doctor;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import com.bee.openhis.domain.Scheduling;
import com.bee.openhis.domain.User;
import com.bee.openhis.dto.SchedulingDto;
import com.bee.openhis.dto.SchedulingFormDto;
import com.bee.openhis.dto.SchedulingQueryDto;
import com.bee.openhis.service.SchedulingService;
import com.bee.openhis.service.UserService;
import com.bee.openhis.utils.ShiroSecurityUtils;
import com.bee.openhis.vo.AjaxResult;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("doctor/scheduling")
public class SchedulingController {

    @Reference
    private SchedulingService schedulingService;

    @Autowired
    private UserService userService;

    /**
     * 查询参与排班的医生信息
     *
     * @param deptId
     * @return
     */
    @GetMapping("queryUsersNeedScheduling")
    public AjaxResult queryUsersNeedScheduling(Long deptId) {
        List<User> users = userService.queryUsersNeedScheduling(null, deptId);
        return AjaxResult.success(users);
    }

    /**
     * 查询当前用户的排班信息
     *
     * @param schedulingQueryDto
     * @return
     */
    @GetMapping("queryMyScheduling")
    public AjaxResult queryMyScheduling(SchedulingQueryDto schedulingQueryDto) {
        List<User> users = userService.queryUsersNeedScheduling(ShiroSecurityUtils.getCurrentActiveUser().getUser().getUserId(), schedulingQueryDto.getDeptId());
        return getSchedulingAjaxResult(schedulingQueryDto, users);
    }

    /**
     * 查询其它医生的排班信息
     *
     * @param schedulingQueryDto
     * @return
     */
    @GetMapping("queryScheduling")
    public AjaxResult queryScheduling(SchedulingQueryDto schedulingQueryDto) {
        List<User> users = userService.queryUsersNeedScheduling(schedulingQueryDto.getUserId(), schedulingQueryDto.getDeptId());
        return getSchedulingAjaxResult(schedulingQueryDto, users);
    }


    /**
     * 保存排班数据
     *
     * @param schedulingFormDto
     * @return
     */
    @PostMapping("saveScheduling")
    public AjaxResult saveScheduling(@RequestBody SchedulingFormDto schedulingFormDto) {
        schedulingFormDto.setSimpleUser(ShiroSecurityUtils.getCurrentSimpleUser());
        return AjaxResult.toAjax(schedulingService.saveScheduling(schedulingFormDto));
    }


    private AjaxResult getSchedulingAjaxResult(SchedulingQueryDto schedulingQueryDto, List<User> users) {
        //取得当前时间
        DateTime date = DateUtil.date();
        if (StringUtils.isNoneBlank(schedulingQueryDto.getQueryDate())) {
            date = DateUtil.parse(schedulingQueryDto.getQueryDate(), "yyyy-MM-dd");
            //根据页面传来的日期进行判断是周几
            int i = DateUtil.dayOfWeek(date); //1-周日 可以得到 下一周   2-周一 可以得到上一周
            if (i == 1) {
                date = DateUtil.offsetDay(date, 1);     //下一周    周一的日期
            } else {
                date = DateUtil.offsetDay(date, -1);    //上一周    的周日日期
            }
        }

        //计算一周的开始日期和结束日期
        DateTime beginTime = DateUtil.beginOfWeek(date);
        DateTime endTime = DateUtil.endOfWeek(date);
        //设置开始日期和结束日期到SchedulingQueryDto
        schedulingQueryDto.setBeginDate(DateUtil.format(beginTime, "yyyy-MM-dd"));
        schedulingQueryDto.setEndDate(DateUtil.format(endTime, "yyyy-MM-dd"));
        //根据开始日期和结束日期查询his_scheduling的值班数据
        List<Scheduling> list = schedulingService.queryScheduling(schedulingQueryDto);
        ArrayList<SchedulingDto> schedulingDtos = new ArrayList<>();

        for (User user : users) {
            SchedulingDto schedulingDto1 = new SchedulingDto(user.getUserId(), user.getDeptId(), "1", initMap(beginTime));
            SchedulingDto schedulingDto2 = new SchedulingDto(user.getUserId(), user.getDeptId(), "2", initMap(beginTime));
            SchedulingDto schedulingDto3 = new SchedulingDto(user.getUserId(), user.getDeptId(), "3", initMap(beginTime));
            //一个用户都有这三条数据
            schedulingDtos.add(schedulingDto1);
            schedulingDtos.add(schedulingDto2);
            schedulingDtos.add(schedulingDto3);
            for (Scheduling scheduling : list) {
                Long userId = scheduling.getUserId();   //获取表里用户id
                String subsectionType = scheduling.getSubsectionType(); //值班的时间段 早中晚
                String schedulingDay = scheduling.getSchedulingDay();   //值班日期
                if (user.getUserId().equals(userId)) {
                    switch (subsectionType) {
                        case "1":
                            Map<String, String> record1 = schedulingDto1.getRecord();
                            record1.put(schedulingDay, scheduling.getSchedulingType());
                            break;
                        case "2":
                            Map<String, String> record2 = schedulingDto2.getRecord();
                            record2.put(schedulingDay, scheduling.getSchedulingType());
                            break;
                        case "3":
                            Map<String, String> record3 = schedulingDto3.getRecord();
                            record3.put(schedulingDay, scheduling.getSchedulingType());
                            break;
                    }
                }
            }
            //把map转成数组数据放到SchedulingType  一周的数据
            schedulingDto1.setSchedulingType(schedulingDto1.getRecord().values()); //一周早上
            schedulingDto2.setSchedulingType(schedulingDto2.getRecord().values()); //一周中午
            schedulingDto3.setSchedulingType(schedulingDto3.getRecord().values());//一周晚上
        }
        //组装返回的对象
        Map<String, Object> resMap = new HashMap<>();
        resMap.put("tableData", schedulingDtos);
        HashMap<String, Object> schedulingData = new HashMap<>();
        schedulingData.put("startTimeThisWeek", schedulingQueryDto.getBeginDate());
        schedulingData.put("endTimeThisWeek", schedulingQueryDto.getEndDate());
        resMap.put("schedulingData", schedulingData);
        resMap.put("labelNames", initLabel(beginTime));
        return AjaxResult.success(resMap);
    }

    /**
     * 初始化labelNames
     *
     * @param beginTime
     * @return
     */
    private Object initLabel(DateTime beginTime) {
        String[] labelNames = new String[7];
        for (int i = 0; i < 7; i++) {
            DateTime d = DateUtil.offsetDay(beginTime, i);
            labelNames[i] = DateUtil.format(d, "yyyy-MM-dd") + formatterWeek(i);
        }
        return labelNames;
    }

    /**
     * 翻译周
     *
     * @param i
     * @return
     */
    private String formatterWeek(int i) {
        switch (i) {
            case 0:
                return "(周一)";
            case 1:
                return "(周二)";
            case 2:
                return "(周三)";
            case 3:
                return "(周四)";
            case 4:
                return "(周五)";
            case 5:
                return "(周六)";
            default:
                return "(周日)";
        }
    }

    private Map<String, String> initMap(DateTime beginTime) {
        Map<String, String> map = new TreeMap<>();
        for (int i = 0; i < 7; i++) {
            DateTime d = DateUtil.offsetDay(beginTime, i);
            String key = DateUtil.format(d, "yyyy-MM-dd");
            map.put(key, "");
        }
        return map;
    }
}
