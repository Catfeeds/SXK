package com.example.cfwifine.sxk.Section.MineNC.Controller.MineCoin;
import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.cfwifine.sxk.R;
import com.example.cfwifine.sxk.Utils.TimeUtils;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;


public class MineScoinAdapter extends RecyclerView.Adapter<MineScoinAdapter.MyViewHolder> {

    private Context mContext;
    private List<MineScoreModel.RecordListBean> mDatas = null;


    public MineScoinAdapter(Context context, List<MineScoreModel.RecordListBean> dataSource) {
        mContext = context;
        mDatas = dataSource;
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        double dd = mDatas.get(position).getAmount();
        holder.price.setText(String.format("%.2f",dd/100));
        double point = mDatas.get(position).getPoint();
        if (mDatas.get(position).getType() == 7){
            holder.coin.setText("- "+String.format("%.2f",point));
        }else {
            holder.coin.setText("+ "+String.format("%.2f",point));
        }
        SimpleDateFormat DEFAULT_SDF = new SimpleDateFormat("yyyy-MM-dd  HH:mm", Locale.getDefault());
        String timea = TimeUtils.milliseconds2String(mDatas.get(position).getCreatetime() * 1000l,DEFAULT_SDF);
        holder.time.setText(timea);
        String names = "";
        switch (mDatas.get(position).getType()){
            case 1:
                names = "租赁完成！";
                break;
            case 2:
                names = "租赁分享奖励";
                break;
            case 3:
                names = "寄卖成功！";
                break;
            case 4:
                names = "寄卖完成奖励";
                break;
            case 5:
                names = "租赁完成奖励";
                break;
            case 6:
                names = "寄卖分享奖励";
                break;
            case 7:
                names = "余额支付";
                break;
            default:
                break;
        }
        holder.name.setText(names);

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup arg0, int arg1) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.mine_score_item, arg0, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }


    class MyViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        TextView price,coin,time,name;
        public MyViewHolder(View itemView) {
            super(itemView);
            cardView = (CardView)itemView.findViewById(R.id.cardView);
            price = (TextView)itemView.findViewById(R.id.price);
            coin = (TextView)itemView.findViewById(R.id.coin);
            time = (TextView)itemView.findViewById(R.id.time);
            name = (TextView)itemView.findViewById(R.id.name);
        }
    }
}

