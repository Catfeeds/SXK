package com.example.cfwifine.sxk.Section.MineNC.Controller;

import android.content.res.AssetManager;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;

import android.support.v7.view.ContextThemeWrapper;
import android.text.TextUtils;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cfwifine.sxk.BaseAC.BaseInterface;
import com.example.cfwifine.sxk.R;
import com.example.cfwifine.sxk.Section.CommunityNC.View.L;
import com.example.cfwifine.sxk.Section.MineNC.CustomDialog.AddressPickerView.OnWheelChangedListener;
import com.example.cfwifine.sxk.Section.MineNC.CustomDialog.AddressPickerView.WheelView;
import com.example.cfwifine.sxk.Section.MineNC.CustomDialog.AddressPickerView.adapters.ArrayWheelAdapter;
import com.example.cfwifine.sxk.Section.MineNC.CustomDialog.AddressPickerView.model.CityModel;
import com.example.cfwifine.sxk.Section.MineNC.CustomDialog.AddressPickerView.model.DistrictModel;
import com.example.cfwifine.sxk.Section.MineNC.CustomDialog.AddressPickerView.model.ProvinceModel;
import com.example.cfwifine.sxk.Section.MineNC.CustomDialog.AddressPickerView.service.XmlParserHandler;
import com.example.cfwifine.sxk.Section.MineNC.CustomDialog.LikeIOSSheetDialog;
import com.example.cfwifine.sxk.Section.MineNC.Model.AddressDetailModel;
import com.example.cfwifine.sxk.Section.MineNC.Model.AddressListModel;
import com.example.cfwifine.sxk.Utils.SharedPreferencesUtils;
import com.example.cfwifine.sxk.Utils.SnackbarUtils;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import okhttp3.Call;


public class AddressDetailAC extends AppCompatActivity implements View.OnClickListener, OnWheelChangedListener {

    private ImageView navi_back_pic;
    private LinearLayout navi_back;
    private TextView navi_title;
    private TextView navi_right;
    private LinearLayout navi_right_lays;
    private EditText add_address_username;
    private EditText add_address_phonenumber;
    private RelativeLayout add_address_selectedaddress;
    private EditText add_address_detailAddress;
    private Button add_address_save;
    private LinearLayout activity_address_detail_ac;
    private Button add_address_saveaddress;

    // 地址框
    protected String[] mProvinceDatas;

    protected Map<String, String[]> mCitisDatasMap = new HashMap<String, String[]>();

    protected Map<String, String[]> mDistrictDatasMap = new HashMap<String, String[]>();


    protected Map<String, String> mZipcodeDatasMap = new HashMap<String, String>();


    protected String mCurrentProviceName;

    protected String mCurrentCityName;

    protected String mCurrentDistrictName ="";


    protected String mCurrentZipCode ="";


