package com.plit.googleplay.listener;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author Cwugs.Chen.
 * @time 2016/8/20  20:12
 * @desc ${TODD}
 */
public class ThreadPoolProxy {
    ThreadPoolExecutor mExecutor;

    //设置参数
    private int corePoolSize;
    private int maxPoolSize;
    private int maxAliveTime;

    //设置构造方法初始化参数
    public ThreadPoolProxy(int corePoolSize, int maxPoolSize, int maxAliveTime) {
        this.corePoolSize = corePoolSize;
        this.maxAliveTime = maxAliveTime;
        this.maxPoolSize = maxPoolSize;
        initThreadPoolExecutor();
    }

    /********提交任务**********/
    public void submit(Runnable task) {
        mExecutor.submit(task);
    }

    /********执行任务**********/
    public void executor(Runnable task) {
        mExecutor.execute(task);
    }

    /********移除任务**********/
    public void remove(Runnable task) {
        mExecutor.remove(task);
    }

    /********初始化mExxcotor**********/
    /********防止创建多个对象，加锁**********/
    public void initThreadPoolExecutor() {
        if(mExecutor == null || mExecutor.isShutdown() || mExecutor.isTerminated()) {
            synchronized (ThreadPoolProxy.class) {
                if(mExecutor == null || mExecutor.isShutdown() || mExecutor.isTerminated()) {
                    TimeUnit unit = TimeUnit.NANOSECONDS;
                    BlockingQueue<Runnable> workQueue = new LinkedBlockingDeque<>();
                    ThreadFactory threadFactory= Executors.defaultThreadFactory();
                    RejectedExecutionHandler handler=new ThreadPoolExecutor.DiscardPolicy();
                    mExecutor = new ThreadPoolExecutor(
                            //ThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime,
                            // TimeUnit unit, BlockingQueue<Runnable> workQueue)
                            corePoolSize,
                            maxPoolSize,
                            maxAliveTime,
                            unit,
                            workQueue,
                            threadFactory,
                            handler
                    );
                }
            }
        }
    }

}
