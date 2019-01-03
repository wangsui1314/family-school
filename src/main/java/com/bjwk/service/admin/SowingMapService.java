package com.bjwk.service.admin;

import com.bjwk.utils.DataWrapper;
import com.github.pagehelper.PageInfo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @program: family-school
 * @description:
 * @author: liqitian.
 * @create: 2018-06-20 16:27
 **/

public interface SowingMapService {

    DataWrapper<Void> insertSowingMap(String imageUrl,Integer type,Integer isEnable);

    DataWrapper<List<Map<String,Object>>> querySowingMapList(Integer type);

    DataWrapper<PageInfo<HashMap<String,Object>>>  querySowingMapListToBackstage(Integer type, Integer isEnable);

    DataWrapper<Void> updatEnableOper(Integer id, Integer isEnable);

}
