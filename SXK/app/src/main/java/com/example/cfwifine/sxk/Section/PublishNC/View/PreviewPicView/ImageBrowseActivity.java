package com.example.cfwifine.sxk.Section.PublishNC.View.PreviewPicView;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.example.cfwifine.sxk.R;
import com.nostra13.universalimageloader.utils.L;

import java.util.ArrayList;
import java.util.List;

public class ImageBrowseActivity extends Activity implements ViewPager.OnPageChangeListener,View.OnClickListener,ImageBrowseView {

    private ViewPager vp;
    private TextView hint;
    private TextView save;
    private ViewPageAdapter adapter;
    private ImageBrowsePresenter presenter;
    LinearLayout back;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_image_browse);
        vp = (ViewPager) this.findViewById(R.id.viewPager);
        hint = (TextView) this.findViewById(R.id.hint);
        save = (TextView) this.findViewById(R.id.save);
        back = (LinearLayout)this.findViewById(R.id.browse_back);
        back.setOnClickListener(this);
        save.setOnClickListener(this);
        initPresenter();
        presenter.loadImage();
    }

    public void initPresenter(){
        presenter = new ImageBrowsePresenter(this);
    }

    @Override
    public Intent getDataIntent() {
        return getIntent();
    }

    @Override
    public Context getMyContext() {
        return this;
    }
    private List<String> mList = new ArrayList<>();
    @Override
    public void setImageBrowse(List<String> images,int position) {

        if(images != null && images.size() != 0){
            images.remove("TAG");
            images.remove("MORE");
            mList = images;
            adapter = new ViewPageAdapter(this,mList);
            vp.setAdapter(adapter);
            vp.setCurrentItem(position);
            vp.addOnPageChangeListener(this);
//            hint.setText(position + 1 + "/" + images.size());
            hint.setText(vp.getCurrentItem()+1+"/"+mList.size());
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    // 获取当前图片的position

    @Override
    public void onPageSelected(int position) {
        presenter.setPosition(position);
//        hint.setText(position + 1 + "/" + presenter.getImages().size());
        hint.setText(vp.getCurrentItem()+1+"/"+mList.size());
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.browse_back:

                finish();
                break;
            case R.id.save:
                // 删除一张图片
                if (mList.size()>1){
                    int currentItem = vp.getCurrentItem();
                    presenter.delePosition(currentItem);
                    adapter.notifyDataSetChanged();
                    Intent data = new Intent();
                    data.putStringArrayListExtra("images", (ArrayList<String>) presenter.getImages());
                    setResult(Activity.RESULT_OK, data);
                }else {
                    mList.clear();
                    adapter.notifyDataSetChanged();
                    Intent data = new Intent();
                    data.putStringArrayListExtra("images", (ArrayList<String>) mList);
                    setResult(Activity.RESULT_OK, data);
                    finish();
                }
                break;
            default:
                break;
        }

    }




    public static void startActivity(Context context, ArrayList<String> images, int position){
        Intent intent = new Intent(context,ImageBrowseActivity.class);
        intent.putStringArrayListExtra("images",images);
        intent.putExtra("position",position);
        context.startActivity(intent);
    }


}
