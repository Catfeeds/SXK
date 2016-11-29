package com.example.cfwifine.sxk.Section.CommunityNC.View;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.w4lle.library.NineGridAdapter;

import java.util.List;

/**
 * Created by cfwifine on 16/11/26.
 */

public class NineGridViewAdapter extends NineGridAdapter {


    public NineGridViewAdapter(Context context, List list) {
        super(context, list);
    }

    @Override
    public int getCount() {
        return (list == null) ? 0 : list.size();
    }

    @Override
    public String getUrl(int position) {
        return getItem(position) == null ? null : ((Image)getItem(position)).getUrl();
    }

    @Override
    public Object getItem(int position) {
        return (list == null) ? null : list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int i, View view) {
        ImageView iv = new ImageView(context);
        iv.setScaleType(ImageView.ScaleType.CENTER_CROP);
        iv.setBackgroundColor(Color.parseColor("#f5f5f5"));
        Picasso.with(context).load(getUrl(i)).placeholder(new ColorDrawable(Color.parseColor("#f5f5f5"))).into(iv);
        return iv;
    }
}
