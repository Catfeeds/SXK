package com.example.cfwifine.sxk.Section.MineNC.Controller.MinePublish.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.cfwifine.sxk.BaseAC.BaseInterface;
import com.example.cfwifine.sxk.R;
import com.example.cfwifine.sxk.Section.MineNC.Adapter.AddressSettingRecycleViewAdapter;
import com.example.cfwifine.sxk.Section.MineNC.Controller.MinePublish.Controller.MineItemAC;
import com.example.cfwifine.sxk.Section.MineNC.Controller.MinePublish.Model.MineItemNoSailModel;
import com.example.cfwifine.sxk.Section.MineNC.Controller.MinePublish.Model.MinePublishShenHeModel;
import com.example.cfwifine.sxk.Utils.LogUtil;

import java.util.List;


public class MineNoSailListAdapter extends RecyclerView.Adapter<MineNoSailListAdapter.ViewHolder> {
    private final List<MineItemNoSailModel.RentListBean> classifyDataSource;
    private Context mContext;
    private LayoutInflater mInflater;


    private MineNoSailListAdapter.OnItemClickListener mOnItemClickListener;

    public interface  OnItemClickListener{
        void OnItemClick(View view, int maintainid);
    }
    public void setOnItemClickListener(MineNoSailListAdapter.OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }

    public MineNoSailListAdapter(Context mContext, List<MineItemNoSailModel.RentListBean> classifyDataSource) {
        this.mContext = mContext;
        this.classifyDataSource = classifyDataSource;
//        mInflater = LayoutInflater.from(mContext);
    }


    @Override
    public MineNoSailListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_mine_nosail_list, parent, false);
        MineNoSailListAdapter.ViewHolder vh = new MineNoSailListAdapter.ViewHolder(view);
        return vh;
//        return new ViewHolder(mInflater.inflate(R.layout.item_mine_nosail_list, parent, false));
    }

    @Override
    public void onBindViewHolder(final MineNoSailListAdapter.ViewHolder holder, final int position) {

        LogUtil.e("审核中数据源"+classifyDataSource.get(position).getName());
        holder.name.setText(classifyDataSource.get(position).getName());
        holder.description.setText(classifyDataSource.get(position).getKeyword());
//        holder.price.setText("¥ "+ String.valueOf(classifyDataSource.get(position).getCounterPrice()/100));
        holder.price.setText("¥ "+ String.valueOf((double)(Math.round(classifyDataSource.get(position).getCounterPrice())/100.0))+"/天");
//        holder.rentPrice.setText("市场价 ¥ "+String.valueOf(classifyDataSource.get(position)));
        String picUrl = BaseInterface.ClassfiyGetAllHotBrandImgUrl + classifyDataSource.get(position).getImgList().get(0);
        Glide.with(mContext).load(picUrl).diskCacheStrategy(DiskCacheStrategy.ALL).placeholder(R.drawable.home_placeholder).animate(R.anim.glide_animal).into(holder.pic);

        if (mOnItemClickListener != null){
            holder.delbtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mOnItemClickListener.OnItemClick(view,classifyDataSource.get(position).getRentid());
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return classifyDataSource != null ? classifyDataSource.size() : 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView rentPrice;
        LinearLayout frameLayout;
        TextView name,description,price;
        ImageView pic;
        Button delbtn;

        public ViewHolder(View itemView) {
            super(itemView);

            frameLayout = (LinearLayout) itemView.findViewById(R.id.minenosail_cell);
            name = (TextView)itemView.findViewById(R.id.minenosail_name);
            description = (TextView)itemView.findViewById(R.id.minenosail_descript);
            price = (TextView)itemView.findViewById(R.id.minenosail_price);
            pic = (ImageView)itemView.findViewById(R.id.minenosail_pic);
            rentPrice = (TextView)itemView.findViewById(R.id.minenosail_rentprice);
            delbtn = (Button)itemView.findViewById(R.id.minenosail_delete_btn);
        }
    }
}
