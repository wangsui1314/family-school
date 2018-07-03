package com.bjwk.zrongcloud.io.rong.models.group;

/**
 * 群组数据模型
 *
 * @author Rongcloud
 * */
public class GroupModel {
    /**
     * 群组id
     **/
    private String id;
    /**
     * 群组成员
     **/
    private GroupMember[] members;
    /**
     * 群组名
     **/
    private String name;

    /**
     * 禁言时间
     * */
    private Integer munite;

    public GroupModel() {
    }
    /**
     * 构造方法
     *
     * @param id 群组id
     * @param members 群组成员
     * @param name 群名
     */
    public GroupModel(String id, GroupMember[] members, String name, Integer munite) {
        this.id = id;
        this.members = members;
        this.name = name;
        this.munite = munite;
    }

    public String getId() {
        return this.id;
    }

    public GroupModel setId(String id) {
        this.id = id;
        return this;
    }

    public GroupMember[] getMembers() {
        return this.members;
    }

    public GroupModel setMembers(GroupMember[] members) {
        this.members = members;
        return this;
    }

    public String getName() {
        return this.name;
    }

    public GroupModel setName(String name) {
        this.name = name;
        return this;
    }

    public Integer getMunite() {
        return this.munite;
    }

    public GroupModel setMunite(Integer munite) {
        this.munite = munite;
        return this;
    }
}
