package com.plit.googleplay.ui;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * @author Cwugs.Chen.
 * @time 2016/8/24  13:40
 * @desc ${TODD}
 */
public class IMageViewPager extends ViewPager {

    private float mStartX;
    private float mStartY;
    public IMageViewPager(Context context) {
        super(context);
    }

    public IMageViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    //重写ontouch事件

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case  MotionEvent.ACTION_DOWN:
                mStartX = ev.getRawX();
                mStartY = ev.getRawY();
                break;
            case  MotionEvent.ACTION_UP:

                break;
            case  MotionEvent.ACTION_MOVE:
                final float endX = ev.getRawX();
                final float endY = ev.getRawY();

                if(Math.abs(mStartX - endX) > Math.abs(mStartY - endY)) {
                    //设置不拦截
                    getParent().requestDisallowInterceptTouchEvent(true);
                } else {
                    getParent().requestDisallowInterceptTouchEvent(false);
                }
                break;
        }
        return super.onTouchEvent(ev);
    }
}
