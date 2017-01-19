package com.example.cfwifine.sxk.Section.MineNC.Controller.MineServerCenter.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.cfwifine.sxk.Section.MineNC.Controller.MineCuring.Controller.MineItemCuringFC;
import com.example.cfwifine.sxk.Section.MineNC.Controller.MineCuring.Controller.MineItemDoneFC;
import com.example.cfwifine.sxk.Section.MineNC.Controller.MineServerCenter.Controller.MineIMBoKeFC;
import com.example.cfwifine.sxk.Section.MineNC.Controller.MineServerCenter.Controller.MineIMBoZhuFC;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cfwifine on 16/12/28.
 */

public class MineItemServerCenterViewpageAdapter extends FragmentStatePagerAdapter {

    public MineItemServerCenterViewpageAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                MineIMBoZhuFC MineItemCuringFC = new MineIMBoZhuFC();
                return MineItemCuringFC;
            case 1:
                MineIMBoKeFC hasReceGoodsFC = new MineIMBoKeFC();
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
