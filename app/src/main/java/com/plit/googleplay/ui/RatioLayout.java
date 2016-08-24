package com.plit.googleplay.ui;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.FrameLayout;

import com.plit.googleplay.R;

/**
 * @author Cwugs.Chen.
 * @time 2016/8/24  13:40
 * @desc ${TODD}
 */
public class RatioLayout extends FrameLayout {

    private float rate;
    private static final int S_WIDTH = 0;
    private static final int S_HEIGHT = 1;
    private int mode = S_WIDTH;
    public RatioLayout(Context context) {
        super(context);
    }

    public RatioLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.RatioLayout);
        rate = a.getFloat(R.styleable.RatioLayout_rate, 1);
        mode = a.getInt(R.styleable.RatioLayout_mode, 0);

        a.recycle();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        //获取子控件的宽高
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(widthMeasureSpec);

        int childWidth = 0;
        int childheight = 0;

        //获取设置模式
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);

        if(widthMode == MeasureSpec.EXACTLY && mode == S_WIDTH && rate != 0) {
            //以宽度为基准
            childWidth = width - getPaddingLeft() - getPaddingRight();
            childheight = (int) (childWidth / rate + .5f);

            //设置子控件的宽高
            int measureWidth = MeasureSpec.makeMeasureSpec(childWidth, MeasureSpec.EXACTLY);
            int measureHeight = MeasureSpec.makeMeasureSpec(childheight, MeasureSpec.EXACTLY);

            measureChildren(measureWidth, measureHeight);

            childheight = childheight +  getPaddingBottom() + getPaddingTop();
            childWidth  = width;
            setMeasuredDimension(childWidth, childheight);
        } else if(heightMode == MeasureSpec.EXACTLY && mode == S_HEIGHT && rate != 0) {
            //以高度为基准
            childheight = height - getPaddingBottom() - getPaddingTop();
            childWidth = (int) (childheight*rate + .5f);

            //测量子控件的宽高
            int measureWidth = MeasureSpec.makeMeasureSpec(childWidth, MeasureSpec.EXACTLY);
            int measureHeight = MeasureSpec.makeMeasureSpec(childheight, MeasureSpec.EXACTLY);

            measureChildren(measureWidth, measureHeight);

            childheight = height;
            childWidth  = width + getPaddingLeft() + getPaddingRight();
            setMeasuredDimension(childWidth, childheight);
        } else {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        }
    }
}
