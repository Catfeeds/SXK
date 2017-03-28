package com.example.cfwifine.sxk.Section.ClassifyNC.MineSearchListDetail.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.cfwifine.sxk.Section.ClassifyNC.MineSearchListDetail.Controller.ClassifyPurchaseFC;
import com.example.cfwifine.sxk.Section.ClassifyNC.MineSearchListDetail.Controller.ClassifyRentFC;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cfwifine on 16/12/28.
 */

public class ClassifySearchViewpageAdapter extends FragmentStatePagerAdapter {

    public ClassifySearchViewpageAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            // 好像写反了？？？？; D
            case 0:
                ClassifyRentFC MineItemCuringFC = new ClassifyRentFC();
                return MineItemCuringFC;
            case 1:
                ClassifyPurchaseFC hasReceGoodsFC = new ClassifyPurchaseFC();
                return hasReceGoodsFC;
            default:
                break;
        }
        return null;
    }
    private List<String> mItems = new ArrayList<>();
    public void addAll(List<String> items) {
        mItems = new ArrayList<>(items);
    }
    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mItems.get(position);
    }
}
