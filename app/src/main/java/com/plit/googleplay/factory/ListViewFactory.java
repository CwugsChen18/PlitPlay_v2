package com.plit.googleplay.factory;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.widget.ListView;

import com.plit.googleplay.utils.UIUtils;

/**
 * @author Cwugs.Chen.
 * @time 2016/8/23  13:34
 * @desc ${TODD}
 */
public class ListViewFactory {

    public static ListView createListView() {
        ListView lv = new ListView(UIUtils.getContext());
        /********lv的常规设置**********/
        lv.setSelector(new ColorDrawable(Color.TRANSPARENT));
        lv.setFastScrollEnabled(true);
        lv.setDividerHeight(0);
        lv.setCacheColorHint(Color.TRANSPARENT);
        return lv;
    }
}
