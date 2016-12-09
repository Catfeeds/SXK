package com.example.cfwifine.sxk.Section.PublishNC.AC;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.cfwifine.sxk.R;
import com.example.cfwifine.sxk.Section.PublishNC.Model.BrandBean;
import com.example.cfwifine.sxk.Section.PublishNC.View.CityAdapter;
import com.example.cfwifine.sxk.Section.PublishNC.View.DividerItemDecoration;
import com.example.cfwifine.sxk.Section.PublishNC.View.HeaderRecyclerAndFooterWrapperAdapter;
import com.example.cfwifine.sxk.Section.PublishNC.View.ViewHolder;
import com.mcxtzhang.indexlib.IndexBar.widget.IndexBar;
import com.mcxtzhang.indexlib.suspension.TitleItemDecoration;



import java.util.ArrayList;
import java.util.List;

public class PublishBrandAC extends AppCompatActivity implements View.OnClickListener {

    private RecyclerView mRv;
    private CityAdapter mAdapter;
    private HeaderRecyclerAndFooterWrapperAdapter mHeaderAdapter;
    private LinearLayoutManager mManager;
    private List<BrandBean> mDatas;
    private TitleItemDecoration mDecoration;

    /**
     * 右侧边栏导航区域
     */
    private IndexBar mIndexBar;

    /**
     * 显示指示器DialogText
     */
    private TextView mTvSideBarHint;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publish_brand_ac);
        configurationNaviTitle();
        initIndexView();
    }
    // TODO*********************************配置导航头**********************************************
    private void configurationNaviTitle() {
        LinearLayout back = (LinearLayout)findViewById(R.id.navi_back);
        back.setOnClickListener(this);
        TextView title = (TextView)findViewById(R.id.navi_title);
        title.setText("品牌");
    }

    private void initIndexView() {
        mRv = (RecyclerView) findViewById(R.id.rv);
        mRv.setLayoutManager(mManager = new LinearLayoutManager(this));
        //initDatas();

        //mDatas = new ArrayList<>();//测试为空或者null的情况 已经通过 2016 09 08


        mAdapter = new CityAdapter(this, mDatas);
        mHeaderAdapter = new HeaderRecyclerAndFooterWrapperAdapter(mAdapter) {
            @Override
            protected void onBindHeaderHolder(ViewHolder holder, int headerPos, int layoutId, Object o) {
                holder.setText(R.id.tvCity, (String) o);
            }
        };
//        mHeaderAdapter.setHeaderView(R.layout.item_city, "测试头部");

        mRv.setAdapter(mHeaderAdapter);
        mRv.addItemDecoration(mDecoration = new TitleItemDecoration(this, mDatas).setHeaderViewCount(mHeaderAdapter.getHeaderViewCount()));
        mDecoration.setColorTitleBg(Color.WHITE);
        mDecoration.setmTitleHeight(128);
        //如果add两个，那么按照先后顺序，依次渲染。
        //mRv.addItemDecoration(new TitleItemDecoration2(this,mDatas));
        mRv.addItemDecoration(new DividerItemDecoration(PublishBrandAC.this, DividerItemDecoration.VERTICAL_LIST));


        //使用indexBar
        mTvSideBarHint = (TextView) findViewById(R.id.tvSideBarHint);//HintTextView
        mIndexBar = (IndexBar) findViewById(R.id.indexBar);//IndexBar

        initDatas(getResources().getStringArray(R.array.provinces));
    }
    /**
     * 组织数据源
     *
     * @param data
     * @return
     */
    private void initDatas(final String[] data) {
        //延迟两秒 模拟加载数据中....
        getWindow().getDecorView().postDelayed(new Runnable() {
            @Override
            public void run() {
                mDatas = new ArrayList<>();
                for (int i = 0; i < data.length; i++) {
                    BrandBean brandBean = new BrandBean();
                    brandBean.setCity(data[i]);//设置城市名称
                    mDatas.add(brandBean);
                }
                mAdapter.setDatas(mDatas);
                mHeaderAdapter.notifyDataSetChanged();

                mIndexBar.setmPressedShowTextView(mTvSideBarHint)//设置HintTextView
                        .setNeedRealIndex(true)//设置需要真实的索引
                        .setmLayoutManager(mManager)//设置RecyclerView的LayoutManager
                        .setmSourceDatas(mDatas)//设置数据
                        .setHeaderViewCount(mHeaderAdapter.getHeaderViewCount())//设置HeaderView数量
                        .invalidate();
                mDecoration.setmDatas(mDatas);
            }
        }, 0);

    }

    /**
     * 更新数据源
     *
     * @param view
     */
    public void updateDatas(View view) {
        for (int i = 0; i < 99; i++) {
            mDatas.add(new BrandBean("东京"));
            mDatas.add(new BrandBean("大阪"));
        }
        mAdapter.notifyDataSetChanged();
        mIndexBar.setmSourceDatas(mDatas);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.navi_back:
                finish();
                break;
            default:
                    break;
        }
    }
}
