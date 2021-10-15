package com.high.thread.daemon;

import java.util.concurrent.TimeUnit;

/**
 * @description: TODO 类描述
 * @Author guanqing
 * @Date 2021/10/14 8:19
 **/
public class Daemon1 {
    public static class T1 extends Thread {
        public T1(String name) {
            super(name);
        }
        @Override
        public void run() {
            System.out.println(this.getName() + "开始执行," + (this.isDaemon() ? "我是守护线程" : "我是用户线程"));
            while (true);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        T1 t1 = new T1("子线程1");
        t1.start();
        TimeUnit.SECONDS.sleep(2);
        System.out.println("主线程结束");
    }
}
