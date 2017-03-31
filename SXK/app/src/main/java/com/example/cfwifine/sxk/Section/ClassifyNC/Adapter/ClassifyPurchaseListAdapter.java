package com.example.cfwifine.sxk.Section.ClassifyNC.Adapter;

import android.content.Context;
import android.graphics.Color;
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
import com.example.cfwifine.sxk.Section.HomeNC.Model.PurchaseListModel;

import java.util.List;

/**
 * Created by Mr.Yang on 2016/12/22.
 */

public class ClassifyPurchaseListAdapter extends RecyclerView.Adapter<ClassifyPurchaseListAdapter.VH>{


    private Context mContext;
    List<PurchaseListModel.PurchaseListBean> rentList;

    public ClassifyPurchaseListAdapter(List<PurchaseListModel.PurchaseListBean> rentList, Context context) {
        this.rentList = rentList;
        this.mContext = context;
    }

    //自定义回调接口
    public interface OnItemClickLintener{
        void OnItemClick(View view, int purchaseid);
    }

    private OnItemClickLintener mOnItemClickLintener;

    //回调监听
    public void setOnItemClickLintener(OnItemClickLintener onItemClickLintener) {
        this.mOnItemClickLintener = onItemClickLintener;
    }

    @Override
    public VH onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.classify_purchaselist_item,parent,false);
        VH vh = new VH(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(final VH holder, final int position) {

        holder.mTitle.setText(rentList.get(position).getName()+"");
        String description = rentList.get(position).getDescription();
        if (description.trim().length() > 25){
            holder.mComment.setText(description.substring(0,25)+"...");
        }else {
            holder.mComment.setText(description);
        }

        double dd = rentList.get(position).getMarketPrice();
        holder.mMoney.setText("市场价：¥ "+String.format("%.2f",dd/100));
        holder.mMoney.setTextColor(Color.BLACK);
        double solePrice = rentList.get(position).getSellingPrice();
        holder.solePrice.setText(" ¥ "+String.format("%.2f",solePrice/100));
        String picUrl = BaseInterface.ClassfiyGetAllHotBrandImgUrl+rentList.get(position).getImgList().get(position).toString();
        Glide.with(mContext).load(picUrl).diskCacheStrategy(DiskCacheStrategy.ALL).placeholder(R.drawable.home_placeholder).animate(R.anim.glide_animal).into(holder.mImg);

        if (mOnItemClickLintener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mOnItemClickLintener.OnItemClick(view,rentList.get(position).getPurchaseid());
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
        private final TextView mMoney,solePrice;

        public VH(View itemView) {
            super(itemView);
            mImg = (ImageView) itemView.findViewById(R.id.img);
            mTitle = (TextView) itemView.findViewById(R.id.title);
            mComment = (TextView) itemView.findViewById(R.id.comment);
            mMoney = (TextView) itemView.findViewById(R.id.money);
            solePrice = (TextView) itemView.findViewById(R.id.sole_price);
        }
    }
}
