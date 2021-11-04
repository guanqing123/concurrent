package com.high.thread.juc.atomic;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

/**
 * @description: TODO 类描述
 * @Author guanqing
 * @Date 2021/11/4 21:03
 **/
public class Atomic5 {

    static Atomic5 atomic5 = new Atomic5();
    //isInit用来标注是否被初始化过
    volatile Boolean isInit = Boolean.FALSE;
    AtomicReferenceFieldUpdater<Atomic5, Boolean> updater = AtomicReferenceFieldUpdater.newUpdater(Atomic5.class, Boolean.class, "isInit");

    /**
     * 模拟初始化工作
     *
     * @throws InterruptedException
     */
    public void init() throws InterruptedException {
        //isInit为false的时候，才进行初始化，并将isInit采用原子操作置为true
        if (updater.compareAndSet(atomic5, Boolean.FALSE, Boolean.TRUE)) {
            System.out.println(System.currentTimeMillis() + "," + Thread.currentThread().getName() + "，开始初始化!");
            //模拟休眠3秒
            TimeUnit.SECONDS.sleep(3);
            System.out.println(System.currentTimeMillis() + "," + Thread.currentThread().getName() + "，初始化完毕!");
        } else {
            System.out.println(System.currentTimeMillis() + "," + Thread.currentThread().getName() + "，有其他线程已经执行了初始化!");
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            new Thread(()->{
                try {
                    atomic5.init();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }
}
