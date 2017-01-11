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

import com.example.cfwifine.sxk.R;
import com.example.cfwifine.sxk.Section.PublishNC.Model.SecondCateModel;
import com.example.cfwifine.sxk.Section.PublishNC.Model.TestModel;
import com.example.cfwifine.sxk.Utils.LogUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AppraisasCheckRecycleViewAdapter extends RecyclerView.Adapter<AppraisasCheckRecycleViewAdapter.ViewHolder> {
    private final Context context;
    public ArrayList<Integer> datas = null;
    public ArrayList<TestModel> datasource = null;

    private AppraisasCheckRecycleViewAdapter.OnItemClickListener mOnItemClickListener = null;

    public interface OnItemClickListener {
        void OnItemClick(View view, int positionl,String value);
    }

    public void setOnItemClickListener(AppraisasCheckRecycleViewAdapter.OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }

    public AppraisasCheckRecycleViewAdapter(Context context, ArrayList<Integer> datas, ArrayList<TestModel> dataSource) {
        this.context = context;
        this.datas = datas;
        this.datasource = dataSource;
    }

    //创建新View，被LayoutManager所调用
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.appraisa_check_item, viewGroup, false);
        ViewHolder vh = new ViewHolder(view);
        return vh;
    }

    //将数据与界面进行绑定的操作
    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, final int position) {
        viewHolder.mTextView.setText(datasource.get(position).getText());
        viewHolder.pic.setImageResource(datas.get(position));
        // 选中狂
        boolean state = datasource.get(position).getState();
        LogUtil.e("选中状态" + state);
        if (state == true) {
            viewHolder.check_pic.setImageResource(R.drawable.check_ok);
        } else {
            viewHolder.check_pic.setImageResource(R.drawable.check_circle);
        }


        viewHolder.content_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for (int i = 0; i < datasource.size(); i++) {
                    datasource.get(i).setState(false);
                }
                datasource.get(position).setState(true);
                notifyDataSetChanged();
                mOnItemClickListener.OnItemClick(view,position,datasource.get(position).getText());
            }
        });

//        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                for (int i = 0; i<datasource.size();i++){
//                    datasource.get(i).setState(false);
//                }
//                datasource.get(position).setState(true);
//                notifyDataSetChanged();
//
//            }
//        });


    }

    //获取数据的数量
    @Override
    public int getItemCount() {
        return datasource.size();
    }

    //自定义的ViewHolder，持有每个Item的的所有界面元素
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView mTextView;
        public RelativeLayout content_layout;
        public LinearLayout checkCircle;
        public ImageView check_pic, pic;

        public ViewHolder(View view) {
            super(view);
            mTextView = (TextView) view.findViewById(R.id.texta);
            content_layout = (RelativeLayout) view.findViewById(R.id.content_layoutx);
            checkCircle = (LinearLayout) view.findViewById(R.id.check_circlea);
            check_pic = (ImageView) view.findViewById(R.id.check_pics);
            pic = (ImageView) view.findViewById(R.id.pic);
        }
    }
}
