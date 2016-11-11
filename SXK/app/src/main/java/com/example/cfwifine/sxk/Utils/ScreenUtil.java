package com.example.cfwifine.sxk.Utils;

import android.content.Context;
import android.content.res.Configuration;
import android.util.DisplayMetrics;
import android.view.WindowManager;

/**
 * 屏幕工具
 * 
 * @author Administrator
 * 
 */
public class ScreenUtil {

	/**
	 * 返回屏幕尺寸
	 * 
	 * @param context
	 * @return DisplayMetrics
	 */
	public static DisplayMetrics getDisplayMetrics(Context context) {
		return context.getResources().getDisplayMetrics();
	}

	/**
	 * 返回屏幕宽度
	 * 
	 * @param context
	 * @return int
	 */
	public static int getScreenWidth(Context context) {
		return getDisplayMetrics(context).widthPixels;
	}

	/**
	 * 返回屏幕高度
	 * 
	 * @param context
	 * @return int
	 */
	public static int getScreenHeight(Context context) {
		return getDisplayMetrics(context).heightPixels;
	}

	/**
	 * 判断是否横屏
	 * 
	 * @param context
	 * @return boolean
	 */
	public static boolean isOrientationLandscape(Context context) {
		if (context.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
			return true;
		}
		return false;
	}

	/**
	 * 判断是否竖屏
	 * 
	 * @param context
	 * @return boolean
	 */
	public static boolean isOrientationPortrait(Context context) {
		if (context.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
			return true;
		}
		return false;
	}

	/**
	 * 获取屏幕的大小
	 * 
	 * @param context
	 * @return
	 */
	public static Screen getScreenPix(Context context) {
		DisplayMetrics dm = new DisplayMetrics();
		WindowManager windowManager = (WindowManager) context
				.getSystemService(Context.WINDOW_SERVICE);
		windowManager.getDefaultDisplay().getMetrics(dm);
		return new Screen(dm.widthPixels, dm.heightPixels);
	}

	/**
	 * 获取屏幕的宽
	 * 
	 * @param context
	 * @return
	 */
	public static int getScreenWidthPix(Context context) {
		DisplayMetrics dm = new DisplayMetrics();
		WindowManager windowManager = (WindowManager) context
				.getSystemService(Context.WINDOW_SERVICE);
		windowManager.getDefaultDisplay().getMetrics(dm);
		return new Screen(dm.widthPixels, dm.heightPixels).widthPixels;
	}

	/**
	 * 获取屏幕的高
	 * 
	 * @param context
	 * @return
	 */
	public static int getScreenHeightPix(Context context) {
		DisplayMetrics dm = new DisplayMetrics();
		WindowManager windowManager = (WindowManager) context
				.getSystemService(Context.WINDOW_SERVICE);
		windowManager.getDefaultDisplay().getMetrics(dm);
		return new Screen(dm.widthPixels, dm.heightPixels).heightPixels;
	}

	public static class Screen {
		public int widthPixels;
		public int heightPixels;

		public Screen() {
		}

		public Screen(int widthPixels, int heightPixels) {
			this.widthPixels = widthPixels;
			this.heightPixels = heightPixels;
		}

		@Override
		public String toString() {
			return "(" + widthPixels + "," + heightPixels + ")";
		}
	}
}
