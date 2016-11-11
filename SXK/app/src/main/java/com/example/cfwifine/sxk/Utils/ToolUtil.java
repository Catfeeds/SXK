package com.example.cfwifine.sxk.Utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager.NameNotFoundException;
import android.database.Cursor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.NetworkInfo.State;
import android.net.Uri;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.view.inputmethod.InputMethodManager;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ToolUtil {
	public static void openBrowser(Context context, String url) {
		try {
			Intent intent = new Intent();
			intent.setAction("android.intent.action.VIEW");
			url = url.trim();
			if (!url.startsWith("https://") && !url.startsWith("http://")) {
				url = "http://" + url;
			}
			Uri content_url = Uri.parse(url);
			intent.setData(content_url);
			context.startActivity(intent);
		} catch (Exception e) {
		}
	}

	public static void openPhone(Context context, String tel) {
		try {
			Uri uri = Uri.parse("tel:" + tel);
			Intent intent = new Intent(Intent.ACTION_DIAL, uri);
			context.startActivity(intent);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 获取当前APP 版本�?
	 * 
	 * @param context
	 * @return 1001
	 */
	public static int getVersionCode(Context context) {
		int verCode = 1;
		try {
			String packgeName = context.getPackageName();
			verCode = context.getPackageManager().getPackageInfo(packgeName, 0).versionCode;
		} catch (NameNotFoundException e) {
		}
		return verCode;
	}

	public static boolean isMobileNum(String mobiles) {
		Pattern p = Pattern
				.compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");
		Matcher m = p.matcher(mobiles);
		return m.matches();
	}

	public static boolean isEmail(String email) {
		String str = "^([a-zA-Z0-9]*[-_]?[a-zA-Z0-9]+)*@([a-zA-Z0-9]*[-_]?[a-zA-Z0-9]+)+[\\.][A-Za-z]{2,3}([\\.][A-Za-z]{2})?$";
		Pattern p = Pattern.compile(str);
		Matcher m = p.matcher(email);
		return m.matches();
	}
	
	
	/**
	 * 手机号中�?位改�?�?
	 * @param mobile
	 * @return
	 */
	public static String mobileToStar(String mobile) {
		if (TextUtils.isEmpty(mobile)) {
			return "";
		}
		return mobile.replaceAll("(\\d{3})\\d{4}(\\d{4})", "$1****$2");
	}

	public static boolean isNetworkAvailable(Context context) {
		ConnectivityManager connectivity = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		if (connectivity == null) {
			return false;
		} else {
			NetworkInfo[] info = connectivity.getAllNetworkInfo();
			if (info != null) {
				for (int i = 0; i < info.length; i++) {
					State state = info[i].getState();
					if (state == State.CONNECTED
							|| state == State.CONNECTING) {
						return true;
					}
				}
			}
		}
		return false;
	}
	
    public static String getContactPhone(Context context,Cursor cursor) {  
        // TODO Auto-generated method stub  
        int phoneColumn = cursor  
                .getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER);  
        int phoneNum = cursor.getInt(phoneColumn);  
        String result = "";  
        if (phoneNum > 0) {  
            // 获得联系人的ID�? 
            int idColumn = cursor.getColumnIndex(ContactsContract.Contacts._ID);  
            String contactId = cursor.getString(idColumn);  
            // 获得联系人电话的cursor  
            Cursor phone = context.getContentResolver().query(  
                    ContactsContract.CommonDataKinds.Phone.CONTENT_URI,  
                    null,  
                    ContactsContract.CommonDataKinds.Phone.CONTACT_ID + "="  
                            + contactId, null, null);  
            if (phone.moveToFirst()) {  
                for (; !phone.isAfterLast(); phone.moveToNext()) {  
                    int index = phone  
                            .getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);  
                    int typeindex = phone  
                            .getColumnIndex(ContactsContract.CommonDataKinds.Phone.TYPE);  
                    int phone_type = phone.getInt(typeindex);  
                    String phoneNumber = phone.getString(index);  
                    result = phoneNumber;  
//                  switch (phone_type) {//此处请看下方注释  
//                  case 2:  
//                      result = phoneNumber;  
//                      break;  
//  
//                  default:  
//                      break;  
//                  }  
                }  
                if (!phone.isClosed()) {  
                    phone.close();  
                }  
            }  
        }  
        result = result.replaceAll(" ", "");
        result = result.replaceAll("-", "");
        return result;  
    }  
    
	/**
	 * 隐藏输入�?
	 * 
	 * @param context
	 * @param view
	 *            显示输入法的控件
	 */
	public static void hideSoftInput(Activity context) {
		InputMethodManager inputMethodManager = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
		if (context.getCurrentFocus()!=null) {
			inputMethodManager.hideSoftInputFromWindow(context.getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
		}
	}
	
}
