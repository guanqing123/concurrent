package com.high.thread.juc.unsafe;

import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.util.concurrent.CountDownLatch;

/**
 * @description: TODO 类描述
 * @Author guanqing
 * @Date 2021/10/28 20:58
 **/
public class Unsafe5 {

    static Unsafe unsafe;
    //用来记录网站访问量,每次访问+1
    static int count;

    static {
        try {
            Field field = Unsafe.class.getDeclaredField("theUnsafe");
            field.setAccessible(true);
            unsafe = (Unsafe) field.get(null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //模拟访问一次
    public static void request() {
        unsafe.monitorEnter(Unsafe5.class);
        try {
            count++;
        } finally {
            unsafe.monitorExit(Unsafe5.class);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        long starTime = System.currentTimeMillis();
        int threadSize = 100;
        CountDownLatch countDownLatch = new CountDownLatch(threadSize);
        for (int i = 0; i < threadSize; i++) {
            Thread thread = new Thread(()->{
                try {
                    for (int j = 0; j < 10; j++) {
                        request();
                    }
                } finally {
                    countDownLatch.countDown();
                }
            });
            thread.start();
        }

        countDownLatch.await();
        long endTime = System.currentTimeMillis();
        System.out.println(Thread.currentThread().getName() + ",耗时:" + (endTime - starTime) + ",count=" + count);
    }

}
