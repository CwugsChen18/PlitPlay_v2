package com.plit.googleplay.protocol;

import com.plit.googleplay.beans.CategoryBeans;
import com.plit.googleplay.utils.LogUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * @author Cwugs.Chen.
 * @time 2016/8/22  13:59
 * @desc ${TODD}
 */
public class CategoryProtocol extends BaseProtocol<ArrayList<CategoryBeans>> {
    private static CategoryProtocol mProtocol = null;

    private CategoryProtocol(){}

    public static CategoryProtocol getInstance() {
        if(mProtocol == null) {
            synchronized (CategoryProtocol.class) {
                if(mProtocol == null) {
                    mProtocol = new CategoryProtocol();
                }
            }
        }
        return mProtocol;
    }


    @Override
    public ArrayList<CategoryBeans> parserJs(String js) {
       // 使用节点获取json解析数据
        ArrayList<CategoryBeans> cBeans = new ArrayList<>();
        //获取json数组
        try {
            JSONArray ja = new JSONArray(js);
            for (int i = 0; i < ja.length(); i++) {
                CategoryBeans titleBean = new CategoryBeans();
                final JSONObject jsonObject = ja.getJSONObject(i);
                final String title = jsonObject.getString("title");
                titleBean.setTitle(title);
                titleBean.setTitle(true);
                cBeans.add(titleBean);
                final JSONArray jsonArray = jsonObject.getJSONArray("infos");
                for (int j =0; j < jsonArray.length(); j++) {
                    final JSONObject jo = jsonArray.getJSONObject(j);
                    final String name1 = jo.getString("name1");
                    final String name2 = jo.getString("name2");
                    final String name3 = jo.getString("name3");

                    final String url1 = jo.getString("url1");
                    final String url2 = jo.getString("url2");
                    final String url3 = jo.getString("url3");

                    final CategoryBeans categoryBeans = new CategoryBeans();
                    categoryBeans.setName1(name1);
                    categoryBeans.setName2(name2);
                    categoryBeans.setName3(name3);

                    categoryBeans.setUrl1(url1);
                    categoryBeans.setUrl2(url2);
                    categoryBeans.setUrl3(url3);
                    LogUtils.logI("cate", categoryBeans.toString());
                    cBeans.add(categoryBeans);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return cBeans;
    }

    @Override
    public String getSpecialKey() {
       return "category";
    }
}
