package com.demo.springboot.controller;

import com.demo.springboot.logpackage.Log;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: user
 * @Date: 2018/11/6
 * @Description: aop统一异常示例
 */
@RestController
public class AspectController {

    /**
     * 简单示例方法
     * @param hello
     * @return
     */
    @RequestMapping("/aop")
    @Log(value="请求了aopdemo方法")
    public String aopDemo(String hello) {
        return "请求参数 " + hello;
    }

    /**
     * 不拦截日志示例
     * @param hello
     * @return
     */
    @RequestMapping("/notaop")
    @Log(ignore=true)
    public String notAopDemo(String hello) {
        return "此方法不记录日志，请求参数为：" + hello;
     }
}
