package com.example.cfwifine.sxk.Section.MineNC.Controller.MineCollection;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.cfwifine.sxk.R;
import com.example.cfwifine.sxk.Section.MineNC.Controller.MineFollow.Helpers;
import com.example.cfwifine.sxk.Section.MineNC.Controller.MineFollow.Model.FollowListModel;
import com.example.cfwifine.sxk.Section.MineNC.Controller.MineFollow.SlidingView;
import com.example.cfwifine.sxk.Utils.LogUtil;
import com.example.cfwifine.sxk.Utils.TimeUtils;
import com.example.cfwifine.sxk.View.CircleImageView;

import java.text.SimpleDateFormat;
import java.util.Locale;


public class CollectionSlideViewRecycleViewAdapter extends RecyclerView.Adapter<CollectionSlideViewRecycleViewAdapter.MyViewHolder> implements SlidingView.IonSlidingButtonListener {

    private Context mContext;

    private IonSlidingViewClickListener mIDeleteBtnClickListener;

    private MineCollectionModel.CollectionBean mDatas =null;

    private SlidingView mMenu = null;

    public CollectionSlideViewRecycleViewAdapter(Context context, MineCollectionModel.CollectionBean dataSource) {

        mContext = context;
        mIDeleteBtnClickListener = (IonSlidingViewClickListener) context;
        mDatas = dataSource;
    }

    @Override
    public int getItemCount() {
        return mDatas.getList().size();
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
//        String picUrl = mDatas.getList().get(position);
//        Glide.with(mContext).load(picUrl).diskCacheStrategy(DiskCacheStrategy.ALL).placeholder(R.drawable.user_header_image_placeholder).animate(R.anim.glide_animal).into(holder.circleImageView);
//        SimpleDateFormat DEFAULT_SDF = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault());
//        holder.textView.setText(mDatas.getList().get(position).getNickname());
//        holder.layout_content.getLayoutParams().width = Helpers.getScreenWidth(mContext);
//        LogUtil.e("世家戳"+mDatas.getUserList().get(position).getCreatetime());
//        holder.texts.setText(TimeUtils.milliseconds2String(mDatas.getUserList().get(position).getCreatetime() * 1000l,DEFAULT_SDF));
////        holder.texts.setText(String.valueOf(mDatas.get(position).getCreatetime()));
//        holder.selde.setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                if (menuIsOpen()) {
//                    closeMenu();
//                } else {
//                    int n = holder.getLayoutPosition();
//                    mIDeleteBtnClickListener.onItemClick(v, n);
//                }
//
//            }
//        });
//        holder.btn_Delete.setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                int n = holder.getLayoutPosition();
//                mIDeleteBtnClickListener.onDeleteBtnCilck(v, mDatas.getUserList().get(position).getUserid(),n);
//            }
//        });
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup arg0, int arg1) {

        View view = LayoutInflater.from(mContext).inflate(R.layout.layouts_item, arg0,false);
        MyViewHolder holder = new MyViewHolder(view);

        return holder;
    }



    class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView btn_Delete;
        public TextView textView;
        public ViewGroup layout_content;
        public CircleImageView circleImageView;
        public TextView texts;
        LinearLayout selde;
        public MyViewHolder(View itemView) {
            super(itemView);
            btn_Delete = (TextView) itemView.findViewById(R.id.tv_delete);
            textView = (TextView) itemView.findViewById(R.id.text);
            layout_content = (ViewGroup) itemView.findViewById(R.id.layout_content);
            circleImageView = (CircleImageView)itemView.findViewById(R.id.headportrait);
            texts = (TextView)itemView.findViewById(R.id.texts);
            selde = (LinearLayout)itemView.findViewById(R.id.slide_view);
            ((SlidingView) itemView).setSlidingButtonListener(CollectionSlideViewRecycleViewAdapter.this);
        }
    }

    public void addData(int position) {
//        mDatas.add(position, "测试");
        notifyItemInserted(position);
    }

    public void removeData(int position){
//        mDatas.getUserList().remove(position);
        notifyItemRemoved(position);

    }


    @Override
    public void onMenuIsOpen(View view) {
        mMenu = (SlidingView) view;
    }


    @Override
    public void onDownOrMove(SlidingView slidingButtonView) {
        if(menuIsOpen()){
            if(mMenu != slidingButtonView){
                closeMenu();
            }
        }
    }


    public void closeMenu() {
        mMenu.closeMenu();
        mMenu = null;

    }

    public Boolean menuIsOpen() {
        if(mMenu != null){
            return true;
        }
        Log.i("asd","mMenuΪnull");
        return false;
    }



    public interface IonSlidingViewClickListener {
        void onItemClick(View view, int position);
        void onDeleteBtnCilck(View view, int userid, int position);
    }
}

