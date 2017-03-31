package com.example.cfwifine.sxk.Section.MineNC.Controller.RentAndSale.MineSale.Adapter;

import android.content.Context;
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
import com.example.cfwifine.sxk.Section.MineNC.Controller.RentAndSale.MineSale.Model.RentOneModel;
import com.example.cfwifine.sxk.Section.MineNC.Controller.RentAndSale.MineSale.Model.RentThreeModel;

import java.util.List;


public class MineRentThreFCAdapter extends RecyclerView.Adapter<MineRentThreFCAdapter.ViewHolder> {
    private final List<RentThreeModel.PurchaseListBean> classifyDataSource;
    private Context mContext;
    private LayoutInflater mInflater;


    private MineRentThreFCAdapter.OnItemClickListener mOnItemClickListener;

    public interface  OnItemClickListener{
        void OnItemClick(View view, int maintainid);
    }
    public void setOnItemClickListener(MineRentThreFCAdapter.OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }

    public MineRentThreFCAdapter(Context mContext, List<RentThreeModel.PurchaseListBean> classifyDataSource) {
        this.mContext = mContext;
        this.classifyDataSource = classifyDataSource;
    }


    @Override
    public MineRentThreFCAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_mine_rentthre_list, parent, false);
        MineRentThreFCAdapter.ViewHolder vh = new MineRentThreFCAdapter.ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(final MineRentThreFCAdapter.ViewHolder holder, final int position) {
        holder.name.setText(classifyDataSource.get(position).getName());
        if (classifyDataSource.get(position).getDescription().trim().length()>15){
            holder.description.setText(classifyDataSource.get(position).getDescription().trim().substring(0,15)+"...");
        }else {
            holder.description.setText(classifyDataSource.get(position).getDescription());
        }
//        holder.adverturePrice.setText(classifyDataSource.get(position).get);
        double marketPrice = classifyDataSource.get(position).getMarketPrice();
        holder.marketPrice.setText("市场价：¥"+String.format("%.2f",marketPrice/100));
        double salePrice = classifyDataSource.get(position).getSellingPrice();
        holder.salePrice.setText("¥"+String.format("%.2f",salePrice/100));
        String picUrl = BaseInterface.ClassfiyGetAllHotBrandImgUrl + classifyDataSource.get(position).getImgList().get(0);
        Glide.with(mContext).load(picUrl).diskCacheStrategy(DiskCacheStrategy.ALL).placeholder(R.drawable.home_placeholder).animate(R.anim.glide_animal).into(holder.pic);
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mOnItemClickListener.OnItemClick(view, classifyDataSource.get(position).getPurchaseid());
            }
        });
    }

    @Override
    public int getItemCount() {
        return classifyDataSource != null ? classifyDataSource.size() : 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        LinearLayout frameLayout;
        TextView name,description,adverturePrice,marketPrice,salePrice;
        ImageView pic;
        Button delete;
        public ViewHolder(View itemView) {
            super(itemView);
            frameLayout = (LinearLayout) itemView.findViewById(R.id.minenosail_cell);
            name = (TextView)itemView.findViewById(R.id.minenosail_name);
            description = (TextView)itemView.findViewById(R.id.minenosail_descript);
            pic = (ImageView)itemView.findViewById(R.id.minenosail_pic);
            adverturePrice = (TextView) itemView.findViewById(R.id.adverture_price);
            marketPrice = (TextView) itemView.findViewById(R.id.market_price);
            salePrice = (TextView) itemView.findViewById(R.id.sale_price);
            delete = (Button) itemView.findViewById(R.id.recrive_btn);
        }
    }
}
