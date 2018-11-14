package com.demo.springboot.controller;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorld {

    @ApiOperation(value="测试swagger",notes = "这只是一个简单测试实例")
    @GetMapping("/hello")
    public String hello(){
        return "hello world  大大";
    }

    @ApiOperation(value  = "测试带参数的swagger")
    @ApiImplicitParams({
            @ApiImplicitParam (name = "id"),
            @ApiImplicitParam (name = "name")
    })
    @GetMapping("/testparams")
    public String testParams(@RequestParam String id, @RequestParam String name){
        return id + name;
    }


}
