package com.bjwk.service.student;

import com.bjwk.model.CourseVideoBankVO;
import com.bjwk.utils.DataWrapper;
import com.github.pagehelper.PageInfo;

public interface CourseLibraryService {
    DataWrapper<Object> getCourseClassType();

    DataWrapper<PageInfo<CourseVideoBankVO>> queryVideoCourse(String categoryId, Integer isCharge,int currentPage, int numberPerPage, String token);

    DataWrapper<Object> queryVideoDetails(Integer courseVideoBankId, String token);

    DataWrapper<Void> deleteVideoCourse(String courseVideoBankIds);

    DataWrapper<Void> downLoadVideoCourse(String token, Integer courseVideoBankId, String courseDownLoadPath);

    DataWrapper<PageInfo<CourseVideoBankVO>> queryMyCourseList(String token,int currentPage,int numberPerPage);

    DataWrapper<Object> queryCourseByKeyword(String keyword, String scrollId);
}
