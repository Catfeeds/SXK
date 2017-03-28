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
import com.example.cfwifine.sxk.Section.HomeNC.Model.ThreeBlockModel;
import com.example.cfwifine.sxk.Utils.LogUtil;

import java.util.List;


public class HotDetailListAdapter extends RecyclerView.Adapter<HotDetailListAdapter.ViewHolder> {
    private Context mContext;
    private LayoutInflater mInflater;
    private List<HomeHotDetalListModel.TopicBean.RentListBean> classifyDataSource;

    private HotDetailListAdapter.OnItemClickListener mOnItemClickListener;

    public interface  OnItemClickListener{
        void OnItemClick(View view, int maintainid);
    }
    public void setOnItemClickListener(HotDetailListAdapter.OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }

    public HotDetailListAdapter(Context mContext, List<HomeHotDetalListModel.TopicBean.RentListBean> hotDataSource) {
        this.mContext = mContext;
        this.classifyDataSource = hotDataSource;
        mInflater = LayoutInflater.from(mContext);
    }


    @Override
    public HotDetailListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(mInflater.inflate(R.layout.item_threeblock_main_list, parent, false));
    }

    @Override
    public void onBindViewHolder(final HotDetailListAdapter.ViewHolder holder, final int position) {
        LogUtil.e("分类数据源"+classifyDataSource.get(position).getName());
        holder.name.setText(classifyDataSource.get(position).getName());
        String description = classifyDataSource.get(position).getDescription();
        if (description.trim().length() > 25){
            holder.description.setText(description.substring(0,25)+"...");
        }else {
            holder.description.setText(description);
        }
        double marketPrice = classifyDataSource.get(position).getMarketPrice();
        holder.price.setText("市场价：¥ "+ String.format("%.2f",marketPrice/100));
        double rentPrice = classifyDataSource.get(position).getRentPrice();
        holder.solePrice.setText("租价：¥ "+ String.format("%.2f",rentPrice/100)+"/天");

        String picUrl = BaseInterface.ClassfiyGetAllHotBrandImgUrl + classifyDataSource.get(position).getImgList().get(0);
        Glide.with(mContext).load(picUrl).diskCacheStrategy(DiskCacheStrategy.ALL).placeholder(R.drawable.home_placeholder).animate(R.anim.glide_animal).into(holder.pic);

        if (mOnItemClickListener != null){
            holder.frameLayout.setOnClickListener(new View.OnClickListener() {
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
        TextView name,description,price,solePrice;
        ImageView pic;

        public ViewHolder(View itemView) {
            super(itemView);

            frameLayout = (LinearLayout) itemView.findViewById(R.id.threeblock_cell);
            name = (TextView)itemView.findViewById(R.id.threeblock_name);
            description = (TextView)itemView.findViewById(R.id.threeblock_descript);
            price = (TextView)itemView.findViewById(R.id.threeblock_price);
            pic = (ImageView)itemView.findViewById(R.id.threeblock_pic);
            solePrice = (TextView) itemView.findViewById(R.id.sole_price);

        }
    }
}
