package com.bjwk.service.publics.reglogin;

import com.bjwk.model.pojo.Users;
import com.bjwk.utils.DataWrapper;

public interface RegLoginService {

	DataWrapper<Users> insertReg(Users user,String code);

	DataWrapper<Users> login(String userName, String passWord,String sign);

	DataWrapper<Void> logout(String token);

	DataWrapper<Users> gestureLogin(String gesturePassWord, String gesturePassWord2);

	DataWrapper<Users> phoneVcodeLogin(String phone, String code, Integer sign);

	String getUserIdByToken(String token);

	DataWrapper<String> userUpdateToPassWordCheck(Integer sign, String phone, String code);

	DataWrapper<Void> changeUserInfo(String token, String headPortrait, String sex, String professionId,
									 String background, String styleSignTure, String nickName, String className, String schoolName, String headMaster);

	DataWrapper<Users> queryUserInfoDetails(String token,Integer sign);

	DataWrapper<Void> userUpdateToPassWord(String passWdVoucher,String newPassWd);

    <T> T queryUserCoins(String token);

	<T> T addOrSubMyCoin(String adminToken,String userId,Integer num,String channelName);
}
