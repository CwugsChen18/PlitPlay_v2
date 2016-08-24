package com.plit.googleplay.holder;

import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.plit.googleplay.R;
import com.plit.googleplay.beans.CategoryBeans;
import com.plit.googleplay.utils.Cons;
import com.plit.googleplay.utils.UIUtils;
import com.squareup.picasso.Picasso;

/**
 * @author Cwugs.Chen.
 * @time 2016/8/20  21:42
 * @desc ${TODD}
 */

public class CateIconHolder extends BaseHolder<CategoryBeans> {


    private LinearLayout item_category_item_1;
    private ImageView item_category_icon_1;
    private TextView item_category_name_1;

    private LinearLayout item_category_item_2;
    private ImageView item_category_icon_2;
    private TextView item_category_name_2;

    private LinearLayout item_category_item_3;
    private ImageView item_category_icon_3;
    private TextView item_category_name_3;

    @Override
    protected void refreshHolderView(CategoryBeans mData) {

        initIcon(item_category_name_1, item_category_icon_1, mData.getName1(), mData.getUrl1());
        initIcon(item_category_name_2, item_category_icon_2, mData.getName2(), mData.getUrl2());
        initIcon(item_category_name_3, item_category_icon_3, mData.getName3(), mData.getUrl3());
        /********使用Picasso加载图片**********//*
        Picasso.with(UIUtils.getContext())
                .load(Cons.IMAGE_URL + mData.getIconUrl())
                .error(R.mipmap.ic_launcher)
                .into(item_appinfo_iv_icon);*/
    }

    private void initIcon(TextView tv, ImageView iv, final String name, String url) {
        final ViewGroup parent = (ViewGroup) iv.getParent();
        if(!(TextUtils.isEmpty(name) && TextUtils.isEmpty(url))) {
            tv.setText(name);
            Picasso.with(UIUtils.getContext()).load(Cons.IMAGE_URL + url).into(iv);
        } else {
            parent.setVisibility(View.INVISIBLE);
        }
        //设置点击事件
        parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(UIUtils.getContext(), name, Toast.LENGTH_SHORT).show();
            }
        });
    }


    @Override
    public View initHolderView() {
        final View view = View.inflate(UIUtils.getContext(), R.layout.item_category_info, null);
        item_category_item_1 = (LinearLayout) view.findViewById(R.id.item_category_item_1);
        item_category_icon_1 = (ImageView) view.findViewById(R.id.item_category_icon_1);
        item_category_name_1 = (TextView) view.findViewById(R.id.item_category_name_1);

        item_category_item_2 = (LinearLayout) view.findViewById(R.id.item_category_item_2);
        item_category_icon_2 = (ImageView) view.findViewById(R.id.item_category_icon_2);
        item_category_name_2 = (TextView) view.findViewById(R.id.item_category_name_2);

        item_category_item_3 = (LinearLayout) view.findViewById(R.id.item_category_item_3);
        item_category_icon_3 = (ImageView) view.findViewById(R.id.item_category_icon_3);
        item_category_name_3 = (TextView) view.findViewById(R.id.item_category_name_3);

        return view;
    }
}
