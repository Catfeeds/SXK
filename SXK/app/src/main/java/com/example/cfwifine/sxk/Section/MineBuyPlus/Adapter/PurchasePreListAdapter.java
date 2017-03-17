package com.example.cfwifine.sxk.Section.MineBuyPlus.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.cfwifine.sxk.BaseAC.BaseInterface;
import com.example.cfwifine.sxk.R;
import com.example.cfwifine.sxk.Section.ClassifyNC.Model.SecondCateModel;
import com.example.cfwifine.sxk.Section.MineBuyPlus.Model.PurchasePreListModel;

import java.util.List;

/**
 * Created by Mr.Yang on 2016/12/13.
 */

public class PurchasePreListAdapter extends RecyclerView.Adapter<PurchasePreListAdapter.VH> {

    private Context mContext;
    //热门包
    private List<PurchasePreListModel.PurchaseListBean> mdatas=null;
    private String conditions;

    public interface OnItemClickLitener {
        void onItemClick(View view, int purchaseid);
    }

    private OnItemClickLitener mOnItemClickLitener;

    public void setOnItemClickLitener(OnItemClickLitener onItemClickLitener) {
        this.mOnItemClickLitener = onItemClickLitener;
    }


    public PurchasePreListAdapter(Context context, List<PurchasePreListModel.PurchaseListBean> mdatas) {
        this.mContext = context;
        this.mdatas = mdatas;
    }

    @Override
    public VH onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.purchase_pre_list_item,null,false);
        VH vh = new VH(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(final VH holder, final int position) {
        holder.name.setText(mdatas.get(position).getName().toString());
        holder.kind.setText(mdatas.get(position).getCategory().getName().toString());
        int cheng = mdatas.get(position).getCondition();
        switch (cheng) {
            case 1:
                conditions = "99成新（未使用）";
                break;
            case 2:
                conditions = "98成新";
                break;
            case 3:
                conditions = "95成新";
                break;
            case 4:
                conditions = "9成新";
                break;
            case 5:
                conditions = "85成新";
                break;
            case 6:
                conditions = "8成新";
                break;
            default:
                break;
        }
        holder.condition.setText("("+conditions+")");
        double pric = mdatas.get(position).getAdvancePrice();
        holder.price.setText(" ¥ "+String.format("%.2f",pric/100));
        holder.content.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mOnItemClickLitener.onItemClick(view,mdatas.get(position).getPurchaseid());
            }
        });
    }

    @Override
    public int getItemCount() {
        return mdatas.size();
    }

    public static class VH extends RecyclerView.ViewHolder {


        private final TextView name;
        private final TextView kind;
        private final TextView condition;
        private final TextView price;
        private final LinearLayout content;


        public VH(View itemView) {
            super(itemView);
            content = (LinearLayout)itemView.findViewById(R.id.content_view);
            name = (TextView)itemView.findViewById(R.id.name);
            kind = (TextView)itemView.findViewById(R.id.kind);
            condition = (TextView)itemView.findViewById(R.id.condition);
            price = (TextView)itemView.findViewById(R.id.price);
        }
    }
}
