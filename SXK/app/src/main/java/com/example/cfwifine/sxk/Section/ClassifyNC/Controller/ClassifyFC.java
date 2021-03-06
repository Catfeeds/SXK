package com.example.cfwifine.sxk.Section.ClassifyNC.Controller;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.cfwifine.sxk.BaseAC.BaseInterface;
import com.example.cfwifine.sxk.R;
import com.example.cfwifine.sxk.Section.ClassifyNC.Adapter.ClassifyBrandListAdapter;
import com.example.cfwifine.sxk.Section.ClassifyNC.Adapter.ClassifyCateListAdapter;
import com.example.cfwifine.sxk.Section.ClassifyNC.Adapter.ClassifyHotListAdapter;
import com.example.cfwifine.sxk.Section.ClassifyNC.Adapter.ClassifyLeftRecycleViewAdapter;
import com.example.cfwifine.sxk.Section.ClassifyNC.Model.ClassfiyBrandModel;
import com.example.cfwifine.sxk.Section.ClassifyNC.Model.ClassfiyHotBrandModel;
import com.example.cfwifine.sxk.Section.ClassifyNC.Model.ClassifyCateModel;
import com.example.cfwifine.sxk.Section.ClassifyNC.Model.ClassifySeconHotModel;
import com.example.cfwifine.sxk.Section.ClassifyNC.Model.RentListModel;
import com.example.cfwifine.sxk.Section.ClassifyNC.Model.SecondCateModel;
import com.example.cfwifine.sxk.Section.CommunityNC.View.L;
import com.example.cfwifine.sxk.Section.PublishNC.Adapter.DividerItemDecoration;
import com.example.cfwifine.sxk.Section.PublishNC.Adapter.HeaderRecyclerAndFooterWrapperAdapter;
import com.example.cfwifine.sxk.Section.PublishNC.Model.BrandBean;
import com.example.cfwifine.sxk.Section.PublishNC.Model.TestModel;
import com.example.cfwifine.sxk.Utils.LoadingUtils;
import com.example.cfwifine.sxk.Utils.LogUtil;
import com.example.cfwifine.sxk.Utils.SharedPreferencesUtils;
import com.example.cfwifine.sxk.Utils.SnackbarUtils;
import com.example.cfwifine.sxk.View.MyGridView;
import com.google.gson.Gson;
import com.mcxtzhang.indexlib.IndexBar.widget.IndexBar;
import com.mcxtzhang.indexlib.suspension.TitleItemDecoration;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;


public class ClassifyFC extends Fragment implements View.OnClickListener {

    View view;
    RecyclerView leftRecycleView, rightRecycleView;
    List<ClassifyCateModel.CategoryListBean> datalist = new ArrayList<>();
    ;
    ArrayList<TestModel> dataListStatue;
    MyGridView myGridView;

