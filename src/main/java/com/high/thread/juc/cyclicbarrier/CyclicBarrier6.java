package com.high.thread.juc.cyclicbarrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * @description: TODO 类描述
 * @Author guanqing
 * @Date 2021/10/19 21:18
 **/
public class CyclicBarrier6 {
    public static CyclicBarrier cyclicBarrier = new CyclicBarrier(10);

    //规则是否已重建
    public static boolean guizhe = false;

    public static class T extends Thread {
        int sleep;

        public T(String name, int sleep) {
            super(name);
            this.sleep = sleep;
        }

        @Override
        public void run() {
            long startTime = 0, endTime = 0;
            try {
                //模拟休眠
                TimeUnit.SECONDS.sleep(sleep);
                startTime = System.currentTimeMillis();
                //调用await()的时候,当前线程将会被阻塞,需要等待其他员工都到达await了才能继续
                System.out.println(this.getName() + "到了! ");
                if (!guizhe) {
                    if (this.getName().equals("员工1")) {
                        cyclicBarrier.await(5, TimeUnit.SECONDS);
                    } else {
                        cyclicBarrier.await();
                    }
                } else {
                    cyclicBarrier.await();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            } catch (TimeoutException e) {
                e.printStackTrace();
            }
            endTime = System.currentTimeMillis();
            System.out.println(this.getName() + ",sleep:" + this.sleep + " 等待了" + (endTime - startTime) + "(ms),开始吃饭了!");
        }
    }

    public static void main(String[] args) throws InterruptedException {
        for (int i = 1; i <= 10; i++) {
            T t = new T("员工" + i, i);
            t.start();
        }

        //等待15秒之后,重置,重建规则
        TimeUnit.SECONDS.sleep(15);
        cyclicBarrier.reset();
        guizhe = true;
        System.out.println("-------------大家太皮了,请大家按规则来-------------------");
        //再来一次
        for (int i = 1; i <= 10; i++) {
            T t = new T("员工"+i, i);
            t.start();
        }
    }
}
