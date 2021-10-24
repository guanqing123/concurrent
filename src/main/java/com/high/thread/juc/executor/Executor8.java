package com.high.thread.juc.executor;

import java.util.concurrent.*;

/**
 * @description: TODO 类描述
 * @Author guanqing
 * @Date 2021/10/24 20:14
 **/
public class Executor8 {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ExecutorService executorService = Executors.newFixedThreadPool(1);
        Future<Integer> result = executorService.submit(()->{
            System.out.println(System.currentTimeMillis() + "," + Thread.currentThread().getName() + ",start!");
            TimeUnit.SECONDS.sleep(5);
            System.out.println(System.currentTimeMillis() + "," + Thread.currentThread().getName() + ",end!");
            return 10;
        });

        executorService.shutdown();

        TimeUnit.SECONDS.sleep(1);
        result.cancel(false);
        System.out.println(result.isCancelled());
        System.out.println(result.isDone());

        TimeUnit.SECONDS.sleep(5);
        System.out.println(System.currentTimeMillis() + "," + Thread.currentThread().getName() + "");
        System.out.println(System.currentTimeMillis() + "," + Thread.currentThread().getName() + ",结果：" + result.get());
        executorService.shutdown();
    }
}
