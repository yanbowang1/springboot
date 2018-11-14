package com.demo.springboot.asyncdemo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * @Auther: wangyanbo
 * @Date: 2018/11/5
 * @Description: 定义一个线程池 让异步任务来调用该线程池中的线程
 */

@Configuration
public class Config {

    /**
     * 配置线程池
     * @return
     */
    @Bean(name="asyncPoolTaskExecutor")
    public ThreadPoolTaskExecutor getAsyncThreadPoolExector(){
        ThreadPoolTaskExecutor taskExecitor = new ThreadPoolTaskExecutor();
        taskExecitor.setCorePoolSize(20);   // 核心线程数
        taskExecitor.setMaxPoolSize(30);    // 最大线程数
        taskExecitor.setQueueCapacity(25);  // 线程队列最大容量
        taskExecitor.setKeepAliveSeconds(200); // 最大保持连接时间
        taskExecitor.setThreadNamePrefix("wangyanbo-");
        // 无可用线程时的处理策略
        taskExecitor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        // 调度器shutdown 被调用时等待当前被调度任务
        taskExecitor.setWaitForTasksToCompleteOnShutdown(true);
        // 等待时间
        taskExecitor.setAwaitTerminationSeconds(60);
        taskExecitor.initialize(); //初始化
        return taskExecitor;
    }
}
