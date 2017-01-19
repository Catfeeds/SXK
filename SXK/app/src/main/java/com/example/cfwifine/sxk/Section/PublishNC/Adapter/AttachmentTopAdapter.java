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
    int pos =-1;
    private AttachmentTopAdapter.OnItemClickListener mOnItemClickListener;
    private int positions=-1;

    public interface  OnItemClickListener{
        void OnItemClick(View view, List<String> name, String attributeName,int pos);
    }
    public void setOnItemClickListener(AttachmentTopAdapter.OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }

    public AttachmentTopAdapter(Context mContext, List<AttachmentModel.CategoryListBean.AttachListBean> dataSource, int material) {
        this.pos = material;
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
        if (position == positions){
            holder.material.setText(dataSource.get(position).getAttributeValueList().get(pos));
        }else {
//            holder.material.setText(dataSource.get(position).getAttributeValueList().get(pos));
        }

        if (mOnItemClickListener != null){
            holder.frameLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mOnItemClickListener.OnItemClick(view,dataSource.get(position).getAttributeValueList(),dataSource.get(position).getAttributeName(),position);
                }
            });
        }
    }

    public void setData(int ATTRIBUTENAMEPOSITION, int pos){
        this.positions = ATTRIBUTENAMEPOSITION;
        this.pos = pos;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return dataSource != null ? dataSource.size() : 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvCity,material;
        RelativeLayout frameLayout;

        public ViewHolder(View itemView) {
            super(itemView);
            tvCity = (TextView) itemView.findViewById(R.id.attach_text);
            frameLayout = (RelativeLayout) itemView.findViewById(R.id.attach_cell);
            material = (TextView)itemView.findViewById(R.id.attach_textdetail);
        }
    }
}
