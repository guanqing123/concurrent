package com.high.thread.juc.threadTrans;

import lombok.Data;

import java.io.Serializable;

/**
 * @description: TODO 类描述
 * @Author guanqing
 * @Date 2021/11/3 21:41
 **/
@Data
public class Msg implements Serializable {

    private int result;
    private String message;
    private Object data;
}
