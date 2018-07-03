package com.bjwk.zrongcloud.io.rong.methods.chatroom.whitelist;

import com.bjwk.zrongcloud.io.rong.RongCloud;
import com.bjwk.zrongcloud.io.rong.exception.ParamException;
import com.bjwk.zrongcloud.io.rong.models.CheckMethod;
import com.bjwk.zrongcloud.io.rong.models.CommonConstrants;
import com.bjwk.zrongcloud.io.rong.models.response.ChatroomWhitelistMsgResult;
import com.bjwk.zrongcloud.io.rong.models.response.ResponseResult;
import com.bjwk.zrongcloud.io.rong.util.CommonUtil;
import com.bjwk.zrongcloud.io.rong.util.GsonUtil;
import com.bjwk.zrongcloud.io.rong.util.HttpUtil;

import java.net.HttpURLConnection;
import java.net.URLEncoder;
/**
 *
 * 聊天室用户白名单服务
 * docs: "http://www.rongcloud.cn/docs/server.html#chatroom_whitelist"
 *
 * */
public class Messages {
    private static final String UTF8 = "UTF-8";
    private static final String PATH = "chatroom/whitelist/message";
    private String appKey;
    private String appSecret;
    private RongCloud rongCloud;

    public RongCloud getRongCloud() {
        return rongCloud;
    }
    public void setRongCloud(RongCloud rongCloud) {
        this.rongCloud = rongCloud;
    }
    public Messages(String appKey, String appSecret) {
        this.appKey = appKey;
        this.appSecret = appSecret;
    }
    /**
     * 添加聊天室白名单成员方法
     *
     * @param  objectNames:消息类型列表
     *
     * @return ResponseResult
     **/
    public ResponseResult add(String[] objectNames) throws Exception {
        if (objectNames == null) {
            throw new ParamException(CommonConstrants.RCLOUD_PARAM_NULL,"/chatroom/message/whitelist/add",
                    "Paramer 'objectNames' is required");
        }

        String errMsg = CommonUtil.checkParam("objectNames",objectNames,PATH, CheckMethod.ADD);
        if(null != errMsg){
            return (ResponseResult)GsonUtil.fromJson(errMsg,ResponseResult.class);
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0 ; i< objectNames.length; i++) {
            String child  = objectNames[i];
            sb.append("&objectnames=").append(URLEncoder.encode(child, UTF8));
        }

        String body = sb.toString();
        if (body.indexOf("&") == 0) {
            body = body.substring(1, body.length());
        }

        HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(rongCloud.getApiHostType(), appKey, appSecret, "/chatroom/whitelist/add.json", "application/x-www-form-urlencoded");
        HttpUtil.setBodyParameter(body, conn);

        return (ResponseResult) GsonUtil.fromJson(CommonUtil.getResponseByCode(PATH,CheckMethod.ADD,HttpUtil.returnResult(conn)), ResponseResult.class);
    }

    /**
     * 添加聊天室白名单成员方法
     *
     * @param  objectNames:消息类型列表
     *
     * @return ResponseResult
     **/
    public ResponseResult remove(String[] objectNames) throws Exception {
        if (objectNames == null) {
            throw new ParamException(CommonConstrants.RCLOUD_PARAM_NULL,"/chatroom/message/whitelist/remove",
                    "Paramer 'objectNames' is required");
        }
        String errMsg = CommonUtil.checkParam("objectNames",objectNames,PATH, CheckMethod.REMOVE);
        if(null != errMsg){
            return (ResponseResult)GsonUtil.fromJson(errMsg,ResponseResult.class);
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0 ; i< objectNames.length; i++) {
            String child  = objectNames[i];
            sb.append("&objectnames=").append(URLEncoder.encode(child, UTF8));
        }
        String body = sb.toString();
        if (body.indexOf("&") == 0) {
            body = body.substring(1, body.length());
        }
        HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(rongCloud.getApiHostType(), appKey, appSecret, "/chatroom/whitelist/delete", "application/x-www-form-urlencoded");
        HttpUtil.setBodyParameter(body, conn);

        return (ResponseResult) GsonUtil.fromJson(CommonUtil.getResponseByCode(PATH,CheckMethod.REMOVE,HttpUtil.returnResult(conn)), ResponseResult.class);
    }

    /**
     * 获取聊天室消息类型白名单列表
     *
     *
     * @return ResponseResult
     **/
    public ChatroomWhitelistMsgResult getList() throws Exception {

        HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(rongCloud.getApiHostType(), appKey, appSecret, "/chatroom/whitelist/query.json", "application/x-www-form-urlencoded");
        return (ChatroomWhitelistMsgResult) GsonUtil.fromJson(CommonUtil.getResponseByCode(PATH,CheckMethod.GETLIST,HttpUtil.returnResult(conn)), ChatroomWhitelistMsgResult.class);
    }
}

