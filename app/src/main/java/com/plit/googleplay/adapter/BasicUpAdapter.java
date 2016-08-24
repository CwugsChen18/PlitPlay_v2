package com.plit.googleplay.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.plit.googleplay.factory.ThreadPoolFactory;
import com.plit.googleplay.holder.BaseHolder;
import com.plit.googleplay.holder.LoadViewHolder;
import com.plit.googleplay.utils.UIUtils;

import java.util.ArrayList;

/**
 * @author Cwugs.Chen.
 * @time 2016/8/20  20:56
 * @desc ${TODD}
 */
public abstract class BasicUpAdapter<T> extends BasicAdapter {

    public static final int NORMAL_ITEM = 1;
    public static final int LOADING_ITEM = 0;
    private static final int PAGERSIZE = 20;
    private LoadViewHolder mLoadViewHolder;
    private BaseHolder mHolder;
    private LoadMoreThread mTask;//加载状态
    private int mState = LoadViewHolder.LOAD_MORE;
    private ListView lv;

    public BasicUpAdapter(ArrayList<T> mData, ListView lv) {
        super(mData);
        this.lv = lv;
        //设置item监听
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //当加载错误界面时，可点击刷新
                if(getItemViewType(position) == LOADING_ITEM && mState == LoadViewHolder.LOAD_ERROR) {
                    mState = LoadViewHolder.LOAD_MORE;
                    mLoadViewHolder.setDataAndRefreshHolderView(mState);
                    triggleData();
                }
            }
        });
    }

    /**
     * 获取item类型的个数
     * @return
     */
    @Override
    public int getViewTypeCount() {
        return super.getViewTypeCount() + 1;
    }

    /**
     * 增加item个数
     * @return
     */
    @Override
    public int getCount() {
        return super.getCount() + 1;
    }

    /**
     * 给lv添加新类型的item
     * @param position
     * @return  返回item的位置
     */
    @Override
    public int getItemViewType(int position) {
        if(position == getCount() - 1) {
            return LOADING_ITEM;
        } else {
            return getNormalItem(position);
        }

    }

    protected int getNormalItem(int position) {
        return NORMAL_ITEM;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        mHolder = null;
        int type = getItemViewType(position);
        if(convertView == null) {
            //获取item的类型
            if(type == LOADING_ITEM) {
                mHolder = getLoadViewHolder();
            } else {
                //holder的具体内容有子类实现
                mHolder = getViewHolder(position);
            }

        } else {
            mHolder = (BaseHolder) convertView.getTag();
        }

        /********判断是否加载数据**********/
        if(type == LOADING_ITEM) {
            if(hasMore()) {
                //加载数据  初始状态
                mHolder.setDataAndRefreshHolderView(mState);
                triggleData();
            }

        } else {
            mHolder.setDataAndRefreshHolderView(mData.get(position));
        }
        return mHolder.mBaseHolderView;
    }

    /**
     * 加载更多的数据
     * 耗时操作在子线程中完成
     */
    protected void triggleData(){
        if(mTask == null) {
            mTask = new LoadMoreThread();
            ThreadPoolFactory.createNormalThreadPoolProxy().executor(mTask);
        }
    }


    private class LoadMoreThread implements Runnable {
        @Override
        public void run() {
             ArrayList<T> loadList = null;
            try {
                loadList = loadMoreData();

                //对loadList做判断
                if(loadList == null) {
                    //加载失败
                    mState = LoadViewHolder.LOAD_ERROR;
                } else if(loadList.size() == PAGERSIZE) {
                    //还有数据可以加载
                    mState = LoadViewHolder.LOAD_MORE;
                } else {
                    //已无更多数据
                    mState = LoadViewHolder.LOAD_NONE;
                }
            } catch (Exception e) {
                e.printStackTrace();
                mState = LoadViewHolder.LOAD_ERROR;
            }
            //更新ui
            final ArrayList<T> finalLoadList = loadList;
            final int finalState = mState;
            UIUtils.postSafeTask(new Runnable() {
                @Override
                public void run() {
                    if(finalLoadList != null) {
                        mData.addAll(finalLoadList);
                    }
                    notifyDataSetChanged();
                    mLoadViewHolder.setDataAndRefreshHolderView(finalState);
                }
            });
            mTask = null;
        }
    }

    protected abstract ArrayList<T> loadMoreData();

    /**
     * 加载的item
     * @return
     */
    private BaseHolder getLoadViewHolder() {
        if(mLoadViewHolder == null) {
            mLoadViewHolder = new LoadViewHolder();
        }
        return mLoadViewHolder;
    }

    /**
     * 判断是否加载数据
     * @return
     */
    public boolean hasMore() {
        return false;
    }

    /**
     *  返回具体的holder
     * @return
     */
    protected abstract BaseHolder getViewHolder(int position);
}
