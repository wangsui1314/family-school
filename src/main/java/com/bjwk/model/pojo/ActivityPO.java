package com.bjwk.model.pojo;

import lombok.Data;

import java.util.Date;

/**
 * @program: family-school
 * @description: 活动po类
 * @author: liqitian.
 * @create: 2019-01-06 15:19
 **/
@Data
public class ActivityPO {
    private int id;
    private String  activityName;
    private int  status;
    private String  contentJson;
    private String  startTime;
    private String  endTime;
    private String createTime;
}
