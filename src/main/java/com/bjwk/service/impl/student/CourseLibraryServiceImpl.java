package com.bjwk.service.impl.student;

import com.bjwk.dao.AppConfigDao;
import com.bjwk.dao.CourseLibraryDao;
import com.bjwk.service.student.CourseLibraryService;
import com.bjwk.utils.DataWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @program: family-school
 * @description: 课程库业务逻辑处理
 * @author: liqitian.
 * @create: 2018-09-08 17:15
 **/
@Service
@Slf4j
public class CourseLibraryServiceImpl implements CourseLibraryService {

    @Autowired
    private CourseLibraryDao courseLibraryDao;

    @Autowired
    private AppConfigDao appConfigDao;

    @Override
    public DataWrapper<Object> getCourseClassType() {
        DataWrapper<Object> dataWrapper=new DataWrapper<Object>();
        log.info("查询课程分类信息 [入参]:{","}");
        String strConfig =appConfigDao.getCourseClassType();
        dataWrapper.setData(strConfig);
        log.info("查询课程分类信息 [出参]:{",strConfig+"}");
        return dataWrapper;
    }
}
