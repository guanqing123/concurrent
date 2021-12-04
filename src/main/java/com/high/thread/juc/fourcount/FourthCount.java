package com.high.thread.juc.fourcount;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.LongAccumulator;

/**
 * @description: TODO 类描述
 * @Author guanqing
 * @Date 2021/12/4 20:52
 **/
public class FourthCount {
    static LongAccumulator count = new LongAccumulator((x, y) -> x + y, 0L);

    public static void incr() {
        count.accumulate(1);
    }

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 10; i++) {
            count.reset();
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
        System.out.println(String.format("结果：%s,耗时(ms): %s", count.longValue(), (t2 - t1)));
    }
}
