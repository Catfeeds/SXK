package com.example.cfwifine.sxk.Section.CommunityNC.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.cfwifine.sxk.BaseAC.BaseInterface;
import com.example.cfwifine.sxk.R;
import com.example.cfwifine.sxk.Section.CommunityNC.Model.CommunityTopicListModel;
import com.example.cfwifine.sxk.Section.PublishNC.Model.TestModel;
import com.example.cfwifine.sxk.Utils.LogUtil;

import java.util.ArrayList;
import java.util.List;

public class TopicListRecycleAdapter extends RecyclerView.Adapter<TopicListRecycleAdapter.ViewHolder> {
    public List<CommunityTopicListModel.ModuleListBean> datas = null;
    private Context context;
    public TopicListRecycleAdapter(Context context, List<CommunityTopicListModel.ModuleListBean> datas) {
        this.context = context;
        this.datas = datas;
    }
    private TopicListRecycleAdapter.OnItemClickListener mOnItemClickListener;

    public interface  OnItemClickListener{
        void OnItemClick(View view, int topicid);
    }
    public void setOnItemClickListener(TopicListRecycleAdapter.OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }
    //创建新View，被LayoutManager所调用
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_topic_list, viewGroup, false);
        ViewHolder vh = new ViewHolder(view);
        return vh;
    }

    //将数据与界面进行绑定的操作
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        Glide.with(context).load(BaseInterface.ClassfiyGetAllHotBrandImgUrl + datas.get(position).getImg()).placeholder(R.drawable.home_placeholder).into(viewHolder.topPic);
        viewHolder.topPic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mOnItemClickListener.OnItemClick(view,datas.get(position).getModuleid());
            }
        });
        viewHolder.detail.setText(datas.get(position).getName());
    }

    //获取数据的数量
    @Override
    public int getItemCount() {
        return datas.size();
    }

    //自定义的ViewHolder，持有每个Item的的所有界面元素
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView topPic;
        public TextView detail;
        CardView check;

        public ViewHolder(View view) {
            super(view);
            topPic = (ImageView)view.findViewById(R.id.topiclist_pic);
            detail = (TextView)view.findViewById(R.id.topiclist_text);
            check = (CardView)view.findViewById(R.id.check);
        }
    }
}
