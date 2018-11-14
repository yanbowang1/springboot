package com.demo.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: user
 * @Date: 2018/11/13
 * @Description:
 */
@RestController
public class MailController {

    @Autowired
    private JavaMailSender javaMailSender;

    @GetMapping("/mail")
    public String sendMessage(){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("2725134584@qq.com");
        message.setTo("727647938@qq.com");
        message.setSubject("主题：来自oKong邮件");
        message.setText("公众号：一枚趔趄的猿(lqdevOps)，作者：oKong");
        javaMailSender.send(message);
        return "success";
    }
}
