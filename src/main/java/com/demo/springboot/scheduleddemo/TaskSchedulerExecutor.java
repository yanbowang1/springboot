package com.demo.springboot.scheduleddemo;

import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.stereotype.Component;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * @Auther: user
 * @Date: 2018/11/5
 * @Description:
 */
@Component
public class TaskSchedulerExecutor extends ThreadPoolTaskScheduler {
    @Bean("taskExecutor")
    public TaskScheduler takExecutor() {
        ThreadPoolTaskScheduler executor = new ThreadPoolTaskScheduler();
        executor.setPoolSize(20);
        executor.setThreadNamePrefix("wangyanbo-taskExecutor-");
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        executor.setWaitForTasksToCompleteOnShutdown(true);
        // 等待时长
        executor.setAwaitTerminationSeconds(60);
        return executor;
    }
}
