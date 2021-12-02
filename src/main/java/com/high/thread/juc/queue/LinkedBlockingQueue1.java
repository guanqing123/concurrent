package com.high.thread.juc.queue;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * @description: TODO 类描述
 * @Author guanqing
 * @Date 2021/12/2 9:00
 **/
public class LinkedBlockingQueue1 {
    /** 推送队列 */
    static LinkedBlockingQueue<String> pushQueue = new LinkedBlockingQueue<>();

    static {
        /**
         * 启动一个线程做真实推送
         */
        new Thread(() -> {
            while (true) {
                String msg;
                try {
                    long startTime = System.currentTimeMillis();
                    //获取一条推送消息,此方法会进行阻塞,直到返回结果
                    msg = pushQueue.take();
                    long endTime = System.currentTimeMillis();
                    //模拟推送耗时
                    TimeUnit.MILLISECONDS.sleep(500);

                    System.out.println(String.format("[%s,%s,take耗时:%s],%s,发送消息:%s", startTime, endTime, (endTime - startTime), Thread.currentThread().getName(), msg));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    //推送消息，需要发送推送消息的调用该方法，会将推送信息先加入推送队列
    public static void pushMsg(String msg) throws InterruptedException {
        pushQueue.put(msg);
    }

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i <= 5; i++) {
            String msg = "一起来学java高并发,第" + i + "天";
            //模拟耗时
            TimeUnit.SECONDS.sleep(i);
            LinkedBlockingQueue1.pushMsg(msg);
        }
    }
}
