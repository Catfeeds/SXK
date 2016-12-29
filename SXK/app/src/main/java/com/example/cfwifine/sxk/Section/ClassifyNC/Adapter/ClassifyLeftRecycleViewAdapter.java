package com.example.cfwifine.sxk.Section.ClassifyNC.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.cfwifine.sxk.R;
import com.example.cfwifine.sxk.Section.ClassifyNC.Model.ClassifyCateModel;
import com.example.cfwifine.sxk.Section.PublishNC.Model.TestModel;
import com.example.cfwifine.sxk.Utils.LogUtil;

import java.util.ArrayList;
import java.util.List;

public class ClassifyLeftRecycleViewAdapter extends RecyclerView.Adapter<ClassifyLeftRecycleViewAdapter.ViewHolder> {
    public List<ClassifyCateModel.CategoryListBean> datas = null;
    public ArrayList<TestModel> dataStatues = null;

    private OnItemClickListener mOnItemClickListener;

    public interface  OnItemClickListener{
        void OnItemClick(View view, int categoryid, int position);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }

    public ClassifyLeftRecycleViewAdapter(List<ClassifyCateModel.CategoryListBean> datas, ArrayList<TestModel> dataListStatue) {
        this.datas = datas;
        this.dataStatues = dataListStatue;
    }

    //创建新View，被LayoutManager所调用
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.right_item, viewGroup, false);
        ViewHolder vh = new ViewHolder(view);
        return vh;
    }

    //将数据与界面进行绑定的操作
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        if (position == 0){
            viewHolder.mTextView.setText("品牌");
        }else {
            viewHolder.mTextView.setText(datas.get(position-1).getName().toString());
            LogUtil.e("品牌分类"+datas.get(position-1).getName().toString());
        }
        // 选中狂
        boolean state = dataStatues.get(position).getState();
        if (state == true){
            viewHolder.content_layout.setBackgroundResource(R.color.white);
        }else {
            viewHolder.content_layout.setBackgroundResource(R.color.classify);
        }
        viewHolder.content_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for (int i = 0; i<dataStatues.size();i++){
                    dataStatues.get(i).setState(false);
                }
                dataStatues.get(position).setState(true);
                notifyDataSetChanged();
                // 一级分类点击事件
                if (position !=0){
                    mOnItemClickListener.OnItemClick(view,datas.get(position-1).getCategoryid(),position);
                }else {
                    mOnItemClickListener.OnItemClick(view,0,position);
                }
            }
        });
    }

    //获取数据的数量
    @Override
    public int getItemCount() {
        return dataStatues.size();
    }

    //自定义的ViewHolder，持有每个Item的的所有界面元素
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView mTextView;
        public RelativeLayout content_layout;

        public ViewHolder(View view) {
            super(view);
            mTextView = (TextView) view.findViewById(R.id.text);
            content_layout = (RelativeLayout) view.findViewById(R.id.content_layout);
        }
    }
}
