package com.bjwk.zrongcloud.io.rong.example.user;


import com.bjwk.zrongcloud.io.RongCloudKeyAndSecret;
import com.bjwk.zrongcloud.io.rong.RongCloud;
import com.bjwk.zrongcloud.io.rong.methods.user.User;
import com.bjwk.zrongcloud.io.rong.models.Result;
import com.bjwk.zrongcloud.io.rong.models.response.TokenResult;
import com.bjwk.zrongcloud.io.rong.models.user.UserModel;

/**
 * Demo class
 *
 * @author hc
 * @date 2017/12/30
 */
public class UserExample {
    /**
     * 此处替换成您的appKey
     * */
    private static final String appKey = RongCloudKeyAndSecret.key;
    /**
     * 此处替换成您的appSecret
     * */
    private static final String appSecret = RongCloudKeyAndSecret.secret;
    /**
     * 自定义api地址
     * */
    private static final String api = "http://api.cn.ronghub.com";

    public static void main(String[] args) throws Exception {

        RongCloud rongCloud = RongCloud.getInstance(appKey, appSecret);
        //自定义 api 地址方式
        // RongCloud rongCloud = RongCloud.getInstance(appKey, appSecret,api);
        User User = rongCloud.user;

        /**
         * API 文档: http://rongcloud.github.io/server-sdk-nodejs/docs/v1/user/user.html#register
         *
         * 注册用户，生成用户在融云的唯一身份标识 Token
         */
        UserModel user = new UserModel()
                .setId("userxxd1")
                .setName("username")
                .setPortrait("http://www.rongcloud.cn/images/logo.png");
        TokenResult result = User.register(user);
        System.out.println("getToken:  " + result.toString());
        //JSONObject jsonObject=JSONObject.fromObject(result.toString());
        //System.out.println(jsonObject.get("result"));
        /**
         *
         * API 文档: http://rongcloud.github.io/server-sdk-nodejs/docs/v1/user/user.html#refresh
         *
         * 刷新用户信息方法
         */
        Result refreshResult = User.update(user);
        System.out.println("refresh:  " + refreshResult.toString());

    }
}
