package com.example.cfwifine.sxk.Section.ClassifyNC.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.example.cfwifine.sxk.BaseAC.BaseInterface;
import com.example.cfwifine.sxk.R;
import com.example.cfwifine.sxk.Section.ClassifyNC.Model.ClassfiyHotBrandModel;
import com.example.cfwifine.sxk.Section.ClassifyNC.Model.ClassifySeconHotModel;

import java.util.List;

public class ClassifyHotListAdapter extends RecyclerView.Adapter<ClassifyHotListAdapter.ViewHolder> {
    public List<ClassifySeconHotModel.HotListBean> mHeaderDatas = null;
    private Context context;

    public interface OnItemClickLitener {
        void onItemClick(View view, int position);
    }
    private OnItemClickLitener mOnItemClickLitener;

    public void setOnItemClickLitener(OnItemClickLitener onItemClickLitener) {
        this.mOnItemClickLitener = onItemClickLitener;
    }

    public ClassifyHotListAdapter(Context context, List<ClassifySeconHotModel.HotListBean> mHeaderDatas) {
        this.context = context;
        this.mHeaderDatas = mHeaderDatas;
    }

    //创建新View，被LayoutManager所调用
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_hot_brand, viewGroup, false);
        ViewHolder vh = new ViewHolder(view);
        return vh;
    }

    //将数据与界面进行绑定的操作
    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, final int position) {
        String picUrl = BaseInterface.ClassfiyGetAllHotBrandImgUrl+mHeaderDatas.get(position).getImg();

        //如果设置了回调，则设置点击事件
        if (mOnItemClickLitener != null) {
            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int pos = viewHolder.getLayoutPosition();
                    mOnItemClickLitener.onItemClick(viewHolder.itemView,mHeaderDatas.get(position).getBrandid());
                }
            });
        }
        /**
         * 适配图片的宽高比例
         */
//        int screenWidth = ScreenUtil.getScreenWidth(context)/2;
//        ViewGroup.LayoutParams lp = viewHolder.headPic.getLayoutParams();
//        lp.width = screenWidth;
//        lp.height = RecyclerView.LayoutParams.WRAP_CONTENT;
//        viewHolder.headPic.setLayoutParams(lp);
//
//        viewHolder.headPic.setMaxWidth(screenWidth);
//        viewHolder.headPic.setMaxHeight((int) (screenWidth / 1.3));
        Glide.with(context)
                .load(picUrl)
                .centerCrop()
                .placeholder(R.drawable.home_placeholder)
                .crossFade()
                .into(viewHolder.headPic);

    }

    //获取数据的数量
    @Override
    public int getItemCount() {
        return mHeaderDatas.size();
    }

    //自定义的ViewHolder，持有每个Item的的所有界面元素
    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView headPic;
        LinearLayout mLinearLayout;
        public ViewHolder(View view) {
            super(view);
            headPic = (ImageView)view.findViewById(R.id.hot_brand_pic);
            mLinearLayout = (LinearLayout) view.findViewById(R.id.cate_linearlayout);

        }
    }
}
