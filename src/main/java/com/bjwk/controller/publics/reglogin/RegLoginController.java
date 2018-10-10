package com.bjwk.controller.publics.reglogin;


import com.bjwk.utils.annotation.AdminTokenValidate;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bjwk.model.pojo.Users;
import com.bjwk.service.publics.reglogin.RegLoginService;
import com.bjwk.utils.DataWrapper;
import com.bjwk.utils.annotation.MyLog;
import com.bjwk.utils.annotation.TokenValidate;

import java.util.Random;


/**
 * @author liqitian
 * @version 1.0
 * @describe 用户登录注册（教师，学生，家长，管理员）
 * @date 2018年2月27日 下午8:46:29
 * @TokenValidate 自定义注解  切面验证token是否通过
 * @AdminTokenValidate 自定义注解 验证管理员操作权限（简略）
 * @MyLog 自定义注解 统一日志处理
 */
@Controller
@RequestMapping("api/regLogin")
public class RegLoginController {


    @Autowired

    private RegLoginService regLoginService;

    /**
     * @param
     * @return
     * @describe 用户注册
     */
    @RequestMapping(value = "_reg")
    @ResponseBody
    @MyLog
    public DataWrapper<Users> insertReg(
            @RequestParam(value = "userName", required = true) String userName,
            @RequestParam(value = "passWord", required = true) String passWord,
            @RequestParam(value = "sign", required = true) String sign,
            @RequestParam(value = "phone", required = true) String phone,
            @RequestParam(value = "code", required = true) String code
    ) {
        Users user = new Users();
        user.setUserName(userName);
        user.setPassWord(passWord);
        user.setSign(sign);
        user.setPhone(phone);
        if (sign.equals("0")) {
           user.setStudentId(getRandom(11));
        }

        return regLoginService.insertReg(user, code);
    }

    /**
     * @param
     * @return
     * @describe 账号密码用户登录
     */
    @RequestMapping(value = "_login")
    @ResponseBody
    @MyLog(description = "用户账号密码登录")
    public DataWrapper<Users> login(
            @RequestParam(value = "userName", required = true) String userName,
            @RequestParam(value = "passWord", required = true) String passWord,
            @RequestParam(value = "sign", required = false) String sign
    ) {
        return regLoginService.login(userName, passWord, sign);
    }

    /**
     * @param sign
     * @param phone
     * @param code
     * @return Users
     */
    @RequestMapping(value = "_phoneVcodeLogin")
    @ResponseBody
    @MyLog
    public DataWrapper<Users> phoneVcodeLogin(
            @RequestParam(value = "sign", required = true) Integer sign,
            @RequestParam(value = "phone", required = true) String phone,
            @RequestParam(value = "code", required = true) String code
    ) {
        return regLoginService.phoneVcodeLogin(phone, code, sign);
    }

    /**
     * @param
     * @return
     * @describe 手势密码用户验证
     */
    @RequestMapping(value = "_gestureLogin")
    @ResponseBody
    @MyLog
    public DataWrapper<Users> gestureLogin(
            @RequestParam(value = "token", required = true) String token,
            @RequestParam(value = "gesturePassWord", required = true) String gesturePassWord
    ) {

        return regLoginService.gestureLogin(token, gesturePassWord);
    }

    /**
     * @param
     * @return
     * @describe 用户登出
     */
    @RequestMapping(value = "_logout")
    @ResponseBody
    @MyLog
    public DataWrapper<Void> logout(
            @RequestParam(value = "token", required = true) String token
    ) {
        return regLoginService.logout(token);
    }

