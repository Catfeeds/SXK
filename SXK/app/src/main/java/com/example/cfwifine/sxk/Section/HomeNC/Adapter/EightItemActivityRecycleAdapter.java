package com.example.cfwifine.sxk.Section.HomeNC.Adapter;

import android.content.Context;
import android.media.Image;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.cfwifine.sxk.BaseAC.BaseInterface;
import com.example.cfwifine.sxk.R;
import com.example.cfwifine.sxk.Section.HomeNC.Model.ActivityListModel;
import com.example.cfwifine.sxk.Utils.TimeUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EightItemActivityRecycleAdapter extends RecyclerView.Adapter<EightItemActivityRecycleAdapter.ViewHolder> {

    public List<ActivityListModel.ActivityListBean> dataSource = null;
    /**
     * 修改 增加context
     *
     * @param datas
     * @param context
     */
    private Context mContext;
    private EightItemActivityRecycleAdapter.OnItemClickListener mOnItemClickListener;

    public interface  OnItemClickListener{
        void OnItemClick(View view, int position);
    }

    public void setOnItemClickListener(EightItemActivityRecycleAdapter.OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }

    public EightItemActivityRecycleAdapter(Context context, List<ActivityListModel.ActivityListBean> dataSource) {
        mContext = context;
        this.dataSource = dataSource;
    }

    //创建新View，被LayoutManager所调用
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_activity_list, viewGroup, false);
        ViewHolder vh = new ViewHolder(view);
        return vh;
    }

    //将数据与界面进行绑定的操作
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        String picUrl = BaseInterface.ClassfiyGetAllHotBrandImgUrl + dataSource.get(position).getImg();
        Glide.with(mContext)
                .load(picUrl)
                .placeholder(R.drawable.home_placeholder)
                .crossFade()
                .into(viewHolder.imageView);
        viewHolder.title.setText(dataSource.get(position).getName());
        // 处理时间戳
        String date = TimeUtils.milliseconds2String(dataSource.get(position).getTime());
        viewHolder.date.setText(String.valueOf(date));
        viewHolder.address.setText(dataSource.get(position).getPlace());
        if (mOnItemClickListener!=null){
            viewHolder.contextsss.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mOnItemClickListener.OnItemClick(view,dataSource.get(position).getActivityid());
                }
            });
        }

    }

    //获取数据的数量
    @Override
    public int getItemCount() {
        return dataSource.size();
    }
    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    //自定义的ViewHolder，持有每个Item的的所有界面元素

    /**
     * 修改：去掉static
     */
    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView title,date,address;
        RelativeLayout contextsss;

        public ViewHolder(View view) {
            super(view);
            imageView = (ImageView)view.findViewById(R.id.home_activity_picture);
            title = (TextView)view.findViewById(R.id.home_activity_title);
            date = (TextView)view.findViewById(R.id.home_activity_date);
            address = (TextView)view.findViewById(R.id.home_activity_address);
            contextsss = (RelativeLayout)view.findViewById(R.id.home_activity_context);
        }
    }
}