    private WheelView mViewProvince;
    private WheelView mViewCity;
    private WheelView mViewDistrict;
    private Button mBtnConfirm,mBtnCancel;
    AlertDialog alertDialog;
    AddressDetailModel.ReceiverBean dataSource = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address_detail_ac);

        initData();
        initView();
        // 判断是谁传过来的值
        judgeValue();
    }

    private void judgeValue() {
         Integer EditViewValue = getIntent().getIntExtra("JUMPPOSITION",0);
        if (EditViewValue>=0){
            getGoodsByReceiveID(EditViewValue);
        }
    }

    private void initView() {
        navi_back_pic = (ImageView) findViewById(R.id.navi_back_pic);
        navi_back = (LinearLayout) findViewById(R.id.navi_back);
        navi_back.setOnClickListener(this);
        navi_title = (TextView) findViewById(R.id.navi_title);
        navi_title.setText("添加收货地址");
        navi_right = (TextView) findViewById(R.id.navi_right);
        navi_right_lays = (LinearLayout) findViewById(R.id.navi_right_lays);
        add_address_username = (EditText) findViewById(R.id.add_address_username);
        add_address_phonenumber = (EditText) findViewById(R.id.add_address_phonenumber);
        add_address_selectedaddress = (RelativeLayout) findViewById(R.id.add_address_selectedaddress);
        add_address_selectedaddress.setOnClickListener(this);
        add_address_detailAddress = (EditText) findViewById(R.id.add_address_detailAddress);
//        add_address_save = (Button) findViewById(R.id.add);
        activity_address_detail_ac = (LinearLayout) findViewById(R.id.activity_address_detail_ac);
//        add_address_save.setOnClickListener(this);
        add_address_saveaddress = (Button) findViewById(R.id.add_address_saveaddress);
        add_address_saveaddress.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.add_address_saveaddress:
                submit();
                break;
            case R.id.navi_back:
                finish();
                break;
            case R.id.add_address_selectedaddress:
                initAddress();
                break;
            case R.id.record_address_btn_ok:
                showSelectedResult();
                break;
            case R.id.record_address_btn_cancel:
                alertDialog.dismiss();
                break;
            default:
                break;
        }
    }

    private void submit() {
        // validate
        String username = add_address_username.getText().toString().trim();
        if (TextUtils.isEmpty(username)) {
//            Toast.makeText(this, "    请输入收货人姓名", Toast.LENGTH_SHORT).show();
            SnackbarUtils.showShortSnackbar(getWindow().getDecorView(), "请输入收货人姓名!", Color.WHITE, Color.parseColor("#16a6ae"));
            return;
        }

        String phonenumber = add_address_phonenumber.getText().toString().trim();
        if (TextUtils.isEmpty(phonenumber)) {
//            Toast.makeText(this, "    请输入联系人电话", Toast.LENGTH_SHORT).show();
            SnackbarUtils.showShortSnackbar(getWindow().getDecorView(), "请输入联系人电话!", Color.WHITE, Color.parseColor("#16a6ae"));
            return;
        }

        String detailAddress = add_address_detailAddress.getText().toString().trim();
        if (TextUtils.isEmpty(detailAddress)) {
//            Toast.makeText(this, "    请输入详细地址", Toast.LENGTH_SHORT).show();
            if (detailAddress.toString().trim().length()>=200){
                SnackbarUtils.showShortSnackbar(getWindow().getDecorView(), "请输入1~200个字符哟!", Color.WHITE, Color.parseColor("#16a6ae"));
            }else {
                SnackbarUtils.showShortSnackbar(getWindow().getDecorView(), "请输入详细地址!", Color.WHITE, Color.parseColor("#16a6ae"));
            }
            return;
        }

        // TODO ********************************点击提交数据***************************************
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("name",username);
            jsonObject.put("mobile",phonenumber);
            jsonObject.put("state","河南省");
            jsonObject.put("city","郑州市");
            jsonObject.put("district","管城区");
//            jsonObject.put("",)
            jsonObject.put("address",detailAddress);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String PHPSESSION = String.valueOf(SharedPreferencesUtils.getParam(getApplicationContext(), BaseInterface.PHPSESSION, ""));
        OkHttpUtils.postString().url(BaseInterface.SettingAddReceiveGoods)
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
                        try {
                            JSONObject Json = new JSONObject(response);
                            if (Json.optInt("code")==1){
                                SnackbarUtils.showShortSnackbar(getWindow().getDecorView(), "添加成功!", Color.WHITE, Color.parseColor("#16a6ae"));
                                finish();
                            }else if (Json.optInt("code")==911){
                                SnackbarUtils.showShortSnackbar(getWindow().getDecorView(), "登录失效，请重新登录!", Color.WHITE, Color.parseColor("#16a6ae"));
                            }else {
                                SnackbarUtils.showShortSnackbar(getWindow().getDecorView(), "地址添加失败!", Color.WHITE, Color.parseColor("#16a6ae"));
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });


    }

    // TODO****************************************获取详细地址********************************************
    private void getGoodsByReceiveID(Integer receiverid) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("receiverid",receiverid);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String PHPSESSION = String.valueOf(SharedPreferencesUtils.getParam(getApplicationContext(), BaseInterface.PHPSESSION, ""));
        OkHttpUtils.postString().url(BaseInterface.SettingGetReceiveGoods)
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
                        Gson gson = new Gson();
                        AddressDetailModel addressDetailModel = gson.fromJson(response,AddressDetailModel.class);
                            if (addressDetailModel.getCode()==1){
//                                SnackbarUtils.showShortSnackbar(getWindow().getDecorView(), "删除成功!", Color.WHITE, Color.parseColor("#16a6ae"));
                                L.e("详细信息"+response);
                                dataSource = addressDetailModel.getReceiver();
                                setDetailValueForView(dataSource);
                            }else if (addressDetailModel.getCode()==911){
                                SnackbarUtils.showShortSnackbar(getWindow().getDecorView(), "登录超时，请重新登录!", Color.WHITE, Color.parseColor("#16a6ae"));
                            }else {
                                SnackbarUtils.showShortSnackbar(getWindow().getDecorView(), "请求失败!", Color.WHITE, Color.parseColor("#16a6ae"));
                            }

                    }
                });
    }

    /**
     * 给控件赋值
     */
    private void setDetailValueForView(AddressDetailModel.ReceiverBean dataSource) {
        add_address_username.setText(dataSource.getName());
        add_address_phonenumber.setText(dataSource.getMobile());
//        add_address_selectedaddress.
        add_address_detailAddress.setText(dataSource.getAddress());
    }

    // TODO******************************************地区选择器*************************************
    @Override
    public void onChanged(WheelView wheel, int oldValue, int newValue) {
        // TODO Auto-generated method stub
        if (wheel == mViewProvince) {
            updateCities();
        } else if (wheel == mViewCity) {
            updateAreas();
        } else if (wheel == mViewDistrict) {
            mCurrentDistrictName = mDistrictDatasMap.get(mCurrentCityName)[newValue];
            mCurrentZipCode = mZipcodeDatasMap.get(mCurrentDistrictName);
        }
    }

    /**
     * 根据当前的市，更新区WheelView的信息
     */
    private void updateAreas() {
        int pCurrent = mViewCity.getCurrentItem();
        mCurrentCityName = mCitisDatasMap.get(mCurrentProviceName)[pCurrent];
        String[] areas = mDistrictDatasMap.get(mCurrentCityName);

        if (areas == null) {
            areas = new String[]{""};
        }
        ArrayWheelAdapter arrayWheelAdapter = new ArrayWheelAdapter<String>(this, areas);
        arrayWheelAdapter.setTextColor(R.color.navi_title_color);
        arrayWheelAdapter.setTextSize(13);
        mViewDistrict.setViewAdapter(arrayWheelAdapter);
        mViewDistrict.setCurrentItem(0);
        mCurrentDistrictName = mDistrictDatasMap.get(mCurrentCityName)[0];
        mCurrentDistrictName = areas[0];
        mCurrentZipCode = mZipcodeDatasMap.get(mCurrentDistrictName);
    }

    /**
     * 根据当前的省，更新市WheelView的信息
     */
    private void updateCities() {
        int pCurrent = mViewProvince.getCurrentItem();
        mCurrentProviceName = mProvinceDatas[pCurrent];
        String[] cities = mCitisDatasMap.get(mCurrentProviceName);
        if (cities == null) {
            cities = new String[]{""};
        }
        ArrayWheelAdapter arrayWheelAdapter = new ArrayWheelAdapter<String>(this, cities);
        arrayWheelAdapter.setTextColor(R.color.navi_title_color);
        arrayWheelAdapter.setTextSize(13);
        mViewCity.setViewAdapter(new ArrayWheelAdapter<String>(this, cities));
        mViewCity.setCurrentItem(0);
        updateAreas();
    }
    private void initAddress() {

        View view = View.inflate(AddressDetailAC.this, R.layout.address_selector_item, null);
        mViewProvince = (WheelView) view.findViewById(R.id.id_province);
        mViewCity = (WheelView) view.findViewById(R.id.id_city);
        mViewDistrict = (WheelView) view.findViewById(R.id.id_district);
        mBtnConfirm = (Button) view.findViewById(R.id.record_address_btn_ok);
        mBtnCancel = (Button)view.findViewById(R.id.record_address_btn_cancel);
        // 添加change事件
        mViewProvince.addChangingListener(this);
        // 添加change事件
        mViewCity.addChangingListener(this);
        // 添加change事件
        mViewDistrict.addChangingListener(this);
        // 添加onclick事件
        mBtnConfirm.setOnClickListener(this);
        mBtnCancel.setOnClickListener(this);

        initData();
        ArrayWheelAdapter arrayWheelAdapter = new ArrayWheelAdapter<String>(AddressDetailAC.this, mProvinceDatas);
        arrayWheelAdapter.setTextColor(R.color.navi_title_color);
        arrayWheelAdapter.setTextSize(13);
        mViewProvince.setViewAdapter(arrayWheelAdapter);
        // 设置可见条目数量
        mViewProvince.setVisibleItems(5);
        mViewCity.setVisibleItems(5);
        mViewDistrict.setVisibleItems(5);
        AlertDialog.Builder builder = new AlertDialog.Builder(new ContextThemeWrapper(this,R.style.style_dialog)).setView(view);
        alertDialog = builder.create();
        alertDialog.getWindow().setGravity(Gravity.BOTTOM);

        alertDialog.show();
        updateCities();
        updateAreas();
        /**
         * 设置dialog全屏
         */
        Window window = alertDialog.getWindow();
        window.setWindowAnimations(R.style.Animation_Bottom_Rising);
        window.setGravity(Gravity.BOTTOM); //可设置dialog的位置
        window.getDecorView().setPadding(0, 0, 0, 0); //消除边距

        WindowManager.LayoutParams lp = window.getAttributes();
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;   //设置宽度充满屏幕
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        window.setAttributes(lp);
    }

    private void showSelectedResult() {
        Toast.makeText(AddressDetailAC.this, "当前选中:" + mCurrentProviceName + "," + mCurrentCityName + ","
                + mCurrentDistrictName + "," + mCurrentZipCode, Toast.LENGTH_SHORT).show();
    }
    private void initData()
    {
        List<ProvinceModel> provinceList = null;
        AssetManager asset = getAssets();
        try {
            InputStream input = asset.open("province_data.xml");

            SAXParserFactory spf = SAXParserFactory.newInstance();

            SAXParser parser = spf.newSAXParser();
            XmlParserHandler handler = new XmlParserHandler();
            parser.parse(input, handler);
            input.close();

            provinceList = handler.getDataList();

            if (provinceList!= null && !provinceList.isEmpty()) {
                mCurrentProviceName = provinceList.get(0).getName();
                List<CityModel> cityList = provinceList.get(0).getCityList();
                if (cityList!= null && !cityList.isEmpty()) {
                    mCurrentCityName = cityList.get(0).getName();
                    List<DistrictModel> districtList = cityList.get(0).getDistrictList();
                    mCurrentDistrictName = districtList.get(0).getName();
                    mCurrentZipCode = districtList.get(0).getZipcode();
                }
            }

            mProvinceDatas = new String[provinceList.size()];
            for (int i=0; i< provinceList.size(); i++) {
                mProvinceDatas[i] = provinceList.get(i).getName();
                List<CityModel> cityList = provinceList.get(i).getCityList();
                String[] cityNames = new String[cityList.size()];
                for (int j=0; j< cityList.size(); j++) {

                    cityNames[j] = cityList.get(j).getName();
                    List<DistrictModel> districtList = cityList.get(j).getDistrictList();
                    String[] distrinctNameArray = new String[districtList.size()];
                    DistrictModel[] distrinctArray = new DistrictModel[districtList.size()];
                    for (int k=0; k<districtList.size(); k++) {
                        DistrictModel districtModel = new DistrictModel(districtList.get(k).getName(), districtList.get(k).getZipcode());
                        mZipcodeDatasMap.put(districtList.get(k).getName(), districtList.get(k).getZipcode());
                        distrinctArray[k] = districtModel;
                        distrinctNameArray[k] = districtModel.getName();
                    }
                    mDistrictDatasMap.put(cityNames[j], distrinctNameArray);
                }
                mCitisDatasMap.put(provinceList.get(i).getName(), cityNames);
            }
        } catch (Throwable e) {
            e.printStackTrace();
        } finally {

        }
    }

}
