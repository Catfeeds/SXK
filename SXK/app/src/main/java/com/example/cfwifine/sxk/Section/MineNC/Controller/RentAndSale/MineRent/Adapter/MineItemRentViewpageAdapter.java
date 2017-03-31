package com.example.cfwifine.sxk.Section.MineNC.Controller.RentAndSale.MineRent.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.cfwifine.sxk.Section.MineNC.Controller.RentAndSale.MineRent.Controller.CompletedOrderFC;
import com.example.cfwifine.sxk.Section.MineNC.Controller.RentAndSale.MineRent.Controller.HasReceGoodsFC;
import com.example.cfwifine.sxk.Section.MineNC.Controller.RentAndSale.MineRent.Controller.RebackGoodsFC;
import com.example.cfwifine.sxk.Section.MineNC.Controller.RentAndSale.MineRent.Controller.WaitReceGoodsFC;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cfwifine on 16/12/28.
 */

public class MineItemRentViewpageAdapter extends FragmentStatePagerAdapter {

    public MineItemRentViewpageAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                WaitReceGoodsFC waitReceGoodsFC = new WaitReceGoodsFC();
                return waitReceGoodsFC;
            case 1:
                HasReceGoodsFC hasReceGoodsFC = new HasReceGoodsFC();
                return hasReceGoodsFC;
            case 3:
                CompletedOrderFC completedOrderFC = new CompletedOrderFC();
                return  completedOrderFC;
            case 2:
                RebackGoodsFC rebackGoodsFC = new RebackGoodsFC();
                return rebackGoodsFC;
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
        return 4;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mItems.get(position);
    }
}
