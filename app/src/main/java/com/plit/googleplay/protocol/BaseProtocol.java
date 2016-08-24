package com.plit.googleplay.protocol;

import com.plit.googleplay.base.MyApplication;
import com.plit.googleplay.utils.Cons;
import com.plit.googleplay.utils.FileUtils;
import com.plit.googleplay.utils.HttpUtils;
import com.plit.googleplay.utils.IOUtils;
import com.plit.googleplay.utils.LogUtils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @author Cwugs.Chen.
 * @time 2016/8/22  14:08
 * @desc ${TODD}
 */
public abstract class BaseProtocol<T> {

    private OkHttpClient mClient;

    public T loadData(int index) {

        T t = getDataFromCache(index);
        if(t != null) {
           return t;
        }

        /*//从缓存中获取数据
        final HashMap<String, String> cacheMap = MyApplication.getCacheMap();
        //获取key值
        String key = createSpecialKey() + index;
        if(cacheMap.containsKey(key)) {
            //内存中有缓存，直接取出
            final String s = cacheMap.get(key);
            //解析数据
            t = jsonParser(s);
            if(t != null) {
                return t;
            }
        }*/

        t = getDataFromFile(index);
        if(t != null) {
            return t;
        }

        //文件中无数据，
        return getData(index);
    }

    public File createFilr(String filaename) {
        String path = FileUtils.getDir("json");
        File cacheFile = new File(path, filaename);
        return cacheFile;
    }

    private T getDataFromFile(int index){
        T t = null;
        //缓存中无数据，则从文件中获取
        String key = createSpecialKey() + index;
        File cacheFile = createFilr(key);
        BufferedReader mBr = null;
        try {
            if(cacheFile.exists()) {
                mBr = new BufferedReader(new FileReader(cacheFile));
                //获取第一行，时间
                final String time = mBr.readLine();
                //判断是否过时
                if(System.currentTimeMillis() - Long.valueOf(time) < Cons.TIME_OUT) {
                    //数据有效
                    final String data = mBr.readLine();
                    t = parserJs(data);
                    if(t != null) {
                        //存储一份到内存中
                        MyApplication.getCacheMap().put(key, data);
                        return t;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            IOUtils.close(mBr);
        }
        return null;
    }

    private T getDataFromCache(int index) {
        //从缓存中获取数据
        T t = null;
        final HashMap<String, String> cacheMap = MyApplication.getCacheMap();
        //获取key值
        String key = createSpecialKey() + index;
        if(cacheMap.containsKey(key)) {
            //内存中有缓存，直接取出
            final String s = cacheMap.get(key);
            //解析数据
            return parserJs(s);
        }
        return null;
    }

    private String createSpecialKey() {
        return getSpecialKey() + ".";
    }

    /**
     * 从网络获取数据
     * @param index
     * @return
     */
    public  T getData(int index) {
        //获取网络数据
        final HashMap<String, Integer> map = new HashMap<>();
        map.put("index", index);
        String url = Cons.URL + getSpecialKey() + "?";
        url = HttpUtils.getUrlByMap(url, map);
        LogUtils.logI("home", url);
        if(mClient == null) {
            mClient = new OkHttpClient.Builder().build();
        }
        Request request = new Request.Builder().url(url).build();
        BufferedWriter bw = null;
        try {
            final Response response = mClient.newCall(request).execute();
            if (response.isSuccessful()) {
                final String js = response.body().string();
                LogUtils.logI("home", js);
                T t = parserJs(js);

                if(t != null) {
                    //存储一份到内存和文件中
                    final HashMap<String, String> cacheMap = MyApplication.getCacheMap();
                    //获取key值
                    String key = createSpecialKey() + index;
                    cacheMap.put(key, js);

                    //获取当前时间
                    final long now = System.currentTimeMillis();
                    File cacheFile = createFilr(key);
                    bw = new BufferedWriter(new FileWriter(cacheFile));
                    bw.write(String.valueOf(now));
                    //换行
                    bw.newLine();
                    bw.write(js);
                }

                return t;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            IOUtils.close(bw);
        }
        return null;
    }

    public abstract T parserJs(String js);

    public abstract String getSpecialKey();
}
