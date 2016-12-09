package com.example.cfwifine.sxk.Section.MineNC.CustomDialog;

import android.content.Context;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;


import com.example.cfwifine.sxk.R;
import com.example.cfwifine.sxk.Section.MineNC.Controller.AddressDetailAC;
import com.example.cfwifine.sxk.Section.MineNC.CustomDialog.AddressPickerView.OnWheelChangedListener;
import com.example.cfwifine.sxk.Section.MineNC.CustomDialog.AddressPickerView.WheelView;
import com.example.cfwifine.sxk.Section.MineNC.CustomDialog.AddressPickerView.adapters.ArrayWheelAdapter;
import com.example.cfwifine.sxk.Section.MineNC.CustomDialog.AddressPickerView.model.CityModel;
import com.example.cfwifine.sxk.Section.MineNC.CustomDialog.AddressPickerView.model.DistrictModel;
import com.example.cfwifine.sxk.Section.MineNC.CustomDialog.AddressPickerView.model.ProvinceModel;
import com.example.cfwifine.sxk.Section.MineNC.CustomDialog.AddressPickerView.service.XmlParserHandler;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

/**
 * Created by Mr.Yang on 2016/12/8.
 */

public class CustomDialog_AddressSelector extends AlertDialog implements OnWheelChangedListener {

    private Context context;
    private WheelView mViewProvince;
    private WheelView mViewCity;
    private WheelView mViewDistrict;
    private Button mBtnConfirm,mCancelBtn;

    protected String[] mProvinceDatas;

    protected Map<String, String[]> mCitisDatasMap = new HashMap<String, String[]>();

    protected Map<String, String[]> mDistrictDatasMap = new HashMap<String, String[]>();


    protected Map<String, String> mZipcodeDatasMap = new HashMap<String, String>();


    protected String mCurrentProviceName;

    protected String mCurrentCityName;

    protected String mCurrentDistrictName ="";

    private List<String> address = new ArrayList<String>();

    protected String mCurrentZipCode ="";

    protected void initProvinceDatas() {
        List<ProvinceModel> provinceList = null;
        AssetManager asset = context.getAssets();
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

    //增加一个回调函数,用以从外部接收返回值
    public interface ICustomDialogEventListener {
        public void customDialogEvent(List<String> addressList);
    }
    private ICustomDialogEventListener mCustomDialogEventListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
    }

    private void initView() {
        View view = View.inflate(context, R.layout.address_selector_item, null);
        mViewProvince = (WheelView) view.findViewById(R.id.id_province);
        mViewCity = (WheelView) view.findViewById(R.id.id_city);
        mViewDistrict = (WheelView) view.findViewById(R.id.id_district);
        mBtnConfirm = (Button) view.findViewById(R.id.record_address_btn_ok);
        mCancelBtn = (Button)view.findViewById(R.id.record_address_btn_cancel);
        mCancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });

        // 添加change事件
        mViewProvince.addChangingListener(this);
        // 添加change事件
        mViewCity.addChangingListener(this);
        // 添加change事件
        mViewDistrict.addChangingListener(this);
        // 添加onclick事件
//        mBtnConfirm.setOnClickListener(this);
        mBtnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                address.add(mCurrentProviceName);
                address.add(mCurrentCityName);
                address.add(mCurrentDistrictName);
                mCustomDialogEventListener.customDialogEvent(address);
                address.clear();
                dismiss();
            }
        });




        initProvinceDatas();
        ArrayWheelAdapter arrayWheelAdapter = new ArrayWheelAdapter<String>(context, mProvinceDatas);
        arrayWheelAdapter.setTextColor(R.color.black);
        arrayWheelAdapter.setTextSize(13);
        mViewProvince.setViewAdapter(arrayWheelAdapter);
        // 设置可见条目数量
        mViewProvince.setVisibleItems(7);
        mViewCity.setVisibleItems(7);
        mViewDistrict.setVisibleItems(7);

        this.setContentView(view);
        this.getWindow().setGravity(Gravity.BOTTOM);


//        AlertDialog.Builder builder = new AlertDialog.Builder(new ContextThemeWrapper(context,R.style.style_dialog)).setView(view);
//        AlertDialog alertDialog = builder.create();
//        alertDialog.getWindow().setGravity(Gravity.BOTTOM);
//        alertDialog.show();

        updateCities();
        updateAreas();
        Window window = this.getWindow();
        window.setGravity(Gravity.BOTTOM); //可设置dialog的位置
        window.setWindowAnimations(R.style.Animation_Bottom_Rising);
        window.getDecorView().setPadding(0, 0, 0, 0); //消除边距

        WindowManager.LayoutParams lp = window.getAttributes();
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;   //设置宽度充满屏幕
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        window.setAttributes(lp);
    }


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
        ArrayWheelAdapter arrayWheelAdapter = new ArrayWheelAdapter<String>(context, areas);
        arrayWheelAdapter.setTextColor(R.color.black);
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

        ArrayWheelAdapter arrayWheelAdapter = new ArrayWheelAdapter<String>(context, cities);
        arrayWheelAdapter.setTextColor(R.color.black);
        arrayWheelAdapter.setTextSize(13);
        mViewCity.setViewAdapter(arrayWheelAdapter);
        mViewCity.setCurrentItem(0);
        updateAreas();
    }






    public CustomDialog_AddressSelector(@NonNull Context context) {
        super(context);
        this.context = context;
    }



    public CustomDialog_AddressSelector(Context context, ICustomDialogEventListener listener, int theme) {
        super(context, theme);
        this.context = context;
        mCustomDialogEventListener = listener;
    }







    private void showSelectedResult() {
        Toast.makeText(context, "当前选中:" + mCurrentProviceName + "," + mCurrentCityName + ","
                + mCurrentDistrictName + "," + mCurrentZipCode, Toast.LENGTH_SHORT).show();

    }

}
