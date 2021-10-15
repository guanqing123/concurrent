package com.high.thread.daemon;

import java.util.concurrent.TimeUnit;

/**
 * @description: TODO 类描述
 * @Author guanqing
 * @Date 2021/10/14 8:19
 **/
public class Daemon4 {
    public static class T1 extends Thread {
        public T1(String name) {
            super(name);
        }
        @Override
        public void run() {
            System.out.println(this.getName() + ".daemon:" + this.isDaemon());
        }
    }

    public static void main(String[] args) throws InterruptedException {
        System.out.println(Thread.currentThread().getName() + ".daemon:" + Thread.currentThread().isDaemon());

        T1 t1 = new T1("t1");
        t1.start();

        Thread t2 = new Thread(){
            @Override
            public void run() {
                System.out.println(this.getName() + ".daemon:" + this.isDaemon());
                T1 t3 = new T1("t3");
                t3.start();
            }
        };

        t2.setName("t2");
        t2.setDaemon(true);
        t2.start();

        TimeUnit.SECONDS.sleep(2);
    }
}
