package com.high.thread.juc.completablefuture;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.function.Supplier;

/**
 * @description: TODO 类描述
 * @Author guanqing
 * @Date 2021/12/4 23:34
 **/
public class CF5 {
    public static void thenAccept() throws ExecutionException, InterruptedException {
        CompletableFuture<Void> future = CompletableFuture.supplyAsync(new Supplier<Integer>() {
            @Override
            public Integer get() {
                return new Random().nextInt(10);
            }
        }).thenAccept(integer -> {
            System.out.println(integer);
        });
        future.get();
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        thenAccept();
    }
}
