package com.plit.googleplay.factory;

import com.plit.googleplay.listener.ThreadPoolProxy;

/**
 * @author Cwugs.Chen.
 * @time 2016/8/20  20:39
 * @desc google市场有两种线程池
 */
public class ThreadPoolFactory {

    public static ThreadPoolProxy mNormalThreadPoolProxy;
    public static ThreadPoolProxy mDownloadThreadPoolProxy;

    public static ThreadPoolProxy createNormalThreadPoolProxy() {
        if(mNormalThreadPoolProxy == null) {
            synchronized (ThreadPoolFactory.class) {
                if(mNormalThreadPoolProxy == null) {
                    mNormalThreadPoolProxy = new ThreadPoolProxy(5, 6, 3400);
                }
            }
        }
        return mNormalThreadPoolProxy;
    }

    public static ThreadPoolProxy createDownloadThreadPoolProxy() {
        if(mDownloadThreadPoolProxy == null) {
            synchronized (ThreadPoolFactory.class) {
                if(mDownloadThreadPoolProxy == null) {
                    mDownloadThreadPoolProxy = new ThreadPoolProxy(5, 6, 3400);
                }
            }
        }
        return mDownloadThreadPoolProxy;
    }
}
