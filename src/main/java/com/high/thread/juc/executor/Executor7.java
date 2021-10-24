package com.high.thread.juc.executor;

import java.util.concurrent.*;

/**
 * @description: TODO 类描述
 * @Author guanqing
 * @Date 2021/10/24 20:04
 **/
public class Executor7 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(1);
        Future<Integer> result = executorService.submit(() -> {
            System.out.println(System.currentTimeMillis() + "," + Thread.currentThread().getName() + ",start!");
            TimeUnit.SECONDS.sleep(5);
            System.out.println(System.currentTimeMillis() + "," + Thread.currentThread().getName() + ",end!");
            return 10;
        });
        System.out.println(System.currentTimeMillis() + "," + Thread.currentThread().getName());
        try {
            System.out.println(System.currentTimeMillis() + "," + Thread.currentThread().getName() + ",结果："+result.get(3, TimeUnit.SECONDS));
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
        executorService.shutdown();
    }
}
