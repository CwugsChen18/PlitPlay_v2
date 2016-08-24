package com.plit.googleplay.holder;

import android.view.View;

/**
 * @author Cwugs.Chen.
 * @time 2016/8/20  21:42
 * @desc ${TODD}
 */
public abstract class BaseHolder<T> {
    //持有一个父控件
    public View mBaseHolderView;
    
    public T mData;
    
    public BaseHolder() {
        //初始化控件
        mBaseHolderView = initHolderView();
        mBaseHolderView.setTag(this);
    }
    
    public void setDataAndRefreshHolderView(T mData) {
        this.mData = mData;
        refreshHolderView(mData);
    }

    protected abstract void refreshHolderView(T mData);

    //具体细节有子类实现
    public abstract View initHolderView();
}
