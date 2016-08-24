package com.plit.googleplay.listener;

import android.support.v4.view.ViewPager;
import android.util.Log;

import com.plit.googleplay.base.BaseFragment;
import com.plit.googleplay.factory.FragmentFactory;
import com.plit.googleplay.utils.LogUtils;

/**
 * @author Cwugs.Chen.
 * @time 2016/8/20  18:58
 * @desc ${TODD}
 */
public class TabPageChangedListener implements ViewPager.OnPageChangeListener {
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        LogUtils.logI("gp", "被选中" + position);
        //被选中是才加载界面
        //获取当前的fragment   数据未加载完成就开始调用数据
        BaseFragment fragment = FragmentFactory.createFragment(position);
        //触发加载数据
        if(fragment == null) {
            Log.i("main","------) nu ll");
        }
        fragment.mLoadPagerView.triggleData();
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
