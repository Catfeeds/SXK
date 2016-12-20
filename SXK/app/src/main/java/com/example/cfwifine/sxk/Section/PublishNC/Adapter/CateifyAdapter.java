package com.example.cfwifine.sxk.Section.PublishNC.Adapter;

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
import com.example.cfwifine.sxk.Section.ClassifyNC.Model.ClassfiyBrandModel;
import com.example.cfwifine.sxk.Section.CommunityNC.Adapter.ComRecycleViewAdapter;
import com.example.cfwifine.sxk.Section.PublishNC.Model.BrandBean;

import java.util.List;


public class CateifyAdapter extends RecyclerView.Adapter<CateifyAdapter.ViewHolder> {
    private Context mContext;
    private List<BrandBean> mDatas;
    private LayoutInflater mInflater;
    private List<ClassfiyBrandModel.BrandListBean> dataSource;

    private CateifyAdapter.OnItemClickListener mOnItemClickListener;

    public interface  OnItemClickListener{
        void OnItemClick(View view, String name);
    }
    public void setOnItemClickListener(CateifyAdapter.OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }

    public CateifyAdapter(Context mContext, List<BrandBean> mDatas, List<ClassfiyBrandModel.BrandListBean> brandNameList) {
        this.mContext = mContext;
        this.mDatas = mDatas;
        this.dataSource = brandNameList;
        mInflater = LayoutInflater.from(mContext);
    }

    public List<BrandBean> getDatas() {
        return mDatas;
    }

    public CateifyAdapter setDatas(List<BrandBean> datas,List<ClassfiyBrandModel.BrandListBean> dataSource) {
        mDatas = datas;
        dataSource = dataSource;
        return this;
    }

    @Override
    public CateifyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(mInflater.inflate(R.layout.item_cateify, parent, false));
    }

    @Override
    public void onBindViewHolder(final CateifyAdapter.ViewHolder holder, final int position) {
        final BrandBean brandBean = mDatas.get(position);
        String picUrl = BaseInterface.ClassfiyGetAllHotBrandImgUrl + dataSource.get(position).getImg();
        Glide.with(mContext).load(picUrl).diskCacheStrategy(DiskCacheStrategy.ALL).placeholder(R.drawable.home_placeholder).animate(R.anim.glide_animal).into(holder.avatar);
        holder.tvCity.setText(dataSource.get(position).getName());
        if (mOnItemClickListener != null){
            holder.frameLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mOnItemClickListener.OnItemClick(view,dataSource.get(position).getName());
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mDatas != null ? mDatas.size() : 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvCity;
        ImageView avatar;
        LinearLayout frameLayout;

        public ViewHolder(View itemView) {
            super(itemView);
            tvCity = (TextView) itemView.findViewById(R.id.brand_text);
            avatar = (ImageView) itemView.findViewById(R.id.brand_pic);
            frameLayout = (LinearLayout) itemView.findViewById(R.id.brand_cell);
        }
    }
}
