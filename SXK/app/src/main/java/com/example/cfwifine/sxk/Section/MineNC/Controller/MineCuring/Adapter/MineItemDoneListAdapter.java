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
import com.example.cfwifine.sxk.Section.MineNC.Controller.MineCuring.Model.MineItemDoneMoel;
import com.example.cfwifine.sxk.Section.MineNC.Controller.MineRent.Model.MineItemWaitReceGoodsModel;
import com.example.cfwifine.sxk.Utils.LogUtil;

import java.util.List;


public class MineItemDoneListAdapter extends RecyclerView.Adapter<MineItemDoneListAdapter.ViewHolder> {
    private final List<MineItemDoneMoel.OrderListBean> classifyDataSource;
    private Context mContext;
    private LayoutInflater mInflater;


    private MineItemDoneListAdapter.OnItemClickListener mOnItemClickListener;

    public interface  OnItemClickListener{
        void OnItemClick(View view, int maintainid, String oddNumber);
    }
    public void setOnItemClickListener(MineItemDoneListAdapter.OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }

    public MineItemDoneListAdapter(Context mContext, List<MineItemDoneMoel.OrderListBean> classifyDataSource) {
//        mInflater = LayoutInflater.from(mContext);
        this.mContext = mContext;
        this.classifyDataSource = classifyDataSource;
    }


    @Override
    public MineItemDoneListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_mine_publish_dones_list, parent, false);
        MineItemDoneListAdapter.ViewHolder vh = new MineItemDoneListAdapter.ViewHolder(view);
        return vh;
//        return new ViewHolder(mInflater.inflate(R.layout.item_mine_nopass_list, parent, false));
    }

    @Override
    public void onBindViewHolder(final MineItemDoneListAdapter.ViewHolder holder, final int position) {

        LogUtil.e("审核中数据源"+classifyDataSource.get(position));
        holder.name.setText(classifyDataSource.get(position).getMaintain().getName());
        holder.description.setText(classifyDataSource.get(position).getMaintain().getKeyword());
        holder.price.setText("¥ "+ String.valueOf((double)(Math.round(classifyDataSource.get(position).getMaintain().getPrice())/100.0)));
        String picUrl = BaseInterface.ClassfiyGetAllHotBrandImgUrl + classifyDataSource.get(position).getMaintain().getImg();
        Glide.with(mContext).load(picUrl).diskCacheStrategy(DiskCacheStrategy.ALL).placeholder(R.drawable.home_placeholder).animate(R.anim.glide_animal).into(holder.pic);

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
        public ViewHolder(View itemView) {
            super(itemView);

            frameLayout = (LinearLayout) itemView.findViewById(R.id.dones_cell);
            name = (TextView)itemView.findViewById(R.id.dones_name);
            description = (TextView)itemView.findViewById(R.id.dones_descript);
            price = (TextView)itemView.findViewById(R.id.dones_price);
            pic = (ImageView)itemView.findViewById(R.id.dones_pic);
            rentPrice = (TextView)itemView.findViewById(R.id.dones_rentprice);
        }
    }
}
