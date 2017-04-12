package com.example.cfwifine.sxk.Section.MineNC.Controller.RentAndSale.MineRent.Adapter;

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
import com.example.cfwifine.sxk.Section.MineNC.Controller.RentAndSale.MineRent.Model.MineItemHasReceGoodsModel;


import java.util.List;


public class MineHasReceGoodsListAdapter extends RecyclerView.Adapter<MineHasReceGoodsListAdapter.ViewHolder> {
    private final List<MineItemHasReceGoodsModel.OrderListBean> classifyDataSource;
    private Context mContext;
    private LayoutInflater mInflater;


    private MineHasReceGoodsListAdapter.OnItemClickListener mOnItemClickListener;

    public interface  OnItemClickListener{
        void OnItemClick(View view, int maintainid, String oddNumber, int i);
    }
    public void setOnItemClickListener(MineHasReceGoodsListAdapter.OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }

    public MineHasReceGoodsListAdapter(Context mContext, List<MineItemHasReceGoodsModel.OrderListBean> classifyDataSource) {
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
        holder.name.setText(classifyDataSource.get(position).getRent().getName());
        String description = classifyDataSource.get(position).getRent().getDescription();
        if (description.trim().length() > 15){
            holder.description.setText(description.substring(0,15)+"...");
        }else {
            holder.description.setText(description);
        }
        double rentPrice = classifyDataSource.get(position).getRent().getRentPrice();
        holder.price.setText("¥ "+ String.format("%.2f",rentPrice/100)+"/天");
        double marketPrice = classifyDataSource.get(position).getRent().getMarketPrice();
        holder.rentPrice.setText("市场价： ¥ "+String.format("%.2f",marketPrice/100));
        holder.ordernumber.setText(classifyDataSource.get(position).getOddNumber());
        String picUrl = BaseInterface.ClassfiyGetAllHotBrandImgUrl + classifyDataSource.get(position).getRent().getImgList().get(0);
        Glide.with(mContext).load(picUrl).diskCacheStrategy(DiskCacheStrategy.ALL).placeholder(R.drawable.home_placeholder).animate(R.anim.glide_animal).into(holder.pic);

        if (mOnItemClickListener != null){
            holder.reback.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mOnItemClickListener.OnItemClick(view,classifyDataSource.get(position).getOrderid(),classifyDataSource.get(position).getOddNumber(),1);
                }
            });
        }
        if (mOnItemClickListener != null){
            holder.frameLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mOnItemClickListener.OnItemClick(view,classifyDataSource.get(position).getOrderid(),classifyDataSource.get(position).getOddNumber(),2);
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
        Button reback;
        public ViewHolder(View itemView) {
            super(itemView);

            frameLayout = (LinearLayout) itemView.findViewById(R.id.wait_cell);
            name = (TextView)itemView.findViewById(R.id.wait_name);
            description = (TextView)itemView.findViewById(R.id.wait_descript);
            price = (TextView)itemView.findViewById(R.id.wait_price);
            pic = (ImageView)itemView.findViewById(R.id.wait_pic);
            rentPrice = (TextView)itemView.findViewById(R.id.wait_rentprice);
            ordernumber = (TextView)itemView.findViewById(R.id.wait_ordernumber);
            reback = (Button)itemView.findViewById(R.id.wait_reback_btn);
        }
    }
}
