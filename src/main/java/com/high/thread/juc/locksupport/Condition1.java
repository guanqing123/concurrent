package com.high.thread.juc.locksupport;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @description: TODO 类描述
 * @Author guanqing
 * @Date 2021/10/17 10:40
 **/
public class Condition1 {

    static ReentrantLock lock = new ReentrantLock();
    static Condition condition = lock.newCondition();

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            lock.lock();
            try {
                System.out.println(System.currentTimeMillis() + "," + Thread.currentThread().getName() + " start!");
                try {
                    condition.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(System.currentTimeMillis() + "," + Thread.currentThread().getName() + " 被唤醒!");
            } finally {
                lock.unlock();
            }
        });
        t1.setName("t1");
        t1.start();
        //休眠5秒
        TimeUnit.SECONDS.sleep(5);
        lock.lock();
        try {
            condition.signal();
        } finally {
            lock.unlock();
        }
    }
}
