package com.example.cfwifine.sxk.Utils;

import android.os.Environment;
import android.os.StatFs;

import java.io.File;
import java.text.DecimalFormat;

public class SdCardUtils {

	/**
	 * �?��可用SD卡内�?50MB
	 */
	private static final long LOW_STORAGE_THRESHOLD = 1024 * 1024 * 50;

	public static String getSdCardRoot(){
		return Environment.getExternalStorageDirectory().getAbsolutePath();
	}
	
	/**
	 * 是否可用或可�?
	 * 
	 * @return
	 */
	public static boolean isWrittenable() {

		if (Environment.getExternalStorageState().equals(
				Environment.MEDIA_MOUNTED)) {
			return true;
		}
		return false;
	}

	/**
	 * 获取SD总容�?
	 * 
	 * @return Byte
	 */
	public long getAllSize() {
		// 取得SD卡文件路�?
		File path = Environment.getExternalStorageDirectory();
		StatFs sf = new StatFs(path.getPath());
		// 获取单个数据块的大小(Byte)
		long blockSize = sf.getBlockSize();
		// 获取�?��数据块数
		long allBlocks = sf.getBlockCount();
		// 返回SD卡大�?
		return allBlocks * blockSize; // 单位Byte
		// return (allBlocks * blockSize)/1024; //单位KB
		// return (allBlocks * blockSize)/1024/1024; //单位MB
	}

	/**
	 * 获取SD可用大小
	 * 
	 * @return
	 */
	public static long getAvailableSize() {

		String storageDirectory = null;
		storageDirectory = Environment.getExternalStorageDirectory().toString();
		try {
			StatFs stat = new StatFs(storageDirectory);
			long avaliableSize = ((long) stat.getAvailableBlocks() * (long) stat
					.getBlockSize());
			return avaliableSize;
		} catch (RuntimeException ex) {
			return 0;
		}
	}

	/**
	 *  �?��剩余sd内存是否大于�?��内存要求
	 * @return
	 */
	public static boolean checkAvailableSize() {
		if (getAvailableSize() < LOW_STORAGE_THRESHOLD) {
			return false;
		}
		return true;
	}

	/**
	 * 大小转换
	 * 
	 * @param size
	 * @return
	 */
	public static String size(long size) {

		if (size / (1024 * 1024) > 0) {
			float tmpSize = (float) (size) / (float) (1024 * 1024);
			DecimalFormat df = new DecimalFormat("#.##");
			return "" + df.format(tmpSize) + "MB";
		} else if (size / 1024 > 0) {
			return "" + (size / (1024)) + "KB";
		} else
			return "" + size + "B";
	}

}