    // 配置右侧indexbar
    private ClassifyBrandListAdapter mAdapter;
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
    //    RelativeLayout relativeLayout ;
    List<ClassfiyBrandModel.BrandListBean> brandNameList;
    List<ClassfiyHotBrandModel.HotListBean> hotBrandList;
    List<RentListModel.RentListBean> rentList;
    private TextView classify_reonline_text;
    private LinearLayout classify_nonet_view;
    private LinearLayout classify_online_view;
    private ImageView classify_cate_header_pic;
    private RecyclerView classify_cate_rv;
    private List<SecondCateModel.CategoryListBean> secondDataSource = null;
    Dialog dialog;
    private List<ClassifySeconHotModel.HotListBean> SecondHotDataSouerce = null;
    private RecyclerView secondHotRV;
    private ClassifyHotListAdapter mRaiseRecycleAdapter;
    private ImageButton classify_search_btn;
    private String HOTBRANDLSIT;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        if (view == null) {
            view = inflater.inflate(R.layout.fragment_classify_fc, container, false);
            dialog = LoadingUtils.createLoadingDialog(getActivity(), "加载中...");
            configurationNaviTitle();
            dialog.show();
            initData(0, -1);
            initleftRecycleView();
            initBrandHotList();
            initView();
        }
        return view;
    }


    FrameLayout frameLayout;
    LinearLayout frameLayouts;

    private void initView() {
        frameLayout = (FrameLayout) view.findViewById(R.id.classify_brand_frame);
        frameLayouts = (LinearLayout) view.findViewById(R.id.classify_brand_frames);
        frameLayout.setVisibility(View.VISIBLE);
        frameLayouts.setVisibility(View.GONE);
        classify_reonline_text = (TextView) view.findViewById(R.id.classify_reonline_text);
        classify_reonline_text.setOnClickListener(this);
        classify_nonet_view = (LinearLayout) view.findViewById(R.id.classify_nonet_view);
        classify_nonet_view.setOnClickListener(this);
        classify_online_view = (LinearLayout) view.findViewById(R.id.classify_online_view);
        classify_online_view.setOnClickListener(this);
        classify_online_view.setVisibility(View.VISIBLE);
        classify_nonet_view.setVisibility(View.GONE);
        classify_cate_header_pic = (ImageView) view.findViewById(R.id.classify_cate_header_pic);
        classify_cate_header_pic.setOnClickListener(this);
        classify_cate_rv = (RecyclerView) view.findViewById(R.id.classify_cate_rv);
        classify_search_btn = (ImageButton) view.findViewById(R.id.classify_search_btn);
        classify_search_btn.setOnClickListener(this);
    }

    /**
     * 左侧 0 一级分类 1 二级分类
     */
    private void initData(final int value, final int pos) {

        /**
         * 初始化左侧的list
         */
        dataListStatue = new ArrayList<>();
//        datalist = new ArrayList<>();
        JSONObject order = new JSONObject();
        try {
            order.put("sort", -1);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("pageNo", 0);
            jsonObject.put("pageSize", 0);
            jsonObject.put("order", order);
            jsonObject.put("parentid", value);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String PHPSESSION = String.valueOf(SharedPreferencesUtils.getParam(getActivity(), BaseInterface.PHPSESSION, ""));
        OkHttpUtils.postString().url(BaseInterface.ClassfiyGoodsCateify)
                .addHeader("Cookie", "PHPSESSID=" + PHPSESSION)
                .addHeader("X-Requested-With", "XMLHttpRequest")
                .addHeader("Content-Type", "application/json;chartset=utf-8")
                .content(jsonObject.toString())
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        SnackbarUtils.showShortSnackbar(getActivity().getWindow().getDecorView(), "请求出错!", Color.WHITE, Color.parseColor("#16a6ae"));
                        classify_online_view.setVisibility(View.GONE);
                        classify_nonet_view.setVisibility(View.VISIBLE);
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Log.e("左侧一级品牌", "" + response);
                        Gson gson = new Gson();
                        ClassifyCateModel cateModel = gson.fromJson(response, ClassifyCateModel.class);
//                        ClassifyBigImgModel bigImgModel = gson.fromJson(response,ClassifyBigImgModel.class);
                        if (cateModel.getCode() == 1) {
                            if (value == 0) {
                                datalist = cateModel.getCategoryList();

                                for (int i = 0; i < cateModel.getTotal() + 1; i++) {
                                    if (i == 0) {
                                        TestModel testModel = new TestModel("品牌" + i, true);
                                        dataListStatue.add(testModel);
                                    } else {
                                        TestModel testModel = new TestModel("测试" + i, false);
                                        dataListStatue.add(testModel);
                                    }
                                }
                                initleftRecycleView();
                            } else {
                                initSecondData(value);
                                LogUtil.e("二级分类" + response);
                                secondDataSource = new ArrayList<SecondCateModel.CategoryListBean>();
                                Gson gson1 = new Gson();
                                SecondCateModel secondCateModel = gson1.fromJson(response, SecondCateModel.class);
                                if (secondCateModel.getCode() == 1) {
                                    secondDataSource = secondCateModel.getCategoryList();
                                    initAllCateList();
                                } else if (secondCateModel.getCode() == 0) {

                                } else if (secondCateModel.getCode() == 911) {

                                }
                            }

                        } else if (cateModel.getCode() == 911) {
                            SnackbarUtils.showShortSnackbar(getActivity().getWindow().getDecorView(), "登录超时，请重新登录!", Color.WHITE, Color.parseColor("#16a6ae"));
                            classify_online_view.setVisibility(View.GONE);
                            classify_nonet_view.setVisibility(View.VISIBLE);
                        } else if (cateModel.getCode() == 0) {
                            SnackbarUtils.showShortSnackbar(getActivity().getWindow().getDecorView(), "请求失败!", Color.WHITE, Color.parseColor("#16a6ae"));
                            classify_online_view.setVisibility(View.GONE);
                            classify_nonet_view.setVisibility(View.VISIBLE);
                        }
                    }
                });


    }

    private void initSecondData(int value) {
        JSONObject order = new JSONObject();
        try {
            order.put("sort", -1);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JSONObject js = new JSONObject();
        try {
            js.put("pageNo", 0);
            js.put("pageSize", 0);
            js.put("order", order);
            js.put("categoryid", value);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String PHPSESSION = String.valueOf(SharedPreferencesUtils.getParam(getActivity(), BaseInterface.PHPSESSION, ""));
        OkHttpUtils.postString().url(BaseInterface.ClassifySecondHot)
                .addHeader("Cookie", "PHPSESSID=" + PHPSESSION)
                .addHeader("X-Requested-With", "XMLHttpRequest")
                .addHeader("Content-Type", "application/json;chartset=utf-8")
                .content(js.toString())
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        SnackbarUtils.showShortSnackbar(getActivity().getWindow().getDecorView(), "请求出错!", Color.WHITE, Color.parseColor("#16a6ae"));
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Log.e("包袋热门列表", "" + response);
                        Gson gson = new Gson();
                        ClassifySeconHotModel classifySeconHotModel = gson.fromJson(response, ClassifySeconHotModel.class);
                        if (classifySeconHotModel.getCode() == 1) {
                            SecondHotDataSouerce = classifySeconHotModel.getHotList();
                            initSecondHotRecycleView();
                        } else if (classifySeconHotModel.getCode() == 0) {
                            SnackbarUtils.showShortSnackbar(getActivity().getWindow().getDecorView(), "请求失败!", Color.WHITE, Color.parseColor("#16a6ae"));
                        } else if (classifySeconHotModel.getCode() == 911) {
                            SnackbarUtils.showShortSnackbar(getActivity().getWindow().getDecorView(), "登录超时，请重新登录!", Color.WHITE, Color.parseColor("#16a6ae"));
                        }
                    }
                });


    }

    private void initSecondHotRecycleView() {
        secondHotRV = (RecyclerView) view.findViewById(R.id.classify_hotbrand_rv);
        mRaiseRecycleAdapter = new ClassifyHotListAdapter(getActivity(), SecondHotDataSouerce);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 3);
        secondHotRV.setLayoutManager(gridLayoutManager);
        secondHotRV.setAdapter(mRaiseRecycleAdapter);
