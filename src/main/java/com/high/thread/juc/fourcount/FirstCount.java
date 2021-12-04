package com.high.thread.juc.fourcount;

import java.util.concurrent.CountDownLatch;

/**
 * @description: TODO 类描述
 * @Author guanqing
 * @Date 2021/12/4 20:08
 **/
public class FirstCount {
    static int count = 0;

    public static synchronized void incr() {
        count++;
    }

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 10; i++) {
            count = 0;
            m1();
        }
    }

    private static void m1() throws InterruptedException {
        long t1 = System.currentTimeMillis();
        int threadCount = 50;
        CountDownLatch countDownLatch = new CountDownLatch(threadCount);
        for (int i = 0; i < threadCount; i++) {
            new Thread(() -> {
                try {
                    for (int j = 0; j < 1000000; j++) {
                        incr();
                    }
                } finally {
                    countDownLatch.countDown();
                }
            }).start();
        }
        countDownLatch.await();
        long t2 = System.currentTimeMillis();
        System.out.println(String.format("结果：%s,耗时(ms): %s", count, (t2 - t1)));
    }
}
