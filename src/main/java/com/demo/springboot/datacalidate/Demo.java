package com.demo.springboot.datacalidate;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

/**
 * @Auther: user
 * @Date: 2018/11/2
 * @Description:
 */
public class Demo {

    @NotBlank(message = "code 不能为空")
    String code;

    @Length(max = 10, message = "name 的长度不能超过10")
    String name;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
