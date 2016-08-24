package com.plit.googleplay.protocol;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.plit.googleplay.beans.ItemBeans;

import java.util.ArrayList;

/**
 * @author Cwugs.Chen.
 * @time 2016/8/23  19:07
 * @desc ${TODD}
 */
public class GameProtocol extends BaseProtocol<ArrayList<ItemBeans>> {
    private static GameProtocol mProtocol = null;

    private GameProtocol(){}

    public static GameProtocol getInstance() {
        if(mProtocol == null) {
            synchronized (AppProtocol.class) {
                if(mProtocol == null) {
                    mProtocol = new GameProtocol();
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
        return "game";
    }
}
