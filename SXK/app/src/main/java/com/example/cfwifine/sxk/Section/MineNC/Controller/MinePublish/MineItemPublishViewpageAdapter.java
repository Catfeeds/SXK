package com.example.cfwifine.sxk.Section.MineNC.Controller.MinePublish;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cfwifine on 16/12/28.
 */

public class MineItemPublishViewpageAdapter extends FragmentStatePagerAdapter {

    public MineItemPublishViewpageAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                WaitPassFC waitPassFC = new WaitPassFC();
                return waitPassFC;
            case 1:
                PublisingFC publisingFC = new PublisingFC();
                return publisingFC;
            case 2:
                RentingFC rentingFC = new RentingFC();
                return  rentingFC;
            case 3:
                DontSailFC dontSailFC = new DontSailFC();
                return dontSailFC;
            case 4:
                RejectFC rejectFC = new RejectFC();
                return rejectFC;
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
        return 5;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mItems.get(position);
    }
}
