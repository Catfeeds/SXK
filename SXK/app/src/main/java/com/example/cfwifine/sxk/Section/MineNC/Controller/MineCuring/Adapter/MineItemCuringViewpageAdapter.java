package com.example.cfwifine.sxk.Section.MineNC.Controller.MineCuring.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.cfwifine.sxk.Section.MineNC.Controller.MineCuring.Controller.MineItemCuringAC;
import com.example.cfwifine.sxk.Section.MineNC.Controller.MineCuring.Controller.MineItemCuringFC;
import com.example.cfwifine.sxk.Section.MineNC.Controller.MineCuring.Controller.MineItemDoneFC;
import com.example.cfwifine.sxk.Section.MineNC.Controller.MineRent.Controller.CompletedOrderFC;
import com.example.cfwifine.sxk.Section.MineNC.Controller.MineRent.Controller.HasReceGoodsFC;
import com.example.cfwifine.sxk.Section.MineNC.Controller.MineRent.Controller.RebackGoodsFC;
import com.example.cfwifine.sxk.Section.MineNC.Controller.MineRent.Controller.WaitReceGoodsFC;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cfwifine on 16/12/28.
 */

public class MineItemCuringViewpageAdapter extends FragmentStatePagerAdapter {

    public MineItemCuringViewpageAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                MineItemCuringFC MineItemCuringFC = new MineItemCuringFC();
                return MineItemCuringFC;
            case 1:
                MineItemDoneFC hasReceGoodsFC = new MineItemDoneFC();
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
