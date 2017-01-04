package com.example.cfwifine.sxk.Section.PublishNC.View.PreviewPicView;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.v4.view.PagerAdapter;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


import com.example.cfwifine.sxk.R;
import com.example.cfwifine.sxk.Section.PublishNC.View.photoview.PhotoViewAttacher;
import com.example.cfwifine.sxk.Utils.ImageFactory;

import java.io.File;
import java.util.ArrayList;
import java.util.List;



public class ViewPageAdapter extends PagerAdapter {
    private Context context;
    private List<String> images;
    private SparseArray<View> cacheView;
    private ViewGroup containerTemp;
    private int mChildCount = 0;

    public ViewPageAdapter(Context context, List<String> images) {
        this.context = context;
        this.images = images;
        cacheView = new SparseArray<>(images.size());
    }

    @Override
    public Object instantiateItem(final ViewGroup container, int position) {
        if(containerTemp == null) containerTemp = container;

        View view = cacheView.get(position);

        if(view == null){
            view = LayoutInflater.from(context).inflate(R.layout.vp_item_image,container,false);
            view.setTag(position);
            final ImageView image = (ImageView) view.findViewById(R.id.image);
            final PhotoViewAttacher photoViewAttacher = new PhotoViewAttacher(image);

//            Glide.with(context).load(new File(images.get(position))).override(400,400).thumbnail(.8f).fitCenter().into(image);

            File file = new File(images.get(position).toString());
            Bitmap bitmap = ImageFactory.getBitmapFormUrix(context, Uri.fromFile(file),false);
            image.setImageBitmap(bitmap);


//            Picasso.with(context).load(new File(images.get(position))).config(Bitmap.Config.RGB_565).into(image, new Callback() {
//                @Override
//                public void onSuccess() {
//                    photoViewAttacher.update();
//                }
//
//                @Override
//                public void onError() {
//
//                }
//            });

            photoViewAttacher.setOnPhotoTapListener(new PhotoViewAttacher.OnPhotoTapListener() {
                @Override
                public void onPhotoTap(View view, float x, float y) {
                    Activity activity = (Activity) context;

                    Intent data = new Intent();
                    data.putStringArrayListExtra("images", (ArrayList<String>) images);
                    activity.setResult(Activity.RESULT_OK, data);
                    notifyDataSetChanged();
                    activity.finish();
                }
            });
            cacheView.put(position,view);
        }
        container.addView(view);
        return view;
    }

    public int Dp2Px(Context context, float dp) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dp * scale + 0.5f);
    }


    @Override
    public int getCount() {
        return images.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        View view = (View) object;
        container.removeView(view);
    }

    @Override
    public int getItemPosition(Object object) {
        if ( mChildCount > 0) {
            mChildCount --;
            return POSITION_NONE;
        }
        return super.getItemPosition(object);
    }

    @Override
    public void notifyDataSetChanged() {
        mChildCount = getCount();
        super.notifyDataSetChanged();
    }
}
