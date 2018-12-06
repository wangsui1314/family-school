package com.bjwk.service.student;

import com.bjwk.model.CourseVideoBankVO;
import com.bjwk.utils.DataWrapper;
import com.github.pagehelper.PageInfo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface CourseLibraryService {
    DataWrapper<Object> getCourseClassType();

    DataWrapper<PageInfo<CourseVideoBankVO>> queryVideoCourse(String categoryId, Integer isCharge,int currentPage, int numberPerPage, String token);

    DataWrapper<Object> queryVideoDetails(Integer courseVideoBankId, String token);

    DataWrapper<Void> deleteVideoCourse(String courseVideoBankIds);

    void downLoadVideoCourse(Integer courseVideoBankId, HttpServletResponse response, HttpServletRequest request);

    DataWrapper<PageInfo<CourseVideoBankVO>> queryMyCourseList(String token,int currentPage,int numberPerPage);

    DataWrapper<Object> queryCourseByKeyword(String keyword, String scrollId);
}
