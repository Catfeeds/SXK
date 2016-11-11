package com.example.cfwifine.sxk.Utils;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.widget.Toast;

public class NetReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		String action = intent.getAction();
		if (ConnectivityManager.CONNECTIVITY_ACTION.equals(action)) {
			boolean isConnected = NetUtils.isNetworkConnected(context);
	        System.out.println("网络状态：" + isConnected);
	        System.out.println("wifi状态：" + NetUtils.isWifiConnected(context));
	        System.out.println("移动网络状态：" + NetUtils.isMobileConnected(context));
	        System.out.println("网络连接类型：" + NetUtils.getConnectedType(context));

			if (!isConnected){
				Toast.makeText(context, "网络已断开，请检查网络!", Toast.LENGTH_SHORT).show();
			}else {
//				Toast.makeText(context, "网络已连接，请放心使用：）", Toast.LENGTH_SHORT).show();
//				if (NetUtils.isWifiConnected(context)){
//					Toast.makeText(context, "当前连接的是WIFI，请放心使用：）", Toast.LENGTH_SHORT).show();
//				}else if (NetUtils.isMobileConnected(context)){
//					Toast.makeText(context, "当前连接的是移动网络，请小心使用：D", Toast.LENGTH_SHORT).show();
//				}
			}
		}
	}
	
}
