package com.plit.googleplay.holder;

import android.annotation.TargetApi;
import android.os.Build;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.nineoldandroids.animation.ObjectAnimator;
import com.nineoldandroids.animation.ValueAnimator;
import com.plit.googleplay.R;
import com.plit.googleplay.beans.ItemBeans;
import com.plit.googleplay.utils.Cons;
import com.plit.googleplay.utils.UIUtils;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * @author Cwugs.Chen.
 * @time 2016/8/25  23:08
 * @desc ${TODD}
 */
public class DetailSafeHolder extends BaseHolder<ItemBeans> implements View.OnClickListener {

    @Bind(R.id.app_detail_safe_iv_arrow)
    ImageView mAppDetailSafeIvArrow;
    @Bind(R.id.app_detail_safe_pic_container)
    LinearLayout mAppDetailSafePicContainer;
    @Bind(R.id.app_detail_safe_des_container)
    LinearLayout mAppDetailSafeDesContainer;
    //默认为折叠
    private boolean isOpen = true;

    @Override
    protected void refreshHolderView(ItemBeans mData) {
        final List<ItemBeans.SafeBean> safes= mData.getSafe();
        for (ItemBeans.SafeBean safe  : safes) {
            final String safeUrl = safe.getSafeUrl();
            final ImageView iv = new ImageView(UIUtils.getContext());
            Picasso.with(UIUtils.getContext()).load(Cons.IMAGE_URL + safeUrl).into(iv);
            mAppDetailSafePicContainer.addView(iv);

            LinearLayout ll = new LinearLayout(UIUtils.getContext());
            final ImageView iv_des = new ImageView(UIUtils.getContext());
            final String safeDesUrl = safe.getSafeDesUrl();
            Picasso.with(UIUtils.getContext()).load(Cons.IMAGE_URL + safeDesUrl).into(iv_des);
            final TextView tv_des = new TextView(UIUtils.getContext());
            tv_des.setText(safe.getSafeDes());
            if(safe.getSafeDesColor() == 0) {
                tv_des.setTextColor(UIUtils.getColor(R.color.app_detail_safe_normal));
            } else {
                tv_des.setTextColor(UIUtils.getColor(R.color.app_detail_safe_warning));
            }
            final int padding = UIUtils.dip2px(4);
            ll.setPadding(padding, padding, padding, padding);
            ll.addView(iv_des);
            ll.addView(tv_des);
            mAppDetailSafeDesContainer.addView(ll);
            doAnimator(true);
        }
    }

    @Override
    public View initHolderView() {
        View view = View.inflate(UIUtils.getContext(), R.layout.item_app_detail_safe_holder, null);
        ButterKnife.bind(this, view);
        //设置点击监听
        view.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        Toast.makeText(UIUtils.getContext(), "折叠", Toast.LENGTH_SHORT).show();
        doAnimator(false);
    }

    private void doAnimator(boolean isAnimator) {
        if(isAnimator) {
            ViewGroup.LayoutParams params = mAppDetailSafeDesContainer.getLayoutParams();
            params.height = 0;
            mAppDetailSafeDesContainer.setLayoutParams(params);
            isOpen = !isOpen;
            return;
        }
        //获取高度
        mAppDetailSafeDesContainer.measure(0, 0); //call onMeasure()
        //测量高度
        int height = mAppDetailSafeDesContainer.getMeasuredHeight();
        int start = 0;
        int end = 0;
        if(isOpen) {
           //当前展开，点击折叠
            //设置折叠的高度
            start = height;
            //设置箭头转动
            ObjectAnimator.ofFloat(mAppDetailSafeIvArrow, "rotation", 180, 0).start();
            //doAction(start, end);
        } else {
            end = height;
            //设置箭头转动
            ObjectAnimator.ofFloat(mAppDetailSafeIvArrow, "rotation", 0, 180).start();

        }
        doAction(start, end);
        isOpen = !isOpen;
    }
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    private void doAction(int start, int end) {
        //创建动画对象
        final ValueAnimator va = ValueAnimator.ofInt(start, end);
        va.start();
        //设置监听获取实时位置信息
        va.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                //获取高度
                int position = (int) valueAnimator.getAnimatedValue();
                //设置给容器
                ViewGroup.LayoutParams params = mAppDetailSafeDesContainer.getLayoutParams();
                params.height = position;
                mAppDetailSafeDesContainer.setLayoutParams(params);
            }
        });
    }
}
