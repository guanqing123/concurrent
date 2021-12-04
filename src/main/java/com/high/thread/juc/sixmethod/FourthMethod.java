package com.high.thread.juc.sixmethod;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

/**
 * @description: FutureTask实现
 * @Author guanqing
 * @Date 2021/12/4 17:15
 **/
public class FourthMethod {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        System.out.println(System.currentTimeMillis());
        /** 创建一个FutureTask */
        FutureTask<Integer> futureTask = new FutureTask<>(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                TimeUnit.SECONDS.sleep(3);
                return 10;
            }
        });
        /** 将futureTask传递一个线程运行 */
        new Thread(futureTask).start();
        System.out.println(System.currentTimeMillis());
        /** futureTask.get()会阻塞当前线程,直到futureTask执行完毕 */
        Integer result = futureTask.get();
        System.out.println(System.currentTimeMillis() + ":" + result);
    }
}