//            Log.e("传的值",""+mHeaderDatas.get(0).getImg());
        //热门品牌点击监听事件
        mRaiseRecycleAdapter.setOnItemClickLitener(new ClassifyHotListAdapter.OnItemClickLitener() {
            @Override
            public void onItemClick(View view, final int hotid) {
                Intent intent = new Intent(getActivity(), CateListAC.class);
                intent.putExtra("brandid", hotid);
                startActivity(intent);
            }
        });

    }

    // TODO*********************************配置导航头**********************************************
    private void configurationNaviTitle() {
        ImageView back = (ImageView) view.findViewById(R.id.navi_back_pic);
        back.setVisibility(View.INVISIBLE);
        TextView title = (TextView) view.findViewById(R.id.navi_title);
        title.setText("分类");
    }

    // TODO*********************************配置左侧recycleview************************************
    private void initleftRecycleView() {
        leftRecycleView = (RecyclerView) view.findViewById(R.id.classify_left_recycleview);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        leftRecycleView.setLayoutManager(linearLayoutManager);
        ClassifyLeftRecycleViewAdapter classifyLeftRecycleViewAdapter = new ClassifyLeftRecycleViewAdapter(datalist, dataListStatue);
        leftRecycleView.setAdapter(classifyLeftRecycleViewAdapter);
        classifyLeftRecycleViewAdapter.setOnItemClickListener(new ClassifyLeftRecycleViewAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(View views, int categoryid, int position) {
                if (position == 0) {
                    frameLayouts.setVisibility(View.GONE);
                    frameLayout.setVisibility(View.VISIBLE);
                } else {
                    frameLayout.setVisibility(View.GONE);
                    frameLayouts.setVisibility(View.VISIBLE);
                    LogUtil.e("position" + position);
                    LogUtil.e("categoryid" + categoryid);
                    initData(categoryid, position);
                }
            }
        });
    }

    // TODO*********************************配置右侧recycleview************************************
    @SuppressLint("NewApi")
    private void initRightRecycleView() {
        rightRecycleView = (RecyclerView) view.findViewById(R.id.classify_right_recycleview);
        rightRecycleView.setLayoutManager(mManager = new LinearLayoutManager(getActivity()));
        //initDatas();
        //mDatas = new ArrayList<>();//测试为空或者null的情况 已经通过
        mAdapter = new ClassifyBrandListAdapter(getActivity(), mDatas, hotBrandList);
//        mHeaderAdapter = new HeaderRecyclerAndFooterWrapperAdapter(mAdapter) {
//            @Override
//            protected void onBindHeaderHolder(ViewHolder holder, int headerPos, int layoutId, Object o) {
//                holder.setImageDrawable(R.id.header_pic,(Drawable) o);
//
////                holder.getLayoutId(R.id.header_pic)
//
//            }
//        };
//        mHeaderAdapter.setHeaderView(R.layout.header_complex,null);

//        ClassifyHeaderCollectionRecycleViewAdapter headerApapter = new ClassifyHeaderCollectionRecycleViewAdapter();
//        RecyclerView rv = (RecyclerView) view.findViewById(R.id.header_rv);
//        rv.setAdapter(headerApapter);
//        rv.setLayoutManager(new GridLayoutManager(getActivity(),3));

//        rightRecycleView.setAdapter(mHeaderAdapter);
//        rightRecycleView.addItemDecoration(mDecoration = new TitleItemDecoration(getActivity(), mDatas).setHeaderViewCount(mHeaderAdapter.getHeaderViewCount()));
//        rightRecycleView.addItemDecoration(mDecoration = new TitleItemDecoration(getActivity(), mDatas));
        rightRecycleView.addItemDecoration(mDecoration = new TitleItemDecoration(getActivity(), mDatas));
        mDecoration.setColorTitleBg(Color.WHITE);
        mDecoration.setmTitleHeight(128);
        //如果add两个，那么按照先后顺序，依次渲染。
        //mRv.addItemDecoration(new TitleItemDecoration2(this,mDatas));
        rightRecycleView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL_LIST));
        rightRecycleView.setAdapter(mAdapter);

        mAdapter.setOnItemClickLitener(new ClassifyBrandListAdapter.OnItemClickLitener() {
            @Override
            public void onItemClick(View view, int type, int position) {
                if (type == 2) {
                    if (position != 0) {
//                        SnackbarUtils.showShortSnackbar(getActivity().getWindow().getDecorView(), "选中了" + brandNameList.get(position-1).getBrandid(), Color.WHITE, Color.parseColor("#16a6ae"));
                        String name = mDatas.get(position).getCity();
                        for (int i = 0; i < brandNameList.size(); i++) {
                            if (brandNameList.get(i).getName().equals(name)) {
                                LogUtil.e("选中的brandid" + brandNameList.get(i).getBrandid());
                                Intent intent = new Intent(getActivity(), CateListAC.class);
                                intent.putExtra("brandid", brandNameList.get(i).getBrandid());
                                startActivity(intent);
                            }
                        }
                    }
                } else if (type == 1) {
//                    SnackbarUtils.showShortSnackbar(getActivity().getWindow().getDecorView(), "选中了" + hotBrandList.get(position).getName(), Color.WHITE, Color.parseColor("#16a6ae"));
                    LogUtil.e("头部的brandid" + hotBrandList.get(position).getBrandid());
                    Intent intent = new Intent(getActivity(), CateListAC.class);
                    intent.putExtra("brandid", hotBrandList.get(position).getBrandid());
                    startActivity(intent);
                }

            }
        });
        // 重写点击事件方法（全部品牌列表）
