package com.example.cfwifine.sxk.Section.ClassifyNC.Adapter;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cfwifine.sxk.R;
import com.example.cfwifine.sxk.Section.ClassifyNC.Model.ClassfiyBrandModel;
import com.example.cfwifine.sxk.Section.ClassifyNC.Model.ClassfiyHotBrandModel;
import com.example.cfwifine.sxk.Section.PublishNC.Model.BrandBean;

import java.util.List;


public class ClassifyBrandListAdapter extends RecyclerView.Adapter<ClassifyBrandListAdapter.ViewHolder> {
    private Context mContext;
    private List<BrandBean> mDatas;
    private List<ClassfiyHotBrandModel.HotListBean> mHeaderDatas;
    private LayoutInflater mInflater;

    public ClassifyBrandListAdapter(Context mContext, List<BrandBean> mDatas, List<ClassfiyHotBrandModel.HotListBean> mHeaderDatas) {
        this.mContext = mContext;
        this.mDatas = mDatas;
        this.mHeaderDatas = mHeaderDatas;
        mInflater = LayoutInflater.from(mContext);
    }

    public List<BrandBean> getDatas() {
        return mDatas;
    }

    public ClassifyBrandListAdapter setDatas(List<BrandBean> datas) {
        mDatas = datas;
//        mHeaderDatas = mHeaderDatas;
        return this;
    }

    @Override
    public ClassifyBrandListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(mInflater.inflate(R.layout.item_city, parent, false));
    }

    @Override
    public void onBindViewHolder(final ClassifyBrandListAdapter.ViewHolder holder, final int position) {
//        final BrandBean brandBean = mDatas.get(position);

        if (position==0){
            holder.cellHeader.setVisibility(View.VISIBLE);
            holder.cell.setVisibility(View.GONE);
            HeaderRecycleAdapter raiseRecycleAdapter = new HeaderRecycleAdapter(mContext,mHeaderDatas);
            holder.raiseRV.setLayoutManager(new GridLayoutManager(mContext,3));
            holder.raiseRV.setAdapter(raiseRecycleAdapter);
            Log.e("传的值",""+mHeaderDatas.get(0).getImg());

        }else {
            holder.cellHeader.setVisibility(View.GONE);
            holder.cell.setVisibility(View.VISIBLE);
            final BrandBean cityBean = mDatas.get(position);
            holder.tvCity.setText(cityBean.getCity());
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(mContext, "pos:" + position, Toast.LENGTH_SHORT).show();
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
        LinearLayout cellHeader,cell;
        RecyclerView raiseRV;

        public ViewHolder(View itemView) {
            super(itemView);
            tvCity = (TextView) itemView.findViewById(R.id.tvCity);
            avatar = (ImageView) itemView.findViewById(R.id.ivAvatar);
            cell = (LinearLayout) itemView.findViewById(R.id.cate_cell);
            cellHeader = (LinearLayout)itemView.findViewById(R.id.cells);
            raiseRV = (RecyclerView)itemView.findViewById(R.id.rvh);
        }
    }
}
