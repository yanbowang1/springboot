package com.demo.springboot.controller;

import com.demo.springboot.datacalidate.Demo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @Auther: user
 * @Date: 2018/11/2
 * @Description:
 */
@RestController
public class DataValidController {
    @GetMapping("/demo/valid")
    public  String demoValid(@Valid Demo req){
        return req.getCode() + "++++" + req.getName();
    }
}
