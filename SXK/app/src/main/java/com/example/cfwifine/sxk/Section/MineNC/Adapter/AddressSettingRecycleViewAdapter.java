package com.example.cfwifine.sxk.Section.MineNC.Adapter;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.cfwifine.sxk.R;
import com.example.cfwifine.sxk.Section.CommunityNC.View.L;
import com.example.cfwifine.sxk.Section.MineNC.Controller.AddressDetailAC;
import com.example.cfwifine.sxk.Section.MineNC.Model.AddressListModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mr.Yang on 2016/11/30.
 */

public class AddressSettingRecycleViewAdapter extends RecyclerView.Adapter<AddressSettingRecycleViewAdapter.ViewHolder> {
    private List<AddressListModel.ReceiverListBean> data;
    private OnItemClickListener mOnItemClickListener;
//    private mEditBtnOnItemClickListener mEditBtnOnItemClickListener;
//    private mDeleBtnOnItemClickListener mDeleBtnOnItemClickListener;
    private MyClickOnListener mClickListener;

    public interface  OnItemClickListener{
            void OnItemClick(View view, int position);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
//        this.mEditClickListener = mEditClickListener;
    }

    public AddressSettingRecycleViewAdapter(List<AddressListModel.ReceiverListBean> data) {
        this.data = data;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_adress, parent, false);
        ViewHolder vh = new ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.username.setText(data.get(position).getName());
        holder.phonenumber.setText(data.get(position).getMobile());
        holder.address.setText(data.get(position).getAddress());
        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 点击了编辑按钮
//                L.e("点击了编辑按钮"+position);
//                holder.edit.setTag(position);
//                mOnItemClickListener.OnItemClick(mEditBtnOnItemClickListener);
                mClickListener.edit(view,data.get(position).getReceiverid());
            }
        });
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 点击了删除按钮
                L.e("点击了删除按钮"+position);
//                mOnItemClickListener.OnItemClick(view,position);
//                holder.delete.setTag(position);
                mClickListener.delete(view,data.get(position).getReceiverid());
            }
        });

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    //自定义的ViewHolder，持有每个Item的的所有界面元素
    class ViewHolder extends RecyclerView.ViewHolder {
        /**
         * 用户姓名、手机号、地址
         * @param view
         */
        public TextView username;
        public TextView phonenumber;
        public TextView address;
        LinearLayout delete,edit;

        public ViewHolder(View view) {
            super(view);
            username = (TextView)view.findViewById(R.id.address_user);
            phonenumber = (TextView)view.findViewById(R.id.address_userphone);
            address = (TextView)view.findViewById(R.id.address_useraddress);
            edit = (LinearLayout)view.findViewById(R.id.address_edit);
            delete = (LinearLayout)view.findViewById(R.id.address_delete);
        }
    }
    public void mEditOnClickListener(MyClickOnListener listener){
        mClickListener = listener;
    }
}
