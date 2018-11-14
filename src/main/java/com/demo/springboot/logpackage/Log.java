package com.demo.springboot.logpackage;

import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.*;

/**
 * @Auther: user
 * @Date: 2018/11/6
 * @Description: 日志注解类
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface Log {

    /**
     * 日志描述
     * @return
     */
    @AliasFor("desc")
    String value() default "";

    /**
     * 日志描述
     * @return
     */
    @AliasFor("value")
    String desc() default "";

    /**
     * 是否不记录日志
     * @return
     */
    boolean ignore() default false;
}
