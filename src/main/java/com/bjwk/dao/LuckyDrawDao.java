package com.bjwk.dao;

import java.util.List;
import java.util.Map;

import com.bjwk.model.JackpotVO;
import com.bjwk.model.pojo.ActivityPO;
import com.bjwk.model.pojo.JackpotPO;
import com.bjwk.model.req.JackpotReq;
import org.apache.ibatis.annotations.Param;

/** 
* @Description: 用户抽奖dao层
* @author  Desolation
* @email:1071680460@qq.com
* @date 创建时间：2018年4月2日 下午10:56:52 
* @version 1.0  
*/
public interface LuckyDrawDao {
	
	int insertPrizeInfo(JackpotReq jackpotReq);

	List<JackpotPO> selectPrizeInfo();

	int removeStockNum(@Param("id") Long id, @Param("removeNum")Integer removeNum);

	List<JackpotVO> queryJackpotDetailList();

    ActivityPO queryActivityDetail(Integer linkActivityId);

	int queryCheckUserIsJackOk(String userId);

	int subCoin(@Param("userId")String userId, @Param("aCoin")int aCoin);
}
