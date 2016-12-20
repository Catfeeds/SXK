package com.example.cfwifine.sxk.Section.PublishNC.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.cfwifine.sxk.R;
import com.example.cfwifine.sxk.Section.ClassifyNC.Model.ClassifyCateModel;

import java.util.List;

public class CateoryRecycleViewAdapter extends RecyclerView.Adapter<CateoryRecycleViewAdapter.ViewHolder> {
    public List<ClassifyCateModel.CategoryListBean> datas = null;

    private CateoryRecycleViewAdapter.OnItemClickListener mOnItemClickListener;

    public interface  OnItemClickListener{
        void OnItemClick(View view, int position,String title);
    }

    public void setOnItemClickListener(CateoryRecycleViewAdapter.OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }
    public CateoryRecycleViewAdapter(List<ClassifyCateModel.CategoryListBean> datas) {
        this.datas = datas;
    }

    //创建新View，被LayoutManager所调用
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cateory_item, viewGroup, false);
        ViewHolder vh = new ViewHolder(view);
        return vh;
    }

    //将数据与界面进行绑定的操作
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        viewHolder.mTextView.setText(datas.get(position).getName());

        if (mOnItemClickListener != null) {
            viewHolder.content_layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mOnItemClickListener.OnItemClick(view,datas.get(position).getCategoryid(),datas.get(position).getName());
                }
            });
        }

    }

    //获取数据的数量
    @Override
    public int getItemCount() {
        return datas.size();
    }

    //自定义的ViewHolder，持有每个Item的的所有界面元素
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView mTextView;
        public RelativeLayout content_layout;

        public ViewHolder(View view) {
            super(view);
            mTextView = (TextView) view.findViewById(R.id.text);
            content_layout = (RelativeLayout) view.findViewById(R.id.catety_content_layout);
        }
    }
}
