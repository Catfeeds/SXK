package com.example.cfwifine.sxk.Section.MineNC.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.cfwifine.sxk.R;

import java.util.ArrayList;

/**
 * Created by Mr.Yang on 2016/11/30.
 */

public class MineRecycleViewAdapter extends RecyclerView.Adapter<MineRecycleViewAdapter.ViewHolder> {

    private ArrayList<String> applicationName = null;
    private ArrayList<Integer> imageView = null;
    private OnItemClickListener mOnItemClickListener;

    public interface  OnItemClickListener{
            void OnItemClick(View view, int position);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }

    public  MineRecycleViewAdapter(ArrayList<String> applicationName, ArrayList<Integer> imageView) {
        this.applicationName = applicationName;
        this.imageView = imageView;

    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.mine_item, parent, false);
        ViewHolder vh = new ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.mTextView.setText(applicationName.get(position));
        holder.mImageView.setImageResource(imageView.get(position));
        if (mOnItemClickListener != null) {
            holder.mGoto.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mOnItemClickListener.OnItemClick(view,position);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return 9;
    }

    //自定义的ViewHolder，持有每个Item的的所有界面元素
    class ViewHolder extends RecyclerView.ViewHolder {
        public TextView mTextView;
        public ImageView mImageView;
        public RelativeLayout mGoto;

        public ViewHolder(View view) {
            super(view);
            mImageView = (ImageView) view.findViewById(R.id.mine_picture);
            mTextView = (TextView) view.findViewById(R.id.mine_application_name);
            mGoto = (RelativeLayout) view.findViewById(R.id.mine_goto);
        }
    }
}
