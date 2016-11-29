package com.example.cfwifine.sxk.Section.ClassifyNC.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.cfwifine.sxk.R;
import com.example.cfwifine.sxk.Section.PublishNC.Model.TestModel;

import java.util.ArrayList;

public class ClassifyLeftRecycleViewAdapter extends RecyclerView.Adapter<ClassifyLeftRecycleViewAdapter.ViewHolder> {
    public ArrayList<TestModel> datas = null;

    public ClassifyLeftRecycleViewAdapter(ArrayList<TestModel> datas) {
        this.datas = datas;
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
        viewHolder.mTextView.setText(datas.get(position).getText());
        // 选中狂
        boolean state = datas.get(position).getState();
        if (state == true){
            viewHolder.content_layout.setBackgroundResource(R.color.white);
        }else {
            viewHolder.content_layout.setBackgroundResource(R.color.classify);
        }

        viewHolder.content_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for (int i = 0; i<datas.size();i++){
                    datas.get(i).setState(false);
                }
                datas.get(position).setState(true);
                notifyDataSetChanged();
            }
        });
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
            content_layout = (RelativeLayout) view.findViewById(R.id.content_layout);
        }
    }
}
