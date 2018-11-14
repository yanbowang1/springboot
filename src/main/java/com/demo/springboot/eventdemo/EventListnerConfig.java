package com.demo.springboot.eventdemo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;

/**
 * @Auther: user
 * @Date: 2018/11/7
 * @Description: 监听配置类
 */
@Configuration
@Slf4j
public class   EventListnerConfig {

    @EventListener
    public void handleEvent(Object event) {
        //监听所有事件 可以看看 系统各类时间 发布了哪些事件
        //可根据 instanceof 监听想要监听的事件
//        if(event instanceof CustomEvent) {
//
//        }
        //log.info("事件：{}", event);
    }

    /**
     * 监听 customerEnvent
     * @param customEvent
     */
    public void handleCustomEvent(CustomEvent customEvent){
        //log.info("监听到Customer事件， 消息为：｛｝，发布时间为｛｝"
        //+ customEvent.getMessageEntity() + "+++++++++" + customEvent.getTimestamp());
    }

    @EventListener(condition = "#customEvent.messageEntity.code == 'oKong'")
    public void handleCustomEventByCondition(CustomEvent customEvent) {
        //log.info("监听到code为‘oKong’的Customer事件，消息为：｛｝ 发布时间为｛｝"
                //+ customEvent.getTimestamp() + "+++++" + customEvent.getTimestamp());
    }

    @EventListener
    public void handlerObjectevent(MessageEntity messageEntity) {
        //log.info("监听到对象事件，消息为：{}" + messageEntity);
    }
}
