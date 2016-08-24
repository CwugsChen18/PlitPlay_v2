package com.plit.googleplay.fragment;

import android.view.View;
import android.widget.ListView;

import com.plit.googleplay.adapter.AppAdapter;
import com.plit.googleplay.base.BaseFragment;
import com.plit.googleplay.base.LoadPagerView;
import com.plit.googleplay.beans.ItemBeans;
import com.plit.googleplay.factory.ListViewFactory;
import com.plit.googleplay.protocol.GameProtocol;
import com.plit.googleplay.utils.HttpUtils;

import java.util.ArrayList;

/**
 * @author Cwugs.Chen.
 * @time 2016/8/19  18:34
 * @desc ${TODD}
 */
public class GameFragment extends BaseFragment {

    private ArrayList<ItemBeans> mData;

    @Override
    protected View initSuccessView() {
        ListView lv_app = ListViewFactory.createListView();
        lv_app.setAdapter(new AppAdapter(mData, lv_app));
        return lv_app;
    }

    @Override
    protected LoadPagerView.LoadingDataResult initData() {
        //模拟耗时操作
        mData = GameProtocol.getInstance().loadData(0);
        final LoadPagerView.LoadingDataResult state = HttpUtils.getState(mData);
        if(state == LoadPagerView.LoadingDataResult.SUCCESS) {
            return HttpUtils.getState(mData);
        }

        return LoadPagerView.LoadingDataResult.ERROR;
    }
}
