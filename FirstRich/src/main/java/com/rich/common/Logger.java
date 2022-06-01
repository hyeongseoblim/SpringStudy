package com.rich.common;

import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.UUID;

@Component
@Scope(value = "request" , proxyMode = ScopedProxyMode.TARGET_CLASS)
@Getter
@Setter
public class Logger {

    private String uuid;
    private String requestUrl;

    public void log(String message){
        System.out.println(String.format("[ %s ] [ %s ] %s",uuid,requestUrl,message));
    }

    @PostConstruct
    public void init(){
        uuid = UUID.randomUUID().toString();
        System.out.println(String.format("[ %s ] request bean scope init",uuid));
    }


    @PreDestroy
    public void close(){
        System.out.println(String.format("[ %s ] request bean scope close",uuid));
    }
}
