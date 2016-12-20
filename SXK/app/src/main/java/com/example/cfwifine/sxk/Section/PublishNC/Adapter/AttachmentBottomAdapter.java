package com.example.cfwifine.sxk.Section.PublishNC.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.cfwifine.sxk.R;
import com.example.cfwifine.sxk.Section.PublishNC.Model.AttachmentModel;
import com.example.cfwifine.sxk.Utils.LogUtil;

import java.util.List;


public class AttachmentBottomAdapter extends RecyclerView.Adapter<AttachmentBottomAdapter.ViewHolder> {
    private Context mContext;
    private LayoutInflater mInflater;
    private List<String> dataSource;

    private AttachmentBottomAdapter.OnItemClickListener mOnItemClickListener;

    public interface  OnItemClickListener{
        void OnItemClick(View view, List<String> name);
    }
    public void setOnItemClickListener(AttachmentBottomAdapter.OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }

    public AttachmentBottomAdapter(Context mContext, List<String> dataSource) {
        this.mContext = mContext;
        this.dataSource = dataSource;
        mInflater = LayoutInflater.from(mContext);
    }


    @Override
    public AttachmentBottomAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(mInflater.inflate(R.layout.item_attachment_bottom, parent, false));
    }

    @Override
    public void onBindViewHolder(final AttachmentBottomAdapter.ViewHolder holder, final int position) {
        holder.tvCity.setText(dataSource.get(position).toString());
        holder.check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        if (mOnItemClickListener != null){
            holder.check.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return dataSource.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvCity;
        RelativeLayout frameLayout;
        ImageButton check;
        public ViewHolder(View itemView) {
            super(itemView);
            tvCity = (TextView) itemView.findViewById(R.id.attach_bottom_text);
            frameLayout = (RelativeLayout) itemView.findViewById(R.id.attach_cell);
            check = (ImageButton)itemView.findViewById(R.id.attach_circle);
        }
    }
}
