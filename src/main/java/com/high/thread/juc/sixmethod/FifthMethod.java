package com.high.thread.juc.sixmethod;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

/**
 * @description: TODO 类描述
 * @Author guanqing
 * @Date 2021/12/4 17:46
 **/
public class FifthMethod {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        System.out.println(System.currentTimeMillis());
        /** 创建一个FutureTask */
        FutureTask<Integer> futureTask = new FutureTask<>(() -> 10);
        /** 将futureTask传递一个线程运行 */
        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            futureTask.run();
        }).start();
        System.out.println(System.currentTimeMillis());
        /** futureTask.get()会阻塞当前线程,直到futureTask执行完毕 */
        Integer result = futureTask.get();
        System.out.println(System.currentTimeMillis() + ":" + result);
    }
}
