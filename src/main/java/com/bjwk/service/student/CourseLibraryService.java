package com.bjwk.service.student;

import com.bjwk.model.CourseVideoBankVO;
import com.bjwk.utils.DataWrapper;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface CourseLibraryService {
    DataWrapper<Object> getCourseClassType();

    DataWrapper<PageInfo<CourseVideoBankVO>> queryVideoCourse(String categoryId, Integer isCharge,int currentPage, int numberPerPage, String token);
}
