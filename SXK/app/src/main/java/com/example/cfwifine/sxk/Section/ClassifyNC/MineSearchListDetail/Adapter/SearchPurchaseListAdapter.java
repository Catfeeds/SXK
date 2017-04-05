package com.example.cfwifine.sxk.Section.ClassifyNC.MineSearchListDetail.Adapter;

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


public class SearchPurchaseListAdapter extends RecyclerView.Adapter<SearchPurchaseListAdapter.ViewHolder> {
    private final List<MineItemCuringModel.OrderListBean> classifyDataSource;
    private Context mContext;
    private LayoutInflater mInflater;


    private SearchPurchaseListAdapter.OnItemClickListener mOnItemClickListener;

    public interface  OnItemClickListener{
        void OnItemClick(View view, int maintainid);
    }
    public void setOnItemClickListener(SearchPurchaseListAdapter.OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }

    public SearchPurchaseListAdapter(Context mContext, List<MineItemCuringModel.OrderListBean> classifyDataSource) {
//        mInflater = LayoutInflater.from(mContext);
        this.mContext = mContext;
        this.classifyDataSource = classifyDataSource;
    }


    @Override
    public SearchPurchaseListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_mine_publish_curings_list, parent, false);
        SearchPurchaseListAdapter.ViewHolder vh = new SearchPurchaseListAdapter.ViewHolder(view);
        return vh;
//        return new ViewHolder(mInflater.inflate(R.layout.item_mine_nopass_list, parent, false));
    }

    @Override
    public void onBindViewHolder(final SearchPurchaseListAdapter.ViewHolder holder, final int position) {

        LogUtil.e("审核中数据源"+classifyDataSource.get(position));
        holder.name.setText(classifyDataSource.get(position).getMaintain().getName());
        String description = classifyDataSource.get(position).getMaintain().getKeyword();
        if (description.trim().length()>25){
            holder.description.setText(classifyDataSource.get(position).getMaintain().getKeyword().trim().substring(0,25)+"...");
        }else {
            holder.description.setText(classifyDataSource.get(position).getMaintain().getKeyword().trim());
        }
        double marketPrice = classifyDataSource.get(position).getMaintain().getPrice();
        holder.price.setText("¥ "+ String.format("%.2f",marketPrice/100));
        String picUrl = BaseInterface.ClassfiyGetAllHotBrandImgUrl + classifyDataSource.get(position).getMaintain().getImg();
        Glide.with(mContext).load(picUrl).diskCacheStrategy(DiskCacheStrategy.ALL).placeholder(R.drawable.home_placeholder).animate(R.anim.glide_animal).into(holder.pic);
        if (mOnItemClickListener != null){
            holder.delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mOnItemClickListener.OnItemClick(view,classifyDataSource.get(position).getOrderid());
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
