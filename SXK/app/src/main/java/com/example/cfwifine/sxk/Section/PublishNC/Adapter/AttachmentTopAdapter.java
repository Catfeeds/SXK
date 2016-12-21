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
import com.example.cfwifine.sxk.Section.ClassifyNC.Model.ClassfiyBrandModel;
import com.example.cfwifine.sxk.Section.PublishNC.Model.AttachmentModel;
import com.example.cfwifine.sxk.Section.PublishNC.Model.BrandBean;

import java.util.List;


public class AttachmentTopAdapter extends RecyclerView.Adapter<AttachmentTopAdapter.ViewHolder> {
    private Context mContext;
    private LayoutInflater mInflater;
    private List<AttachmentModel.CategoryListBean.AttachListBean> dataSource;

    private AttachmentTopAdapter.OnItemClickListener mOnItemClickListener;

    public interface  OnItemClickListener{
        void OnItemClick(View view, List<String> name, String attributeName);
    }
    public void setOnItemClickListener(AttachmentTopAdapter.OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }

    public AttachmentTopAdapter(Context mContext, List<AttachmentModel.CategoryListBean.AttachListBean> dataSource) {
        this.mContext = mContext;
        this.dataSource = dataSource;
        mInflater = LayoutInflater.from(mContext);
    }


    @Override
    public AttachmentTopAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(mInflater.inflate(R.layout.item_attachment, parent, false));
    }

    @Override
    public void onBindViewHolder(final AttachmentTopAdapter.ViewHolder holder, final int position) {
        holder.tvCity.setText(dataSource.get(position).getAttributeName());
        if (mOnItemClickListener != null){
            holder.frameLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mOnItemClickListener.OnItemClick(view,dataSource.get(position).getAttributeValueList(),dataSource.get(position).getAttributeName());
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return dataSource != null ? dataSource.size() : 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvCity;
        RelativeLayout frameLayout;

        public ViewHolder(View itemView) {
            super(itemView);
            tvCity = (TextView) itemView.findViewById(R.id.attach_text);
            frameLayout = (RelativeLayout) itemView.findViewById(R.id.attach_cell);
        }
    }
}
