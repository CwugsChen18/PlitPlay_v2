package com.plit.googleplay.base;

import android.content.Context;
import android.view.View;
import android.widget.FrameLayout;

import com.plit.googleplay.R;
import com.plit.googleplay.factory.ThreadPoolFactory;
import com.plit.googleplay.utils.UIUtils;

/**
 * @author Cwugs.Chen.
 * @time 2016/8/20  13:45
 * @desc ${TODD}
 */
public abstract class LoadPagerView extends FrameLayout {

    //创建返回需要的视图
    private View mSuccessView;
    private View mErrorView;
    private View mLoadingView;
    private View mEmptyView;

    //添加显示加载的view的flag
    public static final int STATE_EMPTY = 2;
    public static final int STATE_ERROR = 1;
    public static final int STATE_LOADING = 3;
    public static final int STATE_SUCCESS = 0;

    //指定当前状态
    public int mCurrentState = STATE_LOADING;
    private TaskThread mTaskThread;

    public LoadPagerView(Context context) {
        super(context);
        //初始化view
        initView();
    }

    private void initView() {
        final Context context = UIUtils.getContext();
        mErrorView = View.inflate(context, R.layout.pager_error, null);
        mLoadingView = View.inflate(context, R.layout.pager_loading, null);
        mEmptyView = View.inflate(context, R.layout.pager_empty, null);
        //添加到父容器
        addView(mErrorView);
        addView(mLoadingView);
        addView(mEmptyView);

        refreshCurrentState();
    }

    private void refreshCurrentState() {

        mErrorView.setVisibility(mCurrentState == STATE_ERROR ? VISIBLE :GONE);
        mEmptyView.setVisibility(mCurrentState == STATE_EMPTY ? VISIBLE :GONE);
        mLoadingView.setVisibility(mCurrentState == STATE_LOADING ? VISIBLE :GONE);

        //页面显示错误时，点击重新加载
        mErrorView.findViewById(R.id.error_btn_retry).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                triggleData();
            }
        });

        //加载成功的视图
        if(mSuccessView == null && mCurrentState == STATE_SUCCESS) {
            mSuccessView = initSuccessView();
            this.addView(mSuccessView);

            //显示mSuccessView
            mSuccessView.setVisibility(mCurrentState == STATE_SUCCESS ? VISIBLE :GONE);
        }
    }



    //获取数据
    public void triggleData() {
        //数据加载完成，不在加载数据
        //mTaskThread不为空   正在加载时不在重新加载
        if(mCurrentState != STATE_SUCCESS && mTaskThread == null) {
            //开始触发时，先刷新
            mCurrentState = STATE_LOADING;
            refreshCurrentState();

            //加载数据需要在子线程中进行
            mTaskThread = new TaskThread();
            //使用线程池加载数据
            ThreadPoolFactory.createNormalThreadPoolProxy().executor(mTaskThread);
           // new Thread(mTaskThread).start();
        }

    }

    private class TaskThread implements Runnable {

        @Override
        public void run() {

            //获取数据
            int state = initData().getState();
            //处理shuju
            mCurrentState = state;
            /********子线程不能更新ui,更新ui是判断当前线程是否是主线程**********/
            UIUtils.postSafeTask(new Runnable() {
                @Override
                public void run() {
                    refreshCurrentState();
                }
            });
            //数据加载完成  允许再次加载
            mTaskThread = null;
        }
    }

    /**
     * 加载数据的方法由子类实现
     * @return  当前显示的视图的flag
     */
    public abstract LoadingDataResult initData();

    /**
     *
     * @return  返回数据加载完成的视图
     */
    protected abstract View initSuccessView();

    /**
     * 定义一个枚举类，设置智能返回给定的值，增强程序的稳定性
     */
    public enum LoadingDataResult {

        SUCCESS(STATE_SUCCESS), EMPTY(STATE_EMPTY), ERROR(STATE_ERROR);

        int state;
        public int getState() {
            return state;
        }
        private LoadingDataResult(int state) {
            this.state = state;
        }
    }
}
