package com.plit.googleplay.fragment;

import android.os.SystemClock;
import android.view.View;
import android.widget.ListView;

import com.plit.googleplay.adapter.SubjectAdapter;
import com.plit.googleplay.base.BaseFragment;
import com.plit.googleplay.base.LoadPagerView;
import com.plit.googleplay.beans.SubjectBeans;
import com.plit.googleplay.factory.ListViewFactory;
import com.plit.googleplay.protocol.SubjectProtocol;
import com.plit.googleplay.utils.HttpUtils;

import java.util.ArrayList;

/**
 * @author Cwugs.Chen.
 * @time 2016/8/19  18:35
 * @desc ${TODD}
 */
public class SubjectFragment extends BaseFragment {

    private ArrayList<SubjectBeans> mBeans;

    @Override
    protected View initSuccessView() {
        ListView lv_sub = ListViewFactory.createListView();
        lv_sub.setAdapter(new SubjectAdapter(mBeans, lv_sub));
        return lv_sub;
    }

    @Override
    protected LoadPagerView.LoadingDataResult initData() {
        //模拟耗时操作
        SystemClock.sleep(2000);

        mBeans = SubjectProtocol.getInstance().loadData(0);
        if(HttpUtils.getState(mBeans) == LoadPagerView.LoadingDataResult.SUCCESS) {
            return LoadPagerView.LoadingDataResult.SUCCESS;
        }

        return LoadPagerView.LoadingDataResult.ERROR;
    }
}
