package com.example.cfwifine.sxk.Section.PublishNC.Adapter;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.cfwifine.sxk.R;
import com.example.cfwifine.sxk.Section.PublishNC.Model.SecondCateModel;
import com.example.cfwifine.sxk.Section.PublishNC.Model.TestModel;
import com.example.cfwifine.sxk.Utils.LogUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CheckRecycleViewAdapter extends RecyclerView.Adapter<CheckRecycleViewAdapter.ViewHolder> {
    public ArrayList<TestModel> datas = null;
    public List<SecondCateModel.CategoryListBean> datasource = null;

    private CheckRecycleViewAdapter.OnItemClickListener mOnItemClickListener;

    public interface  OnItemClickListener{
        void OnItemClick(View view, int position,String title);
    }

    public void setOnItemClickListener(CheckRecycleViewAdapter.OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }

    public CheckRecycleViewAdapter(ArrayList<TestModel> datas, List<SecondCateModel.CategoryListBean> dataSource) {
        this.datas = datas;
        this.datasource = dataSource;
    }

    //创建新View，被LayoutManager所调用
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.check_item, viewGroup, false);
        ViewHolder vh = new ViewHolder(view);
        return vh;
    }
    private HashMap<Integer,Boolean> positionMap = new HashMap<>();
    ArrayList<Boolean> arr = new ArrayList<>();
    //将数据与界面进行绑定的操作
    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, final int position) {
        viewHolder.mTextView.setText(datasource.get(position).getName());
        LogUtil.e("输出"+datasource.get(position).getName());
        // 选中狂
        boolean state = datas.get(position).getState();
        if (state == true){
            viewHolder.check_pic.setImageResource(R.drawable.check_ok);
        }else {
            viewHolder.check_pic.setImageResource(R.drawable.check_circle);
        }

//        viewHolder.checkCircle.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Log.e("选中了",""+datas.get(position));
//            }
//        });
//        viewHolder.check_pic.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                for (int i = 0; i<datas.size();i++){
//                    datas.get(i).setState(false);
//                }
//                datas.get(position).setState(true);
//                notifyDataSetChanged();
//
//            }
//        });

        if (mOnItemClickListener != null) {
            viewHolder.content_layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    for (int i = 0; i<datas.size();i++){
                        datas.get(i).setState(false);
                    }
                    datas.get(position).setState(true);
                    notifyDataSetChanged();
                    mOnItemClickListener.OnItemClick(view,datasource.get(position).getCategoryid(),datasource.get(position).getName());
                }
            });
        }
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });



    }

    //获取数据的数量
    @Override
    public int getItemCount() {
        return datasource.size();
    }

    //自定义的ViewHolder，持有每个Item的的所有界面元素
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView mTextView;
        public RelativeLayout content_layout;
        public LinearLayout checkCircle;
        public ImageView check_pic;

        public ViewHolder(View view) {
            super(view);
            mTextView = (TextView) view.findViewById(R.id.text);
            content_layout = (RelativeLayout) view.findViewById(R.id.content_layout);
            checkCircle = (LinearLayout)view.findViewById(R.id.check_circle);
            check_pic = (ImageView)view.findViewById(R.id.check_pic);
        }
    }
}
