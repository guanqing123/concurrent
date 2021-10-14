package com.high.thread.synchronize;

/**
 * @description: TODO 类描述
 * @Author guanqing
 * @Date 2021/10/14 8:53
 **/
public class Sync2 {
    static int num = 0;

    public synchronized static void m1(){
        for (int i = 0; i < 10000; i++) {
            num++;
        }
    }

    public static class T1 extends Thread {
        @Override
        public void run() {
            Sync2.m1();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        T1 t1 = new T1();
        T1 t2 = new T1();
        T1 t3 = new T1();
        t1.start();
        t2.start();
        t3.start();

        //等待3个线程结束打印num
        t1.join();
        t2.join();
        t3.join();

        System.out.println(Sync2.num);
        /**
         * 打印结果：
         * 21429
         */
    }
}
