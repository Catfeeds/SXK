package com.example.cfwifine.sxk.Section.MineNC.Controller;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cfwifine.sxk.BaseAC.BaseInterface;
import com.example.cfwifine.sxk.R;
import com.example.cfwifine.sxk.Section.CommunityNC.View.Image;
import com.example.cfwifine.sxk.Section.CommunityNC.View.L;
import com.example.cfwifine.sxk.Section.CommunityNC.View.ProgressView;
import com.example.cfwifine.sxk.Section.MineNC.Adapter.AddressSettingRecycleViewAdapter;
import com.example.cfwifine.sxk.Section.MineNC.Adapter.MyClickOnListener;
import com.example.cfwifine.sxk.Section.MineNC.Model.AddressListModel;
import com.example.cfwifine.sxk.Utils.SharedPreferencesUtils;
import com.example.cfwifine.sxk.Utils.SnackbarUtils;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import me.fangx.haorefresh.HaoRecyclerView;
import me.fangx.haorefresh.LoadMoreListener;
import okhttp3.Call;

public class AddressSettingCommomAC extends AppCompatActivity implements View.OnClickListener {
    private SwipeRefreshLayout swiperefresh;
    private HaoRecyclerView hao_recycleview;
    private AddressSettingRecycleViewAdapter mAdapter;
    private ArrayList<String> listData = new ArrayList<>();
    private int pageSize = 10;
    private int pageNo = 1;
    private int receiverid = -1;
    private List<List<Image>> imagesList;
    private ImageView navi_back_pic;
    private LinearLayout navi_back;
    private TextView navi_title;
    private TextView navi_right;
    private LinearLayout navi_right_lays;
    AddressListModel addressListModel;
    List<AddressListModel.ReceiverListBean> data = null;
    List<AddressListModel.ReceiverListBean> newDataSource;
    int Total = 0;
    int pageNum = 10;
    private ImageView img_float;
    ArrayList<Integer> receiveList = new ArrayList<Integer>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address_setting_commom_ac);
        initView();
        configurationNaviTitle();
        getListData(pageNo, pageSize);
    }

    // TODO*********************************配置View**********************************************
    private void configurationNaviTitle() {
        navi_back_pic = (ImageView) findViewById(R.id.navi_back_pic);
        navi_back = (LinearLayout) findViewById(R.id.navi_back);
        navi_back.setOnClickListener(this);
        navi_title = (TextView) findViewById(R.id.navi_title);
        navi_title.setText("地址设置");
        navi_right = (TextView) findViewById(R.id.navi_right);
        navi_right_lays = (LinearLayout) findViewById(R.id.navi_right_lays);
    }

    // TODO*********************************配置刷新RECYCLEVIEW**********************************************
    private void initAddressListView() {
        swiperefresh = (SwipeRefreshLayout) findViewById(R.id.swiperefresh);
        swiperefresh.setColorSchemeResources(R.color.textBlueDark, R.color.textBlueDark, R.color.textBlueDark,
                R.color.textBlueDark);

        swiperefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                new Handler().postDelayed(new Runnable() {
                    public void run() {
                        data.clear();
                        pageNum = 10;
//                        pageSize+=10;
                        getListData(pageNo, 10);
                        //注意此处
                        hao_recycleview.refreshComplete();
                        swiperefresh.setRefreshing(false);
                        mAdapter.notifyDataSetChanged();
                    }
                }, 1000);

            }
        });
        hao_recycleview = (HaoRecyclerView) findViewById(R.id.hao_recycleview);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this) {
            //            @Override
//            public boolean canScrollVertically() {
//            return false;
//        }
            // SC嵌套ReCV防止数据显示不完整
            @Override
            public void onMeasure(RecyclerView.Recycler recycler, RecyclerView.State state, int widthSpec, int heightSpec) {
                int expandSpec = View.MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, View.MeasureSpec.AT_MOST);
                super.onMeasure(recycler, state, widthSpec, expandSpec);
            }
        };
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        hao_recycleview.setLayoutManager(layoutManager);
        //设置自定义加载中和到底了效果
        ProgressView progressView = new ProgressView(this);
        progressView.setIndicatorId(ProgressView.BallPulse);
        progressView.setIndicatorColor(0xff69b3e0);
        hao_recycleview.setFootLoadingView(progressView);

        TextView textView = new TextView(this);
        textView.setText("已经到底啦~");
        textView.setTextColor(getResources().getColor(R.color.black));
        hao_recycleview.setFootEndView(textView);

        hao_recycleview.setLoadMoreListener(new LoadMoreListener() {
            @Override
            public void onLoadMore() {
                new Handler().postDelayed(new Runnable() {
                    public void run() {

                        pageNum += 10;
                        L.e("pageNum" + pageNum);
                        if (pageNum >= Total) {
                            hao_recycleview.loadMoreEnd();
                            return;
                        }
                        getListData(pageNo, pageNum);
                        mAdapter.notifyDataSetChanged();
                        hao_recycleview.loadMoreComplete();
                    }
                }, 1000);
            }
        });

