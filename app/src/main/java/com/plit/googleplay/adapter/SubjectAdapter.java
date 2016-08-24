package com.plit.googleplay.adapter;

import android.os.SystemClock;
import android.widget.ListView;

import com.plit.googleplay.beans.SubjectBeans;
import com.plit.googleplay.holder.BaseHolder;
import com.plit.googleplay.holder.SubjectHolder;
import com.plit.googleplay.protocol.SubjectProtocol;

import java.util.ArrayList;

/**
 * @author Cwugs.Chen.
 * @time 2016/8/23  13:57
 * @desc ${TODD}
 */
public class SubjectAdapter extends BasicUpAdapter<SubjectBeans> {

    public SubjectAdapter(ArrayList<SubjectBeans> mData, ListView lv) {
        super(mData, lv);
    }

    @Override
    protected ArrayList<SubjectBeans> loadMoreData() {
        //模拟加载数据beans
        SystemClock.sleep(2000);

        final ArrayList<SubjectBeans> beans = SubjectProtocol.getInstance().loadData(mData.size());
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
    protected BaseHolder getViewHolder(int position) {
        return new SubjectHolder();
    }
}
