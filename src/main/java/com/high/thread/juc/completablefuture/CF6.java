package com.high.thread.juc.completablefuture;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.function.Supplier;

/**
 * @description: TODO 类描述
 * @Author guanqing
 * @Date 2021/12/4 23:39
 **/
public class CF6 {
    public static void thenRun() throws ExecutionException, InterruptedException {
        CompletableFuture<Void> future = CompletableFuture.supplyAsync(new Supplier<Integer>() {
            @Override
            public Integer get() {
                return new Random().nextInt(10);
            }
        }).thenRun(new Runnable() {
            @Override
            public void run() {
                System.out.println("thenRun...");
            }
        });
        future.get();
    }
}
