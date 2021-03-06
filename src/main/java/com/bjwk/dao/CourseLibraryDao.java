package com.bjwk.dao;

import com.bjwk.model.CourseVideoBankCatalogDTO;
import com.bjwk.model.CourseVideoBankDetailVO;
import com.bjwk.model.CourseVideoBankVO;
import com.bjwk.model.pojo.UserCollection;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;
import java.util.Set;

public interface CourseLibraryDao {

    List<CourseVideoBankVO> queryVideoCourse(@Param("array") String[] categoryId, @Param("isCharge")Integer isCharge);

    List<UserCollection> queryVideoCollection(@Param("userId")String userId);

    Integer queryIsCollection(@Param("userId")String userId, @Param("thingId")Integer thingId, @Param("type")Integer type);

    int insertCollection(@Param("userId")String userId, @Param("thingId")Integer thingId, @Param("type")Integer type);

    int deleteCollection(@Param("count")Integer count);

    CourseVideoBankDetailVO queryVideoDetails(@Param("courseVideoBankId")Integer courseVideoBankId);

    List<CourseVideoBankCatalogDTO> queryVideoCourseCatalog(@Param("packageNum")String packageNum);

    Integer deleteVideoCourse(@Param("array") String[] courseVideoBankStrIds);


    List<CourseVideoBankVO> queryMyCourseList(String userId);

    List<HashMap<String,Object>> downLoadVideoCourseList(@Param("array")Set<String> courseBankIdSet);
}
