package com.example.cfwifine.sxk.Section.ClassifyNC.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
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
    public void onBindViewHolder(final VH holder, final int position) {

        holder.mTitle.setText(rentList.get(position).getName()+"");
        String description = rentList.get(position).getDescription();
        if (description.trim().length()>25){
            holder.mComment.setText(description.substring(0,25)+"...");
        }else {
            holder.mComment.setText(description);
        }
        double marketPrice = rentList.get(position).getMarketPrice();
        holder.marketPrice.setText("市场价：¥ "+ String.format("%.2f",marketPrice/100));
        double rentPrice = rentList.get(position).getRentPrice();
        holder.mMoney.setText(String.format("%.2f",rentPrice/100));
        String picUrl = BaseInterface.ClassfiyGetAllHotBrandImgUrl+rentList.get(position).getImgList().get(0).toString();
        Glide.with(mContext).load(picUrl).diskCacheStrategy(DiskCacheStrategy.ALL).placeholder(R.drawable.home_placeholder).animate(R.anim.glide_animal).into(holder.mImg);
//        holder.mImg.setImageResource(R.drawable.home_placeholder);




        if (mOnItemClickLintener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mOnItemClickLintener.OnItemClick(view,rentList.get(position).getRentid());
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
        private final TextView mMoney,marketPrice;

        public VH(View itemView) {
            super(itemView);
            mImg = (ImageView) itemView.findViewById(R.id.img);
            mTitle = (TextView) itemView.findViewById(R.id.title);
            mComment = (TextView) itemView.findViewById(R.id.comment);
            mMoney = (TextView) itemView.findViewById(R.id.money);
            marketPrice = (TextView) itemView.findViewById(R.id.market_price);
        }
    }
}
