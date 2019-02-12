package com.bjwk.service.impl.student.problems;

import com.bjwk.service.student.problems.ProblemsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import com.bjwk.model.problems.Problems;
import com.bjwk.dao.problems.ProblemsDao;

@Service
public class ProblemsServiceImpl implements ProblemsService {

    @Autowired
    private ProblemsDao problemsDao;


    @Override
    public int insert(Problems pojo){
        return problemsDao.insert(pojo);
    }

    @Override
    public int insertList(List< Problems> pojos){
        return problemsDao.insertList(pojos);
    }

    @Override
    public List<Problems> select(Problems pojo){
        return problemsDao.select(pojo);
    }

    @Override
    public int update(Problems pojo){
        return problemsDao.update(pojo);
    }

}
