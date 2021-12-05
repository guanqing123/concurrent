package com.high.thread.juc.guava;

import com.google.common.util.concurrent.*;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @description: TODO 类描述
 * @Author guanqing
 * @Date 2021/12/5 9:43
 **/
@Slf4j
public class Guava4 {
    public static void main(String[] args) {
        log.info("star");
        ExecutorService delegate = Executors.newFixedThreadPool(5);
        ListeningExecutorService executorService = MoreExecutors.listeningDecorator(delegate);
        List<ListenableFuture<Integer>> futureList = new ArrayList<>();
        for (int i = 5; i >= 0 ; i--) {
            int j = i;
            futureList.add(executorService.submit(() -> {
                TimeUnit.SECONDS.sleep(j);
                return j;
            }));
        }
        ListenableFuture<List<Integer>> listListenableFuture = Futures.allAsList(futureList);
        Futures.addCallback(listListenableFuture, new FutureCallback<List<Integer>>() {
            @Override
            public void onSuccess(List<Integer> list) {
                log.info("result中所有结果之和:" + list.stream().reduce(Integer::sum).get());
            }

            @Override
            public void onFailure(Throwable t) {
                log.error("执行任务发生异常:" + t.getMessage(), t);
            }
        }, MoreExecutors.directExecutor());
    }
}