//        rightRecycleView.addOnItemTouchListener(new RecycleViewListener(rightRecycleView, new RecycleViewListener.OnItemClickListener() {
//            @Override
//            public void onItemClick(View view, int position) {
//                if (position != 0) {
//                 SnackbarUtils.showShortSnackbar(getActivity().getWindow().getDecorView(), "选中了" + mDatas.get(position).getCity(), Color.WHITE, Color.parseColor("#16a6ae"));
//                }
//            }
//            @Override
//            public void onItemLongClick(View view, int position) {
//
//            }
//        }));
        //使用indexBar
        mTvSideBarHint = (TextView) view.findViewById(R.id.tvSideBarHint);//HintTextView
        mIndexBar = (IndexBar) view.findViewById(R.id.indexBar);//IndexBar

//        initDatas(getResources().getStringArray(R.array.provinces));
//        initBrandList();
//        initBrandHotList();
        initDatas(brandListArray);
    }

    /**
     * 组织数据源
     *
     * @param data
     * @return
     */
    private void initDatas(final ArrayList<String> data) {
        //延迟两秒 模拟加载数据中....
        getActivity().getWindow().getDecorView().postDelayed(new Runnable() {
            @Override
            public void run() {
                mDatas = new ArrayList<>();
                mDatas.add(0, new BrandBean().setCity("").setTop(true));
                for (int i = 0; i < data.size(); i++) {
                    BrandBean brandBean = new BrandBean();
                    brandBean.setCity(data.get(i).toString());//设置城市名称
                    mDatas.add(brandBean);
                }

                L.e("hotBrandList", hotBrandList.get(0).getImg());
                mAdapter.setDatas(mDatas);
//                mHeaderAdapter.notifyDataSetChanged();
                mAdapter.notifyDataSetChanged();

                mIndexBar.setmPressedShowTextView(mTvSideBarHint)//设置HintTextView
                        .setNeedRealIndex(false)//设置需要真实的索引
                        .setmLayoutManager(mManager)//设置RecyclerView的LayoutManager
                        .setmSourceDatas(mDatas)//设置数据
//                        .setHeaderViewCount(mHeaderAdapter.getHeaderViewCount())//设置HeaderView数量
                        .invalidate();
                mDecoration.setmDatas(mDatas);
            }
        }, 0);

    }

    ArrayList<String> brandListArray;
    String[] brandListString;

    // TODO*********************************配置右侧recycleview数据********************************
    private void initBrandList() {
        JSONObject order = new JSONObject();
        try {
            order.put("brandid", -1);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("pageNo", 0);
            jsonObject.put("pageSize", 0);
            jsonObject.put("order", order);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String PHPSESSION = String.valueOf(SharedPreferencesUtils.getParam(getActivity(), BaseInterface.PHPSESSION, ""));
        OkHttpUtils.postString().url(BaseInterface.ClassfiyGetAllBrand)
                .addHeader("Cookie", "PHPSESSID=" + PHPSESSION)
                .addHeader("X-Requested-With", "XMLHttpRequest")
                .addHeader("Content-Type", "application/json;chartset=utf-8")
                .content(jsonObject.toString())
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        SnackbarUtils.showShortSnackbar(getActivity().getWindow().getDecorView(), "请求出错!", Color.WHITE, Color.parseColor("#16a6ae"));
                        dialog.dismiss();
                        classify_online_view.setVisibility(View.GONE);
                        classify_nonet_view.setVisibility(View.VISIBLE);
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Log.e("品牌1", "" + response);
                        dialog.dismiss();
                        Gson gson = new Gson();
                        ClassfiyBrandModel brandListData = gson.fromJson(response, ClassfiyBrandModel.class);
                        if (brandListData.getCode() == 1) {
                            brandListArray = new ArrayList<String>();
//                            brandListString = new String[]{};
                            brandNameList = brandListData.getBrandList();

                            for (int i = 0; i < brandNameList.size(); i++) {
                                brandListArray.add(brandNameList.get(i).getName());
                            }
//                            initDatas(brandListArray);
                            initRightRecycleView();
                        } else if (brandListData.getCode() == 0) {
                            SnackbarUtils.showShortSnackbar(getActivity().getWindow().getDecorView(), "请求失败!", Color.WHITE, Color.parseColor("#16a6ae"));
                        } else if (brandListData.getCode() == 911) {
                            SnackbarUtils.showShortSnackbar(getActivity().getWindow().getDecorView(), "登录超时，请重新登录!", Color.WHITE, Color.parseColor("#16a6ae"));
                            classify_online_view.setVisibility(View.GONE);
                            classify_nonet_view.setVisibility(View.VISIBLE);
                        }
                    }
                });
    }


    // TODO********************************配置右侧recycleviewHot数据********************************
    //加载右边包袋列表
    private void initAllCateList() {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 3);
        classify_cate_rv.setLayoutManager(gridLayoutManager);
        ClassifyCateListAdapter adapter = new ClassifyCateListAdapter(getActivity(), secondDataSource);
        classify_cate_rv.setAdapter(adapter);
        adapter.setOnItemClickLitener(new ClassifyCateListAdapter.OnItemClickLitener() {
            @Override
            public void onItemClick(View view, int position) {
                //跳转到详细列表界面
                Intent intent = new Intent(getContext(), CateListAC.class);
                intent.putExtra("cateid", secondDataSource.get(position).getCategoryid());
                startActivity(intent);
            }


        });
