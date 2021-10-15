package com.high.thread.synchronize;

/**
 * @description: TODO 类描述
 * @Author guanqing
 * @Date 2021/10/14 12:40
 **/
public class Sync4 implements Runnable{
    static Sync4 instance = new Sync4();
    static int i = 0;

    @Override
    public void run() {
        //省略其他耗时操作....
        //使用同步代码块对变量i进行同步操作,锁对象为instance
        synchronized (instance) {
            for (int j = 0; j < 10000; j++) {
                i++;
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(instance);
        Thread t2 = new Thread(instance);
        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println(i);
    }
}
