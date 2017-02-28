package com.example.cfwifine.sxk.Section.MineNC.Controller.MineCollection;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.cfwifine.sxk.BaseAC.BaseInterface;
import com.example.cfwifine.sxk.R;
import com.example.cfwifine.sxk.Section.ClassifyNC.Controller.CateListAC;
import com.example.cfwifine.sxk.Section.ClassifyNC.Controller.ProductDetailsAC;
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
        return mDatas.getRentList().size();
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        String picUrl = BaseInterface.ClassfiyGetAllHotBrandImgUrl + mDatas.getRentList().get(position).getImgList().get(0);
        Glide.with(mContext).load(picUrl).diskCacheStrategy(DiskCacheStrategy.ALL).placeholder(R.drawable.user_header_image_placeholder).animate(R.anim.glide_animal).into(holder.collection_pic);
        holder.layout_content.getLayoutParams().width = Helpers.getScreenWidth(mContext);
        holder.name.setText(mDatas.getRentList().get(position).getName());
        holder.keyword.setText(mDatas.getRentList().get(position).getKeyword());
        holder.marketPrice.setText("¥ "+ String.valueOf((double)(Math.round(mDatas.getRentList().get(position).getRentPrice())/100.0))+"/天");
        holder.rentPrice.setText("市场价 ¥ ："+String.valueOf((double)(Math.round(mDatas.getRentList().get(position).getMarketPrice())/100.0)));
        holder.selde.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                if (menuIsOpen()) {
                    closeMenu();
                } else {
                    int n = holder.getLayoutPosition();
                    mIDeleteBtnClickListener.onItemClick(v, n);
                    Intent intent = new Intent(mContext,ProductDetailsAC.class);
                    intent.putExtra("RENTID",mDatas.getRentList().get(position).getRentid());
                    mContext.startActivity(intent);
                }

            }
        });
        holder.btn_Delete.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                int n = holder.getLayoutPosition();
                mIDeleteBtnClickListener.onDeleteBtnCilck(v, mDatas.getRentList().get(position).getRentid(),n);
            }
        });
        holder.layout_content.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext,ProductDetailsAC.class);
                intent.putExtra("RENTID",mDatas.getRentList().get(position).getRentid());
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup arg0, int arg1) {

        View view = LayoutInflater.from(mContext).inflate(R.layout.collection_item, arg0,false);
        MyViewHolder holder = new MyViewHolder(view);

        return holder;
    }



    class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView btn_Delete;
        public ViewGroup layout_content;
        LinearLayout selde;
        ImageView collection_pic;
        TextView name,keyword,rentPrice,marketPrice;
        public MyViewHolder(View itemView) {
            super(itemView);
            btn_Delete = (TextView) itemView.findViewById(R.id.tv_delete);
            layout_content = (ViewGroup) itemView.findViewById(R.id.layout_content);
            selde = (LinearLayout)itemView.findViewById(R.id.slide_view);
            collection_pic = (ImageView)itemView.findViewById(R.id.collection_pic);
            name = (TextView)itemView.findViewById(R.id.collection_name);
            keyword = (TextView)itemView.findViewById(R.id.collection_descript);
            rentPrice = (TextView)itemView.findViewById(R.id.collection_rentprice);
            marketPrice = (TextView)itemView.findViewById(R.id.collection_price);
            ((SlidingView) itemView).setSlidingButtonListener(CollectionSlideViewRecycleViewAdapter.this);
        }
    }

    public void addData(int position) {
//        mDatas.add(position, "测试");
        notifyItemInserted(position);
    }

    public void removeData(int position){
        mDatas.getRentList().remove(position);
//        notifyItemRemoved(position);
        notifyDataSetChanged();
        if (menuIsOpen()) {
            closeMenu();
        }

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
        return false;
    }



    public interface IonSlidingViewClickListener {
        void onItemClick(View view, int position);
        void onDeleteBtnCilck(View view, int rentid, int position);
    }
}

