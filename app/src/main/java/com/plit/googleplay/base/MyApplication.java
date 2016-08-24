package com.plit.googleplay.base;

import android.app.Application;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.os.Process;

import java.lang.ref.WeakReference;
import java.util.HashMap;

/**
 * @author Cwugs.Chen.
 * @time 2016/8/19  14:10
 * @desc ${TODD}
 */
public class MyApplication extends Application {

    private static PlayHandler mHandler;
    private static Context mContext;
    private static int mMainThread;

    private static HashMap<String, String> cacheMap = new HashMap<>();

    public static HashMap<String, String> getCacheMap() {
        return cacheMap;
    }

    public static PlayHandler getHandler() {
        return mHandler;
    }

    public static Context getContext() {
        return mContext;
    }

    public static int getMainThread() {
        return mMainThread;
    }

    @Override
    public void onCreate() {
        mContext = getApplicationContext();
        mHandler = new PlayHandler(this);
        mMainThread = Process.myTid();

        super.onCreate();
    }

    public static class PlayHandler extends Handler {
        private WeakReference<MyApplication> app;

        public PlayHandler(MyApplication ma) {
            app = new WeakReference<MyApplication>(ma);
        }

        @Override
        public void handleMessage(Message msg) {

        }
    }
}
