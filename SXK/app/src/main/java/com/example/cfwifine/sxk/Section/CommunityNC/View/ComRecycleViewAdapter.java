package com.example.cfwifine.sxk.Section.CommunityNC.View;

import android.app.Activity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.cfwifine.sxk.R;
import com.w4lle.library.NineGridlayout;

import java.util.List;

public class ComRecycleViewAdapter extends RecyclerView.Adapter<ComRecycleViewAdapter.ViewHolder> {
    public List<List<Image>> datas = null;
    public List<String> Stringdatas = null;

    private Activity context;
    public ComRecycleViewAdapter(Activity context, List<List<Image>> datas) {
        this.context = context;
        this.datas = datas;
    }

    //创建新View，被LayoutManager所调用
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_comcell, viewGroup, false);
        ViewHolder vh = new ViewHolder(view);
        return vh;
    }

    //将数据与界面进行绑定的操作
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
//        viewHolder.mTextView.setText(datas.get(position));
        List<Image> itemList = datas.get(position);

        // 判断是不是头部，呵呵
        if (position == 0 && position<1){
            viewHolder.headerView.setVisibility(View.VISIBLE);
        }else {
            viewHolder.headerView.setVisibility(View.GONE);
        }


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
//        viewHolder.content_layout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//            }
//        });

//        if (itemList.isEmpty()){
//            viewHolder.nineGridlayout.setVisibility(View.GONE);
//        }else {
            NineGridViewAdapter nineGridViewAdapter = new NineGridViewAdapter(context,itemList);
            viewHolder.nineGridlayout.setAdapter(nineGridViewAdapter);
            viewHolder.nineGridlayout.setOnItemClickListerner(new NineGridlayout.OnItemClickListerner() {
                @Override
                public void onItemClick(View view, int position) {
                    Log.e("点击了",""+position);
                }
            });
        // 点赞RecycleView
        RaiseRecycleAdapter raiseRecycleAdapter = new RaiseRecycleAdapter(Stringdatas);
        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        viewHolder.raiseRV.setLayoutManager(layoutManager);
        viewHolder.raiseRV.setAdapter(raiseRecycleAdapter);

        // 评论 RecycleView
        CommentRecycleAdapter commentRecycleAdapter = new CommentRecycleAdapter(Stringdatas);
        LinearLayoutManager layoutManagers = new LinearLayoutManager(context);
        layoutManagers.setOrientation(LinearLayoutManager.VERTICAL);
        viewHolder.commentRV.setLayoutManager(layoutManagers);
        viewHolder.commentRV.setAdapter(commentRecycleAdapter);



//        }



    }

    //获取数据的数量
    @Override
    public int getItemCount() {
        return datas.size();
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    //自定义的ViewHolder，持有每个Item的的所有界面元素
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView mTextView;
//        public TextView tag_text;
//        public TextView title_text;
//        public LinearLayout content_layout;
        public NineGridlayout nineGridlayout;
        public RecyclerView raiseRV,commentRV;
        LinearLayout headerView,commentView;
        public HorizontalScrollView horizontalScrollView;


        public ViewHolder(View view) {
            super(view);
            mTextView = (TextView) view.findViewById(R.id.text);
//            tag_text = (TextView) view.findViewById(R.id.tag_text);
//            title_text = (TextView) view.findViewById(R.id.title_text);
//            content_layout = (LinearLayout) view.findViewById(R.id.content_layout);
            nineGridlayout = (NineGridlayout)view.findViewById(R.id.iv_ngrid_layout);
            raiseRV = (RecyclerView)view.findViewById(R.id.raise_recycleview);
            commentRV = (RecyclerView)view.findViewById(R.id.comment_recycleview);
            headerView = (LinearLayout)view.findViewById(R.id.commentHeaderView);
            commentView = (LinearLayout)view.findViewById(R.id.commentView);
            horizontalScrollView = (HorizontalScrollView)view.findViewById(R.id.com_horscrollview_item);


        }
    }
}
