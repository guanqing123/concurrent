package com.high.thread.juc.log;

import cn.hutool.core.util.IdUtil;

/**
 * @description: TODO 类描述
 * @Author guanqing
 * @Date 2021/12/4 22:21
 **/
public class TraceUtil {

    public static final String REQUEST_HEADER_TRACE_ID = "com.ms.header.trace.id";
    public static final String MDC_TRACE_ID = "trace_id";

    private static InheritableThreadLocal<String> inheritableThreadLocal = new InheritableThreadLocal<>();

    /**
     * 获取traceid
     * @Author guanqing
     * @Date 2021/12/4 22:23
     **/
    public static String get(){
        String traceId = inheritableThreadLocal.get();
        if (traceId == null) {
            traceId = IdUtil.randomUUID();
            inheritableThreadLocal.set(traceId);
        }
        return traceId;
    }

    public static void set(String trace_id) {
        inheritableThreadLocal.set(trace_id);
    }

    public static void remove(){
        inheritableThreadLocal.remove();
    }
}
