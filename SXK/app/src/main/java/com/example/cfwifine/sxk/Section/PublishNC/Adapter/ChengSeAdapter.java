package com.example.cfwifine.sxk.Section.PublishNC.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.cfwifine.sxk.R;
import com.example.cfwifine.sxk.Section.PublishNC.Model.TestModel;
import com.example.cfwifine.sxk.Utils.LogUtil;

import java.util.ArrayList;
import java.util.HashMap;

public class ChengSeAdapter extends RecyclerView.Adapter<ChengSeAdapter.ViewHolder> {
    private String[] info= new String[]{};
    String[] introlist = new String[]{"未使用，包装配件齐全","包装配件不齐全，或因存放产生细微痕迹","未使用但有明显存放痕迹，或轻微使用痕迹","有使用痕迹，整体无变形或护理后状态非常好","有明显使用痕迹，整体轻微变形","有明显使用痕迹，整体状态有变化"};

    public ArrayList<TestModel> datas = null;
    private ChengSeAdapter.OnItemClickListener mOnItemClickListener;
    private ArrayList<String> into;

    public interface  OnItemClickListener{
        void OnItemClick(View view, int position, String title);
    }

    public void setOnItemClickListener(ChengSeAdapter.OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }

    public ChengSeAdapter(ArrayList<TestModel> datas) {
        this.datas = datas;
    }

    //创建新View，被LayoutManager所调用
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.chengse_item, viewGroup, false);
        ViewHolder vh = new ViewHolder(view);
        return vh;
    }
    private HashMap<Integer,Boolean> positionMap = new HashMap<>();
    ArrayList<Boolean> arr = new ArrayList<>();
    //将数据与界面进行绑定的操作
    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, final int position) {
        viewHolder.mTextView.setText(datas.get(position).getText());
        viewHolder.intro.setText(introlist[position]);
        // 选中狂
        boolean state = datas.get(position).getState();
        if (state == true){
            viewHolder.check_pic.setImageResource(R.drawable.check_ok);
        }else {
            viewHolder.check_pic.setImageResource(R.drawable.check_circle);
        }
        if (mOnItemClickListener != null) {
            viewHolder.content_layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    for (int i = 0; i<datas.size();i++){
                        datas.get(i).setState(false);
                    }
                    datas.get(position).setState(true);
                    notifyDataSetChanged();
                    mOnItemClickListener.OnItemClick(view,-1,datas.get(position).getText());
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
        return datas.size();
    }

    //自定义的ViewHolder，持有每个Item的的所有界面元素
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView mTextView,intro;
        public RelativeLayout content_layout;
        public LinearLayout checkCircle;
        public ImageView check_pic;

        public ViewHolder(View view) {
            super(view);
            mTextView = (TextView) view.findViewById(R.id.text);
            intro = (TextView)view.findViewById(R.id.textdetail);
            content_layout = (RelativeLayout) view.findViewById(R.id.content_layout);
            checkCircle = (LinearLayout)view.findViewById(R.id.check_circle);
            check_pic = (ImageView)view.findViewById(R.id.check_pic);
        }
    }
}
