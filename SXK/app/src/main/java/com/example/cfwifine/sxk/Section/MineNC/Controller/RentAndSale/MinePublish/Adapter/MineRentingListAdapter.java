package com.example.cfwifine.sxk.Section.MineNC.Controller.RentAndSale.MinePublish.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.cfwifine.sxk.BaseAC.BaseInterface;
import com.example.cfwifine.sxk.R;
import com.example.cfwifine.sxk.Section.MineNC.Controller.RentAndSale.MinePublish.Model.MineItemRentingModel;

import java.util.List;


public class MineRentingListAdapter extends RecyclerView.Adapter<MineRentingListAdapter.ViewHolder> {
    private final List<MineItemRentingModel.RentListBean> classifyDataSource;
    private Context mContext;
    private LayoutInflater mInflater;


    private MineRentingListAdapter.OnItemClickListener mOnItemClickListener;

    public interface  OnItemClickListener{
        void OnItemClick(View view, int maintainid, int type,int pos);
    }
    public void setOnItemClickListener(MineRentingListAdapter.OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }

    public MineRentingListAdapter(Context mContext, List<MineItemRentingModel.RentListBean> classifyDataSource) {
        this.mContext = mContext;
        this.classifyDataSource = classifyDataSource;
    }


    @Override
    public MineRentingListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_mine_publish_renting_list, parent, false);
        MineRentingListAdapter.ViewHolder vh = new MineRentingListAdapter.ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(final MineRentingListAdapter.ViewHolder holder, final int position) {
        holder.name.setText(classifyDataSource.get(position).getName());
        String description = classifyDataSource.get(position).getDescription();
        if (description.trim().length()>15){
            holder.description.setText(description.trim().substring(0,15)+"...");
        }else {
            holder.description.setText(description);
        }
        double rentPrice = classifyDataSource.get(position).getRentPrice();
        holder.price.setText(" ¥ "+ String.format("%.2f",rentPrice/100)+"/天");
        double marketPrice = classifyDataSource.get(position).getMarketPrice();
        holder.rentPrice.setText("市场价： ¥ "+String.format("%.2f",marketPrice/100));
        String picUrl = BaseInterface.ClassfiyGetAllHotBrandImgUrl + classifyDataSource.get(position).getImgList().get(0);
        Glide.with(mContext).load(picUrl).diskCacheStrategy(DiskCacheStrategy.ALL).placeholder(R.drawable.home_placeholder).animate(R.anim.glide_animal).into(holder.pic);
        holder.ordernumberedit.setText("  "+classifyDataSource.get(position).getOddNumber());
        if (mOnItemClickListener != null){
            holder.inputOrderNumber.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mOnItemClickListener.OnItemClick(view,classifyDataSource.get(position).getRentid(), 1,position);
                }
            });
        }

        if (mOnItemClickListener!=null){
            holder.reBackNumber.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mOnItemClickListener.OnItemClick(view,classifyDataSource.get(position).getRentid(),2,position);
                }
            });
        }
        if (mOnItemClickListener!=null){
            holder.frameLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mOnItemClickListener.OnItemClick(view,classifyDataSource.get(position).getOrderid(),3,position);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return classifyDataSource != null ? classifyDataSource.size() : 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView rentPrice;
        private final Button inputOrderNumber,reBackNumber;
        LinearLayout frameLayout;
        TextView name,description,price;
        ImageView pic;
        TextView ordernumberedit;
        String oddNumber;

        public ViewHolder(View itemView) {
            super(itemView);

            frameLayout = (LinearLayout) itemView.findViewById(R.id.mine_renting_cell);
            name = (TextView)itemView.findViewById(R.id.mine_renting_name);
            description = (TextView)itemView.findViewById(R.id.mine_renting_descript);
            price = (TextView)itemView.findViewById(R.id.mine_renting_price);
            pic = (ImageView)itemView.findViewById(R.id.mine_renting_pic);
            rentPrice = (TextView)itemView.findViewById(R.id.mine_renting_marketprice);
            inputOrderNumber = (Button)itemView.findViewById(R.id.mine_renting_inputOrderNumber_btn);
            ordernumberedit = (TextView)itemView.findViewById(R.id.mine_renting_ordernumber);
            reBackNumber = (Button)itemView.findViewById(R.id.mine_renting_confirmreuse_btn);
//            oddNumber = ordernumberedit.getText().toString().trim();


        }
    }

//    //自定义EditText的监听类
//    class TextSwitcher implements TextWatcher {
//
//        private ViewHolder mHolder;
//
//        public TextSwitcher(ViewHolder mHolder) {
//            this.mHolder = mHolder;
//        }
//
//        @Override
//        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//        }
//
//        @Override
//        public void onTextChanged(CharSequence s, int start, int before, int count) {
//
//        }
//
//        @Override
//        public void afterTextChanged(Editable s) {
//            //用户输入完毕后，处理输入数据，回调给主界面处理
////            SaveEditListener listener= (SaveEditListener) mContext;
//            if(s!=null){
//                mOnItemClickListener.SaveEdit(Integer.parseInt(mHolder.ordernumberedit.getTag().toString()),s.toString());
//            }
//
//        }
//    }

}
