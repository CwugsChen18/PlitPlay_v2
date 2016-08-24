package com.plit.googleplay.beans;

import java.util.ArrayList;

/**
 * @author Cwugs.Chen.
 * @time 2016/8/21  21:35
 * @desc ${TODD}
 */
public class HomeBeans {

    private ArrayList<String> picture;
    /**
     * id : 1525489
     * name : 小码哥程序员
     * packageName : com.m520it.www
     * iconUrl : app/com.m520it.www/icon.jpg
     * stars : 5
     * size : 91767
     * downloadUrl : app/com.m520it.www/com.m520it.www.apk
     * des : 产品介绍：google市场app测试。
     */

    private ArrayList<ItemBeans> list;

    public ArrayList<String> getPicture() {
        return picture;
    }

    public void setPicture(ArrayList<String> picture) {
        this.picture = picture;
    }

    public ArrayList<ItemBeans> getArrayList() {
        return list;
    }

    public void setList(ArrayList<ItemBeans> list) {
        this.list = list;
    }
}
