package com.plit.googleplay.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.ViewGroup;

import com.plit.googleplay.factory.FragmentFactory;

/**
 * @author Cwugs.Chen.
 * @time 2016/8/19  17:29
 * @desc ${TODD}
 */
public class MainFragmentAdapter extends FragmentStatePagerAdapter {

    private String[] main_titles;

    public MainFragmentAdapter(FragmentManager fm, String[] main_titles) {
        super(fm);
        this.main_titles = main_titles;
    }

    @Override
    public Fragment getItem(int position) {
        return FragmentFactory.createFragment(position);
    }

    @Override
    public int getCount() {
        return main_titles == null ? 0 : main_titles.length;
    }
    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        return super.instantiateItem(container, position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        super.destroyItem(container, position, object);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return main_titles[position];
    }
}
