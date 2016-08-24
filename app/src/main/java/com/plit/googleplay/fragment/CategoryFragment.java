package com.plit.googleplay.fragment;

import android.view.View;
import android.widget.ListView;

import com.plit.googleplay.adapter.CategoryAdapter;
import com.plit.googleplay.base.BaseFragment;
import com.plit.googleplay.base.LoadPagerView;
import com.plit.googleplay.beans.CategoryBeans;
import com.plit.googleplay.factory.ListViewFactory;
import com.plit.googleplay.protocol.CategoryProtocol;
import com.plit.googleplay.utils.HttpUtils;

import java.util.ArrayList;

/**
 * @author Cwugs.Chen.
 * @time 2016/8/19  18:34
 * @desc ${TODD}
 */
public class CategoryFragment extends BaseFragment {

    private ArrayList<CategoryBeans> aBeans;

    @Override
    protected View initSuccessView() {
        ListView lv_app = ListViewFactory.createListView();
        lv_app.setAdapter(new CategoryAdapter(aBeans, lv_app));
        return lv_app;
    }

    @Override
    protected LoadPagerView.LoadingDataResult initData() {
        aBeans = CategoryProtocol.getInstance().loadData(0);
        LoadPagerView.LoadingDataResult state = HttpUtils.getState(aBeans);
        if(state == LoadPagerView.LoadingDataResult.SUCCESS) {
            return HttpUtils.getState(aBeans);
        }
        return LoadPagerView.LoadingDataResult.ERROR;
    }
}
