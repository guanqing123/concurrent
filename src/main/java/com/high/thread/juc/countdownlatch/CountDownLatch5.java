package com.high.thread.juc.countdownlatch;

import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @description: TODO 类描述
 * @Author guanqing
 * @Date 2021/10/18 20:29
 **/
public class CountDownLatch5 {

    static int POOL_SIZE = 0;

    static {
        POOL_SIZE = Math.max(Runtime.getRuntime().availableProcessors(), 5);
    }

    public static <T> void dispose(List<T> taskList, Consumer<T> consumer) throws InterruptedException {
        dispose(true, POOL_SIZE, taskList, consumer);
    }

    public static <T> void dispose(boolean moreThread, int poolSize, List<T> taskList, Consumer<T> consumer) throws InterruptedException {
        if (CollectionUtils.isEmpty(taskList)) {
            return;
        }
        if (moreThread && poolSize > 1) {  //多线程处理
            poolSize = Math.min(poolSize, taskList.size());
            ExecutorService executorService = null;
            try {
                executorService = Executors.newFixedThreadPool(poolSize);
                CountDownLatch countDownLatch = new CountDownLatch(taskList.size());
                for (T item: taskList) {
                    executorService.execute(() -> {
                        try {
                            consumer.accept(item);
                        } finally {
                            countDownLatch.countDown();
                        }
                    });
                }
                countDownLatch.await();
            } finally {
                if (executorService != null) {
                    executorService.shutdown();
                }
            }
        } else {
            for (T item: taskList) {
                consumer.accept(item);
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        List<Integer> taskList = Stream.iterate(1, a -> a+1).limit(10).collect(Collectors.toList());
        CountDownLatch5.dispose(taskList, item -> {
            long startTime = System.currentTimeMillis();
            Thread ct = Thread.currentThread();
            ct.setName("t-"+item);

            try {
                TimeUnit.SECONDS.sleep(item);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            long endTime = System.currentTimeMillis();

            System.out.println("线程:"+ct.getName()+"执行完成;耗时:" + (endTime - startTime) + "秒");
        });

        System.out.println("全部执行完");
    }
}
