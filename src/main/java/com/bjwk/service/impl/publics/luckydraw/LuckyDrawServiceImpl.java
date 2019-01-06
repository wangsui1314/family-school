package com.bjwk.service.impl.publics.luckydraw;

import com.bjwk.dao.LuckyDrawDao;
import com.bjwk.model.ActivityVO;
import com.bjwk.model.JackpotVO;
import com.bjwk.model.pojo.ActivityPO;
import com.bjwk.model.pojo.JackpotPO;
import com.bjwk.model.req.JackpotReq;
import com.bjwk.service.publics.reglogin.RegLoginService;
import com.bjwk.utils.CallStatusEnum;
import com.bjwk.utils.DataWrapper;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.bjwk.service.publics.luckydraw.LuckyDrawService;

import java.math.BigDecimal;
import java.util.*;

/**
 * @author Desolation
 * @version 1.0
 * @Description: 用户抽奖实现类
 * @email:1071680460@qq.com
 * @date 创建时间：2018年3月27日 下午11:51:00
 */
@Service
@Slf4j
public class LuckyDrawServiceImpl implements LuckyDrawService {

    @Autowired
    private LuckyDrawDao luckyDrawDao;
    @Autowired
    private RegLoginService regLoginService;
    // 放大倍数
    private static final int mulriple = 100000;


    @Override
    public DataWrapper<Void> setLuckyDrawInfo(JackpotReq jackpotReq) {
        DataWrapper<Void> dataWrapper = new DataWrapper<Void>();
        luckyDrawDao.insertPrizeInfo(jackpotReq);
        dataWrapper.setMsg("奖品新增成功");
        return dataWrapper;
    }


    @Override
    public <T> T luckDraw(String token) {
        DataWrapper<JackpotPO> dataWrapper = new DataWrapper<JackpotPO>();
        String userId = regLoginService.getUserIdByToken(token);

        /**
         * 检查用户  积分是否大于等于1000 具有抽奖资格
         */
        int coin = luckyDrawDao.queryCheckUserIsJackOk(userId);


        if (coin < 1000){
            dataWrapper.setCallStatus(CallStatusEnum.FAILED);
            dataWrapper.setMsg("积分小于1000不具有抽奖资格");
            return (T) dataWrapper;
        }

        List<JackpotPO> jackpotPOList = luckyDrawDao.selectPrizeInfo();

        Long id = startLuckDraw(jackpotPOList);

        if (id.intValue() == 0) {
            //未中奖
            dataWrapper.setCallStatus(CallStatusEnum.FAILED);
            dataWrapper.setData(noWinning());
            dataWrapper.setMsg("很遗憾，就差那么一点点");

            /**
             * 扣除积分
             */
            luckyDrawDao.subCoin(userId,20);

            return (T) dataWrapper;
        } else {
            for (JackpotPO jackpotPO : jackpotPOList) {
                if (jackpotPO.getId().equals(id)) {
                    /**
                     * 记录中奖信息
                     */
                    jackpotPO.setGetProbabi(null);
                    dataWrapper.setData(jackpotPO);
                    luckyDrawDao.subCoin(userId,40);
                    return (T) dataWrapper;
                }
            }
        }
        luckyDrawDao.subCoin(userId,40);
        return (T) dataWrapper;
    }

    @Override
    public <T> T removeStockNum(Long id, Integer removeNum) {
        DataWrapper<Void> dataWrapper = new DataWrapper<Void>();
        if (luckyDrawDao.removeStockNum(id, removeNum) == 0) {
            dataWrapper.setMsg("删除失败");
            dataWrapper.setCallStatus(CallStatusEnum.FAILED);
        }
        return (T) dataWrapper;
    }

    @Override
    public <T> T queryJackpotDetailList(Integer linkActivityId) {
        DataWrapper<Object> dataWrapper = new DataWrapper<Object>();

        /**
         * 查询活动信息
         */
        ActivityPO activityPO = luckyDrawDao.queryActivityDetail(linkActivityId);
        String  gsonContent = activityPO.getContentJson();
        Map<String,Object> jsonMap = new Gson().fromJson(gsonContent,HashMap.class);
        ActivityVO activityVO = new ActivityVO();
        activityVO.setActivityName(activityPO.getActivityName());
        activityVO.setContentJson(jsonMap);
        activityVO.setId(activityPO.getId());
        Map<String,Object> ma= new HashMap<String, Object>(2);
        ma.put("activityRules",activityVO);

        /**
         * 奖品
         */
        List<JackpotVO> jackpotVOList = luckyDrawDao.queryJackpotDetailList();
        ma.put("activityGoodDetails",jackpotVOList);
        dataWrapper.setData(ma);
        return (T) dataWrapper;
    }

    public Long startLuckDraw(List<JackpotPO> jackpotPOList) {
        int lastScope = 0;
        // 洗牌，打乱奖品次序
        Collections.shuffle(jackpotPOList);
        Map<Long, int[]> prizeScopes = new HashMap<Long, int[]>();
        Map<Long, Integer> prizeQuantity = new HashMap<Long, Integer>();
        for (JackpotPO jackpotPO : jackpotPOList) {
            Long prizeId = jackpotPO.getId();
            // 划分区间
            int currentScope = lastScope + jackpotPO.getGetProbabi().multiply(new BigDecimal(mulriple)).intValue();
            prizeScopes.put(prizeId, new int[]{lastScope + 1, currentScope});
            prizeQuantity.put(prizeId, jackpotPO.getStockNum());

            lastScope = currentScope;
        }
        // 获取1-1000000之间的一个随机数
        int luckyNumber = new Random().nextInt(mulriple);
        Long luckyPrizeId = 0L;
        // 查找随机数所在的区间
        if ((null != prizeScopes) && !prizeScopes.isEmpty()) {
            Set<Map.Entry<Long, int[]>> entrySets = prizeScopes.entrySet();
            for (Map.Entry<Long, int[]> m : entrySets) {
                Long key = m.getKey();
                if (luckyNumber >= m.getValue()[0] && luckyNumber <= m.getValue()[1] && prizeQuantity.get(key) > 0) {
                    luckyPrizeId = key;
                    break;
                }
            }
        }
        if (luckyPrizeId > 0) {
            removeStockNum(luckyPrizeId, -1);
        }
        return luckyPrizeId;
    }

    public JackpotPO noWinning(){
        JackpotPO jackpotPO = new JackpotPO();
        jackpotPO.setPrizeName("很遗憾，就差那么一点点");
        jackpotPO.setPrizeImg("");
        return jackpotPO;
    }
}
