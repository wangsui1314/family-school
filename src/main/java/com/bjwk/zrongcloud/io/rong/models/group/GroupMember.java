package com.bjwk.zrongcloud.io.rong.models.group;

import com.bjwk.zrongcloud.io.rong.models.chatroom.ChatroomMember;
import com.bjwk.zrongcloud.io.rong.util.GsonUtil;

/**
 * @author RongCloud
 */
public class GroupMember {
    /**
     * 群组成员Id。
     * */
    public String id;
    /**
     * 群组ID
     * */
    public String groupId;
    /**
     * 禁言时间
     * */
    public Integer munite;

    public GroupMember() {
        super();
    }

    public GroupMember(String id, String groupId, Integer munite) {
        this.id = id;
        this.groupId = groupId;
        this.munite = munite;
    }

    /**
     * 设置id
     *
     */
    public GroupMember setId(String id) {
        this.id = id;
        return this;
    }

    /**
     * 获取id
     *
     * @return String
     */
    public String getId() {
        return id;
    }

    /**
     * 获取groupId
     *
     * @return String
     */
    public String getGroupId() {
        return this.groupId;
    }
    /**
     * 设置群组id
     *
     * @return String
     */
    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    /**
     * 获取禁言时长
     *
     * @return String
     */
    public Integer getMunite() {
        return this.munite;
    }
    /**
     * 设置munite
     *
     * @return String
     */
    public void setMunite(Integer munite) {
        this.munite = munite;
    }
    @Override
    public String toString() {
        return GsonUtil.toJson(this, ChatroomMember.class);
    }

}
