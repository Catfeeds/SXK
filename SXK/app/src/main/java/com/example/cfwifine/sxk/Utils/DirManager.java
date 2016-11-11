package com.example.cfwifine.sxk.Utils;



/**
 * 项目目录管理
 * 
 * @author Administrator
 *
 */
public class DirManager {
	public static String appRoot = "/wuhuaniu/";
	public static String cache = "cache/";
	public static String data = "data/";
	public static String log = "log/";
	public static String imageCache = "cache/image/";
	public static String dataCache = "cache/data/";
	public static String readCache = "cache/read";

	/**
	 * 程序更路径
	 * 
	 * @return
	 */
	public static String getAppRoot() {
		return SdCardUtils.getSdCardRoot() + appRoot;
	}

	/**
	 * 缓存路径
	 * 
	 * @return
	 */
	public static String getCachePath() {
		return getAppRoot() + cache;
	}

	/**
	 * 数据路径
	 * 
	 * @return
	 */
	public static String getDataPath() {
		return getAppRoot() + data;
	}

	/**
	 * 日志路径
	 * 
	 * @return
	 */
	public static String getLogPath() {
		return getAppRoot() + log;
	}

	public static String getImageCache() {
		return getAppRoot() + imageCache;
	}

	public static String getDataCache() {
		return getAppRoot() + dataCache;
	}

	public static String getReadCache() {
		return getAppRoot() + readCache;
	}

	/**
	 * 创建app�?��的的目录
	 */
	public static void createAppDirs() {
		FileUtils.CreateDir(getAppRoot());
		FileUtils.CreateDir(getCachePath());
		FileUtils.CreateDir(getDataPath());
		FileUtils.CreateDir(getLogPath());
		FileUtils.CreateDir(getImageCache());
		FileUtils.CreateDir(getDataCache());
		FileUtils.CreateDir(getReadCache());
	}
}
