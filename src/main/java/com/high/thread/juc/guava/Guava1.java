package com.high.thread.juc.guava;

import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @description: TODO 类描述
 * @Author guanqing
 * @Date 2021/12/5 9:06
 **/
@Slf4j
public class Guava1 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        /** 创建一个线程池 */
        ExecutorService delegate = Executors.newFixedThreadPool(5);
        try {
            ListeningExecutorService executorService = MoreExecutors.listeningDecorator(delegate);
            /** 异步执行一个任务 */
            ListenableFuture<Integer> submit = executorService.submit(() -> {
                log.info("{}", System.currentTimeMillis());
                /** 休眠2秒,默认耗时 */
                TimeUnit.SECONDS.sleep(2);
                log.info("{}", System.currentTimeMillis());
                return 10;
            });
            /** 当任务执行完毕之后回调对应的方法 */
            submit.addListener(() -> {
                log.info("任务执行完毕了,我被回调了");
            }, MoreExecutors.directExecutor());
            log.info("{}", submit.get());
        } finally {
            delegate.shutdown();
        }
    }
}
