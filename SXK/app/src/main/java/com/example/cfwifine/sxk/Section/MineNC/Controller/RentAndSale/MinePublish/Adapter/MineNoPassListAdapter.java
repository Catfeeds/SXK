package com.example.cfwifine.sxk.Section.MineNC.Controller.RentAndSale.MinePublish.Adapter;

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
import com.example.cfwifine.sxk.Section.MineNC.Controller.RentAndSale.MinePublish.Model.MineItemNoPassModel;

import java.util.List;


public class MineNoPassListAdapter extends RecyclerView.Adapter<MineNoPassListAdapter.ViewHolder> {
    private final List<MineItemNoPassModel.RentListBean> classifyDataSource;
    private Context mContext;
    private LayoutInflater mInflater;


    private MineNoPassListAdapter.OnItemClickListener mOnItemClickListener;

    public interface  OnItemClickListener{
        void OnItemClick(View view, int maintainid);
    }
    public void setOnItemClickListener(MineNoPassListAdapter.OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }

    public MineNoPassListAdapter(Context mContext, List<MineItemNoPassModel.RentListBean> classifyDataSource) {
//        mInflater = LayoutInflater.from(mContext);
        this.mContext = mContext;
        this.classifyDataSource = classifyDataSource;
    }


    @Override
    public MineNoPassListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_mine_nopass_list, parent, false);
        MineNoPassListAdapter.ViewHolder vh = new MineNoPassListAdapter.ViewHolder(view);
        return vh;
//        return new ViewHolder(mInflater.inflate(R.layout.item_mine_nopass_list, parent, false));
    }

    @Override
    public void onBindViewHolder(final MineNoPassListAdapter.ViewHolder holder, final int position) {
        holder.name.setText(classifyDataSource.get(position).getName());
        if (classifyDataSource.get(position).getDescription().trim().length()>25){
            holder.description.setText(classifyDataSource.get(position).getDescription().trim().substring(0,25)+"...");
        }else {
            holder.description.setText(classifyDataSource.get(position).getDescription());
        }
        double rentPrice = classifyDataSource.get(position).getCounterPrice();
        holder.price.setText(" ¥ "+ String.format("%.2f",rentPrice/100));
        String picUrl = BaseInterface.ClassfiyGetAllHotBrandImgUrl + classifyDataSource.get(position).getImgList().get(0);
        Glide.with(mContext).load(picUrl).diskCacheStrategy(DiskCacheStrategy.ALL).placeholder(R.drawable.home_placeholder).animate(R.anim.glide_animal).into(holder.pic);

        if (mOnItemClickListener != null){
            holder.delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mOnItemClickListener.OnItemClick(view,classifyDataSource.get(position).getRentid());
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

            frameLayout = (LinearLayout) itemView.findViewById(R.id.minenopass_cell);
            name = (TextView)itemView.findViewById(R.id.minenopass_name);
            description = (TextView)itemView.findViewById(R.id.minenopass_descript);
            price = (TextView)itemView.findViewById(R.id.minenopass_price);
            pic = (ImageView)itemView.findViewById(R.id.minenopass_pic);
            delete = (Button)itemView.findViewById(R.id.minenopass_delete_btn);
        }
    }
}
