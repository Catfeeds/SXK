package com.example.cfwifine.sxk.Section.MineNC.Controller;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.cfwifine.sxk.R;
import com.example.cfwifine.sxk.View.MyGridAdapter;
import com.example.cfwifine.sxk.View.MyGridView;


public class MineFC extends Fragment {
    private MyGridView gridview;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_mine_fc, container, false);
        initGridView();


        return view;
    }
    public void initGridView(){
        gridview=(MyGridView) view.findViewById(R.id.mygridview);
        gridview.setAdapter(new MyGridAdapter(getActivity()));
    }





}
