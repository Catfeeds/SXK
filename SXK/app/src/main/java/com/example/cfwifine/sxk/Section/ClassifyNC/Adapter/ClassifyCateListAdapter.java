package com.example.cfwifine.sxk.Section.ClassifyNC.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.cfwifine.sxk.BaseAC.BaseInterface;
import com.example.cfwifine.sxk.R;
import com.example.cfwifine.sxk.Section.ClassifyNC.Model.SecondCateModel;

import java.util.List;

/**
 * Created by Mr.Yang on 2016/12/13.
 */

public class ClassifyCateListAdapter extends RecyclerView.Adapter<ClassifyCateListAdapter.VH> {

    private Context mContext;
    //热门包
    private List<SecondCateModel.CategoryListBean> mdatas=null;

    public interface OnItemClickLitener {
        void onItemClick(View view, int position);
    }

    private OnItemClickLitener mOnItemClickLitener;

    public void setOnItemClickLitener(OnItemClickLitener onItemClickLitener) {
        this.mOnItemClickLitener = onItemClickLitener;
    }


    public ClassifyCateListAdapter(Context context,List<SecondCateModel.CategoryListBean> mdatas) {
        this.mContext = context;
        this.mdatas = mdatas;
    }

    @Override
    public VH onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.home_cate_item,null);
        VH vh = new VH(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(final VH holder, int position) {
        String picUrl = BaseInterface.ClassfiyGetAllHotBrandImgUrl+mdatas.get(position).getImg();
        Glide.with(mContext)
                .load(picUrl)
                .centerCrop()
                .placeholder(R.drawable.home_placeholder)
                .crossFade()
                .into(holder.mImg);
        holder.mName.setText(mdatas.get(position).getName());

        if (mOnItemClickLitener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int pos = holder.getLayoutPosition();
                    mOnItemClickLitener.onItemClick(holder.itemView, pos);
                }
            });
        }

    }

    @Override
    public int getItemCount() {
        return mdatas.size();
    }

    public static class VH extends RecyclerView.ViewHolder {


        private final ImageView mImg;
        private final TextView mName;
        private final LinearLayout mLayout;

        public VH(View itemView) {
            super(itemView);
            mImg = (ImageView) itemView.findViewById(R.id.home_cate_img);
            mName = (TextView) itemView.findViewById(R.id.home_cate_name);
            mLayout = (LinearLayout) itemView.findViewById(R.id.home_cate);

        }
    }
}
