package com.example.cfwifine.sxk.Section.CommunityNC.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.cfwifine.sxk.BaseAC.BaseInterface;
import com.example.cfwifine.sxk.R;
import com.example.cfwifine.sxk.Section.CommunityNC.Model.TopicListModel;
import com.example.cfwifine.sxk.Section.CommunityNC.View.Image;
import com.example.cfwifine.sxk.Utils.LogUtil;
import com.squareup.picasso.Picasso;
import com.w4lle.library.NineGridAdapter;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

/**
 * Created by cfwifine on 16/11/26.
 */

public class NineGridViewAdapter extends NineGridAdapter {

    private List<TopicListModel.TopicListBean.ImgListBean> data = null;

    public NineGridViewAdapter(Context context, List<TopicListModel.TopicListBean.ImgListBean> list) {
        super(context, list);
        this.data = list;
    }

    @Override
    public int getCount() {
        return (data == null) ? 0 : data.size();
    }

    @Override
    public String getUrl(int position) {
        return getItem(position) == null ? null : ((Image)getItem(position)).getUrl();
//        return null;
    }

    @Override
    public Object getItem(int position) {
        return (data == null) ? null : data.get(position);
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
        LogUtil.e("图片地址"+list.get(i));
        String picUrl = BaseInterface.ClassfiyGetAllHotBrandImgUrl+ data.get(i).getImage();
//        Picasso.with(context).load(list.get(i).toString()).placeholder(new ColorDrawable(Color.parseColor("#f5f5f5"))).into(iv);
        Glide.with(context).load(picUrl).fitCenter().placeholder(R.drawable.home_placeholder).into(iv);
        return iv;
    }
}
