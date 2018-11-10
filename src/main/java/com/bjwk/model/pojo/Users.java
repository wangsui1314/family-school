package com.bjwk.model.pojo;


import java.util.Date;
import java.util.HashMap;
import java.util.List;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonFormat;


/**
 * 
 * @author liqitian
 * @version 1.0
 * @describe 
 * 2018年3月22日 下午4:15:47
 */
@Data
public class Users {
    
	private Integer userId;
	@NotBlank(message = "用户名不能为空")
	private String userName; 
	@NotBlank(message = "用户密码不能为空")
	private  String passWord; 
	private String phone;
	private String email;
	@NotBlank(message = "用户身份不能为空或空串")
	private String sign;  //身份标识  0 学生 1家长，2老师
	private  int coin;//优币
	private String headPortrait;//头像
	private Integer sex;//性别
	private String nickName;//用户昵称
	private String background;//背景墙
	private  String styleSignTure;//个性签名
	private  String gesturePassWord;//手势密码
	private  int gestrueIsOk; //是否启用手势密码
	private Date created; //创建时间
	private String className;
	private String schoolName;
	private String headMaster;
	private Date updateTime; //修改个人资料时间
	private String studentId;
	private List<HashMap<String,Object>> labelMap;
}
