package com.plit.googleplay.utils;

import android.content.Context;
import android.content.res.Resources;
import com.plit.googleplay.base.MyApplication;

/**
 * @author 王维波
 * @time 2016/8/19  10:37
 * @desc ${TODD}
 */
public class UIUtils {

    // 得到上下文
    public static Context getContext(){
        return MyApplication.getContext();
    }

    //得到Resources对象
    public static Resources getResources(){
        return getContext().getResources();
    }

    //得到配置的String信息
    public  static String getString(int resId){
        return  getResources().getString(resId);
    }

    //得到配置String数组信息
    public  static String[] getStringArr(int resId){
        return getResources().getStringArray(resId);
    }

    //得到包名
    public static String getPackageName() {
        return getContext().getPackageName();
    }

    //安全执行ui操作
    public static void postSafeTask(Runnable task) {
        //获取当前的线程
        long currentThread = android.os.Process.myTid();
        //获取主线程
        final int mainThread = MyApplication.getMainThread();
        if(currentThread == mainThread) {
            task.run();
        } else {
            //获取handler对象
            final MyApplication.PlayHandler handler = MyApplication.getHandler();
            handler.post(task);
        }
    }

    public static int dip2px(int dip) {
        // px/dip=density
        //拿到设备密度
        float density=getResources().getDisplayMetrics().density;
        int px= (int) (dip*density+.5f);
        return px;
    }

}
