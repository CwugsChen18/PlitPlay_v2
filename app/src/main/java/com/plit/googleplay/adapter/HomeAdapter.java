package com.plit.googleplay.adapter;

import android.os.SystemClock;
import android.widget.ListView;

import com.plit.googleplay.beans.HomeBeans;
import com.plit.googleplay.beans.ItemBeans;
import com.plit.googleplay.holder.BaseHolder;
import com.plit.googleplay.holder.ItemHolder;
import com.plit.googleplay.protocol.HomeProtocol;

import java.util.ArrayList;

/**
 * @author Cwugs.Chen.
 * @time 2016/8/20  20:56
 * @desc ${TODD}
 */
public class HomeAdapter extends BasicUpAdapter<ItemBeans> {


    public HomeAdapter(ArrayList<ItemBeans> mData, ListView lv) {
        super(mData, lv);
    }

    @Override
    protected ArrayList<ItemBeans> loadMoreData() {
        //模拟加载数据
        SystemClock.sleep(2000);

        final HomeBeans beans = HomeProtocol.getInstance().loadData(mData.size());
        if(beans != null) {
            return beans.getArrayList();
        }
        return null;
    }

    @Override
    public boolean hasMore() {
        //加载数据设置为true
        return true;
    }

    @Override
    protected BaseHolder getViewHolder(int position) {
        return new ItemHolder();
    }
}
