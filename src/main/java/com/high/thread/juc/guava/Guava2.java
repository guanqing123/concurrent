package com.high.thread.juc.guava;

import com.google.common.util.concurrent.*;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @description: TODO 类描述
 * @Author guanqing
 * @Date 2021/12/5 9:19
 **/
@Slf4j
public class Guava2 {
    public static void main(String[] args) {
        ExecutorService delegate = Executors.newFixedThreadPool(5);
        try {
            ListeningExecutorService executorService = MoreExecutors.listeningDecorator(delegate);
            ListenableFuture<Integer> submit = executorService.submit(() -> {
                log.info("{}", System.currentTimeMillis());
                TimeUnit.SECONDS.sleep(4);
                int i = 10/0;
                log.info("{}", System.currentTimeMillis());
                return 10;
            });
            Futures.addCallback(submit, new FutureCallback<Integer>() {
                @Override
                public void onSuccess(Integer result) {
                    log.info("执行成功:{}", result);
                }

                @Override
                public void onFailure(Throwable t) {
                    try {
                        TimeUnit.MILLISECONDS.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    log.error("执行任务发生异常:" + t.getMessage(), t);
                }
            }, MoreExecutors.directExecutor());
        } finally {
            delegate.shutdown();
        }
    }
}
