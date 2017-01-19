package com.example.cfwifine.sxk.Section.MineNC.Controller.MineServerCenter.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.cfwifine.sxk.R;
import com.example.cfwifine.sxk.Section.MineNC.Controller.MineServerCenter.Model.AnswerModel;

import java.util.ArrayList;
import java.util.List;

public class ThreeItemRecycleAdapter extends RecyclerView.Adapter<ThreeItemRecycleAdapter.ViewHolder> {
    public List<AnswerModel.QuestionListBean> datasText = null;
    /**
     * 修改 增加context
     *
     * @param datas
     * @param context
     */
    private Context mContext;
    private ThreeItemRecycleAdapter.OnItemClickListener mOnItemClickListener;

    public interface  OnItemClickListener{
        void OnItemClick(View view, String position);
    }

    public void setOnItemClickListener(ThreeItemRecycleAdapter.OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }

    public ThreeItemRecycleAdapter(List<AnswerModel.QuestionListBean> datasText) {
//        mContext = context;
        this.datasText = datasText;

    }

    //创建新View，被LayoutManager所调用
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_three, viewGroup, false);
        ViewHolder vh = new ViewHolder(view);
        return vh;
    }

    //将数据与界面进行绑定的操作
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {

        viewHolder.items_text.setText(datasText.get(position).getName());
        if (mOnItemClickListener != null) {
            viewHolder.items.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mOnItemClickListener.OnItemClick(view,datasText.get(position).getContent());
                }
            });
        }
    }

    //获取数据的数量
    @Override
    public int getItemCount() {
        return datasText.size();
    }

    //自定义的ViewHolder，持有每个Item的的所有界面元素

    /**
     * 修改：去掉static
     */
    public class ViewHolder extends RecyclerView.ViewHolder {
        LinearLayout items;
        TextView items_text;

        public ViewHolder(View view) {
            super(view);
            items = (LinearLayout)view.findViewById(R.id.items);
            items_text = (TextView)view.findViewById(R.id.items_text);


        }
    }
}