//        getListData();
        mAdapter = new AddressSettingRecycleViewAdapter(data);
        hao_recycleview.setAdapter(mAdapter);


        hao_recycleview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(AddressSettingCommomAC.this, "click-----position" + i, Toast.LENGTH_SHORT).show();
            }
        });


        hao_recycleview.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(AddressSettingCommomAC.this, "long click------position" + i, Toast.LENGTH_SHORT).show();
                return false;
            }
        });
        // 获取收货地址id
        for (int i = 0; i<data.size();i++){
            int rece = data.get(i).getReceiverid();
            receiveList.add(rece);
        }
        mAdapter.mEditOnClickListener(new MyClickOnListener() {
            @Override
            public void edit(View view, int position) {
                L.e("编辑"+position);
                /**
                 * 编辑接口
                 */
                editGoodsByPositon(position);
            }

            @Override
            public void delete(View view, int position) {
                /**
                 * 删除接口
                 */
                deleteGoodsByReceiveID(position);
            }
        });

    }
    // TODO****************************************编辑********************************************
    private void editGoodsByPositon(int position){
        startActivity(AddressDetailAC.class,position);
    }
    // TODO****************************************删除********************************************
    private void deleteGoodsByReceiveID(Integer receiverid) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("receiverid",receiverid);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String PHPSESSION = String.valueOf(SharedPreferencesUtils.getParam(getApplicationContext(), BaseInterface.PHPSESSION, ""));
        OkHttpUtils.postString().url(BaseInterface.SettingDelReceiveGoods)
                .addHeader("Cookie", "PHPSESSID=" + PHPSESSION)
                .addHeader("X-Requested-With", "XMLHttpRequest")
                .addHeader("Content-Type", "application/json;chartset=utf-8")
                .content(jsonObject.toString())
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        SnackbarUtils.showShortSnackbar(getWindow().getDecorView(), "请求出错!", Color.WHITE, Color.parseColor("#16a6ae"));
                    }
                    @Override
                    public void onResponse(String response, int id) {
                        try {
                            JSONObject jsonObject1 = new JSONObject(response);
                            if (jsonObject1.optInt("code")==1){
                                getListData(pageNo,pageNum);
                                SnackbarUtils.showShortSnackbar(getWindow().getDecorView(), "删除成功!", Color.WHITE, Color.parseColor("#16a6ae"));
                            }else if (jsonObject1.optInt("code")==911){
                                SnackbarUtils.showShortSnackbar(getWindow().getDecorView(), "登录超时，请重新登录!", Color.WHITE, Color.parseColor("#16a6ae"));
                            }else {
                                SnackbarUtils.showShortSnackbar(getWindow().getDecorView(), "请求失败!", Color.WHITE, Color.parseColor("#16a6ae"));
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
    }

    private void getListData(int pageNo, int pageSize) {

        JSONObject order = new JSONObject();
        try {
            order.put("receiverid", receiverid);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("pageNo", pageNo);
            jsonObject.put("pageSize", pageSize);
            jsonObject.put("order", order);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String PHPSESSION = String.valueOf(SharedPreferencesUtils.getParam(getApplicationContext(), BaseInterface.PHPSESSION, ""));
        Log.e("PHPSESSION", "" + PHPSESSION);
        Log.e("构造体", "" + jsonObject);
        /**
         * 发送报头的时候PHPSESSIONID是作为Cookie发送的，不然无法验证！！！！！！
         */
        OkHttpUtils.postString().url(BaseInterface.SettingListReceiveGoods)
                .addHeader("Cookie", "PHPSESSID=" + PHPSESSION)
                .addHeader("X-Requested-With", "XMLHttpRequest")
                .addHeader("Content-Type", "application/json;chartset=utf-8")
                .content(jsonObject.toString())
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        SnackbarUtils.showShortSnackbar(getWindow().getDecorView(), "请求出错!", Color.WHITE, Color.parseColor("#16a6ae"));
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Log.e("返回值", "" + response);
                        Gson gson = new Gson();
                        addressListModel = gson.fromJson(response, AddressListModel.class);
                        if (addressListModel.getCode() == 1) {
                            data = addressListModel.getReceiverList();
                            Total = addressListModel.getTotal();
                            L.e("获取的数据" + data);
                            L.e("获取的数据" + data.size());
                            if (Total != 0) {
                                initAddressListView();
                            }
                        } else if (addressListModel.getCode() == 911) {
                            SnackbarUtils.showShortSnackbar(getWindow().getDecorView(), "登录超时，请重新登录!", Color.WHITE, Color.parseColor("#16a6ae"));
                        } else {
                            SnackbarUtils.showShortSnackbar(getWindow().getDecorView(), "请求出错!", Color.WHITE, Color.parseColor("#16a6ae"));
                        }


//                        moviesList bean = gson.fromJson(response,moviesList.class);
//
//                        Log.e("gson解析出来的头部",""+ bean.getData().get(0).getDuration());
//                        List<moviesList.DataBean> data = bean.getData();
//                        for (int i = 0; i<data.size();i ++){
//                            List<moviesList.DataBean.CatesBean> catesBeen = data.get(i).getCates();
//                            String app_fu_title = data.get(i).getApp_fu_title();
//                            Log.e("",""+app_fu_title);
//                            for (int j= 0; j < catesBeen.size();j++){
//                                String catename = catesBeen.get(j).getCatename();
//                                Log.e("",""+catename);
//                            }
//                        }
//


                    }
                });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.navi_back:
                finish();
                break;
            case R.id.img_float:
//                Intent intent = new Intent(AddressSettingCommomAC.this,AddressDetailAC.class);
//                intent.putExtra("")
//                startActivity(intent);
                startActivity(AddressDetailAC.class,-1);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 912){
            if (resultCode == this.RESULT_OK){
                if (data!=null){
//                    data.getStringExtra()
                }
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onResume() {
        getListData(pageNo,pageNum);
        super.onResume();
    }

    private void initView() {
        img_float = (ImageView) findViewById(R.id.img_float);
        img_float.setOnClickListener(this);
    }
    private void startActivity(Class<?> cls,Integer jumpvalue) {
        Intent intent = new Intent(AddressSettingCommomAC.this, cls);
        intent.putExtra("JUMPPOSITION",jumpvalue);
        startActivity(intent);
    }
}
