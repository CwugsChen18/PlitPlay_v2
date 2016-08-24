package com.plit.googleplay.holder;

import android.view.View;
import android.widget.TextView;

import com.plit.googleplay.beans.CategoryBeans;
import com.plit.googleplay.utils.UIUtils;

/**
 * @author Cwugs.Chen.
 * @time 2016/8/20  21:42
 * @desc ${TODD}
 */

public class CateTitleHolder extends BaseHolder<CategoryBeans> {

    private TextView item_cate_title;

    @Override
    protected void refreshHolderView(CategoryBeans mData) {

        item_cate_title.setText(mData.getTitle());
    }

    @Override
    public View initHolderView() {
        item_cate_title = new TextView(UIUtils.getContext());
        int padding=UIUtils.dip2px(5);
        item_cate_title.setPadding(padding,padding,padding,padding);
        return item_cate_title;
    }
}
