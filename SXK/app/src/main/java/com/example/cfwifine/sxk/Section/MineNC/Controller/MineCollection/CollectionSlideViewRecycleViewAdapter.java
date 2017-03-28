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
import com.example.cfwifine.sxk.Section.PublishNC.CuringAC.CuringDetailAC;
import com.example.cfwifine.sxk.Utils.LogUtil;
import com.example.cfwifine.sxk.Utils.TimeUtils;
import com.example.cfwifine.sxk.View.CircleImageView;

import java.text.SimpleDateFormat;
import java.util.Locale;


public class CollectionSlideViewRecycleViewAdapter extends RecyclerView.Adapter<CollectionSlideViewRecycleViewAdapter.MyViewHolder> implements SlidingView.IonSlidingButtonListener {

    private MineCuringCollectionListModel.CollectionBean curingDataSource = null;
    private Context mContext;

    private IonSlidingViewClickListener mIDeleteBtnClickListener;

    private MineCollectionModel.CollectionBean mDatas =null;

    MinePurchaseListModel.CollectionBean purchaseDataSource = null;

    private SlidingView mMenu = null;

    public CollectionSlideViewRecycleViewAdapter(Context context, MineCollectionModel.CollectionBean dataSource, MineCuringCollectionListModel.CollectionBean curingDataSource, MinePurchaseListModel.CollectionBean purchaseDataSource) {
        mContext = context;
        mIDeleteBtnClickListener = (IonSlidingViewClickListener) context;
        mDatas = dataSource;
        this.curingDataSource = curingDataSource;
        this.purchaseDataSource = purchaseDataSource;
    }

    @Override
    public int getItemCount() {
        if (mDatas != null){
            return mDatas.getRentList().size();
        }else if (curingDataSource != null){
            return curingDataSource.getMaintainList().size();
        }else{
            return purchaseDataSource.getPurchaseList().size();
        }
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {

        if (mDatas != null){
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
                    mIDeleteBtnClickListener.onDeleteBtnCilck(v, mDatas.getRentList().get(position).getRentid(),-1,-1,n);
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
        }else if (curingDataSource != null){
            String picUrl = BaseInterface.ClassfiyGetAllHotBrandImgUrl + curingDataSource.getMaintainList().get(position).getImg();
            Glide.with(mContext).load(picUrl).diskCacheStrategy(DiskCacheStrategy.ALL).placeholder(R.drawable.user_header_image_placeholder).animate(R.anim.glide_animal).into(holder.collection_pic);
            holder.layout_content.getLayoutParams().width = Helpers.getScreenWidth(mContext);
            holder.name.setText(curingDataSource.getMaintainList().get(position).getName());
            holder.keyword.setText(curingDataSource.getMaintainList().get(position).getKeyword());
            holder.marketPrice.setText("¥ "+ String.valueOf((double)(Math.round(curingDataSource.getMaintainList().get(position).getPrice())/100.0))+"/天");
            holder.rentPrice.setText("");
            holder.selde.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (menuIsOpen()) {
                        closeMenu();
                    } else {
                        int n = holder.getLayoutPosition();
                        mIDeleteBtnClickListener.onItemClick(v, n);
                        Intent intent = new Intent(mContext,CuringDetailAC.class);
                        intent.putExtra("maintainid",curingDataSource.getMaintainList().get(position).getMaintainid());
                        mContext.startActivity(intent);
                    }

                }
            });
            holder.btn_Delete.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    int n = holder.getLayoutPosition();
                    mIDeleteBtnClickListener.onDeleteBtnCilck(v, -1,curingDataSource.getMaintainList().get(position).getMaintainid(),-1,n);
                }
            });
            holder.layout_content.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(mContext,CuringDetailAC.class);
                    intent.putExtra("maintainid",curingDataSource.getMaintainList().get(position).getMaintainid());
                    mContext.startActivity(intent);
                }
            });
        }else if (purchaseDataSource != null){
            String picUrl = BaseInterface.ClassfiyGetAllHotBrandImgUrl + purchaseDataSource.getPurchaseList().get(position).getImgList().get(0);
            Glide.with(mContext).load(picUrl).diskCacheStrategy(DiskCacheStrategy.ALL).placeholder(R.drawable.user_header_image_placeholder).animate(R.anim.glide_animal).into(holder.collection_pic);
            holder.layout_content.getLayoutParams().width = Helpers.getScreenWidth(mContext);
            holder.name.setText(purchaseDataSource.getPurchaseList().get(position).getName());
            String description = purchaseDataSource.getPurchaseList().get(position).getDescription();
            if (description.trim().length() >= 28){
                String newString = description.substring(0,28)+"...";
                holder.keyword.setText(newString);
            }else {
                holder.keyword.setText(description);
            }

            double marketPri = purchaseDataSource.getPurchaseList().get(position).getMarketPrice();
            holder.txt.setText("市场价：");
            holder.marketPrice.setText(" ¥ "+ String.format("%.2f",marketPri/100));
            double solePrice = purchaseDataSource.getPurchaseList().get(position).getSellingPrice();
            holder.rentPrice.setText("售价： ¥ "+ String.format("%.2f",solePrice/100));
            holder.selde.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (menuIsOpen()) {
                        closeMenu();
                    } else {
                        int n = holder.getLayoutPosition();
                        mIDeleteBtnClickListener.onItemClick(v, n);
                        Intent intent = new Intent(mContext,ProductDetailsAC.class);
                        intent.putExtra("PURCHASEID",purchaseDataSource.getPurchaseList().get(position).getPurchaseid());
                        mContext.startActivity(intent);
                    }

                }
            });
            holder.btn_Delete.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    int n = holder.getLayoutPosition();
                    mIDeleteBtnClickListener.onDeleteBtnCilck(v, -1,-1,purchaseDataSource.getPurchaseList().get(position).getPurchaseid(),n);
                }
            });
            holder.layout_content.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(mContext,ProductDetailsAC.class);
                    intent.putExtra("PURCHASEID",purchaseDataSource.getPurchaseList().get(position).getPurchaseid());
                    mContext.startActivity(intent);
                }
            });
        }

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
        TextView name,keyword,rentPrice,marketPrice,txt;
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
            txt = (TextView)itemView.findViewById(R.id.txt);
        }
    }

    public void addData(int position) {
//        mDatas.add(position, "测试");
        notifyItemInserted(position);
    }

    public void removeData(int position){
        if (mDatas != null){
            mDatas.getRentList().remove(position);
//        notifyItemRemoved(position);
            notifyDataSetChanged();
            if (menuIsOpen()) {
                closeMenu();
            }
        }
        else if (curingDataSource != null){
            curingDataSource.getMaintainList().remove(position);
//        notifyItemRemoved(position);
            notifyDataSetChanged();
            if (menuIsOpen()) {
                closeMenu();
            }
        }else if (purchaseDataSource != null){
            purchaseDataSource.getPurchaseList().remove(position);
//        notifyItemRemoved(position);
            notifyDataSetChanged();
            if (menuIsOpen()) {
                closeMenu();
            }
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
        void onDeleteBtnCilck(View view, int rentid,int maitainid,int purchaseid, int position);
    }
}

