package com.high.thread.juc.guava;

import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * @description: TODO 类描述
 * @Author guanqing
 * @Date 2021/12/5 9:30
 **/
@Slf4j
public class Guava3 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        log.info("star");
        ExecutorService delegate = Executors.newFixedThreadPool(5);
        try {
            ListeningExecutorService executorService = MoreExecutors.listeningDecorator(delegate);
            List<ListenableFuture<Integer>> futureList = new ArrayList<>();
            for (int i = 5; i >= 0 ; i--) {
                int j = i;
                futureList.add(executorService.submit(() -> {
                    TimeUnit.SECONDS.sleep(j);
                    return j;
                }));
            }
            /** 获取一批任务的执行结果 */
            List<Integer> resultList = Futures.allAsList(futureList).get();
            /** 输出 */
            resultList.forEach(item -> {
                log.info("{}", item);
            });
        } finally {
            delegate.shutdown();
        }
    }
}
