package com.example.guideactivity;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;

/**
 *	���������������������
 */
public class MainActivity extends Activity implements OnClickListener,OnPageChangeListener {
	//����ViewPager����
	private ViewPager viewPager;
	
	//����ViewPager������
	private ViewPagerAdapter vpAdapter;
	
	//����һ��ArrayList�����View
	private ArrayList<View> views;

	// �����������View����
	private View view1, view2, view3, view4;
	 	
	//�ײ�С���ͼƬ
    private ImageView[] points;
    
    //��¼��ǰѡ��λ��
    private int currentIndex;
    
  
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
	    setContentView(R.layout.guide_layout);
		
		initView();
		initData();	
	}

	/**
	 * ��ʼ�����
	 */
	private void initView() {
		//ʵ������������Ĳ��ֶ��� 
		LayoutInflater mLi = LayoutInflater.from(this);
		view1 = mLi.inflate(R.layout.guide_page1, null);
		view2 = mLi.inflate(R.layout.guide_page2, null);
		view3 = mLi.inflate(R.layout.guide_page3, null);
		view4 = mLi.inflate(R.layout.guide_page4, null);
	
		// ʵ����ViewPager
		viewPager = (ViewPager) findViewById(R.id.viewpager);

		// ʵ����ArrayList����
		views = new ArrayList<View>();

		// ʵ����ViewPager������
		vpAdapter = new ViewPagerAdapter(views);
		
		//ʵ������ʼ��ť
		//startBt = (Button) view4.findViewById(R.id.startBtn);
	}
	/**
	 * ��ʼ������
	 */
	private void initData() {
		// ���ü���
		viewPager.setOnPageChangeListener(this);
		// ��������������
		viewPager.setAdapter(vpAdapter);

		//��Ҫ��ҳ��ʾ��Viewװ��������		
		views.add(view1);
		views.add(view2);
		views.add(view3);
		views.add(view4);		
	        
        //��ʼ���ײ�С��
        initPoint(views.size());
	}
	/**
	 * ��ʼ���ײ�С��
	 */
	private void initPoint(int views){
		LinearLayout linearLayout = (LinearLayout) findViewById(R.id.point);       
		
        points = new ImageView[views];

        //ѭ��ȡ��С��ͼƬ
        for (int i = 0; i < views; i++) {
        	//�õ�һ��LinearLayout�����ÿһ����Ԫ��
        	points[i] = (ImageView) linearLayout.getChildAt(i);
        	//Ĭ�϶���Ϊ��ɫ
        	points[i].setEnabled(true);
        	//��ÿ��С�����ü���
        	points[i].setOnClickListener(this);
        	//����λ��tag������ȡ���뵱ǰλ�ö�Ӧ
        	points[i].setTag(i);
        }
        
        //���õ���Ĭ�ϵ�λ��
        currentIndex = 0;
        //����Ϊ��ɫ����ѡ��״̬
        points[currentIndex].setEnabled(false);
	}
	
	/**
	 * ������״̬�ı�ʱ����
	 */
	@Override
	public void onPageScrollStateChanged(int arg0) {

	}
	
	/**
	 * ����ǰҳ�汻����ʱ����
	 */

	@Override
	public void onPageScrolled(int arg0, float arg1, int arg2) {

	}
	
	/**
	 * ���µ�ҳ�汻ѡ��ʱ����
	 */

	@Override
	public void onPageSelected(int position) {
		//���õײ�С��ѡ��״̬
        setCurDot(position);
	}

	/**
	 * ͨ������¼����л���ǰ��ҳ��
	 */
	@Override
	public void onClick(View v) {
		 int position = (Integer)v.getTag();
         setCurView(position);
         setCurDot(position);		
	}

	/**
	 * ���õ�ǰҳ���λ��
	 */
	private void setCurView(int position){
         if (position < 0 || position >= 4) {
             return;
         }
         viewPager.setCurrentItem(position);
     }

     /**
     * ���õ�ǰ��С���λ��
     */
    private void setCurDot(int positon){
         if (positon < 0 || positon > 3 || currentIndex == positon) {
             return;
         }
         points[positon].setEnabled(false);
         points[currentIndex].setEnabled(true);

         currentIndex = positon;
     }
	
}
