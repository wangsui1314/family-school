package com.bjwk.service.impl.publics;

import com.bjwk.dao.UserAttendDao;
import com.bjwk.service.UserAttendServie;
import com.bjwk.service.publics.reglogin.RegLoginService;
import com.bjwk.utils.CallStatusEnum;
import com.bjwk.utils.DataWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: family-school
 * @description: 用户签到相关接口
 * @author: liqitian.
 * @create: 2018-09-12 20:38
 **/
@Service
@Slf4j
public class UserAttendServieImpl implements UserAttendServie {

    @Autowired
    private UserAttendDao userAttendDao;
    @Autowired
    private RegLoginService regLoginService;

    /**
     * @Description:用户签到
     * @Param: [token]
     * @return: com.bjwk.utils.DataWrapper<java.lang.Void>
     * @Author: liqitian
     * @Date: 2018/9/12
     */
    @Override
    public DataWrapper<Void> attend(String token, Integer attendType) {
        DataWrapper<Void> dataWrapper = new DataWrapper<Void>();
        String userId = regLoginService.getUserIdByToken(token);
        /**
         * 检查当天  该用户 是否已经对该类型的签到
         */
        Integer id = userAttendDao.checkTodayIsAttend(userId, attendType);
        if (id != null) {
            dataWrapper.setCallStatus(CallStatusEnum.FAILED);
            dataWrapper.setMsg("该类型签到今天已经完成，无法再次签到");
            return dataWrapper;
        }
        userAttendDao.attend(userId, attendType);
        /**
         * 签到成功 后面的赠送逻辑
         */
        dataWrapper.setMsg("签到成功");
        return dataWrapper;
    }

    /**
     * @Description: 查看用户签到情况
     * @Param: [token, month]
     * @return: com.bjwk.utils.DataWrapper<java.lang.Void>
     * @Author: liqitian
     * @Date: 2018/9/12
     */
    @Override
    public DataWrapper<List<String>> queryAttendSituation(String token, Integer attendType,String month) {
        DataWrapper<List<String>> dataWrapper = new DataWrapper<List<String>>();
        String userId = regLoginService.getUserIdByToken(token);
        List<String> timeList = userAttendDao.queryAttendSituation(userId,attendType, month);
        dataWrapper.setData(timeList);
        return dataWrapper;
    }

}
