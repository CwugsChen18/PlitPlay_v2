package com.plit.googleplay.protocol;

import com.plit.googleplay.beans.HomeBeans;
import com.plit.googleplay.utils.JsonUtils;

/**
 * @author Cwugs.Chen.
 * @time 2016/8/22  13:59
 * @desc ${TODD}
 */
public class HomeProtocol extends BaseProtocol<HomeBeans> {
    private static HomeProtocol mProtocol = null;

    private HomeProtocol(){}

    public static HomeProtocol getInstance() {
        if(mProtocol == null) {
            synchronized (HomeProtocol.class) {
                if(mProtocol == null) {
                    mProtocol = new HomeProtocol();
                }
            }
        }
        return mProtocol;
    }

    @Override
    public HomeBeans parserJs(String js) {
        return JsonUtils.jsonParser(js, HomeBeans.class);
    }

    @Override
    public String getSpecialKey() {
       return "home";
    }
}
