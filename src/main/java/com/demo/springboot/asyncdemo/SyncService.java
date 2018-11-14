package com.demo.springboot.asyncdemo;

/**
 * @Auther: user
 * @Date: 2018/11/5
 * @Description:
 */

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;

import java.util.concurrent.Future;

@Component
public class SyncService {
    /**
     * 异步方法
     * @throws InterruptedException
     */
    @Async("asyncPoolTaskExecutor")
    public Future<String> asyncEvent() throws InterruptedException {
        Thread.sleep(1000);
        System.out.println("线程名称 ：" + Thread.currentThread().getName());
        return  new AsyncResult<>("异步方法的返回值");
    }

    /**
     * 同步方法
     * @throws InterruptedException
     */
    public void syncEvent() throws InterruptedException {
        Thread.sleep(1000);
    }
}
