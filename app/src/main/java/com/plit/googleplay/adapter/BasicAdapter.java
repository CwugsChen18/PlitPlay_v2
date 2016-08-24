package com.plit.googleplay.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;

/**
 * @author Cwugs.Chen.
 * @time 2016/8/20  20:56
 * @desc ${TODD}
 */
public class BasicAdapter<T> extends BaseAdapter {
    public ArrayList<T> mData;
    public BasicAdapter(ArrayList<T> mData) {
        this.mData = mData;
    }

    @Override
    public int getCount() {
        return mData == null ? 0 : mData.size();
    }

    @Override
    public Object getItem(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return null;
    }
}
