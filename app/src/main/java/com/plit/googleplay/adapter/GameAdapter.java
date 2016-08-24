package com.plit.googleplay.adapter;

import android.widget.ListView;

import com.plit.googleplay.beans.ItemBeans;
import com.plit.googleplay.holder.BaseHolder;
import com.plit.googleplay.holder.ItemHolder;
import com.plit.googleplay.protocol.AppProtocol;

import java.util.ArrayList;

/**
 * @author Cwugs.Chen.
 * @time 2016/8/23  19:14
 * @desc ${TODD}
 */
public class GameAdapter extends BasicUpAdapter<ItemBeans> {
    public GameAdapter(ArrayList<ItemBeans> mData, ListView lv) {
        super(mData, lv);
    }

    @Override
    protected ArrayList<ItemBeans> loadMoreData() {
        final ArrayList<ItemBeans> beans = AppProtocol.getInstance().loadData(mData.size());
        if(beans != null) {
            return beans;
        }
        return null;
    }

    @Override
    protected BaseHolder getViewHolder(int position) {
        return new ItemHolder();
    }

    @Override
    public boolean hasMore() {
        return true;
    }


}
