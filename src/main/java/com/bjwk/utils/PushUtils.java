package com.bjwk.utils;

import io.goeasy.GoEasy;

/**
 * @program: family-school
 * @description: {消息推送}
 * @author: liqitian3344
 * @create: 2018-12-12 16:20
 */

public class PushUtils {
    static GoEasy goEasy = null;
    static {
         Config config = new Config();
         goEasy = new GoEasy(config.goEasyServerHost, config.goEasyServerAppKey);
    }
    public static void  push(String channel,String content){
        goEasy.publish(channel, content);
    }
}
