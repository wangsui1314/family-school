package com.bjwk.dao;

import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface SowingMapDao {

    int insertSowingMap(@Param("imageUrl") String imageUrl,@Param("type") Integer type, @Param("isEnable")Integer isEnable);

    List<Map<String,Object>> querySowingMapList(@Param("type")Integer type);

    List<HashMap<String,Object>> querySowingMapListToBackstage(@Param("type")Integer type,@Param("isEnable")Integer isEnable);

    int updatEnableOper(@Param("id")Integer id, @Param("isEnable")Integer isEnable);

}
