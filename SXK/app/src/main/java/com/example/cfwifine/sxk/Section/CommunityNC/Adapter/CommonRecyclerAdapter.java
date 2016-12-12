package com.example.cfwifine.sxk.Section.CommunityNC.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * RecyclerView通用Adapter
 * <p/>
 * Created by linyingkun on 2016/9/19.
 */
public abstract class CommonRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private int itemLayout;
    private OnItemClickListener onItemClickListener;
    private onItemLongClickListener onItemLongClickListener;

    public CommonRecyclerAdapter(int itemLayout) {
        this.itemLayout = itemLayout;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(itemLayout, parent, false);
        return new RecyclerView.ViewHolder(view) {

        };
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        final View itemView = holder.itemView;
        final int pos = holder.getAdapterPosition();
        bindViewHolder(itemView, pos);

        if (onItemClickListener != null) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListener.onItemClick(itemView, pos);
                }
            });
        }
        if (onItemLongClickListener != null) {
            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    onItemLongClickListener.onItemLongClick(itemView, pos);
                    return false;
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return setItemCount();
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public void setOnItemLongClickListener(onItemLongClickListener onItemLongClickListener) {
        this.onItemLongClickListener = onItemLongClickListener;
    }

    interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    interface onItemLongClickListener {
        void onItemLongClick(View view, int position);
    }

    abstract void bindViewHolder(View view, int position);

    abstract int setItemCount();

}
