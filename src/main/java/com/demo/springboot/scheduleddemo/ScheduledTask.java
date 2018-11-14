package com.demo.springboot.scheduleddemo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @Auther: user
 * @Date: 2018/11/5
 * @Description: SpringTask spring自带的定时任务
 */
@Component
@Slf4j
public class ScheduledTask {

    //@Async("asyncPoolTaskExecutor")
    //@Scheduled(fixedRate = 5000)
    public void getCurrentDate() {
        log.info("线程名称：" + Thread.currentThread().getName());
        log.info("SpringTask Scheduled 定时任务执行：" + new Date());
    }
}
