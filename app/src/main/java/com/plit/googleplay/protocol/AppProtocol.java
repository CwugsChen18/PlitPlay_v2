package com.plit.googleplay.protocol;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.plit.googleplay.beans.ItemBeans;

import java.util.ArrayList;

/**
 * @author Cwugs.Chen.
 * @time 2016/8/22  13:59
 * @desc ${TODD}
 */
public class AppProtocol extends BaseProtocol<ArrayList<ItemBeans>> {
    private static AppProtocol mProtocol = null;

    private AppProtocol(){}

    public static AppProtocol getInstance() {
        if(mProtocol == null) {
            synchronized (AppProtocol.class) {
                if(mProtocol == null) {
                    mProtocol = new AppProtocol();
                }
            }
        }
        return mProtocol;
    }


    @Override
    public ArrayList<ItemBeans> parserJs(String js) {
       // return JsonUtils.jsonForm(js);
        Gson gson = new Gson();
       return gson.fromJson(js, new TypeToken<ArrayList<ItemBeans>>(){}.getType());
    }

    @Override
    public String getSpecialKey() {
       return "app";
    }
}
