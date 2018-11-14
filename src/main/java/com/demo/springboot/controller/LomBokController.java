package com.demo.springboot.controller;

import com.demo.springboot.asyncdemo.Student;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: user
 * @Date: 2018/11/5
 * @Description:
 */
@Slf4j
@RestController
public class LomBokController {
    @GetMapping("/lombok")
    public String getLomBokeDemo(){
        Student s = new Student();
        s.setName("testWangYanBo");
        log.info("testwangyanbo");
        return s.getName();
    }
}
