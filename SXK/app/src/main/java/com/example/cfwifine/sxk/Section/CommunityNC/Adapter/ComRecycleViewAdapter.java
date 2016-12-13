package com.example.cfwifine.sxk.Section.CommunityNC.Adapter;

import android.app.Activity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.cfwifine.sxk.R;
import com.example.cfwifine.sxk.Section.CommunityNC.Model.CommunityTopicListModel;
import com.example.cfwifine.sxk.Section.CommunityNC.View.Image;
import com.example.cfwifine.sxk.Utils.LogUtil;
import com.example.cfwifine.sxk.Utils.ScreenUtil;
import com.w4lle.library.NineGridlayout;

import java.util.List;

public class ComRecycleViewAdapter extends RecyclerView.Adapter<ComRecycleViewAdapter.ViewHolder> {
    public List<List<Image>> datas = null;
    public List<String> Stringdatas = null;
    public String headerPic = "";
    public List<CommunityTopicListModel.ModuleListBean> topList= null;

    private Activity context;

    public ComRecycleViewAdapter(Activity context, List<List<Image>> datas, String picUrl, List<CommunityTopicListModel.ModuleListBean> topicList) {
        this.context = context;
        this.datas = datas;
        this.headerPic = picUrl;
        this.topList = topicList;
    }

    //创建新View，被LayoutManager所调用
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_comcell, viewGroup, false);
        ViewHolder vh = new ViewHolder(view);
        return vh;
    }

    //将数据与界面进行绑定的操作
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
//        viewHolder.mTextView.setText(datas.get(position));
        List<Image> itemList = datas.get(position);

        // 判断是不是头部，呵呵
        if (position == 0 && position < 1) {
            viewHolder.headerView.setVisibility(View.VISIBLE);
            LogUtil.e("图片地址" + headerPic);

            /**
             * 适配图片的宽高比例
             */
            int screenWidth = ScreenUtil.getScreenWidth(context);
            ViewGroup.LayoutParams lp = viewHolder.header_pic.getLayoutParams();
            lp.width = screenWidth;
            lp.height = RecyclerView.LayoutParams.WRAP_CONTENT;
            viewHolder.header_pic.setLayoutParams(lp);

//            viewHolder.header_pic.setMaxWidth(screenWidth);
//            viewHolder.header_pic.setMaxHeight(300);

            Glide.with(context).load(headerPic).placeholder(R.drawable.home_placeholder).into(viewHolder.header_pic);
            // 话题列表的Recycleview
            TopicListRecycleAdapter topicListRecycleAdapter = new TopicListRecycleAdapter(context,topList);
            LinearLayoutManager layoutManager = new LinearLayoutManager(context);
            layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
            viewHolder.topicListRV.setLayoutManager(layoutManager);
            viewHolder.topicListRV.setAdapter(topicListRecycleAdapter);
        } else {
            viewHolder.headerView.setVisibility(View.GONE);
        }

        NineGridViewAdapter nineGridViewAdapter = new NineGridViewAdapter(context, itemList);
        viewHolder.nineGridlayout.setAdapter(nineGridViewAdapter);
        viewHolder.nineGridlayout.setOnItemClickListerner(new NineGridlayout.OnItemClickListerner() {
            @Override
            public void onItemClick(View view, int position) {
                Log.e("点击了", "" + position);
            }
        });
        // 点赞RecycleView
        RaiseRecycleAdapter raiseRecycleAdapter = new RaiseRecycleAdapter(Stringdatas);
        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        viewHolder.raiseRV.setLayoutManager(layoutManager);
        viewHolder.raiseRV.setAdapter(raiseRecycleAdapter);

        // 评论 RecycleView
        CommentRecycleAdapter commentRecycleAdapter = new CommentRecycleAdapter(Stringdatas);
        LinearLayoutManager layoutManagers = new LinearLayoutManager(context);
        layoutManagers.setOrientation(LinearLayoutManager.VERTICAL);
        viewHolder.commentRV.setLayoutManager(layoutManagers);
        viewHolder.commentRV.setAdapter(commentRecycleAdapter);


//        }


    }

    //获取数据的数量
    @Override
    public int getItemCount() {
        return datas.size();
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    //自定义的ViewHolder，持有每个Item的的所有界面元素
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView mTextView;
        public NineGridlayout nineGridlayout;
        public RecyclerView raiseRV, commentRV, topicListRV;
        LinearLayout headerView, commentView;
        ImageView header_pic;


        public ViewHolder(View view) {
            super(view);
            mTextView = (TextView) view.findViewById(R.id.text);
            nineGridlayout = (NineGridlayout) view.findViewById(R.id.iv_ngrid_layout);
            raiseRV = (RecyclerView) view.findViewById(R.id.raise_recycleview);
            commentRV = (RecyclerView) view.findViewById(R.id.comment_recycleview);
            headerView = (LinearLayout) view.findViewById(R.id.commentHeaderView);
            commentView = (LinearLayout) view.findViewById(R.id.commentView);
            header_pic = (ImageView) view.findViewById(R.id.community_header_pic);



            topicListRV = (RecyclerView) view.findViewById(R.id.community_topic_list);


        }
    }
}
