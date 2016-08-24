package com.plit.googleplay.adapter;

import android.os.SystemClock;
import android.widget.ListView;

import com.plit.googleplay.beans.ItemBeans;
import com.plit.googleplay.holder.BaseHolder;
import com.plit.googleplay.holder.ItemHolder;
import com.plit.googleplay.protocol.AppProtocol;

import java.util.ArrayList;

/**
 * @author Cwugs.Chen.
 * @time 2016/8/23  13:57
 * @desc ${TODD}
 */
public class AppAdapter extends BasicUpAdapter<ItemBeans> {

    public AppAdapter(ArrayList<ItemBeans> mData, ListView lv) {
        super(mData, lv);
    }

    @Override
    protected ArrayList<ItemBeans> loadMoreData() {
        //模拟加载数据beans
        SystemClock.sleep(2000);

        final ArrayList<ItemBeans> beans = AppProtocol.getInstance().loadData(mData.size());
        if(beans != null) {
            return beans;
        }
        return null;
    }

    @Override
    public boolean hasMore() {
        //加载数据设置为true
        return true;
    }

    @Override
    protected BaseHolder getViewHolder(int posittion) {
        return new ItemHolder();
    }
}
