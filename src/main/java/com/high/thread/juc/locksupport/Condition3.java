package com.high.thread.juc.locksupport;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @description: TODO 类描述
 * @Author guanqing
 * @Date 2021/10/17 10:53
 **/
public class Condition3 {

    static ReentrantLock lock = new ReentrantLock();
    static Condition condition = lock.newCondition();

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
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
        TimeUnit.SECONDS.sleep(1);
        lock.lock();
        try {
            condition.signal();
        } finally {
            lock.unlock();
        }
        System.out.println(System.currentTimeMillis() + ",condition.signal();执行完毕");
    }
}
