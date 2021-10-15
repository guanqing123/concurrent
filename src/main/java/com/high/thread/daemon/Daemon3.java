package com.high.thread.daemon;

import java.util.concurrent.TimeUnit;

/**
 * @description: TODO 类描述
 * @Author guanqing
 * @Date 2021/10/14 8:19
 **/
public class Daemon3 {

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(){
            @Override
            public void run() {
                try {
                    TimeUnit.SECONDS.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        t1.start();
        t1.setDaemon(true);
    }
}
