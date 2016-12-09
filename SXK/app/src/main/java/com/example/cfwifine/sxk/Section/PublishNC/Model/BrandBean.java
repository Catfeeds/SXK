package com.example.cfwifine.sxk.Section.PublishNC.Model;

import com.mcxtzhang.indexlib.IndexBar.bean.BaseIndexPinyinBean;


public class BrandBean extends BaseIndexPinyinBean {

    private String brandName;//城市名字
    private boolean isTop;//是否是最上面的 不需要被转化成拼音的

    public BrandBean() {
    }

    public BrandBean(String city) {
        this.brandName = city;
    }

    public String getCity() {
        return brandName;
    }

    public BrandBean setCity(String city) {
        this.brandName = city;
        return this;
    }

    public boolean isTop() {
        return isTop;
    }

    public BrandBean setTop(boolean top) {
        isTop = top;
        return this;
    }

    @Override
    public String getTarget() {
        return brandName;
    }

    @Override
    public boolean isNeedToPinyin() {
        return !isTop;
    }

    @Override
    public boolean isShowSuspension() {
        return !isTop;
    }
}
