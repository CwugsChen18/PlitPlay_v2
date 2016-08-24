package com.plit.googleplay.factory;

import android.util.Log;

import com.plit.googleplay.base.BaseFragment;
import com.plit.googleplay.fragment.AppFragment;
import com.plit.googleplay.fragment.CategoryFragment;
import com.plit.googleplay.fragment.GameFragment;
import com.plit.googleplay.fragment.HomeFragment;
import com.plit.googleplay.fragment.HotFragment;
import com.plit.googleplay.fragment.RecmmendFragment;
import com.plit.googleplay.fragment.SubjectFragment;

import java.util.HashMap;

/**
 * @author Cwugs.Chen.
 * @time 2016/8/19  18:25
 * @desc fragment工厂类
 */
public class FragmentFactory {

    public static final int FRAGMENT_HOME = 0;//首页的fragment
    private static final int FRAGMENT_APP = 1;//应用
    private static final int FRAGMENT_GAME = 2;//游戏
    private static final int FRAGMENT_SUBJECT = 3;//专题
    private static final int FRAGMENT_RECOMMOND = 4;//推荐
    private static final int FRAGMENT_CATORY = 5;//分类
    private static final int FRAGMENT_HOT = 6;//排行

    //将创建的fragment存入map中
    public static HashMap<Integer, BaseFragment> fragmentMap = new HashMap<>();

    public static BaseFragment createFragment(int position) {
        //判断是否已有对象，有则返回
        if(fragmentMap.containsKey(position)) {
            Log.i("main","---->"  +position);
            return fragmentMap.get(position);
        }
        BaseFragment fragment = null;
        switch (position) {
            case  FRAGMENT_HOME:
                fragment = new HomeFragment();
                break;
            case  FRAGMENT_APP:
                fragment = new AppFragment();
                break;
            case  FRAGMENT_GAME:
                fragment = new GameFragment();
                break;
            case  FRAGMENT_SUBJECT:
                fragment = new SubjectFragment();
                break;
            case  FRAGMENT_RECOMMOND:
                fragment = new RecmmendFragment();
                break;
            case  FRAGMENT_CATORY:
                fragment = new CategoryFragment();
                break;
            case  FRAGMENT_HOT:
                fragment = new HotFragment();
                break;
        }
        fragmentMap.put(position,fragment);

        return fragment;
    }

}
