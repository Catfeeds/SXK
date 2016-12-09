package com.example.cfwifine.sxk.Section.HomeNC.Adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.cfwifine.sxk.R;
import com.example.cfwifine.sxk.Section.MineNC.Adapter.MineRecycleViewAdapter;

import java.util.ArrayList;

public class EightItemRecycleAdapter extends RecyclerView.Adapter<EightItemRecycleAdapter.ViewHolder> {
    public ArrayList<String> datasText = null;
    public ArrayList<Integer> datasPic = null;
    /**
     * 修改 增加context
     *
     * @param datas
     * @param context
     */
    private Context mContext;
    private EightItemRecycleAdapter.OnItemClickListener mOnItemClickListener;

    public interface  OnItemClickListener{
        void OnItemClick(View view, int position);
    }

    public void setOnItemClickListener(EightItemRecycleAdapter.OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }

    public EightItemRecycleAdapter(ArrayList<Integer> datasPic, ArrayList<String> datasText) {
//        mContext = context;
        this.datasText = datasText;
        this.datasPic = datasPic;
    }

    //创建新View，被LayoutManager所调用
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_eight, viewGroup, false);
        ViewHolder vh = new ViewHolder(view);
        return vh;
    }

    //将数据与界面进行绑定的操作
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {

        viewHolder.items_text.setText(datasText.get(position));
        viewHolder.items_pic.setImageResource(datasPic.get(position));
        if (mOnItemClickListener != null) {
            viewHolder.items.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mOnItemClickListener.OnItemClick(view,position);
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
        ImageView items_pic;

        public ViewHolder(View view) {
            super(view);
            items = (LinearLayout)view.findViewById(R.id.items);
            items_text = (TextView)view.findViewById(R.id.items_text);
            items_pic = (ImageView)view.findViewById(R.id.items_pic);


        }
    }
}
