package com.demo.springboot.controller;

import com.demo.springboot.scheduleddemo.TaskSchedulerExecutor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @Auther: user
 * @Date: 2018/11/5
 * @Description:
 */
@RestController
@Slf4j
public class TimerController {

    @GetMapping("/timer")
    public String doTimer() {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                log.info("Timer定时任务启动：" + new Date());
            }
        }, 1000, 1000); // 延迟一秒启动 ， 每一秒执行一次
        return "timer";
    }

    @GetMapping("/executor")
    public  String ScheduledExectorService() {
        ScheduledExecutorService service = Executors.newScheduledThreadPool(10);
        service.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                log.info("ScheduledExecutorService 定时任务执行：" + new Date());
            }
        }, 1, 1, TimeUnit.SECONDS);
        log.info("ScheduledExecutorService 定时任务启动：" + new Date());
        return "ScheduledExecutorService";
    }

    @Autowired
    TaskSchedulerExecutor task;

    @GetMapping("/poolTask")
    public  String threadpoolTaskScheduler() {
        task.schedule(new Runnable() {

            @Override
            public void run() {
                log.info("ThreadPoolTaskScheduler定时任务：" + new Date());
            }
        }, new CronTrigger("0/3 * * * * ?"));//每3秒执行一次
        return "ThreadPoolTaskScheduler!";
    }
}
