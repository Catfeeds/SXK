package com.example.cfwifine.sxk.Section.HomeNC.Adapter;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.cfwifine.sxk.BaseAC.BaseInterface;
import com.example.cfwifine.sxk.R;
import com.example.cfwifine.sxk.Section.HomeNC.Model.HomeSelectedClassModel;

import java.util.ArrayList;
import java.util.List;

public class RecycleAdapter extends RecyclerView.Adapter<RecycleAdapter.ViewHolder> {
    public List<HomeSelectedClassModel.ClassListBean> datas = null;

    /**
     * 修改 增加context
     *
     * @param datas
     * @param context
     */
    private Context mContext;

    /**
     * 修改 增加context参数
     *
     * @param datas
     * @param context
     */
    public RecycleAdapter(List<HomeSelectedClassModel.ClassListBean> datas, Context context) {
        mContext = context;
        this.datas = datas;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item, viewGroup, false);
        ViewHolder vh = new ViewHolder(view);
        return vh;
    }

    //将数据与界面进行绑定的操作
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        String picUrl = BaseInterface.ClassfiyGetAllHotBrandImgUrl + datas.get(position).getImg();
        Glide.with(mContext).load(picUrl).diskCacheStrategy(DiskCacheStrategy.ALL).placeholder(R.drawable.home_placeholder).animate(R.anim.glide_animal).into(viewHolder.titlePic);


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
        private LinearLayout content;
        private ImageView titlePic;
        private RecyclerView recyclerView;

        public ViewHolder(View view) {
            super(view);
            content = (LinearLayout) view.findViewById(R.id.content_layout);
            titlePic = (ImageView) view.findViewById(R.id.home_classify_pic);
            recyclerView = (RecyclerView) view.findViewById(R.id.home_classify_rv);
        }


    }
}
