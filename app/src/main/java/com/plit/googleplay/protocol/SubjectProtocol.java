package com.plit.googleplay.protocol;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.plit.googleplay.beans.SubjectBeans;

import java.util.ArrayList;

/**
 * @author Cwugs.Chen.
 * @time 2016/8/22  13:59
 * @desc ${TODD}
 */
public class SubjectProtocol extends BaseProtocol<ArrayList<SubjectBeans>> {
    private static SubjectProtocol mProtocol = null;

    private SubjectProtocol(){}

    public static SubjectProtocol getInstance() {
        if(mProtocol == null) {
            synchronized (SubjectProtocol.class) {
                if(mProtocol == null) {
                    mProtocol = new SubjectProtocol();
                }
            }
        }
        return mProtocol;
    }


    @Override
    public ArrayList<SubjectBeans> parserJs(String js) {
       // return JsonUtils.jsonForm(js);
        Gson gson = new Gson();
       return gson.fromJson(js, new TypeToken<ArrayList<SubjectBeans>>(){}.getType());
    }

    @Override
    public String getSpecialKey() {
       return "subject";
    }
}
