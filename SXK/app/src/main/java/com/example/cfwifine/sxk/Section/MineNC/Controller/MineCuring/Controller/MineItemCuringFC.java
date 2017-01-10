package com.example.cfwifine.sxk.Section.MineNC.Controller.MineCuring.Controller;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.cfwifine.sxk.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class MineItemCuringFC extends Fragment {


    private View view=null;
    private MineItemCuringAC mineItemCuringAC;

    public MineItemCuringFC() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (view == null){
            view = inflater.inflate(R.layout.fragment_mine_item_curing_fc, container, false);
            mineItemCuringAC = (MineItemCuringAC) getActivity();
        }

        return view;
    }

}
