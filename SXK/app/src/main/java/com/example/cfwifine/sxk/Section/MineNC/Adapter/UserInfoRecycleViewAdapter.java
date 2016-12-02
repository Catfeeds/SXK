package com.example.cfwifine.sxk.Section.MineNC.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.cfwifine.sxk.R;

import java.util.ArrayList;

/**
 * Created by Mr.Yang on 2016/11/30.
 */

public class UserInfoRecycleViewAdapter extends RecyclerView.Adapter<UserInfoRecycleViewAdapter.ViewHolder> {

    private ArrayList<String> applicationName = null;
    private ArrayList<String> data = null;
    private OnItemClickListener mOnItemClickListener;

    public interface  OnItemClickListener{
            void OnItemClick(View view, int position);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }

    public UserInfoRecycleViewAdapter(ArrayList<String> applicationName,ArrayList<String> data) {
        this.applicationName = applicationName;
        this.data = data;

    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.userinfo_item, parent, false);
        ViewHolder vh = new ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        if (data.size()!=0){
            holder.rTextView.setText(data.get(position));
        }
        holder.mTextView.setText(applicationName.get(position));
//        if (position == 4){
//            holder.lineView.setVisibility(View.VISIBLE);
//        }
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
        return 6;
    }

    //自定义的ViewHolder，持有每个Item的的所有界面元素
    class ViewHolder extends RecyclerView.ViewHolder {
        public TextView mTextView,rTextView;
        public RelativeLayout mGoto;
//        public RelativeLayout lineView;

        public ViewHolder(View view) {
            super(view);
            mTextView = (TextView) view.findViewById(R.id.user_title);
            rTextView = (TextView)view.findViewById(R.id.user_text);
            mGoto = (RelativeLayout) view.findViewById(R.id.content_layout);
//            lineView = (RelativeLayout) view.findViewById(R.id.line_dc_view);
        }
    }
}
