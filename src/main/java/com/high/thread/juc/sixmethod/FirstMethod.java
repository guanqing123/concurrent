package com.high.thread.juc.sixmethod;

import java.util.concurrent.TimeUnit;

/**
 * @description: join实现
 * @Author guanqing
 * @Date 2021/12/4 16:04
 **/
public class FirstMethod {
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
        /** 用于存放子线程执行的结果 */
        Result<Integer> result = new Result<>();
        /** 创建一个子线程 */
        Thread thread = new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            result.setResult(10);
        });
        thread.start();
        /** 让主线程等待thread线程执行完毕之后再继续,join方法会让当前线程阻塞 */
        thread.join();

        /** 获取thread线程的执行结果 */
        Integer rs = result.getResult();
        System.out.println(System.currentTimeMillis());
        System.out.println(System.currentTimeMillis() + ":" + rs);
    }
}
