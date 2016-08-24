package com.plit.googleplay.adapter;

import android.widget.ListView;

import com.plit.googleplay.beans.CategoryBeans;
import com.plit.googleplay.holder.BaseHolder;
import com.plit.googleplay.holder.CateIconHolder;
import com.plit.googleplay.holder.CateTitleHolder;

import java.util.ArrayList;

/**
 * @author Cwugs.Chen.
 * @time 2016/8/23  13:57
 * @desc ${TODD}
 */
public class CategoryAdapter extends BasicUpAdapter<CategoryBeans> {

    public CategoryAdapter(ArrayList<CategoryBeans> mData, ListView lv) {
        super(mData, lv);
    }

    @Override
    protected ArrayList<CategoryBeans> loadMoreData() {
        return null;
    }

    @Override
    public int getViewTypeCount() {
        return super.getViewTypeCount() + 1;
    }

    @Override
    protected int getNormalItem(int position) {
        CategoryBeans data = (CategoryBeans) mData.get(position);
        if(data.isTitle()) {
            return 1;
        } else {
            return 2;
        }
    }

    @Override
    protected BaseHolder getViewHolder(int position) {
        //根据是否是标题，返回不同的holder
        CategoryBeans data = (CategoryBeans) mData.get(position);
        if(data.isTitle()) {
            return new CateTitleHolder();
        }
        return new CateIconHolder();
    }
}
