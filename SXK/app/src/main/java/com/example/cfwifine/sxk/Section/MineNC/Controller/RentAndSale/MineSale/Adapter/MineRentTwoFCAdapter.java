package com.example.cfwifine.sxk.Section.MineNC.Controller.RentAndSale.MineSale.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.cfwifine.sxk.BaseAC.BaseInterface;
import com.example.cfwifine.sxk.R;
import com.example.cfwifine.sxk.Section.MineNC.Controller.RentAndSale.MineSale.Model.RentOneModel;
import com.example.cfwifine.sxk.Section.MineNC.Controller.RentAndSale.MineSale.Model.RentTwoModel;

import java.util.List;


public class MineRentTwoFCAdapter extends RecyclerView.Adapter<MineRentTwoFCAdapter.ViewHolder> {
    private final List<RentTwoModel.PurchaseListBean> classifyDataSource;
    private Context mContext;
    private LayoutInflater mInflater;


    private MineRentTwoFCAdapter.OnItemClickListener mOnItemClickListener;

    public interface  OnItemClickListener{
        void OnItemClick(View view, int maintainid);
    }
    public void setOnItemClickListener(MineRentTwoFCAdapter.OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }

    public MineRentTwoFCAdapter(Context mContext, List<RentTwoModel.PurchaseListBean> classifyDataSource) {
        this.mContext = mContext;
        this.classifyDataSource = classifyDataSource;
    }


    @Override
    public MineRentTwoFCAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_mine_renttwo_list, parent, false);
        MineRentTwoFCAdapter.ViewHolder vh = new MineRentTwoFCAdapter.ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(final MineRentTwoFCAdapter.ViewHolder holder, final int position) {
        holder.name.setText(classifyDataSource.get(position).getName());
        if (classifyDataSource.get(position).getDescription().trim().length()>15){
            holder.description.setText(classifyDataSource.get(position).getDescription().trim().substring(0,15)+"...");
        }else {
            holder.description.setText(classifyDataSource.get(position).getDescription());
        }
        double adverturePrice = classifyDataSource.get(position).getAdvancePrice();
        holder.adverturePrice.setText("¥"+String.format("%.2f",adverturePrice/100));
        double marketPrice = classifyDataSource.get(position).getMarketPrice();
        holder.marketPrice.setText("市场价：¥"+String.format("%.2f",marketPrice/100));
        double salePrice = classifyDataSource.get(position).getSellingPrice();
        holder.salePrice.setText("¥"+String.format("%.2f",salePrice/100));
        String picUrl = BaseInterface.ClassfiyGetAllHotBrandImgUrl + classifyDataSource.get(position).getImgList().get(0);
        Glide.with(mContext).load(picUrl).diskCacheStrategy(DiskCacheStrategy.ALL).placeholder(R.drawable.home_placeholder).animate(R.anim.glide_animal).into(holder.pic);

    }

    @Override
    public int getItemCount() {
        return classifyDataSource != null ? classifyDataSource.size() : 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        LinearLayout frameLayout;
        TextView name,description,adverturePrice,marketPrice,salePrice;
        ImageView pic;

        public ViewHolder(View itemView) {
            super(itemView);
            frameLayout = (LinearLayout) itemView.findViewById(R.id.minenosail_cell);
            name = (TextView)itemView.findViewById(R.id.minenosail_name);
            description = (TextView)itemView.findViewById(R.id.minenosail_descript);
            pic = (ImageView)itemView.findViewById(R.id.minenosail_pic);
            adverturePrice = (TextView) itemView.findViewById(R.id.adverture_price);
            marketPrice = (TextView) itemView.findViewById(R.id.market_price);
            salePrice = (TextView) itemView.findViewById(R.id.sale_price);
        }
    }
}
