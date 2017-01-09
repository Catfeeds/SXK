package com.example.cfwifine.sxk.Section.MineNC.Controller.MineRent.Adapter;

import android.content.Context;
import android.icu.text.DecimalFormat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.cfwifine.sxk.BaseAC.BaseInterface;
import com.example.cfwifine.sxk.R;
import com.example.cfwifine.sxk.Section.MineNC.Controller.MineRent.Model.MineItemHasReceGoodsModel;
import com.example.cfwifine.sxk.Section.MineNC.Controller.MineRent.Model.MineItemWaitReceGoodsModel;
import com.example.cfwifine.sxk.Utils.LogUtil;


import java.util.List;


public class MineHasReceGoodsListAdapter extends RecyclerView.Adapter<MineHasReceGoodsListAdapter.ViewHolder> {
    private final List<MineItemHasReceGoodsModel.BrandListBean> classifyDataSource;
    private Context mContext;
    private LayoutInflater mInflater;


    private MineHasReceGoodsListAdapter.OnItemClickListener mOnItemClickListener;

    public interface  OnItemClickListener{
        void OnItemClick(View view, int maintainid, String oddNumber, int i);
    }
    public void setOnItemClickListener(MineHasReceGoodsListAdapter.OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }

    public MineHasReceGoodsListAdapter(Context mContext, List<MineItemHasReceGoodsModel.BrandListBean> classifyDataSource) {
//        mInflater = LayoutInflater.from(mContext);
        this.mContext = mContext;
        this.classifyDataSource = classifyDataSource;
    }


    @Override
    public MineHasReceGoodsListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_mine_publish_hasreceivegoods_list, parent, false);
        MineHasReceGoodsListAdapter.ViewHolder vh = new MineHasReceGoodsListAdapter.ViewHolder(view);
        return vh;
//        return new ViewHolder(mInflater.inflate(R.layout.item_mine_nopass_list, parent, false));
    }

    @Override
    public void onBindViewHolder(final MineHasReceGoodsListAdapter.ViewHolder holder, final int position) {

        LogUtil.e("审核中数据源"+classifyDataSource.get(position));
        holder.name.setText(classifyDataSource.get(position).getRent().getName());
        holder.description.setText(classifyDataSource.get(position).getRent().getKeyword());
        holder.price.setText("¥ "+ String.valueOf((double)(Math.round(classifyDataSource.get(position).getRent().getRentPrice())/100.0))+"/天");
        holder.rentPrice.setText("市场价 ¥ "+String.valueOf((double)(Math.round(classifyDataSource.get(position).getRent().getMarketPrice())/100.0)));
        holder.ordernumber.setText(classifyDataSource.get(position).getOddNumber());
        String picUrl = BaseInterface.ClassfiyGetAllHotBrandImgUrl + classifyDataSource.get(position).getRent().getImg();
        Glide.with(mContext).load(picUrl).diskCacheStrategy(DiskCacheStrategy.ALL).placeholder(R.drawable.home_placeholder).animate(R.anim.glide_animal).into(holder.pic);

        if (mOnItemClickListener != null){
            holder.reback.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mOnItemClickListener.OnItemClick(view,classifyDataSource.get(position).getOrderid(),classifyDataSource.get(position).getOddNumber(),1);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return classifyDataSource != null ? classifyDataSource.size() : 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView rentPrice;
        LinearLayout frameLayout;
        TextView name,description,price,ordernumber;
        ImageView pic;
        Button delete,reback;
        public ViewHolder(View itemView) {
            super(itemView);

            frameLayout = (LinearLayout) itemView.findViewById(R.id.wait_cell);
            name = (TextView)itemView.findViewById(R.id.wait_name);
            description = (TextView)itemView.findViewById(R.id.wait_descript);
            price = (TextView)itemView.findViewById(R.id.wait_price);
            pic = (ImageView)itemView.findViewById(R.id.wait_pic);
            rentPrice = (TextView)itemView.findViewById(R.id.wait_rentprice);
            delete = (Button)itemView.findViewById(R.id.wait_confirm_btn);
            ordernumber = (TextView)itemView.findViewById(R.id.wait_ordernumber);
            reback = (Button)itemView.findViewById(R.id.wait_reback_btn);
        }
    }
}