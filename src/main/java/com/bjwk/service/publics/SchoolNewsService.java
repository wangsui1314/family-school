package com.bjwk.service.publics;

import com.bjwk.utils.DataWrapper;

public interface SchoolNewsService {
    DataWrapper<Void> addSchoolNews(String newsTitle, String newsContext, String newsImage);

    DataWrapper<Void> updateSchoolNews(Integer id, String newsTitle, String newsContext, String newsImage);

    DataWrapper<Void> deleteSchoolNews(String id);

    DataWrapper<Object> querySchoolNews(String id, int currentPage, int numberPerPage);
}