    /**
     * 用户更改个人信息..
     *
     * @param headPortrait
     * @param sex
     * @param
     * @param background
     * @param styleSignTure
     * @return
     */
    @RequestMapping(value = "_changeUserInfo")
    @ResponseBody
    @TokenValidate
    @MyLog
    public DataWrapper<Void> changeUserInfo(
            @RequestParam(value = "token", required = false) String token,
            @RequestParam(value = "headPortrait", required = false) String headPortrait,
            @RequestParam(value = "sex", required = false) String sex,
            @RequestParam(value = "nickName", required = false) String nickName,
            @RequestParam(value = "lableId", required = false) String lableId,//入参格式  1,2,3,4,5,7,12
            @RequestParam(value = "background", required = false) String background,
            @RequestParam(value = "styleSignTure", required = false) String styleSignTure
    ) {
        System.out.println(styleSignTure);//Connector标签增加useBodyEncodingForURI="true"
        return regLoginService.changeUserInfo(token, headPortrait, sex, lableId, background, styleSignTure
                , nickName);
    }

    /**
     * @Description: 主要获取更改密码凭证
     * @Param: [sign, phone, code]
     * @return: com.bjwk.utils.DataWrapper<java.lang.String>
     * @Author: liqitian
     * @Date: 2018/6/14
     */
    @RequestMapping(value = "_userUpdateToPassWordCheck")
    @ResponseBody
    @MyLog
    public DataWrapper<String> _userUpdateToPassWordCheck(
            @RequestParam(value = "sign", required = false) Integer sign,
            @RequestParam(value = "phone", required = false) String phone,
            @RequestParam(value = "code", required = false) String code
    ) {
        return regLoginService.userUpdateToPassWordCheck(sign, phone, code);
    }


    /**
     * @Description:开始更改密码
     * @Param: [passWdVoucher]
     * @return: com.bjwk.utils.DataWrapper<java.lang.Void>
     * @Author: liqitian
     * @Date: 2018/6/14
     */
    @RequestMapping(value = "_userUpdateToPassWord")
    @ResponseBody
    @MyLog
    public DataWrapper<Void> _userUpdateToPassWord(
            @RequestParam(value = "passWdVoucher", required = true) String passWdVoucher,
            @RequestParam(value = "newPassWd", required = true) String newPassWd
    ) {
        return regLoginService.userUpdateToPassWord(passWdVoucher, newPassWd);
    }

    /**
     * @param
     * @return
     * @describe 管理员修改用户
     */
    @RequestMapping(value = "_updateUser")
    @ResponseBody
    @MyLog
    public DataWrapper<Void> _updateUser(
//			@RequestParam(value="token",required=true)String token
    ) {
        int i = 1;
        int x = 2;
        i = x / 0;
        return null;
    }
//	@RequestMapping("_test")
//	@ResponseBody
//	public DataWrapper<Void> test(
//			@RequestParam(value="token",required=false)String token
//			){
//		RedisUtil redisUtil= BeanUtil.getBean("redisUtil");
//    	RedisTemplate<String, Object> redisTemplate=redisUtil.getRedisTemplate();
//		DataWrapper<Void>  dataWrapper=new DataWrapper<Void>();
//		Jedis jedis=RedisClient.getInstance().getJedis();
//		if(jedis.hget("loginStatus", token)==null) {
//			dataWrapper.setMsg("请重新登录");
//			return dataWrapper;
//		}
//		dataWrapper.setMsg("1234");
//		return  dataWrapper;
//	}

    /**
     * @param
     * @return
     * @describe 用户个人详情
     */
    @RequestMapping(value = "_queryUserInfoDetails")
    @ResponseBody
    @MyLog
    public DataWrapper<Users> queryUserInfoDetails(
            @RequestParam(value = "token", required = false) String token,
            @RequestParam(value = "sign", required = false) Integer sign
    ) {
        return regLoginService.queryUserInfoDetails(token, sign);
    }


    @MyLog(description = "测试的AAAAAAAAAAAAAA")
    @RequestMapping(value = "/UserDemo")
    public @ResponseBody
    Object UserDemo() {
        return null;
    }


    public  String getRandom(int count){
        StringBuffer sb = new StringBuffer();
        String str = "0123456789";
        Random r = new Random();
        for(int i=0;i<count;i++){
            int num = r.nextInt(str.length());
            sb.append(str.charAt(num));
            str = str.replace((str.charAt(num)+""), "");
        }
        return sb.toString();
    }

}