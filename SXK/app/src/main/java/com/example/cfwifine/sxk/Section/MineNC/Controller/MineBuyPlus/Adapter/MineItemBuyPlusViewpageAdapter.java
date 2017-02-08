package com.example.cfwifine.sxk.Section.MineNC.Controller.MineBuyPlus.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.cfwifine.sxk.Section.MineNC.Controller.MineBuyPlus.Controller.HasSendGoodsFC;
import com.example.cfwifine.sxk.Section.MineNC.Controller.MineBuyPlus.Controller.MinesWaitReceiveGoodsFC;
import com.example.cfwifine.sxk.Section.MineNC.Controller.MineBuyPlus.Controller.WaitReceiveGoodsFC;
import com.example.cfwifine.sxk.Section.MineNC.Controller.MineCuring.Controller.MineItemCuringFC;
import com.example.cfwifine.sxk.Section.MineNC.Controller.MineCuring.Controller.MineItemDoneFC;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cfwifine on 16/12/28.
 */

public class MineItemBuyPlusViewpageAdapter extends FragmentStatePagerAdapter {

    public MineItemBuyPlusViewpageAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                WaitReceiveGoodsFC MineItemCuringFC = new WaitReceiveGoodsFC();
                return MineItemCuringFC;
            case 1:
                MinesWaitReceiveGoodsFC hasReceGoodsFC = new MinesWaitReceiveGoodsFC();
                return hasReceGoodsFC;
            case 2:
                HasSendGoodsFC hasSendGoodsFC = new HasSendGoodsFC();
                return hasSendGoodsFC;
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
