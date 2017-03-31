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
import com.example.cfwifine.sxk.Section.MineNC.Controller.RentAndSale.MineRent.Model.MineItemWaitReceGoodsModel;

import java.util.List;


public class MineWaitReceGoodsListAdapter extends RecyclerView.Adapter<MineWaitReceGoodsListAdapter.ViewHolder> {
    private final List<MineItemWaitReceGoodsModel.OrderListBean> classifyDataSource;
    private Context mContext;
    private LayoutInflater mInflater;


    private MineWaitReceGoodsListAdapter.OnItemClickListener mOnItemClickListener;

    public interface  OnItemClickListener{
        void OnItemClick(View view, int maintainid,String oddNumber);
    }
    public void setOnItemClickListener(MineWaitReceGoodsListAdapter.OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }

    public MineWaitReceGoodsListAdapter(Context mContext, List<MineItemWaitReceGoodsModel.OrderListBean> classifyDataSource) {
        this.mContext = mContext;
        this.classifyDataSource = classifyDataSource;
    }


    @Override
    public MineWaitReceGoodsListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_mine_publish_waitreceivegoods_list, parent, false);
        MineWaitReceGoodsListAdapter.ViewHolder vh = new MineWaitReceGoodsListAdapter.ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(final MineWaitReceGoodsListAdapter.ViewHolder holder, final int position) {
        holder.name.setText(classifyDataSource.get(position).getRent().getName());
        if (classifyDataSource.get(position).getRent().getDescription().trim().length() > 15){
            holder.description.setText(classifyDataSource.get(position).getRent().getDescription().trim().substring(0,15)+"...");
        }else {
            holder.description.setText(classifyDataSource.get(position).getRent().getDescription());
        }
        double rentPrice = classifyDataSource.get(position).getRent().getRentPrice();
        holder.price.setText("¥ "+ String.format("%.2f",rentPrice/100)+"/天");
        double marketPrice = classifyDataSource.get(position).getRent().getMarketPrice();
        holder.rentPrice.setText("市场价： ¥ "+String.format("%.2f",marketPrice/100));
        holder.ordernumber.setText(classifyDataSource.get(position).getOddNumber());
        String picUrl = BaseInterface.ClassfiyGetAllHotBrandImgUrl + classifyDataSource.get(position).getRent().getImgList().get(0);
        Glide.with(mContext).load(picUrl).diskCacheStrategy(DiskCacheStrategy.ALL).placeholder(R.drawable.home_placeholder).animate(R.anim.glide_animal).into(holder.pic);

        if (mOnItemClickListener != null){
            holder.delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mOnItemClickListener.OnItemClick(view,classifyDataSource.get(position).getOrderid(),classifyDataSource.get(position).getOddNumber());
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
        Button delete;
        public ViewHolder(View itemView) {
            super(itemView);

            frameLayout = (LinearLayout) itemView.findViewById(R.id.waits_cell);
            name = (TextView)itemView.findViewById(R.id.waits_name);
            description = (TextView)itemView.findViewById(R.id.waits_descript);
            price = (TextView)itemView.findViewById(R.id.waits_price);
            pic = (ImageView)itemView.findViewById(R.id.waits_pic);
            rentPrice = (TextView)itemView.findViewById(R.id.waits_rentprice);
            delete = (Button)itemView.findViewById(R.id.waits_confirm_btn);
            ordernumber = (TextView)itemView.findViewById(R.id.waits_ordernumber);
        }
    }
}
