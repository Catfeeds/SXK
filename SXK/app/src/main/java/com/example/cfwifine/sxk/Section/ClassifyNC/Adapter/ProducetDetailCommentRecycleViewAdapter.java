package com.example.cfwifine.sxk.Section.ClassifyNC.Adapter;

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
import com.example.cfwifine.sxk.Section.ClassifyNC.Model.ProductCommentListModel;
import com.example.cfwifine.sxk.Section.CommunityNC.Adapter.CommentRecycleAdapter;
import com.example.cfwifine.sxk.Section.CommunityNC.Adapter.NineGridViewAdapter;
import com.example.cfwifine.sxk.Section.CommunityNC.Adapter.RaiseRecycleAdapter;
import com.example.cfwifine.sxk.Section.CommunityNC.Adapter.TopicListRecycleAdapter;
import com.example.cfwifine.sxk.Section.CommunityNC.Model.CommunityTopicListModel;
import com.example.cfwifine.sxk.Section.CommunityNC.Model.TopicListModel;
import com.example.cfwifine.sxk.Utils.LogUtil;
import com.example.cfwifine.sxk.Utils.ScreenUtil;
import com.example.cfwifine.sxk.Utils.SharedPreferencesUtils;
import com.example.cfwifine.sxk.Utils.TimeUtils;
import com.example.cfwifine.sxk.View.CircleImageView;
import com.w4lle.library.NineGridlayout;

import java.util.List;

public class ProducetDetailCommentRecycleViewAdapter extends RecyclerView.Adapter<ProducetDetailCommentRecycleViewAdapter.ViewHolder> {
    public List<ProductCommentListModel.BrandListBean> mDatas = null;
    private Activity context;
    private ProducetDetailCommentRecycleViewAdapter.OnItemClickListener mOnItemClickListener;
    private int type = -1;

    public interface  OnItemClickListener{
        void OnItemClick(View view, int topicid, int position, int cateid, int picPosition, int modleid);
    }
    public void setOnItemClickListener(ProducetDetailCommentRecycleViewAdapter.OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }

    public ProducetDetailCommentRecycleViewAdapter(Activity context, List<ProductCommentListModel.BrandListBean> list, int i) {
        this.context = context;
        this.mDatas = list;
        this.type = i;
    }

    //创建新View，被LayoutManager所调用
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_product_comcell, viewGroup, false);
        ViewHolder vh = new ViewHolder(view);
        return vh;
    }

    //将数据与界面进行绑定的操作
    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, final int position) {


            /**
             * 朋友圈详情
             */
            String topicHeaderUserUrl = mDatas.get(position).getHeadimgurl();
            Glide.with(context).load(topicHeaderUserUrl).diskCacheStrategy(DiskCacheStrategy.ALL).placeholder(R.drawable.user_header_image_placeholder).animate(R.anim.glide_animal).into(viewHolder.tiopicHeaderPic);
//        ImageLoader.getInstance().displayImage(topicHeaderUserUrl,viewHolder.tiopicHeaderPic);
            viewHolder.topic_username.setText(mDatas.get(position).getNickname());
            viewHolder.topic_content.setText(mDatas.get(position).getContent());

            LogUtil.e("" + TimeUtils.milliseconds2String(mDatas.get(position).getCreatetime() * 1000l));
            viewHolder.topic_during.setText(TimeUtils.milliseconds2String(mDatas.get(position).getCreatetime() * 1000l));

            // 图片
            NineGridViewAdapter nineGridViewAdapter = new NineGridViewAdapter(context, mDatas.get(position).getImgList());
            viewHolder.nineGridlayout.setAdapter(nineGridViewAdapter);
            viewHolder.nineGridlayout.setOnItemClickListerner(new NineGridlayout.OnItemClickListerner() {
                @Override
                public void onItemClick(View view, int picPosition) {
                    Log.e("点击了", "" + picPosition);
//                    mOnItemClickListener.OnItemClick(view, mDatas.get(position).getTopicid(), position, -3, picPosition, -6);

                }
            });




    }


    //获取数据的数量
    @Override
    public int getItemCount() {
        if (type == 1&&mDatas.size()>=3){
            return 3;
        }else {
            return mDatas.size();
        }

    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    //自定义的ViewHolder，持有每个Item的的所有界面元素
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public NineGridlayout nineGridlayout;
        CircleImageView tiopicHeaderPic;
        TextView topic_username, topic_content, topic_during;


        public ViewHolder(View view) {
            super(view);
            nineGridlayout = (NineGridlayout) view.findViewById(R.id.iv_ngrid_layout);
            tiopicHeaderPic = (CircleImageView) view.findViewById(R.id.topic_header_pic);
            topic_username = (TextView) view.findViewById(R.id.topic_username);
            topic_content = (TextView) view.findViewById(R.id.topic_contente);
            topic_during = (TextView) view.findViewById(R.id.topic_during);
        }
    }
}
