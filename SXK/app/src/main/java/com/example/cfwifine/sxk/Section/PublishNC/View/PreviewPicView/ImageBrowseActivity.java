package com.example.cfwifine.sxk.Section.PublishNC.View.PreviewPicView;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cfwifine.sxk.R;
import com.example.cfwifine.sxk.Utils.LogUtil;
import com.example.cfwifine.sxk.Utils.SnackbarUtils;

import java.util.ArrayList;
import java.util.List;

import static com.example.cfwifine.sxk.R.color.colorBlue;

public class ImageBrowseActivity extends Activity implements ViewPager.OnPageChangeListener, View.OnClickListener, ImageBrowseView {

    private ViewPager vp;
    private TextView hint;
    private TextView save;
    private ViewPageAdapter adapter;
    private ImageBrowsePresenter presenter;
    LinearLayout back;
    private CommunityViewPageAdapter CommuAdapter;
    private int TAG;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_image_browse);
        vp = (ViewPager) this.findViewById(R.id.viewPager);
        hint = (TextView) this.findViewById(R.id.hint);
        save = (TextView) this.findViewById(R.id.save);
        back = (LinearLayout) this.findViewById(R.id.browse_back);
        back.setOnClickListener(this);
        save.setOnClickListener(this);
        initPresenter();
        presenter.loadImage();
    }

    public void initPresenter() {
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
    public void setImageBrowse(List<String> images, int position, int i) {

        TAG = i;
        if (i == 999) {
            save.setText("保存");
//            save.setTextColor(getResources().getColor(colorBlue));
            LogUtil.e("点击了预览" + i);
            if (images != null && images.size() != 0) {
                images.remove("TAG");
                images.remove("MORE");
                mList = images;
                CommuAdapter = new CommunityViewPageAdapter(this, mList);
                vp.setAdapter(CommuAdapter);
                vp.setCurrentItem(position);
                vp.addOnPageChangeListener(this);
//                hint.setTextColor(getResources().getColor(colorBlue));
                hint.setText(vp.getCurrentItem() + 1 + "/" + mList.size());
            }
        } else {
            if (images != null && images.size() != 0) {
                images.remove("TAG");
                images.remove("MORE");
                mList = images;
                adapter = new ViewPageAdapter(this, mList);
                vp.setAdapter(adapter);
                vp.setCurrentItem(position);
                vp.addOnPageChangeListener(this);
//            hint.setText(position + 1 + "/" + images.size());
                hint.setText(vp.getCurrentItem() + 1 + "/" + mList.size());
            }
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
        hint.setText(vp.getCurrentItem() + 1 + "/" + mList.size());
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.browse_back:
                finish();
                break;
            case R.id.save:

                if (TAG == 999) {
                    // 保存一张图
                    int cur = vp.getCurrentItem();
                    Log.e("图片------",cur+"");
                    presenter.saveImage(cur,this);
                    SnackbarUtils.showShortSnackbar(this.getWindow().getDecorView(), "图片已保存成功!", Color.WHITE, Color.parseColor("#16a6ae"));

                } else {
                    // 删除一张图片
                    if (mList.size() > 1) {
                        int currentItem = vp.getCurrentItem();
                        presenter.delePosition(currentItem);
                        adapter.notifyDataSetChanged();
                        Intent data = new Intent();
                        data.putStringArrayListExtra("images", (ArrayList<String>) presenter.getImages());
                        setResult(Activity.RESULT_OK, data);
                    } else {
                        mList.clear();
                        adapter.notifyDataSetChanged();
                        Intent data = new Intent();
                        data.putStringArrayListExtra("images", (ArrayList<String>) mList);
                        setResult(Activity.RESULT_OK, data);
                        finish();
                    }
                }
                break;
            default:
                break;
        }

    }


    public static void startActivity(Context context, ArrayList<String> images, int position) {
        Intent intent = new Intent(context, ImageBrowseActivity.class);
        intent.putStringArrayListExtra("images", images);
        intent.putExtra("position", position);
        context.startActivity(intent);
    }
}
