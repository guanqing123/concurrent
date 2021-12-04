package com.high.thread.juc.sixmethod;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * @description: CountDownLatch实现
 * @Author guanqing
 * @Date 2021/12/4 16:27
 **/
public class SecondMethod {
    /** 用于封装结果 */
    static class Result<T> {
        T result;

        public T getResult() {
            return result;
        }

        public void setResult(T result) {
            this.result = result;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        System.out.println(System.currentTimeMillis());
        CountDownLatch countDownLatch = new CountDownLatch(1);
        /** 用于存放子线程执行的结果 */
        Result<Integer> result = new Result<>();
        /** 创建一个子线程 */
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    TimeUnit.SECONDS.sleep(3);
                    result.setResult(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    countDownLatch.countDown();
                }
            }
        });
        thread.start();
        /** countDownLatch.await()会让当前线程阻塞,当countDownLatch中的计数器变为0的时候,await方法会返回 */
        countDownLatch.await();

        /** 获取thread线程的执行结果 */
        Integer rs = result.getResult();
        System.out.println(System.currentTimeMillis());
        System.out.println(System.currentTimeMillis() + ":" + rs);
    }
}
