package com.example.cfwifine.sxk.Utils;

public class FileManager {

	public static String getSaveImagePath() {
		if (CommonUtil.hasSDCard()) {
			return CommonUtil.getRootFilePath() + "sxk/images/";
		} else {
			return CommonUtil.getRootFilePath() + "sxk/images/";
		}
	}

}
