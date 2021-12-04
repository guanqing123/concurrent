package com.high.thread.juc.completablefuture;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import java.util.function.BiConsumer;
import java.util.function.Function;

/**
 * @description: TODO 类描述
 * @Author guanqing
 * @Date 2021/12/4 23:05
 **/
public class CF2 {
    public static void main(String[] args) throws InterruptedException {
        whenComplete();
    }

    public static void whenComplete() throws InterruptedException {
        CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (new Random().nextInt()%2>=0) {
                int i = 12/0;
            }
            System.out.println("run end...");
        });

        future.whenComplete(new BiConsumer<Void, Throwable>() {
            @Override
            public void accept(Void aVoid, Throwable throwable) {
                System.out.println("执行完成！");
            }
        });
        future.exceptionally(new Function<Throwable, Void>() {
            @Override
            public Void apply(Throwable throwable) {
                System.out.println("执行失败!" + throwable.getMessage());
                return null;
            }
        });

        TimeUnit.SECONDS.sleep(2);
    }
}
