package com.bjwk.controller.publics.luckydraw;

import com.bjwk.model.req.JackpotReq;
import com.bjwk.utils.annotation.MyLog;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.alibaba.fastjson.JSONObject;
import com.bjwk.service.publics.luckydraw.LuckyDrawService;


/** 
* @Description: 用户抽奖
* @author  Desolation
* @email:1071680460@qq.com
* @date 创建时间：2018年3月27日 下午11:39:55 
* @version 1.0  
*/

@Controller
@RequestMapping("api/luckyDraw")
public class LuckyDrawController {
	
	private static final Log _logger = LogFactory.getLog(LuckyDrawController.class);
	@Autowired
	private LuckyDrawService luckyDrawService;

	/**
	 * @Description "设置奖品"
	 * @Date 2018/12/13 13:20
	 * @Param [jackpotReq]
	 * @return T
	 */
	@PostMapping("setLuckyDrawInfo")
	@ResponseBody
	@MyLog
	public <T> T setLuckyDrawInfo(
		//	@RequestParam("adminToken") String adminToken,
			JackpotReq jackpotReq
	){
		return  luckyDrawService.setLuckyDrawInfo(jackpotReq);
	}

	/**
	 * @Description "抽奖入口"
	 * @Date 2018/12/13 13:33
	 * @Param [token]
	 * @return T
	 */
	@PostMapping("luckDraw")
	@ResponseBody
	@MyLog
	public <T> T luckDraw(
				@RequestParam("token") String token
	){
		return  luckyDrawService.luckDraw(token);
	}


	/**
	 * @Description "删除奖池中特定商品的库存"
	 * @Date 2018/12/13 13:46
	 * @Param [id, removeNum]  removeNum：删除个数
	 * @return com.alibaba.fastjson.JSONObject
	 */
	@RequestMapping("removeStockNum")
	@ResponseBody
	public <T> T removeStockNum(
			@RequestParam("id") Long id,
			@RequestParam(value = "removeNum",required = false,defaultValue = "1") Integer removeNum
	){
		return luckyDrawService.removeStockNum(id,removeNum);
	}


	/**
	 * @Description "查看奖池详细信息"
	 * @Date 2018/12/13 14:01
	 * @Param [adminToken]
	 * @return T
	 */
	@RequestMapping("queryJackpotDetailList")
	@ResponseBody
	public <T> T queryJackpotDetailList(
			//@RequestParam("adminToken") String adminToken
	){
		return luckyDrawService.queryJackpotDetailList();
	}

}
