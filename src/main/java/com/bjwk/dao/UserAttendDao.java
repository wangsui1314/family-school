package com.bjwk.dao;

import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserAttendDao {
    Integer attend(@Param("userId") String userId, @Param("attendType")Integer attendType);

    List<String> queryAttendSituation(@Param("userId")String userId, @Param("attendType") Integer attendType, @Param("month")String month);

    Integer checkTodayIsAttend(@Param("userId")String userId,@Param("attendType")Integer attendType);
}
