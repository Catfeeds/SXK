package com.example.cfwifine.sxk.CircleViewImage;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by cfwifine on 16/10/29.
 */

public class viewPagerAdapter extends FragmentPagerAdapter {
    List<Fragment> mList;

    public viewPagerAdapter(FragmentManager fm, List<Fragment> list) {
        super(fm);
        this.mList = list;
    }

    public viewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int arg0) {
        return mList.get(arg0);
    }

    @Override
    public int getCount() {
        return mList.isEmpty() ? 0 : mList.size();
    }
}
