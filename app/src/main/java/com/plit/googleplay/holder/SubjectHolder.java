package com.plit.googleplay.holder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.plit.googleplay.R;
import com.plit.googleplay.beans.SubjectBeans;
import com.plit.googleplay.utils.Cons;
import com.plit.googleplay.utils.UIUtils;
import com.squareup.picasso.Picasso;

/**
 * @author Cwugs.Chen.
 * @time 2016/8/20  21:42
 * @desc ${TODD}
 */

public class SubjectHolder extends BaseHolder<SubjectBeans> {

    private ImageView item_subject_iv_icon;
    private TextView item_subject_tv_title;

    @Override
    protected void refreshHolderView(SubjectBeans mData) {
        item_subject_tv_title.setText(mData.getDes());

        /********使用Picasso加载图片**********/
        Picasso.with(UIUtils.getContext())
                .load(Cons.IMAGE_URL + mData.getUrl())
                .error(R.mipmap.ic_launcher)
                .into(item_subject_iv_icon);
    }

    @Override
    public View initHolderView() {
        final View view = View.inflate(UIUtils.getContext(), R.layout.item_subject, null);
        item_subject_iv_icon = (ImageView) view.findViewById(R.id.item_subject_iv_icon);
        item_subject_tv_title = (TextView) view.findViewById(R.id.item_subject_tv_title);

        return view;
    }
}
