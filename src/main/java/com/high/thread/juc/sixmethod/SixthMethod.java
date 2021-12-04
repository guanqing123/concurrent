package com.high.thread.juc.sixmethod;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * @description: CompletableFuture实现
 * @Author guanqing
 * @Date 2021/12/4 18:33
 **/
public class SixthMethod {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        System.out.println(System.currentTimeMillis());
        CompletableFuture<Integer> completableFuture = CompletableFuture.supplyAsync(()->{
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 10;
        });
        System.out.println(System.currentTimeMillis());
        /** completableFuture.get()会阻塞当前线程,直到completableFuture执行完毕 */
        Integer result = completableFuture.get();
        System.out.println(System.currentTimeMillis() + ":" + result);
    }
}
