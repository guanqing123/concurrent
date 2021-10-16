package com.high.thread.juc.condition;

import lombok.SneakyThrows;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @description: TODO 类描述
 * @Author guanqing
 * @Date 2021/10/16 20:19
 **/
public class Await6 {

    static ReentrantLock lock = new ReentrantLock();
    static Condition condition = lock.newCondition();

    public static class T1 extends Thread {
        @Override
        public void run() {
            lock.lock();
            try {
                System.out.println("x");
                condition.await();
                System.out.println("y");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                System.out.println("z");
                lock.unlock();
            }
        }
    }

    public static class T2 extends Thread {
        @Override
        public void run() {
            lock.lock();
            try {
                System.out.println("xx");
                condition.signal();
                System.out.println("yy");
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                System.out.println("zz");
                lock.unlock();
            }
        }
    }

    public static void main(String[] args) {
        T1 t1 = new T1();
        t1.setName("t1");
        t1.start();

        T2 t2 = new T2();
        t2.setName("t2");
        t2.start();
    }

}
