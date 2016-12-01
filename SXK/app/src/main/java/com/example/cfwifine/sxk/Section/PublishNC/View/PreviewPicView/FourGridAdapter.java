package com.example.cfwifine.sxk.Section.PublishNC.View.PreviewPicView;

import android.content.Context;
import android.view.View;

import java.util.List;

/**
 * 适配器
 */
public abstract class FourGridAdapter {
    protected Context context;
    protected List list;

    public FourGridAdapter(Context context, List list) {
        this.context = context;
        this.list = list;
    }

    public abstract int getCount();

    public abstract String getUrl(int positopn);

    public abstract Object getItem(int position);

    public abstract long getItemId(int position);

    public abstract View getView(int i, View view);
}
