package com.example.cfwifine.sxk.Section.HomeNC.Adapter;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
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
import com.example.cfwifine.sxk.Section.HomeNC.Model.HomeHotListModel;

import java.util.ArrayList;
import java.util.List;

public class HotTopicAdapter extends RecyclerView.Adapter<HotTopicAdapter.ViewHolder> {
    public List<HomeHotListModel.TopicListBean> datas = null;

    /**
     * 修改 增加context
     * @param datas
     * @param context
     */
    private Context mContext;
    private HotTopicAdapter.OnItemClickListener mOnItemClickListener;

    public interface  OnItemClickListener{
        void OnItemClick(View view, int position);
    }

    public void setOnItemClickListener(HotTopicAdapter.OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }

    /**
     * 修改 增加context参数
     * @param datas
     * @param context
     */
    public HotTopicAdapter(List<HomeHotListModel.TopicListBean> datas, Context context) {
        mContext = context;
        this.datas = datas;
    }

    //创建新View，被LayoutManager所调用
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.hot_item, viewGroup, false);
        ViewHolder vh = new ViewHolder(view);
        return vh;
    }

    //将数据与界面进行绑定的操作
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {

        viewHolder.title.setText(datas.get(position).getName().toString());
        String picUrl = BaseInterface.ClassfiyGetAllHotBrandImgUrl+datas.get(position).getImg();
        Glide.with(mContext).load(picUrl).diskCacheStrategy(DiskCacheStrategy.ALL).placeholder(R.drawable.home_placeholder).animate(R.anim.glide_animal).into(viewHolder.imageView);
        viewHolder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mOnItemClickListener.OnItemClick(view,datas.get(position).getTopicid());
            }
        });
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
    public  class ViewHolder extends RecyclerView.ViewHolder {
        public LinearLayout contents_layout;
        private ImageView  imageView;
        private TextView title;


        @RequiresApi(api = Build.VERSION_CODES.M)
        public ViewHolder(View view) {
            super(view);
            contents_layout = (LinearLayout) view.findViewById(R.id.contents_layout);
            imageView = (ImageView)view.findViewById(R.id.home_hot_pic) ;
            title = (TextView)view.findViewById(R.id.home_hot_title_textview);
        }


    }
}
