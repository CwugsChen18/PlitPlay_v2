package com.plit.googleplay.activity;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.ViewTreeObserver;

import com.astuetz.PagerSlidingTabStripMy;
import com.plit.googleplay.R;
import com.plit.googleplay.adapter.MainFragmentAdapter;
import com.plit.googleplay.factory.FragmentFactory;
import com.plit.googleplay.listener.TabPageChangedListener;
import com.plit.googleplay.utils.UIUtils;

public class MainActivity extends AppCompatActivity {

    private ActionBar mActionBar;
    private DrawerLayout dl_main;
    private ActionBarDrawerToggle mToggle;
    private PagerSlidingTabStripMy tabs;
    private ViewPager viewpager ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        //初始化actionbar
        initActionBar();
        //初始化数据
        initData();
    }

    private void initView() {
        dl_main = (DrawerLayout)findViewById(R.id.dl_main);
        viewpager = (ViewPager)findViewById(R.id.viewpager);
        tabs = (PagerSlidingTabStripMy)findViewById(R.id.tabs);



        mToggle = new ActionBarDrawerToggle(this, dl_main, R.string.open, R.string.close);

        //同步状态
        mToggle.syncState();
        //设置toggle点击监听
        dl_main.setDrawerListener(mToggle);

        //监听tabs点击事件，选中时才加载对应的fragment
        final TabPageChangedListener listener = new TabPageChangedListener();
        tabs.setOnPageChangeListener(listener);
        /**
         * 渲染一个对象,和渲染一个View图所用的时间不是一个量级,
         * 当Fragment对象对象创建完毕的时候,View可以能还没有渲染完成,
         * 但是所有的View都是在loadingPager中渲染的,
         * 所以这个时候loadingFragment这个时候可能会为空,
         * 这个时候触发就会导致后面出现空指针异常
         */
        //加载渲染器
        viewpager.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                //默认监听FRAGMENT_HOME
                listener.onPageSelected(FragmentFactory.FRAGMENT_HOME);
                //移除渲染器
                viewpager.getViewTreeObserver().removeOnGlobalLayoutListener(this);
            }
        });

    }

    private void initData() {
        //获取菜单项
        final String[] main_titles = UIUtils.getStringArr(R.array.main_titles);
        viewpager.setAdapter(new MainFragmentAdapter(getSupportFragmentManager(), main_titles));
        tabs.setViewPager(viewpager);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                mToggle.onOptionsItemSelected(item);
                break;
            default:

        }
        return super.onOptionsItemSelected(item);
    }

    private void initActionBar() {
        // 获取ActionBar
        mActionBar = getSupportActionBar();

        mActionBar.setTitle("GooglePlay");// 设置主title部分
      //  mActionBar.setSubtitle("SubTitle");// 设置子title部分

        mActionBar.setIcon(R.mipmap.ic_launcher);// 设置应用图标

        mActionBar.setDisplayShowTitleEnabled(true);// 设置菜单 标题是否可见
        mActionBar.setDisplayShowHomeEnabled(true);// 设置应用图标是否
        mActionBar.setDisplayUseLogoEnabled(false);// 设置是否显示Logo优先
        mActionBar.setDisplayHomeAsUpEnabled(true);// 设置back按钮是否可见
    }
}
