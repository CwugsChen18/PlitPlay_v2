package com.plit.googleplay.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.plit.googleplay.utils.UIUtils;

/**
 * @author Cwugs.Chen.
 * @time 2016/8/20  11:44
 * @desc 抽取一个fragment基类
 */
public abstract class BaseFragment extends Fragment {

    public LoadPagerView mLoadPagerView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if(mLoadPagerView == null) {
            mLoadPagerView = new LoadPagerView(UIUtils.getContext()) {
                @Override
                public LoadingDataResult initData() {
                    return BaseFragment.this.initData();
                }

                @Override
                protected View initSuccessView() {
                    return BaseFragment.this.initSuccessView();
                }
            };
        }
        //触发加载数据
      //  mLoadPagerView.triggleData();

        return mLoadPagerView;
    }

    protected abstract View initSuccessView();

    protected abstract LoadPagerView.LoadingDataResult initData();
}
