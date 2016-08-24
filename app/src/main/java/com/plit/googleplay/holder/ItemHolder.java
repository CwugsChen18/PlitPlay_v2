package com.plit.googleplay.holder;

import android.text.format.Formatter;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.plit.googleplay.R;
import com.plit.googleplay.beans.ItemBeans;
import com.plit.googleplay.utils.Cons;
import com.plit.googleplay.utils.UIUtils;
import com.squareup.picasso.Picasso;

/**
 * @author Cwugs.Chen.
 * @time 2016/8/20  21:42
 * @desc ${TODD}
 */

public class ItemHolder extends BaseHolder<ItemBeans> {

    private ImageView item_appinfo_iv_icon;
    private TextView item_appinfo_tv_title;
    private RatingBar item_appinfo_rb_stars;
    private TextView item_appinfo_tv_size;
   // private LinearLayout item_appinfo_pcv_progress;
    private TextView item_appinfo_tv_des;

    @Override
    protected void refreshHolderView(ItemBeans mData) {
        item_appinfo_tv_title.setText(mData.getName());
        item_appinfo_rb_stars.setRating(mData.getStars());
        item_appinfo_tv_size.setText(Formatter.formatFileSize(UIUtils.getContext(), mData.getSize()));
        item_appinfo_tv_des.setText(mData.getDes());

        /********使用Picasso加载图片**********/
        Picasso.with(UIUtils.getContext())
                .load(Cons.IMAGE_URL + mData.getIconUrl())
                .error(R.mipmap.ic_launcher)
                .into(item_appinfo_iv_icon);
    }

    @Override
    public View initHolderView() {
        final View view = View.inflate(UIUtils.getContext(), R.layout.item_app_info, null);
        item_appinfo_iv_icon = (ImageView) view.findViewById(R.id.item_appinfo_iv_icon);
        item_appinfo_tv_title = (TextView) view.findViewById(R.id.item_appinfo_tv_title);
        item_appinfo_rb_stars = (RatingBar) view.findViewById(R.id.item_appinfo_rb_stars);
        item_appinfo_tv_size = (TextView) view.findViewById(R.id.item_appinfo_tv_size);
       // item_appinfo_pcv_progress = (LinearLayout) view.findViewById(R.id.item_appinfo_pcv_progress);
        item_appinfo_tv_des = (TextView) view.findViewById(R.id.item_appinfo_tv_des);

        return view;
    }
}
