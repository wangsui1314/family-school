package com.bjwk.service.student;

import com.bjwk.model.CourseVideoBankVO;
import com.bjwk.utils.DataWrapper;
import com.github.pagehelper.PageInfo;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface CourseLibraryService {
    DataWrapper<Object> getCourseClassType();

    DataWrapper<PageInfo<CourseVideoBankVO>> queryVideoCourse(String categoryId, Integer isCharge,int currentPage, int numberPerPage, String token);

    DataWrapper<Object> queryVideoDetails(Integer courseVideoBankId, String token);

    DataWrapper<Void> deleteVideoCourse(String courseVideoBankIds);

    DataWrapper<Void> test(String testname, String testpassword);

    void downLoadVideoCourse(Integer courseVideoBankId, HttpServletResponse response);
}
