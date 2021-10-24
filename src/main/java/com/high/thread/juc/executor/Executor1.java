package com.high.thread.juc.executor;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @description: TODO schedule
 * @Author guanqing
 * @Date 2021/10/22 9:00
 **/
public class Executor1 {
    public static void main(String[] args) {
        System.out.println(System.currentTimeMillis());
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(10);
        scheduledExecutorService.schedule(() -> {
            System.out.println(System.currentTimeMillis() + "开始执行");
            //模拟任务耗时
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(System.currentTimeMillis() + "执行结束");
        }, 2, TimeUnit.SECONDS);
        scheduledExecutorService.shutdown();
    }
}
