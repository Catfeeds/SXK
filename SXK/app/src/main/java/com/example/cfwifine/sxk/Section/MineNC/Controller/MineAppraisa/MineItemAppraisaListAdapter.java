package com.example.cfwifine.sxk.Section.MineNC.Controller.MineAppraisa;

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
import com.example.cfwifine.sxk.Section.MineNC.Controller.MineCuring.Model.MineItemCuringModel;
import com.example.cfwifine.sxk.Utils.LogUtil;

import java.util.List;


public class MineItemAppraisaListAdapter extends RecyclerView.Adapter<MineItemAppraisaListAdapter.ViewHolder> {
    private final List<MineItemAppraisaModel.OrderListBean> classifyDataSource;
    private Context mContext;
    private LayoutInflater mInflater;


    private MineItemAppraisaListAdapter.OnItemClickListener mOnItemClickListener;

    public interface  OnItemClickListener{
        void OnItemClick(View view, int maintainid);
    }
    public void setOnItemClickListener(MineItemAppraisaListAdapter.OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }

    public MineItemAppraisaListAdapter(Context mContext, List<MineItemAppraisaModel.OrderListBean> classifyDataSource) {
//        mInflater = LayoutInflater.from(mContext);
        this.mContext = mContext;
        this.classifyDataSource = classifyDataSource;
    }


    @Override
    public MineItemAppraisaListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_mine_publish_appraisa_list, parent, false);
        MineItemAppraisaListAdapter.ViewHolder vh = new MineItemAppraisaListAdapter.ViewHolder(view);
        return vh;
//        return new ViewHolder(mInflater.inflate(R.layout.item_mine_nopass_list, parent, false));
    }

    @Override
    public void onBindViewHolder(final MineItemAppraisaListAdapter.ViewHolder holder, final int position) {

        LogUtil.e("审核中数据源"+classifyDataSource.get(position));
        holder.name.setText(classifyDataSource.get(position).getOrderNo());

        if (classifyDataSource.get(position).getStatus() == 2){
            holder.description.setText("目前状态:检测中");
        }else {
            holder.description.setText("目前状态:已完成");
        }
        String picUrl = BaseInterface.ClassfiyGetAllHotBrandImgUrl + classifyDataSource.get(position).getSetup().getCampaign().toString();
        Glide.with(mContext).load(picUrl).diskCacheStrategy(DiskCacheStrategy.ALL).placeholder(R.drawable.home_placeholder).animate(R.anim.glide_animal).into(holder.pic);
        if (mOnItemClickListener != null){
            holder.delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mOnItemClickListener.OnItemClick(view,classifyDataSource.get(position).getOrderid());
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return classifyDataSource != null ? classifyDataSource.size() : 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        LinearLayout frameLayout;
        TextView name,description;
        ImageView pic;
        Button delete;
        public ViewHolder(View itemView) {
            super(itemView);

            frameLayout = (LinearLayout) itemView.findViewById(R.id.appraisa_cell);
            name = (TextView)itemView.findViewById(R.id.appraisa_name);
            description = (TextView)itemView.findViewById(R.id.appraisa_descript);
            pic = (ImageView)itemView.findViewById(R.id.appraisa_pic);
            delete = (Button)itemView.findViewById(R.id.appraisa_confirm_btn);
        }
    }
}
