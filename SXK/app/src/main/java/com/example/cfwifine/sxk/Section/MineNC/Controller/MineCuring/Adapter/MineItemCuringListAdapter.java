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
import com.example.cfwifine.sxk.Section.MineNC.Controller.MineCuring.Model.MineItemCuringModel;
import com.example.cfwifine.sxk.Utils.LogUtil;

import java.util.List;


public class MineItemCuringListAdapter extends RecyclerView.Adapter<MineItemCuringListAdapter.ViewHolder> {
    private final List<MineItemCuringModel.OrderListBean> classifyDataSource;
    private Context mContext;
    private LayoutInflater mInflater;


    private MineItemCuringListAdapter.OnItemClickListener mOnItemClickListener;

    public interface  OnItemClickListener{
        void OnItemClick(View view,int type, int maintainid);
    }
    public void setOnItemClickListener(MineItemCuringListAdapter.OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }

    public MineItemCuringListAdapter(Context mContext, List<MineItemCuringModel.OrderListBean> classifyDataSource) {
//        mInflater = LayoutInflater.from(mContext);
        this.mContext = mContext;
        this.classifyDataSource = classifyDataSource;
    }


    @Override
    public MineItemCuringListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_mine_publish_curings_list, parent, false);
        MineItemCuringListAdapter.ViewHolder vh = new MineItemCuringListAdapter.ViewHolder(view);
        return vh;
//        return new ViewHolder(mInflater.inflate(R.layout.item_mine_nopass_list, parent, false));
    }

    @Override
    public void onBindViewHolder(final MineItemCuringListAdapter.ViewHolder holder, final int position) {

        LogUtil.e("审核中数据源"+classifyDataSource.get(position));
        holder.name.setText(classifyDataSource.get(position).getMaintain().getName());
        String description = classifyDataSource.get(position).getMaintain().getKeyword();
        if (description.trim().length()>25){
            holder.description.setText(description.substring(0,25)+"...");
        }else {
            holder.description.setText(description);
        }
        double rentPrice = classifyDataSource.get(position).getMaintain().getPrice();
        holder.price.setText(" ¥ "+ String.format("%.2f",rentPrice/100));
        String picUrl = BaseInterface.ClassfiyGetAllHotBrandImgUrl + classifyDataSource.get(position).getMaintain().getImg();
        Glide.with(mContext).load(picUrl).diskCacheStrategy(DiskCacheStrategy.ALL).placeholder(R.drawable.home_placeholder).animate(R.anim.glide_animal).into(holder.pic);
        if (mOnItemClickListener != null){
            holder.delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mOnItemClickListener.OnItemClick(view,1,classifyDataSource.get(position).getOrderid());
                }
            });
        }
        if (mOnItemClickListener != null){
            holder.frameLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mOnItemClickListener.OnItemClick(view,2,classifyDataSource.get(position).getOrderid());
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return classifyDataSource != null ? classifyDataSource.size() : 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        LinearLayout frameLayout;
        TextView name,description,price;
        ImageView pic;
        Button delete;
        public ViewHolder(View itemView) {
            super(itemView);
            frameLayout = (LinearLayout) itemView.findViewById(R.id.curings_cell);
            name = (TextView)itemView.findViewById(R.id.curings_name);
            description = (TextView)itemView.findViewById(R.id.curings_descript);
            price = (TextView)itemView.findViewById(R.id.curings_price);
            pic = (ImageView)itemView.findViewById(R.id.curings_pic);
            delete = (Button)itemView.findViewById(R.id.curings_confirm_btn);
        }
    }
}
