package com.example.cfwifine.sxk.Section.HomeNC.Adapter;

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
import com.example.cfwifine.sxk.Section.HomeNC.Model.HomeSelectedClassModel;

import java.util.ArrayList;
import java.util.List;

public class JingSelectedRecycleAdapter extends RecyclerView.Adapter<JingSelectedRecycleAdapter.ViewHolder> {
    private final List<HomeSelectedClassModel.ClassListBean.RentListBean> datas;
    /**
     * 修改 增加context
     *
     * @param datas
     * @param context
     */
    private Context mContext;
    private JingSelectedRecycleAdapter.OnItemClickListener mOnItemClickListener;

    public interface  OnItemClickListener{
        void OnItemClick(View view, int position);
    }

    public void setOnItemClickListener(JingSelectedRecycleAdapter.OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }

    public JingSelectedRecycleAdapter(Context context, List<HomeSelectedClassModel.ClassListBean.RentListBean> datas) {
        mContext = context;
        this.datas = datas;
    }

    //创建新View，被LayoutManager所调用
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_jing_selected, viewGroup, false);
        ViewHolder vh = new ViewHolder(view);
        return vh;
    }

    //将数据与界面进行绑定的操作
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {

        viewHolder.items_text.setText(datas.get(position).getName().toString());
        String picUrl = BaseInterface.ClassfiyGetAllHotBrandImgUrl+datas.get(position).getImgList().get(0).toString();
        Glide.with(mContext).load(picUrl).diskCacheStrategy(DiskCacheStrategy.ALL).placeholder(R.drawable.home_placeholder).animate(R.anim.glide_animal).into(viewHolder.items_pic);
        viewHolder.price.setText("¥ "+ String.valueOf((double)(Math.round(datas.get(position).getRentPrice())/100.0))+"/天");
        if (mOnItemClickListener != null) {
            viewHolder.items.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mOnItemClickListener.OnItemClick(view,datas.get(position).getRentid());
                }
            });
        }
    }

    //获取数据的数量
    @Override
    public int getItemCount() {
        return datas.size();
    }

    //自定义的ViewHolder，持有每个Item的的所有界面元素

    /**
     * 修改：去掉static
     */
    public class ViewHolder extends RecyclerView.ViewHolder {
        LinearLayout items;
        TextView items_text;
        ImageView items_pic;
        TextView price;

        public ViewHolder(View view) {
            super(view);
            items = (LinearLayout)view.findViewById(R.id.items_jing_selected);
            items_text = (TextView)view.findViewById(R.id.jing_selected_items_text);
            items_pic = (ImageView)view.findViewById(R.id.jing_selected_items_pic);
            price = (TextView)view.findViewById(R.id.jing_selected_items_price);

        }
    }
}
