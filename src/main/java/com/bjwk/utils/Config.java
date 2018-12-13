package com.bjwk.utils;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * @program: family-school
 * @description: {类描述}
 * @author: liqitian3344
 * @create: 2018-11-29 14:11
 */
@Component
@PropertySource(value = {"classpath:config.properties"})
@Data
public class Config {
    @Value("${databaseurl}")
    public String databaseurl;

    @Value("${username}")
    public String username;

    @Value("${password}")
    public String password;

    @Value("${spring.elasticsearch.jest.uris}")
    public String uris;

    @Value("${redisHost}")
    public String redisHost;

    @Value("${redisPort}")
    public String redisPort;

    @Value("${redisPw}")
    public String redisPw;

    @Value("${go_easy_server_host}")
    public String goEasyServerHost;

    @Value("${go_easy_server_appKey}")
    public String goEasyServerAppKey;
}