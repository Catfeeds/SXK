package com.example.cfwifine.sxk.Section.PublishNC.View.PreviewPicView;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.example.cfwifine.sxk.R;
import com.example.cfwifine.sxk.Utils.ImageFactory;
import com.example.cfwifine.sxk.Utils.LogUtil;

import java.io.File;
import java.util.List;

/**
 * Created by cfwifine on 16/11/26.
 */

public class FriendFourGridViewAdapter extends FourGridAdapter {


    public FriendFourGridViewAdapter(Context context, List list) {
        super(context, list);
    }

    @Override
    public int getCount() {
        return (list == null) ? 0 : list.size();
    }

    @Override
    public String getUrl(int position) {
        return getItem(position) == null ? null : ((Image)getItem(position)).getUrl();
    }

    @Override
    public Object getItem(int position) {
        return (list == null) ? null : list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int i, View view) {
        ImageView iv = new ImageView(context);
        iv.setScaleType(ImageView.ScaleType.CENTER_CROP);
        iv.setBackgroundColor(Color.parseColor("#f5f5f5"));
//        Picasso.with(context).load(getUrl(i)).placeholder(new ColorDrawable(Color.parseColor("#f5f5f5"))).into(iv);


            if (list.get(i).equals("TAG")){
                iv.setImageResource(R.drawable.publish_publish_addpic);
            }else if (list.get(i).equals("MORE")){
                iv.setImageResource(R.drawable.ninepicmax);
            }else{
                File file = new File(list.get(i).toString());
                Bitmap bitmap = ImageFactory.getBitmapFormUri(context, Uri.fromFile(file),false);
                float s = ImageFactory.getSizeOfBitmap(bitmap);
                LogUtil.e("图片的大小"+s);
                iv.setImageBitmap(bitmap);
//                Picasso.with(context).load(new File(list.get(i).toString())).error(R.drawable.image_selected).into(iv);
            }
        return iv;
    }
}
