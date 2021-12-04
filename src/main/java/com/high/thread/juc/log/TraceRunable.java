package com.high.thread.juc.log;

import org.slf4j.MDC;

/**
 * @description: TODO 类描述
 * @Author guanqing
 * @Date 2021/12/4 22:20
 **/
public class TraceRunable implements Runnable {
    private String traceId;
    private Runnable target;

    public TraceRunable(Runnable target) {
        this.traceId = TraceUtil.get();
        this.target = target;
    }

    @Override
    public void run() {
        try {
            TraceUtil.set(this.traceId);
            MDC.put(TraceUtil.MDC_TRACE_ID, TraceUtil.get());
            this.target.run();
        } finally {
            MDC.remove(TraceUtil.MDC_TRACE_ID);
            TraceUtil.remove();
        }
    }

    public static Runnable trace(Runnable target) {
        return new TraceRunable(target);
    }
}
