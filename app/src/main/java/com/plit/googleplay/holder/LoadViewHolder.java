package com.plit.googleplay.holder;

import android.view.View;
import android.widget.LinearLayout;

import com.plit.googleplay.R;
import com.plit.googleplay.utils.UIUtils;

/**
 * @author Cwugs.Chen.
 * @time 2016/8/22  17:15
 * @desc 加载数据的holder
 */
public class LoadViewHolder extends BaseHolder<Integer> {
    /********设置状态**********/
    public static final int LOAD_MORE = 0;
    public static final int LOAD_NONE = 1;
    public static final int LOAD_ERROR = 2;

    /********获取控件**********/
    private LinearLayout item_loadmore_container_loading;
    private LinearLayout item_loadmore_container_retry;
    private LinearLayout item_loadmore_container_none;
   // private TextView item_loadmore_tv_retry;

    @Override
    protected void refreshHolderView(Integer mData) {
        item_loadmore_container_loading.setVisibility(View.GONE);
        item_loadmore_container_none.setVisibility(View.GONE);
        item_loadmore_container_retry.setVisibility(View.GONE);
        //根据返回的mData判断当前状态
        switch (mData) {
            case  LOAD_MORE:
                item_loadmore_container_loading.setVisibility(View.VISIBLE);
                break;
            case  LOAD_NONE:
                item_loadmore_container_none.setVisibility(View.VISIBLE);
                break;
            case  LOAD_ERROR:
                item_loadmore_container_retry.setVisibility(View.VISIBLE);
                break;
        }
    }

    @Override
    public View initHolderView() {
        final View view = View.inflate(UIUtils.getContext(), R.layout.item_load_more, null);
        item_loadmore_container_loading = (LinearLayout) view.findViewById(R.id.item_loadmore_container_loading);
        item_loadmore_container_retry = (LinearLayout) view.findViewById(R.id.item_loadmore_container_retry);
        item_loadmore_container_none = (LinearLayout) view.findViewById(R.id.item_loadmore_container_none);
       // item_loadmore_tv_retry = (TextView) view.findViewById(R.id.item_loadmore_tv_retry);

        /*item_loadmore_tv_retry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogUtils.logI("home", "可以加载更多吗，我想再试一下");
            }
        });*/

        return view;
    }
}
