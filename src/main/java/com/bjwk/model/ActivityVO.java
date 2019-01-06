package com.bjwk.model;

/**
 * @program: family-school
 * @description:
 * @author: liqitian.
 * @create: 2019-01-06 15:34
 **/

import lombok.Data;

import java.util.Map;


@Data
public class ActivityVO {
    private int id;
    private String  activityName;
    private int  status;
    private Map<String,Object> contentJson;
    private String  startTime;
    private String  endTime;
    private String createTime;
}
