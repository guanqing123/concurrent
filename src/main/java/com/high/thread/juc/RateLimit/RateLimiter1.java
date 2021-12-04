package com.high.thread.juc.RateLimit;

import com.google.common.util.concurrent.RateLimiter;

/**
 * @description: TODO 类描述
 * @Author guanqing
 * @Date 2021/12/4 12:18
 **/
public class RateLimiter1 {

    public static void main(String[] args) {
        /** 设置QPS为5 */
        RateLimiter rateLimiter = RateLimiter.create(5);
        for (int i = 0; i < 10; i++) {
            rateLimiter.acquire();
            System.out.println(System.currentTimeMillis());
        }
        System.out.println("-----------------");
        /** 可以随时调整速率,我们将qps调整为10 */
        rateLimiter.setRate(10);
        for (int i = 0; i < 10; i++) {
            rateLimiter.acquire();
            System.out.println(System.currentTimeMillis());
        }
    }
}
