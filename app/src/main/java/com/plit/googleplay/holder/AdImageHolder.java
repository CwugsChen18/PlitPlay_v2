package com.plit.googleplay.holder;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.plit.googleplay.R;
import com.plit.googleplay.base.MyApplication;
import com.plit.googleplay.ui.IMageViewPager;
import com.plit.googleplay.utils.Cons;
import com.plit.googleplay.utils.LogUtils;
import com.plit.googleplay.utils.UIUtils;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * @author Cwugs.Chen.
 * @time 2016/8/23  19:59
 * @desc ${TODD}
 */
public class AdImageHolder extends BaseHolder<ArrayList<String>> implements ViewPager.OnPageChangeListener, View.OnTouchListener {

    private IMageViewPager item_home_picture_pager;
    private LinearLayout item_home_picture_container_indicator;
    private AutoSlideThread mAutoSlideThread;

    // private ArrayList<String> mData;
    @Override
    public void refreshHolderView(ArrayList<String> mData) {
      //  mData = mData;
        item_home_picture_pager.setAdapter(new AdImageAdapter(mData));
        LogUtils.logI("size", mData.size() + "初始化");
        for (int i = 0; i < mData.size(); i++) {
            final ImageView idot = new ImageView(UIUtils.getContext());
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(UIUtils.dip2px(5), UIUtils.dip2px(5));
            idot.setImageResource(R.drawable.indicator_normal);
            if(i == 0) {
                idot.setImageResource(R.drawable.indicator_selected);
            }
            params.bottomMargin = UIUtils.dip2px(5);
            params.rightMargin = UIUtils.dip2px(5);
            item_home_picture_container_indicator.addView(idot, params);
        }
        //监听图片滑动监听
        item_home_picture_pager.setOnPageChangeListener(this);
        //设置当前位置
        int mCurrentPosition = Integer.MAX_VALUE/2;
        mCurrentPosition = mCurrentPosition - mCurrentPosition/mData.size();
        item_home_picture_pager.setCurrentItem(mCurrentPosition);
        //设置自动滑动
        mAutoSlideThread = new AutoSlideThread();
        mAutoSlideThread.start();

        //设置touch监听，当点击上去时不自动轮播
        item_home_picture_pager.setOnTouchListener(this);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (event.getAction()) {
            case  MotionEvent.ACTION_DOWN:
                //停止自动轮播
                mAutoSlideThread.stop();
                break;
            case  MotionEvent.ACTION_UP:

                break;
            case  MotionEvent.ACTION_CANCEL:
                mAutoSlideThread.start();
                break;
        }
        return true;
    }

    private class AutoSlideThread implements Runnable {
        @Override
        public void run() {
            //获取当前位置
            int currentItem = item_home_picture_pager.getCurrentItem();
            //设置延时时间
            MyApplication.getHandler().postDelayed(this, 2000);
            currentItem ++;
            item_home_picture_pager.setCurrentItem(currentItem);
        }

        public void start() {
            run();
        }

        public void stop() {
            MyApplication.getHandler().removeCallbacks(this);
        }
    }

    @Override
    public View initHolderView() {
        final View adView = View.inflate(UIUtils.getContext(), R.layout.item_home_viewpager, null);
        item_home_picture_container_indicator = (LinearLayout) adView.findViewById(R.id.item_home_picture_container_indicator);
        item_home_picture_pager = (IMageViewPager) adView.findViewById(R.id.item_home_picture_pager);
        return adView;
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        position = position%mData.size();
        //添加页面圆点
      //  LogUtils.logI("vp", position + "");
      //  LogUtils.logI("size", mData.size() + "");
        for (int i = 0; i < mData.size(); i++) {
            ImageView iv = (ImageView) item_home_picture_container_indicator.getChildAt(i);
            if(i == position) {
                iv.setImageResource(R.drawable.indicator_selected);
            } else {
                iv.setImageResource(R.drawable.indicator_normal);
            }
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    private class AdImageAdapter extends PagerAdapter {
        private ArrayList<String> mData;
        public AdImageAdapter(ArrayList<String> mData) {
            this.mData = mData;
        }

        @Override
        public int getCount() {
            return mData == null ? 0 : Integer.MAX_VALUE;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            position = position%mData.size();

            ImageView iv = new ImageView(UIUtils.getContext());
            iv.setAdjustViewBounds(true);
            iv.setScaleType(ImageView.ScaleType.FIT_XY);
          //  LogUtils.logI("vp", Cons.IMAGE_URL + mData.get(position));
            Picasso.with(UIUtils.getContext())
                    .load(Cons.IMAGE_URL + mData.get(position))
                    .into(iv);
            container.addView(iv);
            return iv;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }
}
