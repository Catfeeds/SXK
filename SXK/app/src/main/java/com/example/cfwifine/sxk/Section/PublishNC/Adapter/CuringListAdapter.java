package com.example.cfwifine.sxk.Section.PublishNC.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.cfwifine.sxk.BaseAC.BaseInterface;
import com.example.cfwifine.sxk.R;
import com.example.cfwifine.sxk.Section.PublishNC.Model.AttachmentModel;
import com.example.cfwifine.sxk.Section.PublishNC.Model.CuringListModel;
import com.example.cfwifine.sxk.Utils.LogUtil;

import java.util.List;


public class CuringListAdapter extends RecyclerView.Adapter<CuringListAdapter.ViewHolder> {
    private Context mContext;
    private LayoutInflater mInflater;
    private List<CuringListModel.MaintainListBean> classifyDataSource;

    private CuringListAdapter.OnItemClickListener mOnItemClickListener;

    public interface  OnItemClickListener{
        void OnItemClick(View view, int maintainid);
    }
    public void setOnItemClickListener(CuringListAdapter.OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }

    public CuringListAdapter(Context mContext, List<CuringListModel.MaintainListBean> classifyDataSource) {
        this.mContext = mContext;
        this.classifyDataSource = classifyDataSource;
        mInflater = LayoutInflater.from(mContext);
    }


    @Override
    public CuringListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(mInflater.inflate(R.layout.item_curing_main_list, parent, false));
    }

    @Override
    public void onBindViewHolder(final CuringListAdapter.ViewHolder holder, final int position) {
        LogUtil.e("分类数据源"+classifyDataSource.get(position).getName());
        holder.name.setText(classifyDataSource.get(position).getName());
        holder.description.setText(classifyDataSource.get(position).getKeyword());
        holder.price.setText("¥ "+ String.valueOf(classifyDataSource.get(position).getPrice()));
        String picUrl = BaseInterface.ClassfiyGetAllHotBrandImgUrl + classifyDataSource.get(position).getImg();
        Glide.with(mContext).load(picUrl).diskCacheStrategy(DiskCacheStrategy.ALL).placeholder(R.drawable.home_placeholder).animate(R.anim.glide_animal).into(holder.pic);

        if (mOnItemClickListener != null){
            holder.frameLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mOnItemClickListener.OnItemClick(view,classifyDataSource.get(position).getMaintainid());
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

            frameLayout = (LinearLayout) itemView.findViewById(R.id.curing_cell);
            name = (TextView)itemView.findViewById(R.id.curing_name);
            description = (TextView)itemView.findViewById(R.id.curing_descript);
            price = (TextView)itemView.findViewById(R.id.curing_price);
            pic = (ImageView)itemView.findViewById(R.id.curing_pic);

        }
    }
}
