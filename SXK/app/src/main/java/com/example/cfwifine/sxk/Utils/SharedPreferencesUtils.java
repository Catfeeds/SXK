package com.example.cfwifine.sxk.Utils;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreferencesUtils {
	/**
	 * 保存在手机里面的文件�?
	 */
	private static final String FILE_NAME = "qingfeng_share_date";
	//关于登录
	public static final String LOGIN_NAME = "qingfeng_login_name";
	public static final String LOGIN_PWD = "qingfeng_login_password";
	public static final String LOGIN_TOKEN = "qingfeng_login_token";
	public static final String IS_LOGIN = "qingfeng_is_login";
	//手机号登录时用到
	public static final String LOGIN_MOBILE_PHONE = "qingfeng_login_mobile_phone";
	
	//头像图片地址
	public static final String AVATAR_FILE_PATH = "avatar_file_path";
	//关于用户信息
	public static final String USER_INFO_NICK_NAME = "user_info_nick_name";
	public static final String USER_INFO_NAME = "user_info_name";
	public static final String USER_INFO_PHONE = "user_info_phone";

	/**
	 * 保存数据的方法，我们�?��拿到保存数据的具体类型，然后根据类型调用不同的保存方�?
	 * 
	 * @param context
	 * @param key
	 * @param object
	 */
	public static void setParam(Context context, String key, Object object) {

		String type = object.getClass().getSimpleName();
		SharedPreferences sp = context.getSharedPreferences(FILE_NAME,
				Context.MODE_PRIVATE);
		SharedPreferences.Editor editor = sp.edit();

		if ("String".equals(type)) {
			editor.putString(key, (String) object);
		} else if ("Integer".equals(type)) {
			editor.putInt(key, (Integer) object);
		} else if ("Boolean".equals(type)) {
			editor.putBoolean(key, (Boolean) object);
		} else if ("Float".equals(type)) {
			editor.putFloat(key, (Float) object);
		} else if ("Long".equals(type)) {
			editor.putLong(key, (Long) object);
		}

		editor.commit();
	}

	/**
	 * 得到保存数据的方法，我们根据默认值得到保存的数据的具体类型，然后调用相对于的方法获取�?
	 * 
	 * @param context
	 * @param key
	 * @param defaultObject
	 * @return
	 */
	public static Object getParam(Context context, String key,
			Object defaultObject) {
		String type = defaultObject.getClass().getSimpleName();
		SharedPreferences sp = context.getSharedPreferences(FILE_NAME,
				Context.MODE_PRIVATE);

		if ("String".equals(type)) {
			return sp.getString(key, (String) defaultObject);
		} else if ("Integer".equals(type)) {
			return sp.getInt(key, (Integer) defaultObject);
		} else if ("Boolean".equals(type)) {
			return sp.getBoolean(key, (Boolean) defaultObject);
		} else if ("Float".equals(type)) {
			return sp.getFloat(key, (Float) defaultObject);
		} else if ("Long".equals(type)) {
			return sp.getLong(key, (Long) defaultObject);
		}

		return null;
	}
}
