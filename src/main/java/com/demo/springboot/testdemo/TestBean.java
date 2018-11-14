package com.demo.springboot.testdemo;

import lombok.Data;

/**
 * @Auther: user
 * @Date: 2018/11/8
 * @Description:
 */
@Data
public class TestBean {
    String content;
    public TestBean(String content) {
        super();
        this.content = content;
    }
}
