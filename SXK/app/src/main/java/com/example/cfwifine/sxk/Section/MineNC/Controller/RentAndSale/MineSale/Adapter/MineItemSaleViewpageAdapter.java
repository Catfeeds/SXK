package com.example.cfwifine.sxk.Section.MineNC.Controller.RentAndSale.MineSale.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.cfwifine.sxk.Section.MineNC.Controller.RentAndSale.MineRent.Controller.CompletedOrderFC;
import com.example.cfwifine.sxk.Section.MineNC.Controller.RentAndSale.MineRent.Controller.HasReceGoodsFC;
import com.example.cfwifine.sxk.Section.MineNC.Controller.RentAndSale.MineRent.Controller.RebackGoodsFC;
import com.example.cfwifine.sxk.Section.MineNC.Controller.RentAndSale.MineRent.Controller.WaitReceGoodsFC;
import com.example.cfwifine.sxk.Section.MineNC.Controller.RentAndSale.MineSale.Controller.OneFC;
import com.example.cfwifine.sxk.Section.MineNC.Controller.RentAndSale.MineSale.Controller.ThrFC;
import com.example.cfwifine.sxk.Section.MineNC.Controller.RentAndSale.MineSale.Controller.TwoFC;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cfwifine on 16/12/28.
 */

public class MineItemSaleViewpageAdapter extends FragmentStatePagerAdapter {

    public MineItemSaleViewpageAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                OneFC oneFC = new OneFC();
                return oneFC;
            case 1:
                TwoFC twoFC = new TwoFC();
                return twoFC;
            case 2:
                ThrFC thrFC = new ThrFC();
                return  thrFC;
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
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mItems.get(position);
    }
}
