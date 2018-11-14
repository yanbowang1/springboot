package com.demo.springboot.conditionaldemo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

/**
 * @Auther: user
 * @Date: 2018/11/7
 * @Description:
 */
@Configuration
public class ContionalConfig {
    @Bean
    @Conditional(WindowsCondition.class)
    public ListService getWindowsListService(){
        return new WindowsListService();
    }
}
