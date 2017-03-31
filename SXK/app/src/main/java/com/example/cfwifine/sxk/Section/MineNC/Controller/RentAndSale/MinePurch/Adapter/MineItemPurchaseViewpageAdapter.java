package com.example.cfwifine.sxk.Section.MineNC.Controller.RentAndSale.MinePurch.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.cfwifine.sxk.Section.MineNC.Controller.RentAndSale.MinePurch.Controller.PurchaseOneFC;
import com.example.cfwifine.sxk.Section.MineNC.Controller.RentAndSale.MinePurch.Controller.PurchaseThreeFC;
import com.example.cfwifine.sxk.Section.MineNC.Controller.RentAndSale.MinePurch.Controller.PurchaseTwoFC;
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

public class MineItemPurchaseViewpageAdapter extends FragmentStatePagerAdapter {

    public MineItemPurchaseViewpageAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                PurchaseOneFC purchaseOneFC = new PurchaseOneFC();
                return purchaseOneFC;
            case 1:
                PurchaseTwoFC purchaseTwoFC = new PurchaseTwoFC();
                return purchaseTwoFC;
            case 2:
                PurchaseThreeFC purchaseThreeFC = new PurchaseThreeFC();
                return purchaseThreeFC;
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
