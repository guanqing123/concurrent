package com.high.thread.juc.RateLimit;

import java.util.Objects;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.LockSupport;

/**
 * @description: TODO 类描述
 * @Author guanqing
 * @Date 2021/12/3 8:57
 **/
public class RateLimit1 {

    public static class BucketLimit{
        static AtomicInteger threadNum = new AtomicInteger(1);
        /** 容量 */
        private int capcity;
        /** 流速 */
        private int flowRate;
        /** 流速时间单位 */
        private TimeUnit flowRateUnit;
        private BlockingQueue<Node> queue;
        /** 漏桶流出的任务时间间隔（纳秒） */
        private long flowRateNanosTime;

        public BucketLimit(int capcity, int flowRate, TimeUnit flowRateUnit) {
            this.capcity = capcity;
            this.flowRate = flowRate;
            this.flowRateUnit = flowRateUnit;
            this.bucketThreadWork();
        }

        /** 漏桶线程 */
        public void bucketThreadWork() {
            this.queue = new ArrayBlockingQueue<Node>(capcity);
            /** 漏桶流出的任务时间间隔（纳秒） */
            this.flowRateNanosTime = flowRateUnit.toNanos(1) / flowRate;
            Thread thread = new Thread(this::bucketWork);
            thread.setName("漏桶线程-" + threadNum.getAndIncrement());
            thread.start();
        }

        /** 漏桶线程开始工作 */
        public void bucketWork(){
            while (true) {
                /** poll移除并返回队列的头部,不会阻塞,当队列为空,返回null */
                Node node = this.queue.poll();
                if (Objects.nonNull(node)) {
                    /** 唤醒任务线程 */
                    LockSupport.unpark(node.thread);
                }
                /** 休眠flowRateNanosTime */
                LockSupport.parkNanos(this.flowRateNanosTime);
            }
        }

        /** 返回一个漏桶 */
        public static BucketLimit build(int capcity, int flowRate, TimeUnit flowRateUnit) {
            if (capcity < 0 || flowRate < 0) {
                throw new IllegalArgumentException("capcity、flowRate必须大于0！");
            }
            return new BucketLimit(capcity, flowRate, flowRateUnit);
        }

        /** 当前线程加入漏桶,返回false,表示漏桶已满; true:表示被漏桶限流成功,可以继续处理任务 */
        public boolean acquire(){
            /** 获取当前线程,构建node对象,如果queue未满,则放入队列,
             *  放入队列的node,都被LockSupport.park阻塞等待,等待
             *  LockSupport.unpark唤醒
             *  queue已满之后,放入不下对象则返回 false
             * */
            Thread thread = Thread.currentThread();
            Node node = new Node(thread);
            /** offer往队列插入元素,不会阻塞,插入失败返回false */
            if (this.queue.offer(node)) {
                LockSupport.park();
                return true;
            }
            return false;
        }

        /** 漏桶中存放的元素 */
        class Node {
            private Thread thread;

            public Node(Thread thread) {
                this.thread = thread;
            }
        }
    }

    public static void main(String[] args) {
        BucketLimit bucketLimit = BucketLimit.build(10, 60, TimeUnit.MINUTES);
        AtomicInteger threadNum = new AtomicInteger(1);
        for (int i = 0; i < 100; i++) {
            new Thread(() -> {
                Thread.currentThread().setName("官线程" + threadNum.getAndIncrement());
                boolean acquire = bucketLimit.acquire();
                System.out.println(Thread.currentThread().getName() +"\t" +System.currentTimeMillis() + " " + acquire);
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }
}
