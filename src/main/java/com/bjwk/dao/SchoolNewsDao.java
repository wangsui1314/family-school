package com.bjwk.dao;

import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;

public interface SchoolNewsDao {
    Integer addSchoolNews(@Param("newsTitle") String newsTitle, @Param("newsContext")String newsContext,@Param("newsImage") String newsImage);

    Integer updateSchoolNews(@Param("id")Integer id, String newsTitle, @Param("newsContext")String newsContext,@Param("newsImage") String newsImage);

    Integer deleteSchoolNews(@Param("array") String[] idStrs);

    List<HashMap<String,Object>> querySchoolNewsList();

    HashMap<String,Object> querySchoolNewsDetail(String id);
}
