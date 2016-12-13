package com.example.cfwifine.sxk.Section.CommunityNC.Controller;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.cfwifine.sxk.R;
import com.example.cfwifine.sxk.Section.CommunityNC.Adapter.PublishFriendMomentRecycleAdapter;
import com.example.cfwifine.sxk.Section.PublishNC.Model.TestModel;
import com.example.cfwifine.sxk.Section.PublishNC.View.PreviewPicView.FourGridlayout;

import java.util.ArrayList;

public class CommunityPublishTopicAC extends AppCompatActivity implements View.OnClickListener {

    private ImageView navi_back_pic;
    private LinearLayout navi_back;
    private TextView navi_title;
    private TextView navi_right;
    private LinearLayout navi_right_lays;
    private FourGridlayout results;
    private ImageView publish_publish_add_pic_imageview;
    private LinearLayout publish_add_pic_lay;
    private RecyclerView hot_cate_publish;
    private LinearLayout activity_community_publish_topic_ac;
    PublishFriendMomentRecycleAdapter publishFriendMomentRecycleAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_community_publish_topic_ac);
        initTopicData();
        initView();


    }
    ArrayList<String> topicNameList = null;
    ArrayList<TestModel> topicListModle = null;
    TestModel testModel = null;
    private void initTopicData() {
         topicNameList = getIntent().getStringArrayListExtra("TOPIC");
        topicListModle = new ArrayList<>();
        for (int i = 0;i<topicNameList.size();i++){
            testModel = new TestModel(topicNameList.get(i).toString(),false);
            topicListModle.add(i,testModel);
        }

        initRecycleView();
    }

    private void initRecycleView() {
        hot_cate_publish = (RecyclerView) findViewById(R.id.hot_cate_publish);
        publishFriendMomentRecycleAdapter = new PublishFriendMomentRecycleAdapter(this,topicListModle);
        hot_cate_publish.setLayoutManager(new GridLayoutManager(this,3));
        hot_cate_publish.setAdapter(publishFriendMomentRecycleAdapter);
        publishFriendMomentRecycleAdapter.setOnItemClickListener(new PublishFriendMomentRecycleAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(View view, String name, int position) {

            }
        });

    }

    private void initView() {
        navi_back_pic = (ImageView) findViewById(R.id.navi_back_pic);
        navi_back = (LinearLayout) findViewById(R.id.navi_back);
        navi_title = (TextView) findViewById(R.id.navi_title);
        navi_right = (TextView) findViewById(R.id.navi_right);
        navi_right_lays = (LinearLayout) findViewById(R.id.navi_right_lays);
        results = (FourGridlayout) findViewById(R.id.results);
        publish_publish_add_pic_imageview = (ImageView) findViewById(R.id.publish_publish_add_pic_imageview);
        publish_add_pic_lay = (LinearLayout) findViewById(R.id.publish_add_pic_lay);

        activity_community_publish_topic_ac = (LinearLayout) findViewById(R.id.activity_community_publish_topic_ac);

        navi_title.setText("社区");
        navi_right.setText("发表");
        navi_right.setTextColor(getResources().getColor(R.color.login_turquoisex));
        navi_back.setOnClickListener(this);
        navi_right_lays.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.navi_back:
                finish();
                break;
            case R.id.navi_right_lays:
                break;

        }
    }
}
