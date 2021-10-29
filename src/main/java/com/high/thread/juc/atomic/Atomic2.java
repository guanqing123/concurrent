package com.high.thread.juc.atomic;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicIntegerArray;

/**
 * @description: TODO 类描述
 * @Author guanqing
 * @Date 2021/10/29 21:46
 **/
public class Atomic2 {

    static AtomicIntegerArray pageRequest = new AtomicIntegerArray(new int[10]);

    /**
     * 模拟访问一次
     *
     * @Author guanqing
     * @Date 2021/10/29 21:47
     **/
    public static void request(int page) throws InterruptedException {
       //模拟耗时5毫秒
        TimeUnit.MILLISECONDS.sleep(5);
        //pageCountIndex为pageCount数组的下标,表示页面对应数组中的位置
        int pageCountIndex = page - 1;
        pageRequest.incrementAndGet(pageCountIndex);
    }

    public static void main(String[] args) throws InterruptedException {
        long starTime = System.currentTimeMillis();
        int threadSize = 100;
        CountDownLatch countDownLatch = new CountDownLatch(100);
        for (int i = 0; i < threadSize; i++) {
            Thread thread = new Thread(()->{
                try {
                    for (int page = 1; page <= 10 ; page++) {
                        for (int j = 0; j < 10; j++) {
                            request(page);
                        }
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    countDownLatch.countDown();
                }
            });
            thread.start();
        }

        countDownLatch.await();
        long endTime = System.currentTimeMillis();
        System.out.println(Thread.currentThread().getName() + ",耗时:" + (endTime - starTime));

        for (int pageIndex = 0; pageIndex < 10; pageIndex++){
            System.out.println("第" + (pageIndex + 1) + "个页面访问次数为" + pageRequest.get(pageIndex));
        }
    }
}
