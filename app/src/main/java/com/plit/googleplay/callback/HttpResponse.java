package com.plit.googleplay.callback;

import android.text.TextUtils;

import com.plit.googleplay.utils.JsonUtils;

import org.json.JSONException;

/**
 * @author Cwugs.Chen.
 * @time 2016/8/14  0:28
 * @desc ${TODD}
 */
public abstract class HttpResponse<T> {

    Class<T> tClass;
    public HttpResponse(Class<T> tClass) {
        this.tClass = tClass;
    }

    public abstract void onSuccess(T t) throws JSONException;
    public abstract void onError(String msg);

    public void parser(String content) {
        if(TextUtils.isEmpty(content)) {
            onError("连接服务器异常");
            return;
        }
        if(tClass == String.class) {
            try {
                onSuccess((T)content);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return;
        }
        T t = JsonUtils.jsonParser(content,tClass);
        if(t != null) {
            try {
                onSuccess(t);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else{
            onError("解析失败");
        }
    }
}
