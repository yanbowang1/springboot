package com.demo.springboot.controller;

import com.demo.springboot.eventdemo.CustomEvent;
import com.demo.springboot.eventdemo.MessageEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: user
 * @Date: 2018/11/7
 * @Description:
 */
@RestController
@Slf4j
public class ListenerController {

    @Autowired
    ApplicationEventPublisher eventPublisher;
    @GetMapping("push")
    public String push(String code,String message) {
        log.info("发布applicationEvent事件:{},{}", code, message);
        eventPublisher.publishEvent(new CustomEvent(this, MessageEntity.builder().code(code).message(message).build()));
        return "事件发布成功!";
    }

    @GetMapping("/obj")
    public String pushObject(String code,String message) {
        log.info("发布对象事件:{},{}", code, message);
        eventPublisher.publishEvent(MessageEntity.builder().code(code).message(message).build());
        return "对象事件发布成功!";
    }

}
