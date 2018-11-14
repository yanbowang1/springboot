package com.demo.springboot.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: user
 * @Date: 2018/11/7
 * @Description:
 */
@RestController
public class ConditionalDemoController {
    //AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(Conditional.class);
    @GetMapping("/conditional")
    public String getOsName(){
        //String oaName = applicationContext.getEnvironment().getProperty("os.name");
        //Map<String, ListService> map = applicationContext.getBeansOfType(ListService.class);
        return null;
    }
}
