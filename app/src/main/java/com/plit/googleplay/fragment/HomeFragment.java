package com.plit.googleplay.fragment;

import android.view.View;
import android.widget.ListView;

import com.plit.googleplay.adapter.HomeAdapter;
import com.plit.googleplay.base.BaseFragment;
import com.plit.googleplay.base.LoadPagerView;
import com.plit.googleplay.beans.HomeBeans;
import com.plit.googleplay.beans.ItemBeans;
import com.plit.googleplay.factory.ListViewFactory;
import com.plit.googleplay.holder.AdImageHolder;
import com.plit.googleplay.protocol.HomeProtocol;
import com.plit.googleplay.utils.HttpUtils;

import java.util.ArrayList;

/**
 * @author Cwugs.Chen.
 * @time 2016/8/19  18:34
 * @desc ${TODD}
 */
public class HomeFragment extends BaseFragment {

    private HomeBeans hBeans;

    @Override
    protected View initSuccessView() {
        ListView lv_home = ListViewFactory.createListView();
        final ArrayList<ItemBeans> itemInfos = hBeans.getArrayList();

        /********在listview头部添加轮播图**********/
        final AdImageHolder holder = new AdImageHolder();
        holder.setDataAndRefreshHolderView(hBeans.getPicture());
        lv_home.addHeaderView(holder.mBaseHolderView);

        lv_home.setAdapter(new HomeAdapter(itemInfos, lv_home));
        return lv_home;
    }

    @Override
    protected LoadPagerView.LoadingDataResult initData() {
        hBeans = HomeProtocol.getInstance().loadData(0);
        LoadPagerView.LoadingDataResult state = HttpUtils.getState(hBeans);
        if(state == LoadPagerView.LoadingDataResult.SUCCESS) {
            return HttpUtils.getState(hBeans.getArrayList());
        }
        return LoadPagerView.LoadingDataResult.ERROR;
    }
}
