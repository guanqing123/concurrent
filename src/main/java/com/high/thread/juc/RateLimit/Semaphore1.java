package com.high.thread.juc.RateLimit;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * @description: TODO 类描述
 * @Author guanqing
 * @Date 2021/12/2 21:44
 **/
public class Semaphore1 {

    static Semaphore semaphore = new Semaphore(5);

    public static void main(String[] args) {
        for (int i = 0; i < 20; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    boolean flag = false;
                    try {
                        flag = semaphore.tryAcquire(100, TimeUnit.MICROSECONDS);
                        if (flag) {
                            /** 休眠2s,模拟下单操作 */
                            System.out.println(Thread.currentThread() + ",尝试下单中...");
                            TimeUnit.SECONDS.sleep(2);
                        } else {
                            System.out.println(Thread.currentThread() + ",秒杀失败,请稍微重试!");
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        if (flag) {
                            semaphore.release();
                        }
                    }
                }
            }).start();
        }
    }
}
