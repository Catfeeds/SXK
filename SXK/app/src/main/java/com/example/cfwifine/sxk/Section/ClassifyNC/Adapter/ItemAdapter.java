package com.example.cfwifine.sxk.Section.ClassifyNC.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.cfwifine.sxk.R;
import com.example.cfwifine.sxk.Section.PublishNC.Model.TestModel;
import com.example.cfwifine.sxk.Utils.LogUtil;

import java.util.ArrayList;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ViewHolder> {
    private final Context context;
    public ArrayList<TestModel> datasource = null;
    ArrayList<Integer> picArr = null;
    private ItemAdapter.OnItemClickListener mOnItemClickListener = null;

    public interface OnItemClickListener {
        void OnItemClick(View view, int positionl ,String name);
    }

    public void setOnItemClickListener(ItemAdapter.OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }

    public ItemAdapter(Context context, ArrayList<TestModel> dataSource, ArrayList<Integer> picArr) {
        this.context = context;
        this.datasource = dataSource;
        this.picArr = picArr;
    }

    //创建新View，被LayoutManager所调用
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.select_item, viewGroup, false);
        ViewHolder vh = new ViewHolder(view);
        return vh;
    }

    //将数据与界面进行绑定的操作
    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, final int position) {
        viewHolder.mTextView.setText(datasource.get(position).getText());
        boolean state = datasource.get(position).getState();
        LogUtil.e("选中状态" + state);
        viewHolder.check.setImageResource(R.drawable.jizudagou);
        if (state == true) {
            viewHolder.check.setVisibility(View.VISIBLE);
        } else {
            viewHolder.check.setVisibility(View.INVISIBLE);
        }
        viewHolder.content_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for (int i = 0; i < datasource.size(); i++) {
                    datasource.get(i).setState(false);
                }
                datasource.get(position).setState(true);
                notifyDataSetChanged();
                mOnItemClickListener.OnItemClick(view,position,datasource.get(position).getText().toString());
            }
        });
        viewHolder.pic.setImageResource(picArr.get(position));
    }

    //获取数据的数量
    @Override
    public int getItemCount() {
        return datasource.size();
    }

    //自定义的ViewHolder，持有每个Item的的所有界面元素
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final ImageView pic,check;
        public TextView mTextView;
        public RelativeLayout content_layout;
        public ViewHolder(View view) {
            super(view);
            mTextView = (TextView) view.findViewById(R.id.texta);
            content_layout = (RelativeLayout) view.findViewById(R.id.content_layoutx);
            pic = (ImageView)view.findViewById(R.id.pic);
            check = (ImageView)view.findViewById(R.id.check_right);
        }
    }
}
