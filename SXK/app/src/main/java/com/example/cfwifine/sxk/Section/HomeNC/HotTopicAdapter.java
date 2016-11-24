package com.example.cfwifine.sxk.Section.HomeNC;

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

import com.example.cfwifine.sxk.R;

import java.util.ArrayList;

public class HotTopicAdapter extends RecyclerView.Adapter<HotTopicAdapter.ViewHolder> {
    public ArrayList<String> datas = null;

    /**
     * 修改 增加context
     * @param datas
     * @param context
     */
    private Context mContext;
    /**
     * 修改 增加context参数
     * @param datas
     * @param context
     */
    public HotTopicAdapter(ArrayList<String> datas, Context context) {
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
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
//        viewHolder.mTextView.setText(datas.get(position));

//        viewHolder.tag_text.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//            }
//        });
//        viewHolder.title_text.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//            }
//        });
        viewHolder.contents_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

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
