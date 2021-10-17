package com.high.thread.juc.locksupport;

import java.util.concurrent.locks.LockSupport;

/**
 * @description: TODO 类描述
 * @Author guanqing
 * @Date 2021/10/17 19:26
 **/
public class LockSupport4 {

    static class BlockerDemo {
    }

    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            LockSupport.park();
        });
        t1.setName("t1");
        t1.start();

        Thread t2 = new Thread(() -> {
           LockSupport.park(new BlockerDemo());
        });
        t2.setName("t2");
        t2.start();
    }
}
