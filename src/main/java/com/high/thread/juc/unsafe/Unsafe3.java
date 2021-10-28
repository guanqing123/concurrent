package com.high.thread.juc.unsafe;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * @description: TODO 类描述
 * @Author guanqing
 * @Date 2021/10/28 8:48
 **/
public class Unsafe3 {
    static Unsafe unsafe;

    static {
        try {
            Field field = Unsafe.class.getDeclaredField("theUnsafe");
            field.setAccessible(true);
            unsafe = (Unsafe) field.get(null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
