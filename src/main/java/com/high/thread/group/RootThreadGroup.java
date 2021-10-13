package com.high.thread.group;

/**
 * @description: TODO 类描述
 * @Author guanqing
 * @Date 2021/10/13 21:40
 **/
public class RootThreadGroup {
    public static void main(String[] args) {
        System.out.println(Thread.currentThread());
        System.out.println(Thread.currentThread().getThreadGroup());
        System.out.println(Thread.currentThread().getThreadGroup().getParent());
        System.out.println(Thread.currentThread().getThreadGroup().getParent().getParent());
    }
}
