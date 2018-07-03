package com.bjwk.zrongcloud.io.rong.models.message;

import com.bjwk.zrongcloud.io.rong.messages.BaseMessage;
import com.bjwk.zrongcloud.io.rong.util.ParamNotNull;

public class RecallMessage {
    /**
     * 撤回消息体
     * 发送人id
     * */
    public String senderUserId;
    /**
     * 接收人id
     * */
    public String targetId;
    /**
     * 消息唯一标识 各端 SDK 发送消息成功后会返回 uId
     * */
    public String uId;
    /**
     * 消息的发送时间，各端 SDK 发送消息成功后会返回 sentTime
     * */
    public String sentTime;

    public RecallMessage() {
    }

    /**
     * @param senderUserId	String	消息发送人用户 Id。（必传）
     * @param conversationType	Int	会话类型，二人会话是 1 、讨论组会话是 2 、群组会话是 3 。（必传）
     * @param targetId	String	目标 Id，根据不同的 ConversationType，可能是用户 Id、讨论组 Id、群组 Id。（必传）
     * @param uId	String	消息唯一标识，可通过服务端实时消息路由获取，对应名称为 msgUID。（必传）
     * @param sentTime
     *
     * */
    public RecallMessage(String senderUserId, String conversationType, String targetId,
                         String uId, String sentTime) {
        this.senderUserId = senderUserId;
        this.targetId = targetId;
        this.uId = uId;
        this.sentTime = sentTime;
    }

    public String getSenderUserId() {
        return this.senderUserId;
    }

    public RecallMessage setSenderUserId(String senderUserId) {
        this.senderUserId = senderUserId;
        return this;
    }

    public String getTargetId() {
        return this.targetId;
    }

    public RecallMessage setTargetId(String targetId) {
        this.targetId = targetId;
        return this;
    }

    public String getuId() {
        return this.uId;
    }

    public RecallMessage setuId(String uId) {
        this.uId = uId;
        return this;
    }

    public String getSentTime() {
        return this.sentTime;
    }

    public RecallMessage setSentTime(String sentTime) {
        this.sentTime = sentTime;
        return this;
    }
}
