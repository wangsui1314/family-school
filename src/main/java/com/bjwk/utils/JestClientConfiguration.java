package com.bjwk.utils;

import com.google.gson.GsonBuilder;
import io.searchbox.client.JestClient;
import io.searchbox.client.JestClientFactory;
import io.searchbox.client.config.ClientConfig;
import io.searchbox.client.config.HttpClientConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.LinkedHashSet;

/**
 *     
 *  * Simple to Introduction  
 *  * @ProjectName:  [] 
 *  * @Package:      [.]  
 *  * @ClassName:    []   
 *  * @Description:  [一句话描述该类的功能]   
 *  * @Author:       []   
 *  * @CreateDate:   [ ]      
 *  
 */
public class JestClientConfiguration {
    private JestClient client ;

    public JestClient getClient() {
        return client;
    }

    public JestClientConfiguration(String esUrl){
        client = getClientConfig(esUrl) ;
    }

    public JestClient getClientConfig(String esUrl){
        JestClientFactory factory = new JestClientFactory();
        factory.setHttpClientConfig(new HttpClientConfig
                .Builder(esUrl)
                .gson(new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create())
                .multiThreaded(true)
                .readTimeout(10000)
                .build());
        JestClient client = factory.getObject();
        return client ;
    }
}
