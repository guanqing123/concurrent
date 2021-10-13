package com.high.thread;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

/**
 * @description: TODO 类描述
 * @Author guanqing
 * @Date 2021/10/13 8:29
 **/
@Slf4j
public class InterruptThread {

    public static void main(String[] args) {

        Thread thread1 = new Thread(){
            @Override
            public void run() {
                try {
                    TimeUnit.SECONDS.sleep(100);
                } catch (InterruptedException e) {
                    this.interrupt();
                    e.printStackTrace();
                }
                if (this.isInterrupted()){
                    log.info("我要退出了...");
                }
            }
        };

        thread1.setName("线程1");
        thread1.start();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread1.interrupt();
    }

}
