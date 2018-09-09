package com.bjwk.model.pojo;

import lombok.Data;

import java.util.Date;

/**
 * @program: family-school
 * @description: 用户 收藏
 * @author: liqitian.
 * @create: 2018-09-09 18:25
 **/
@Data
public class UserCollection {

    private Integer id;

    private Integer userId;

    private int thingId;

    private Integer type;

    private Date createTime;
}
