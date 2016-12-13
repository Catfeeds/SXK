package com.example.cfwifine.sxk.Section.CommunityNC.Adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.cfwifine.sxk.R;
import com.example.cfwifine.sxk.Section.CommunityNC.Model.CommentModel;
import com.example.cfwifine.sxk.Utils.LogUtil;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class CommentRecycleAdapter extends RecyclerView.Adapter<CommentRecycleAdapter.ViewHolder> {
    public List<?> datas = null;
    private Activity context;
    private int poss = 0;
    private ArrayList<String> mData = null;

    public CommentRecycleAdapter(List<?> datas, int pos) {
//        this.context = context;
        this.datas = datas;
        this.poss = pos;
    }

    //创建新View，被LayoutManager所调用
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_comment, viewGroup, false);
        ViewHolder vh = new ViewHolder(view);
        return vh;
    }

    //将数据与界面进行绑定的操作
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {

        LogUtil.e("评论------" + String.valueOf(datas));
//        JSONObject jsonObject = new JSONObject();
//        try {
//            jsonObject.put("commentList",datas);
//            LogUtil.e("大事发生地方"+jsonObject);
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
        try {
            JSONObject jsonObject = new JSONObject((datas.get(position).toString()));
            LogUtil.e("jsonObject字符串" + jsonObject);

            LogUtil.e("评论的内容" + jsonObject.optString("nickname"));
            viewHolder.username.setText(jsonObject.optString("nickname") + ":");
            LogUtil.e("评论的内容" + jsonObject.optString("comment"));
            viewHolder.mTextView.setText(jsonObject.optString("comment"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
//        Gson gson = new Gson();
//        CommentModel commentModel = gson.fromJson(jsonObject.toString(),CommentModel.class);
//        viewHolder.username.setText(commentModel.getCommentList().get(position).getNickname());
//        viewHolder.mTextView.setText(commentModel.getCommentList().get(position).getComment());


    }

    //获取数据的数量
    @Override
    public int getItemCount() {
        return datas.size();
    }

    //自定义的ViewHolder，持有每个Item的的所有界面元素
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView mTextView;
        public TextView username;
        LinearLayout commentView;

        public ViewHolder(View view) {
            super(view);
            mTextView = (TextView) view.findViewById(R.id.comment_says);
            username = (TextView) view.findViewById(R.id.comment_username);
            commentView = (LinearLayout) view.findViewById(R.id.comment_view);

        }
    }
}
