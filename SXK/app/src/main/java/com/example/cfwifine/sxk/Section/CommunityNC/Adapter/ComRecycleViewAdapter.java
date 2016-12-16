package com.example.cfwifine.sxk.Section.CommunityNC.Adapter;

import android.app.Activity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.cfwifine.sxk.BaseAC.BaseInterface;
import com.example.cfwifine.sxk.R;
import com.example.cfwifine.sxk.Section.CommunityNC.Model.CommunityTopicListModel;
import com.example.cfwifine.sxk.Section.CommunityNC.Model.TopicListModel;
import com.example.cfwifine.sxk.Utils.DateUtils;
import com.example.cfwifine.sxk.Utils.LogUtil;
import com.example.cfwifine.sxk.Utils.ScreenUtil;
import com.example.cfwifine.sxk.Utils.SharedPreferencesUtils;
import com.example.cfwifine.sxk.Utils.TimeUtils;
import com.example.cfwifine.sxk.View.CircleImageView;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.w4lle.library.NineGridlayout;

import org.json.JSONException;
import org.json.JSONObject;

import java.sql.Time;
import java.util.List;

public class ComRecycleViewAdapter extends RecyclerView.Adapter<ComRecycleViewAdapter.ViewHolder> {
    public List<String> Stringdatas = null;
    public String headerPic = "";
    public List<CommunityTopicListModel.ModuleListBean> topic = null;
    public List<TopicListModel.TopicListBean> topicList = null;
    private Activity context;

    private ComRecycleViewAdapter.OnItemClickListener mOnItemClickListener;

    public interface  OnItemClickListener{
        void OnItemClick(View view, int topicid, int position);
    }
    public void setOnItemClickListener(ComRecycleViewAdapter.OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }

    public ComRecycleViewAdapter(Activity context, String picUrl, List<CommunityTopicListModel.ModuleListBean> topicList, List<TopicListModel.TopicListBean> list) {
        this.context = context;
        this.headerPic = picUrl;
        this.topic = topicList;
        this.topicList = list;
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
    public void onBindViewHolder(final ViewHolder viewHolder, final int position) {
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

            Glide.with(context).load(headerPic).placeholder(R.drawable.home_placeholder).fitCenter().into(viewHolder.header_pic);
            // 话题列表的Recycleview
            TopicListRecycleAdapter topicListRecycleAdapter = new TopicListRecycleAdapter(context, topic);
            LinearLayoutManager layoutManager = new LinearLayoutManager(context);
            layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
            viewHolder.topicListRV.setLayoutManager(layoutManager);
            viewHolder.topicListRV.setAdapter(topicListRecycleAdapter);
        } else {
            viewHolder.headerView.setVisibility(View.GONE);
        }

        /**
         * 朋友圈详情
         */
        String topicHeaderUserUrl = topicList.get(position).getHeadimgurl();
        Glide.with(context).load(topicHeaderUserUrl).diskCacheStrategy(DiskCacheStrategy.ALL).placeholder(R.drawable.user_header_image_placeholder).animate(R.anim.glide_animal).into(viewHolder.tiopicHeaderPic);
//        ImageLoader.getInstance().displayImage(topicHeaderUserUrl,viewHolder.tiopicHeaderPic);
        viewHolder.topic_username.setText(topicList.get(position).getNickname());
        viewHolder.topic_content.setText(topicList.get(position).getContent());

        LogUtil.e("" + TimeUtils.milliseconds2String(topicList.get(position).getCreatetime() * 1000l));
        viewHolder.topic_during.setText(TimeUtils.milliseconds2String(topicList.get(position).getCreatetime() * 1000l));

        // 图片
        NineGridViewAdapter nineGridViewAdapter = new NineGridViewAdapter(context, topicList.get(position).getImgList());
        viewHolder.nineGridlayout.setAdapter(nineGridViewAdapter);
        viewHolder.nineGridlayout.setOnItemClickListerner(new NineGridlayout.OnItemClickListerner() {
            @Override
            public void onItemClick(View view, int position) {
                Log.e("点击了", "" + position);
            }
        });

        /**
         * 注意！！！复用机制的原理，必须对没有起作用的cell也进行处理
         */
        if (topicList.get(position).getLikeList().size() == 0) {
            viewHolder.raiseRV.setVisibility(View.GONE);
        } else {
            viewHolder.raiseRV.setVisibility(View.VISIBLE);
        }
        if (topicList.get(position).getCommentList().size() == 0) {
            viewHolder.commentRV.setVisibility(View.GONE);
        } else {
            viewHolder.commentRV.setVisibility(View.VISIBLE);
        }
        if (topicList.get(position).getCommentList().size() == 0 && topicList.get(position).getLikeList().size() == 0) {
            viewHolder.sanjiaoxing.setVisibility(View.GONE);
        } else {
            viewHolder.sanjiaoxing.setVisibility(View.VISIBLE);
        }

        String username = String.valueOf(SharedPreferencesUtils.getParam(context, BaseInterface.USERNAME,""));
//        for (int i = 0; i < topicList.get(position).getLikeList().size();i++){
//            if (username.equals(topicList.get(position).getLikeList().get(i))){
//                viewHolder.raiseBtn.setImageResource(R.drawable.com_raised);
//            }else {
//                viewHolder.raiseBtn.setImageResource(R.drawable.com_raise);
//            }
//        }

        // 点赞RecycleView
        RaiseRecycleAdapter raiseRecycleAdapter = new RaiseRecycleAdapter(topicList.get(position).getLikeList());
        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        viewHolder.raiseRV.setLayoutManager(layoutManager);
        viewHolder.raiseRV.setAdapter(raiseRecycleAdapter);


        // 评论 RecycleView
        CommentRecycleAdapter commentRecycleAdapter = new CommentRecycleAdapter(topicList.get(position).getCommentList());
        LinearLayoutManager layoutManagers = new LinearLayoutManager(context);
        layoutManagers.setOrientation(LinearLayoutManager.VERTICAL);
        viewHolder.commentRV.setLayoutManager(layoutManagers);
        viewHolder.commentRV.setAdapter(commentRecycleAdapter);

        // 点赞按钮
        viewHolder.raiseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mOnItemClickListener.OnItemClick(view,topicList.get(position).getTopicid(),position);


            }
        });

    }

    //获取数据的数量
    @Override
    public int getItemCount() {
        return topicList.size();
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
        LinearLayout headerView, commentView, sanjiaoxing;
        ImageView header_pic;
        CircleImageView tiopicHeaderPic;
        TextView topic_username, topic_content, topic_during;
        ImageButton raiseBtn, commentBtn;


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
            tiopicHeaderPic = (CircleImageView) view.findViewById(R.id.topic_header_pic);
            topic_username = (TextView) view.findViewById(R.id.topic_username);
            topic_content = (TextView) view.findViewById(R.id.topic_contente);
            topic_during = (TextView) view.findViewById(R.id.topic_during);
            raiseBtn = (ImageButton) view.findViewById(R.id.topic_raise);
            commentBtn = (ImageButton) view.findViewById(R.id.topic_comment);
            sanjiaoxing = (LinearLayout) view.findViewById(R.id.sanjiaoxing);
        }
    }
}
