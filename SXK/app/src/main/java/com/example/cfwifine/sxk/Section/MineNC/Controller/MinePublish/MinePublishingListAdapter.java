package com.example.cfwifine.sxk.Section.MineNC.Controller.MinePublish;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.cfwifine.sxk.BaseAC.BaseInterface;
import com.example.cfwifine.sxk.R;
import com.example.cfwifine.sxk.Section.MineNC.Model.MineItemPublishingModel;
import com.example.cfwifine.sxk.Section.PublishNC.Model.MinePublishShenHeModel;
import com.example.cfwifine.sxk.Utils.LogUtil;

import java.util.List;


public class MinePublishingListAdapter extends RecyclerView.Adapter<MinePublishingListAdapter.ViewHolder> {
    private final List<MineItemPublishingModel.RentListBean> rentListDataSouce;
    private Context mContext;
    private LayoutInflater mInflater;


    private MinePublishingListAdapter.OnItemClickListener mOnItemClickListener;

    public interface  OnItemClickListener{
        void OnItemClick(View view, int maintainid);
    }
    public void setOnItemClickListener(MinePublishingListAdapter.OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }

    public MinePublishingListAdapter(Context mContext, List<MineItemPublishingModel.RentListBean> rentListDataSouce) {
        this.mContext = mContext;
        this.rentListDataSouce = rentListDataSouce;
        mInflater = LayoutInflater.from(mContext);
    }


    @Override
    public MinePublishingListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(mInflater.inflate(R.layout.item_mine_publish_list, parent, false));
    }

    @Override
    public void onBindViewHolder(final MinePublishingListAdapter.ViewHolder holder, final int position) {

        LogUtil.e("审核中数据源"+rentListDataSouce.get(position).getName());
        holder.name.setText(rentListDataSouce.get(position).getName());
        holder.description.setText(rentListDataSouce.get(position).getKeyword());
        holder.price.setText("¥ "+ String.valueOf(rentListDataSouce.get(position).getCounterPrice()));
        holder.rentPrice.setText("市场价 ¥ "+String.valueOf(rentListDataSouce.get(position).getMarketPrice()));
        String picUrl = BaseInterface.ClassfiyGetAllHotBrandImgUrl + rentListDataSouce.get(position).getImgList().get(0);
        Glide.with(mContext).load(picUrl).diskCacheStrategy(DiskCacheStrategy.ALL).placeholder(R.drawable.home_placeholder).animate(R.anim.glide_animal).into(holder.pic);

        if (mOnItemClickListener != null){
            holder.frameLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mOnItemClickListener.OnItemClick(view,rentListDataSouce.get(position).getRentid());
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return rentListDataSouce != null ? rentListDataSouce.size() : 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView rentPrice;
        LinearLayout frameLayout;
        TextView name,description,price;
        ImageView pic;

        public ViewHolder(View itemView) {
            super(itemView);

            frameLayout = (LinearLayout) itemView.findViewById(R.id.curing_cell);
            name = (TextView)itemView.findViewById(R.id.mine_publish_name);
            description = (TextView)itemView.findViewById(R.id.mine_publish_descript);
            price = (TextView)itemView.findViewById(R.id.mine_publish_price);
            pic = (ImageView)itemView.findViewById(R.id.mine_publish_pic);
            rentPrice = (TextView)itemView.findViewById(R.id.mine_publish_rentprice);

        }
    }
}