//        classify_cate_header_pic.setImageResource();
        String picUrl = BaseInterface.ClassfiyGetAllHotBrandImgUrl + datalist.get(1).getImg();
        Glide.with(getActivity())
                .load(picUrl)
                .centerCrop()
                .placeholder(R.drawable.home_placeholder)
                .crossFade()
                .into(classify_cate_header_pic);

    }

    private void initBrandHotList() {
        JSONObject order = new JSONObject();
        try {
            order.put("sort", -1);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("pageNo", 0);
            jsonObject.put("pageSize", 0);
            jsonObject.put("order", order);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String PHPSESSION = String.valueOf(SharedPreferencesUtils.getParam(getActivity(), BaseInterface.PHPSESSION, ""));
        OkHttpUtils.postString().url(BaseInterface.ClassfiyGetAllHotBrand)
                .addHeader("Cookie", "PHPSESSID=" + PHPSESSION)
                .addHeader("X-Requested-With", "XMLHttpRequest")
                .addHeader("Content-Type", "application/json;chartset=utf-8")
                .content(jsonObject.toString())
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        SnackbarUtils.showShortSnackbar(getActivity().getWindow().getDecorView(), "请求出错!", Color.WHITE, Color.parseColor("#16a6ae"));
                        dialog.dismiss();
                        classify_online_view.setVisibility(View.GONE);
                        classify_nonet_view.setVisibility(View.VISIBLE);
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Log.e("热门品牌", "" + response);
                        dialog.dismiss();
                        hotBrandList = new ArrayList<ClassfiyHotBrandModel.HotListBean>();
                        HOTBRANDLSIT = response;
                        Gson gson = new Gson();
                        ClassfiyHotBrandModel hotBrandListData = gson.fromJson(response, ClassfiyHotBrandModel.class);
                        if (hotBrandListData.getCode() == 1) {
                            hotBrandList = hotBrandListData.getHotList();
                            initBrandList();
                        } else if (hotBrandListData.getCode() == 0) {
                            SnackbarUtils.showShortSnackbar(getActivity().getWindow().getDecorView(), "请求失败!", Color.WHITE, Color.parseColor("#16a6ae"));
                        } else if (hotBrandListData.getCode() == 911) {
                            SnackbarUtils.showShortSnackbar(getActivity().getWindow().getDecorView(), "登录超时，请重新登录!", Color.WHITE, Color.parseColor("#16a6ae"));
                            classify_online_view.setVisibility(View.GONE);
                            classify_nonet_view.setVisibility(View.VISIBLE);
                        }
                    }
                });
    }

    // TODO*********************************配置分类recycleview数据********************************


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.classify_reonline_text:
                /**
                 * 断网重新加载
                 */
                initData(0, -1);
                initleftRecycleView();
                initBrandHotList();
                initView();
                break;
            case R.id.classify_search_btn:
                Intent intent = new Intent(getActivity(),ClassifySearchAC.class);
                intent.putExtra("HOTBRANDLIST",HOTBRANDLSIT);
                startActivity(intent);
                break;
            default:
                break;
        }
    }
}
