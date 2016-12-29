package com.example.cfwifine.sxk.Section.ClassifyNC.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.cfwifine.sxk.BaseAC.BaseInterface;
import com.example.cfwifine.sxk.R;
import com.example.cfwifine.sxk.Section.ClassifyNC.Model.RentListModel;

import java.util.List;

/**
 * Created by Mr.Yang on 2016/12/22.
 */

public class ClassifyAllListAdapter extends RecyclerView.Adapter<ClassifyAllListAdapter.VH>{


    private Context mContext;
    List<RentListModel.RentListBean> rentList;

    public ClassifyAllListAdapter( List<RentListModel.RentListBean> rentList, Context context) {
        this.rentList = rentList;
        this.mContext = context;
    }

    //自定义回调接口
    public interface OnItemClickLintener{
        void OnItemClick(View view, int position);
    }

    private OnItemClickLintener mOnItemClickLintener;

    //回调监听
    public void setOnItemClickLintener(OnItemClickLintener onItemClickLintener) {
        this.mOnItemClickLintener = onItemClickLintener;
    }

    @Override
    public VH onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.classify_catelist_item,parent,false);
        VH vh = new VH(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(final VH holder, int position) {

        holder.mTitle.setText(rentList.get(position).getName()+"");
        holder.mComment.setText(rentList.get(position).getKeyword()+"");
        holder.mMoney.setText(rentList.get(position).getCounterPrice()+"");

        String picUrl = BaseInterface.ClassfiyGetAllHotBrandImgUrl+rentList.get(position).getImgList().get(position).toString();
        Glide.with(mContext)
                .load(picUrl)
                .centerCrop()
                .placeholder(R.drawable.home_placeholder)
                .crossFade()
                .into(holder.mImg);
//        holder.mImg.setImageResource(R.drawable.home_placeholder);




        if (mOnItemClickLintener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mOnItemClickLintener.OnItemClick(holder.itemView,holder.getLayoutPosition());
                }
            });
        }

    }

    @Override
    public int getItemCount() {
        return rentList.size();
    }

    class VH extends RecyclerView.ViewHolder {

        private final ImageView mImg;
        private final TextView mTitle;
        private final TextView mComment;
        private final TextView mMoney;

        public VH(View itemView) {
            super(itemView);
            mImg = (ImageView) itemView.findViewById(R.id.img);
            mTitle = (TextView) itemView.findViewById(R.id.title);
            mComment = (TextView) itemView.findViewById(R.id.comment);
            mMoney = (TextView) itemView.findViewById(R.id.money);
        }
    }
}
