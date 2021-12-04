package com.high.thread.juc.sixmethod;

import java.util.concurrent.*;

/**
 * @description: 线程池实现
 * @Author guanqing
 * @Date 2021/12/4 16:55
 **/
public class ThirdMethod {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        /** 创建一个线程池 */
        ExecutorService executorService = Executors.newCachedThreadPool();
        System.out.println(System.currentTimeMillis());
        Future<Integer> future = executorService.submit(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                try {
                    TimeUnit.SECONDS.sleep(3);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return 10;
            }
        });
        /** 关闭线程池 */
        executorService.shutdown();
        System.out.println(System.currentTimeMillis());
        Integer result = future.get();
        System.out.println(System.currentTimeMillis() + ":" + result);
    }
}
