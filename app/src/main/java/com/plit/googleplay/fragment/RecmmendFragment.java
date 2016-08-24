package com.plit.googleplay.fragment;

import android.os.SystemClock;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.plit.googleplay.base.BaseFragment;
import com.plit.googleplay.base.LoadPagerView;

/**
 * @author Cwugs.Chen.
 * @time 2016/8/19  18:35
 * @desc ${TODD}
 */
public class RecmmendFragment extends BaseFragment {
    @Override
    protected View initSuccessView() {
        final TextView textView = new TextView(getContext());
        textView.setText("success");
        textView.setGravity(Gravity.CENTER);
        return textView;
    }

    @Override
    protected LoadPagerView.LoadingDataResult initData() {
        //模拟耗时操作
        SystemClock.sleep(2000);

        return LoadPagerView.LoadingDataResult.SUCCESS;
    }
}
