package com.demo.springboot.controller;

import com.demo.springboot.asyncdemo.SyncService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * @Auther: user
 * @Date: 2018/11/5
 * @Description:
 */
@RestController
public class AsyncController {

    @Autowired
    SyncService syncService;

    @GetMapping("/async")
    public String doAsync() throws InterruptedException, TimeoutException, ExecutionException {
        long start = System.currentTimeMillis();
        System.out.println("方法执行开始：" + start);
        // 调用同步方法
        syncService.syncEvent();
        long syncTime = System.currentTimeMillis();
        System.out.println("同步方法执行用时：" + (syncTime - start) );
        // 调用异步方法
        Future<String> doFuture = syncService.asyncEvent();
        while(true) {
            if(doFuture.isDone()) { // 异步方法是否完成
                break;
            }
            Thread.sleep(100);
        }
        String result = doFuture.get(60, TimeUnit.SECONDS);
        long asyncStart = System.currentTimeMillis();
        System.out.println("异步方法用时：" + (System.currentTimeMillis() - asyncStart));
        return "async!!!";
    }
}
