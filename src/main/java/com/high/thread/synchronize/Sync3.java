package com.high.thread.synchronize;

/**
 * @description: TODO 类描述
 * @Author guanqing
 * @Date 2021/10/14 8:53
 **/
public class Sync3 {
    int num = 0;

    public synchronized void add(){
        num++;
    }

    public static class T extends Thread {
        private Sync3 sync3;

        public T(Sync3 sync3) {
            this.sync3 = sync3;
        }

        @Override
        public void run() {
            for (int i = 0; i < 10000; i++) {
                this.sync3.add();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Sync3 sync3 = new Sync3();
        T t1 = new T(sync3);
        T t2 = new T(sync3);
        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println(sync3.num);
    }
}
