package com.high.thread.juc.reentrantlock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @description: TODO 类描述
 * @Author guanqing
 * @Date 2021/10/15 8:54
 **/
public class RELock {

    private static int num = 0;

    private static ReentrantLock lock = new ReentrantLock();

    public static void add(){
        lock.lock();
        try {
            num++;
        } finally {
            lock.unlock();
        }
    }

    public static class T extends Thread {
        @Override
        public void run() {
            for (int i = 0; i < 10000; i++){
                RELock.add();
            }
        }
    }


    public static void main(String[] args) throws InterruptedException {
        T t1 = new T();
        T t2 = new T();
        T t3 = new T();

        t1.start();
        t2.start();
        t3.start();

        t1.join();
        t2.join();
        t3.join();

        System.out.println(RELock.num);
    }
}
