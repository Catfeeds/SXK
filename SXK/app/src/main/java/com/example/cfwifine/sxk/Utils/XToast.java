package com.example.cfwifine.sxk.Utils;

import android.content.Context;
import android.widget.Toast;



public class XToast {



	public static void show(String msg) {
		show(AppConfig.applicationContext, msg);
	}

	public static void show(int resId) {
		show(AppConfig.applicationContext, resId);
	}

	public static void showLongTime(int resId) {
		showLongTime(AppConfig.applicationContext, resId);
	}

	public static void showLongTime(String msg) {
		showLongTime(AppConfig.applicationContext, msg);
	}

	public static Toast mToast;

	public static void show(Context context, String msg) {
		if (mToast == null) {
			mToast = Toast.makeText(context, msg, Toast.LENGTH_SHORT);
		} else {
			mToast.setText(msg);
		}
		mToast.show();
	}

	public static void show(Context context, int resId) {
		if (mToast == null) {
			mToast = Toast.makeText(context, resId, Toast.LENGTH_SHORT);
		} else {
			mToast.setText(resId);
		}
		mToast.show();
	}

	public static void showLongTime(Context context, int resId) {
		if (mToast == null) {
			mToast = Toast.makeText(context, resId, Toast.LENGTH_SHORT);
		} else {
			mToast.setText(resId);
		}
		mToast.setDuration(Toast.LENGTH_SHORT);
		mToast.show();
	}

	public static void showLongTime(Context context, String msg) {
		if (mToast == null) {
			mToast = Toast.makeText(context, msg, Toast.LENGTH_SHORT);
		} else {
			mToast.setText(msg);
		}
		mToast.setDuration(Toast.LENGTH_SHORT);
		mToast.show();
	}
}
