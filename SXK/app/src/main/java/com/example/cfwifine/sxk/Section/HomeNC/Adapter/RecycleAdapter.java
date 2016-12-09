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

import com.example.cfwifine.sxk.R;

import java.util.ArrayList;

public class RecycleAdapter extends RecyclerView.Adapter<RecycleAdapter.ViewHolder> {
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
    public RecycleAdapter(ArrayList<String> datas, Context context) {
        mContext = context;
        this.datas = datas;
    }

    //创建新View，被LayoutManager所调用
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item, viewGroup, false);
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
        viewHolder.content_layout.setOnClickListener(new View.OnClickListener() {
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
//        public TextView mTextView;
//        public TextView tag_text;
//        public TextView title_text;
        public LinearLayout content_layout;
        private ImageView  imageView;

        /**
         *
         * 新增以下3个
         */
        private HorizontalScrollView horizontalScrollView;
        private LinearLayout mGallery;
        private int[] mImgIds;

        @RequiresApi(api = Build.VERSION_CODES.M)
        public ViewHolder(View view) {
            super(view);
            initHorscrollViewData();
            content_layout = (LinearLayout) view.findViewById(R.id.content_layout);
            imageView = (ImageView)view.findViewById(R.id.home_classify_pic) ;


            /**
             * 修改horizontalscrllview部分
             */
            horizontalScrollView = (HorizontalScrollView) view.findViewById(R.id.id_horscrollview);
            mGallery = (LinearLayout) view.findViewById(R.id.id_gallerys);

            for (int i = 0; i < mImgIds.length; i++) {

                View view1 = LayoutInflater.from(mContext).inflate(R.layout.activity_index_gallery_item,
                        mGallery, false);
                ImageView img = (ImageView) view1
                        .findViewById(R.id.id_index_gallery_item_image);
                img.setImageResource(mImgIds[i]);
                TextView txt = (TextView) view1
                        .findViewById(R.id.id_index_gallery_item_text);
                txt.setText("我是图");
                // 自适应屏幕
                RelativeLayout lay = (RelativeLayout) view1.findViewById(R.id.change_size);
                WindowManager windowManager = (WindowManager) mContext.getSystemService(mContext.WINDOW_SERVICE);
                int width = windowManager.getDefaultDisplay().getWidth();
                int height = windowManager.getDefaultDisplay().getHeight();

                RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) lay.getLayoutParams();
                params.width = width / 3;
                params.height = 500;
                lay.setLayoutParams(params);

                // 点击事件
                view1.setId(i);
                view1.setTag("美女第" + i + "位");
                view1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int id = v.getId();
                        String content = (String) v.getTag();
                        for (int i = 0; i<mImgIds.length;i++){
                            if (i == id){
                                Log.e("你点击了",""+content);
                            }
                        }
                    }
                });
                mGallery.addView(view1);
            }
        }

        private void initHorscrollViewData() {
            mImgIds = new int[]{R.drawable.home_placeholder, R.drawable.home_placeholder, R.drawable.home_placeholder,
                    R.drawable.home_placeholder, R.drawable.home_placeholder, R.drawable.home_placeholder, R.drawable.home_placeholder,
                    R.drawable.home_placeholder, R.drawable.home_placeholder};
        }
    }
}
