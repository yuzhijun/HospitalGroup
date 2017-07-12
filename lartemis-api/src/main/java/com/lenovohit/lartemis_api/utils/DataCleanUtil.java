package com.lenovohit.lartemis_api.utils;

import android.os.Environment;

import com.lenovohit.lartemis_api.core.LArtemis;

import java.io.File;

/**
 * 清楚本地缓存
 * 
 * @author LinHao 439224@qq.com
 * @version 创建时间： 2015-7-2 上午10:25:03
 */
public class DataCleanUtil {

	/** * 删除方法 这里只会删除某个文件夹下的文件，如果传入的directory是个文件，将不做处理 * * @param directory */
	private static void deleteFilesByDirectory(File directory) {
		if (directory != null && directory.exists() && directory.isDirectory()) {
			for (File item : directory.listFiles()) {
				item.delete();
			}
		}
	}

	/**
	 * 清除本应用内部缓存(/data/data/com.xxx.xxx/cache)
	 * 
	 **/
	public static void cleanCacheData() {
		deleteFilesByDirectory(LArtemis.getInstance().getApplication().getCacheDir());
	}

	/** * 清除本应用所有数据库(/data/data/com.xxx.xxx/databases) * * @param context */
	public static void cleanSQLData() {
		deleteFilesByDirectory(new File("/data/data/"
				+ LArtemis.getInstance().getApplication().getPackageName() + "/databases"));
	}

	/**
	 * * 清除本应用SharedPreference(/data/data/com.xxx.xxx/shared_prefs) * * @param
	 * context
	 */
	public static void cleanSharedPreferenceData() {
		deleteFilesByDirectory(new File("/data/data/"
				+ LArtemis.getInstance().getApplication().getPackageName() + "/shared_prefs"));
	}

	/** * 按名字清除本应用数据库 * * @param context * @param dbName */
	public static void cleanDatabaseByName(String dbName) {
		LArtemis.getInstance().getApplication().deleteDatabase(dbName);
	}

	/** * 清除/data/data/com.xxx.xxx/files下的内容 * * @param context */
	public static void cleanFilesData() {
		deleteFilesByDirectory(LArtemis.getInstance().getApplication().getFilesDir());
	}

	/**
	 * * 清除外部cache下的内容(/mnt/sdcard/android/data/com.xxx.xxx/cache) * * @param
	 * context
	 */
	public static void cleanSDCache() {
		if (Environment.getExternalStorageState().equals(
				Environment.MEDIA_MOUNTED)) {
			deleteFilesByDirectory(LArtemis.getInstance().getApplication().getExternalCacheDir());
		}
	}

	/** * 清除自定义路径下的文件，使用需小心，请不要误删。而且只支持目录下的文件删除 * * @param filePath */
	public static void cleanCustomCache(String filePath) {
		deleteFilesByDirectory(new File(filePath));
	}

	/** * 清除本应用所有的数据 * * @param context * @param filepath */
	public static void cleanApplicationData(String... filepath) {
		cleanCacheData();
		cleanSDCache();
		cleanSQLData();
		cleanSharedPreferenceData();
		cleanFilesData();
		for (String filePath : filepath) {
			cleanCustomCache(filePath);
		}
	}
}
