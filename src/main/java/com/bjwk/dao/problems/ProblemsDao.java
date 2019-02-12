package com.bjwk.dao.problems;

import org.apache.ibatis.annotations.Param;
import java.util.List;
import com.bjwk.model.problems.Problems;

public interface ProblemsDao {

    int insert(@Param("pojo") Problems pojo);

    int insertList(@Param("pojos") List< Problems> pojo);

    List<Problems> select(@Param("pojo") Problems pojo);

    int update(@Param("pojo") Problems pojo);

}
