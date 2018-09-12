package com.bjwk.service;

import com.bjwk.utils.DataWrapper;

import java.util.List;

public interface UserAttendServie {
    DataWrapper<Void> attend(String token,Integer attendType);

    DataWrapper<List<String>> queryAttendSituation(String token, Integer attendType,String month);
}
