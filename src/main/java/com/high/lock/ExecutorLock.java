package com.high.lock;

import java.util.concurrent.*;

/**
 * @description: 饥饿死锁
 * @Author guanqing
 * @Date 2021/10/11 20:53
 **/
public class ExecutorLock {
    private static ExecutorService single = Executors.newSingleThreadExecutor();

    public static class AnotherCallable implements Callable<String> {
        @Override
        public String call() throws Exception {
            System.out.println("in AnotherCallable");
            return "another success";
        }
    }

    public static class MyCallable implements Callable<String> {
        @Override
        public String call() throws Exception {
            System.out.println("in MyCallable");
            Future<String> submit = single.submit(new AnotherCallable());
            return "success:" + submit.get();
        }
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        MyCallable task = new MyCallable();
        Future<String> submit = single.submit(task);
        System.out.println(submit.get());
        System.out.println("over");
        single.shutdown();
    }

}
