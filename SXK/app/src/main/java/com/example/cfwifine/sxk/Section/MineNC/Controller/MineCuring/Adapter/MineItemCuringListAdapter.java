package com.example.cfwifine.sxk.Section.MineNC.Controller.MineCuring.Adapter;

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
import com.example.cfwifine.sxk.Section.MineNC.Controller.MineRent.Model.MineItemWaitReceGoodsModel;
import com.example.cfwifine.sxk.Utils.LogUtil;

import java.util.List;


public class MineItemCuringListAdapter extends RecyclerView.Adapter<MineItemCuringListAdapter.ViewHolder> {
    private final List<MineItemWaitReceGoodsModel.BrandListBean> classifyDataSource;
    private Context mContext;
    private LayoutInflater mInflater;


    private MineItemCuringListAdapter.OnItemClickListener mOnItemClickListener;

    public interface  OnItemClickListener{
        void OnItemClick(View view, int maintainid, String oddNumber);
    }
    public void setOnItemClickListener(MineItemCuringListAdapter.OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }

    public MineItemCuringListAdapter(Context mContext, List<MineItemWaitReceGoodsModel.BrandListBean> classifyDataSource) {
//        mInflater = LayoutInflater.from(mContext);
        this.mContext = mContext;
        this.classifyDataSource = classifyDataSource;
    }


    @Override
    public MineItemCuringListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_mine_publish_waitreceivegoods_list, parent, false);
        MineItemCuringListAdapter.ViewHolder vh = new MineItemCuringListAdapter.ViewHolder(view);
        return vh;
//        return new ViewHolder(mInflater.inflate(R.layout.item_mine_nopass_list, parent, false));
    }

    @Override
    public void onBindViewHolder(final MineItemCuringListAdapter.ViewHolder holder, final int position) {

        LogUtil.e("审核中数据源"+classifyDataSource.get(position));
        holder.name.setText(classifyDataSource.get(position).getRent().getName());
        holder.description.setText(classifyDataSource.get(position).getRent().getKeyword());
        holder.price.setText("¥ "+ String.valueOf((double)(Math.round(classifyDataSource.get(position).getRent().getRentPrice())/100.0))+"/天");
        holder.rentPrice.setText("市场价 ¥ "+String.valueOf((double)(Math.round(classifyDataSource.get(position).getRent().getMarketPrice())/100.0)));
        holder.ordernumber.setText(classifyDataSource.get(position).getOddNumber());
        String picUrl = BaseInterface.ClassfiyGetAllHotBrandImgUrl + classifyDataSource.get(position).getRent().getImg();
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
