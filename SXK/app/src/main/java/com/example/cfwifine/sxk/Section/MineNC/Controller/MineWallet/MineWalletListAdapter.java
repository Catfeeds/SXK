package com.example.cfwifine.sxk.Section.MineNC.Controller.MineWallet;
import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.cfwifine.sxk.R;
import com.example.cfwifine.sxk.Section.MineNC.Controller.MineCoin.MineScoreModel;
import com.example.cfwifine.sxk.Section.MineNC.Model.MineWallentModel;
import com.example.cfwifine.sxk.Utils.TimeUtils;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;


public class MineWalletListAdapter extends RecyclerView.Adapter<MineWalletListAdapter.MyViewHolder> {

    private Context mContext;
    private List<MineWallentModel.WalletListBean> mDatas = null;


    public MineWalletListAdapter(Context context, List<MineWallentModel.WalletListBean> dataSource) {
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
        double amount = mDatas.get(position).getAmount();
        holder.coin.setText("+ "+String.format("%.2f",amount/100));
        double rest = mDatas.get(position).getBalance();
        holder.restMoney.setText(String.format("%.2f",rest/100));
        SimpleDateFormat DEFAULT_SDF = new SimpleDateFormat("yyyy-MM-dd  HH:mm", Locale.getDefault());
        String timea = TimeUtils.milliseconds2String(mDatas.get(position).getCreatetime() * 1000l,DEFAULT_SDF);
        holder.time.setText(timea);

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup arg0, int arg1) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.mine_wallet_item, arg0, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }


    class MyViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        TextView price,coin,time,restMoney;
        public MyViewHolder(View itemView) {
            super(itemView);
            cardView = (CardView)itemView.findViewById(R.id.cardView);
            price = (TextView)itemView.findViewById(R.id.price);
            coin = (TextView)itemView.findViewById(R.id.coin);
            time = (TextView)itemView.findViewById(R.id.time);
            restMoney = (TextView)itemView.findViewById(R.id.rest_money);
        }
    }
}

