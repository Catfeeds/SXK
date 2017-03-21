package com.example.cfwifine.sxk.Section.HomeNC.Adapter;

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
import com.example.cfwifine.sxk.Section.HomeNC.Model.HomeHotDetalListModel;
import com.example.cfwifine.sxk.Section.HomeNC.Model.PurchaseListModel;
import com.example.cfwifine.sxk.Section.MineBuyPlus.Model.PurchasePreListModel;
import com.example.cfwifine.sxk.Utils.LogUtil;

import java.util.List;


public class PurchaseDetailListAdapter extends RecyclerView.Adapter<PurchaseDetailListAdapter.ViewHolder> {
    private Context mContext;
    private LayoutInflater mInflater;
    private List<PurchaseListModel.PurchaseListBean> classifyDataSource;

    private PurchaseDetailListAdapter.OnItemClickListener mOnItemClickListener;



    public interface  OnItemClickListener{
        void OnItemClick(View view, int maintainid);
    }
    public void setOnItemClickListener(PurchaseDetailListAdapter.OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }

    public PurchaseDetailListAdapter(Context mContext, List<PurchaseListModel.PurchaseListBean> hotDataSource) {
        this.mContext = mContext;
        this.classifyDataSource = hotDataSource;
        mInflater = LayoutInflater.from(mContext);
    }


    @Override
    public PurchaseDetailListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(mInflater.inflate(R.layout.item_purchaise_main_list, parent, false));
    }

    @Override
    public void onBindViewHolder(final PurchaseDetailListAdapter.ViewHolder holder, final int position) {
        LogUtil.e("分类数据源"+classifyDataSource.get(position).getName());
        holder.name.setText(classifyDataSource.get(position).getName());
//        holder.description.setText(classifyDataSource.get(position).getKeyword());
        holder.price.setText("¥ "+ String.valueOf((double)(Math.round(classifyDataSource.get(position).getAdvancePrice())/100.0))+"/天");

        String picUrl = BaseInterface.ClassfiyGetAllHotBrandImgUrl + classifyDataSource.get(position).getImgList().get(0);
        Glide.with(mContext).load(picUrl).diskCacheStrategy(DiskCacheStrategy.ALL).placeholder(R.drawable.home_placeholder).animate(R.anim.glide_animal).into(holder.pic);

        if (mOnItemClickListener != null){
            holder.frameLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
//                    mOnItemClickListener.OnItemClick(view,classifyDataSource.get(position).());
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

        public ViewHolder(View itemView) {
            super(itemView);

            frameLayout = (LinearLayout) itemView.findViewById(R.id.threeblock_cell);
            name = (TextView)itemView.findViewById(R.id.threeblock_name);
            description = (TextView)itemView.findViewById(R.id.threeblock_descript);
            price = (TextView)itemView.findViewById(R.id.threeblock_price);
            pic = (ImageView)itemView.findViewById(R.id.threeblock_pic);

        }
    }
}
