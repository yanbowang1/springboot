package com.demo.springboot.eventdemo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * @Auther: user
 * @Date: 2018/11/7
 * @Description: 事件监听
 */
@Component
@Slf4j
public class EventListener implements ApplicationListener<CustomEvent> {
    @Override
    public void onApplicationEvent(CustomEvent event) {
        //log.info("ApplicationListener方式监听事件：{}", event);
    }
}
