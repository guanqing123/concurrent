package com.high.thread.juc.countdownlatch;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * @description: TODO 类描述
 * @Author guanqing
 * @Date 2021/10/18 11:03
 **/
public class CountDownLatch4 {

    public static class T extends Thread {
        //跑步耗时(秒)
        int runCostSeconds;
        CountDownLatch commanderCd;
        CountDownLatch countDown;

        public T(String name, int runCostSeconds, CountDownLatch commanderCd, CountDownLatch countDown) {
            super(name);
            this.runCostSeconds = runCostSeconds;
            this.commanderCd = commanderCd;
            this.countDown = countDown;
        }

        @Override
        public void run() {
            //等待指令员枪响
            try {
                commanderCd.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Thread ct = Thread.currentThread();
            long startTime = System.currentTimeMillis();
            System.out.println(startTime + "," + ct.getName() + ",开始跑!");

            //模拟耗时操作,休眠runCostSeconds秒
            try {
                TimeUnit.SECONDS.sleep(this.runCostSeconds);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                countDown.countDown();
            }
            long endTime = System.currentTimeMillis();
            System.out.println(endTime + "," + ct.getName() + ",跑步结束,耗时:" + (endTime - startTime));
        }
    }

    public static void main(String[] args) throws InterruptedException {
        System.out.println(System.currentTimeMillis() + "," + Thread.currentThread().getName() + "线程 start!");
        CountDownLatch commanderCd = new CountDownLatch(1);
        CountDownLatch countDownLatch = new CountDownLatch(3);

        long startTime = System.currentTimeMillis();
        T t1 = new T("小张", 2, commanderCd, countDownLatch);
        t1.start();

        T t2 = new T("小李", 5, commanderCd, countDownLatch);
        t2.start();

        T t3 = new T("路人甲", 10, commanderCd, countDownLatch);
        t3.start();

        //主线程休眠5秒,模拟指令员准备发枪耗时操作
        TimeUnit.SECONDS.sleep(5);
        System.out.println(System.currentTimeMillis() + ",枪响了,大家开始跑");
        commanderCd.countDown();

        countDownLatch.await();
        long endTime = System.currentTimeMillis();
        System.out.println(System.currentTimeMillis() + "," + Thread.currentThread().getName() + "所有人跑完了,主线程耗时:" + (endTime - startTime));
    }
}
