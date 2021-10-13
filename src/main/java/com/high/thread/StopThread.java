package com.high.thread;
import lombok.extern.slf4j.Slf4j;
import java.util.concurrent.TimeUnit;

/**
 * @description: TODO 类描述
 * @Author guanqing
 * @Date 2021/10/12 21:07
 **/
@Slf4j
public class StopThread {
    public static void main(String[] args) throws InterruptedException {
        Thread thread1 = new Thread(){
            @Override
            public void run() {
                log.info("start");
                boolean flag = true;
                while (flag) {
                    ;
                }
                log.info("end");
            }
        };
        thread1.setName("thread1");
        thread1.start();
        TimeUnit.SECONDS.sleep(1);
        thread1.stop();
        log.info("{}", thread1.getState());
        TimeUnit.SECONDS.sleep(1);
        log.info("{}", thread1.getState());
    }
}
