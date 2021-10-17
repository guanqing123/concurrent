package com.high.thread.juc.locksupport;

import java.util.concurrent.TimeUnit;

/**
 * @description: TODO 类描述
 * @Author guanqing
 * @Date 2021/10/16 21:11
 **/
public class WaitAndNotify2 {

    static Object lock = new Object();

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            System.out.println(System.currentTimeMillis() + "," + Thread.currentThread().getName() + " start!");
            try {
                lock.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(System.currentTimeMillis() + "," + Thread.currentThread().getName() + " 被唤醒!");
        });
        t1.setName("t1");
        t1.start();
        //休眠5秒
        TimeUnit.SECONDS.sleep(5);
        lock.notify();
    }
}
