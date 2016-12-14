package com.example.cfwifine.sxk.Section.CommunityNC.Adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.cfwifine.sxk.R;
import com.example.cfwifine.sxk.Section.ClassifyNC.Adapter.ClassifyLeftRecycleViewAdapter;
import com.example.cfwifine.sxk.Section.PublishNC.Model.TestModel;
import com.example.cfwifine.sxk.Utils.LogUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class PublishFriendMomentRecycleAdapter extends RecyclerView.Adapter<PublishFriendMomentRecycleAdapter.ViewHolder> {

    private Context context;
    private ArrayList<Integer> ModelList = null;
    private ArrayList<TestModel> mData = null;
    public PublishFriendMomentRecycleAdapter(Context context, ArrayList<TestModel> datas, ArrayList<Integer> topicModelList) {
        this.context = context;
        this.mData = datas;
        this.ModelList = topicModelList;
    }

    private PublishFriendMomentRecycleAdapter.OnItemClickListener mOnItemClickListener;

    public interface  OnItemClickListener{
        void OnItemClick(View view, Integer name, int position);
    }

    public void setOnItemClickListener(PublishFriendMomentRecycleAdapter.OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }

    //创建新View，被LayoutManager所调用
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_publish_friend_moment, viewGroup, false);
        ViewHolder vh = new ViewHolder(view);
        return vh;
    }

    //将数据与界面进行绑定的操作
    @SuppressLint("NewApi")
    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, final int position) {
        viewHolder.selectedBtn.setText(mData.get(position).getText());

        // 选中狂
        boolean state = mData.get(position).getState();
        if (state == true){
            viewHolder.selectedBtn.setBackgroundDrawable(context.getDrawable(R.drawable.table_shape_cate_green));
            viewHolder.selectedBtn.setTextColor(context.getResources().getColor(R.color.login_turquoisex));
        }else {
            viewHolder.selectedBtn.setBackgroundDrawable(context.getDrawable(R.drawable.table_shape_cate_white));
            viewHolder.selectedBtn.setTextColor(context.getResources().getColor(R.color.textGray));
        }


        viewHolder.selectedBtn.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("NewApi")
            @Override
            public void onClick(View view) {
                for (int i = 0; i<mData.size();i++){
                    mData.get(i).setState(false);
                }
                mData.get(position).setState(true);
                notifyDataSetChanged();
                mOnItemClickListener.OnItemClick(view,ModelList.get(position),position);

            }
        });

    }

    //获取数据的数量
    @Override
    public int getItemCount() {
        return mData.size();
    }

    //自定义的ViewHolder，持有每个Item的的所有界面元素
    public static class ViewHolder extends RecyclerView.ViewHolder {

        Button selectedBtn;
        public ViewHolder(View view) {
            super(view);
            selectedBtn = (Button)view.findViewById(R.id.friend_publish_text);

        }
    }
}
