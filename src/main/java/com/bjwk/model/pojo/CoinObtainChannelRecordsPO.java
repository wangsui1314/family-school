package com.bjwk.model.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

/**
 * @program: family-school
 * @description:
 * @author: liqitian.
 * @create: 2019-01-06 16:17
 **/
@Data
public class CoinObtainChannelRecordsPO {

    private int id;

    private int num;

    private int currentNum;
    private String channelName;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private String createTime;
}

