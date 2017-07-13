package com.lenovohit.lartemis_api.utils;

import android.content.Context;
import android.os.Environment;
import android.util.Log;

import com.lenovohit.lartemis_api.core.LArtemis;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

import libcore.io.DiskLruCache;


/**
 * Created by SharkChao on 2017-07-13.
 */

public class DiskLruCacheUtil {

    private static DiskLruCache sMDiskLruCache = null;
    private static ByteArrayOutputStream sByt;

    //拿到缓存地址
    private static File getDiskCacheDir(Context context, String uniqueName) {
        String cachePath;
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())
                || !Environment.isExternalStorageRemovable()) {
            cachePath = context.getExternalCacheDir().getPath();
        } else {
            cachePath = context.getCacheDir().getPath();
        }
        return new File(cachePath + File.separator + uniqueName);
    }

    //拿到DiskLruCache
    private static DiskLruCache getDiskLruCache(Context context, String uniqueName) {
        sMDiskLruCache = null;
        try {
            File cacheDir = getDiskCacheDir(context, uniqueName);
            if (!cacheDir.exists()) {
                cacheDir.mkdirs();
            }
            sMDiskLruCache = DiskLruCache.open(cacheDir, CommonUtil.getVersionCode(context), 1, 1 * 1024 * 1024);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sMDiskLruCache;
    }

    public static void putData( String name, Object object) {
        if (sMDiskLruCache == null) {
            getDiskLruCache(LArtemis.getInstance().getApplication(), name);
        }
        DiskLruCache.Editor editor = null;
        try {
            editor = sMDiskLruCache.edit(name);
            OutputStream outputStream = editor.newOutputStream(0);
            //将对象转化成流存放在磁盘中
            sByt = new ByteArrayOutputStream();
            ObjectOutputStream obj = new ObjectOutputStream(sByt);
            obj.writeObject(object);
            outputStream.write(sByt.toByteArray());
            editor.commit();
            sMDiskLruCache.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                sByt.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static Object getData(String name) {
        Object object = null;
        if (sMDiskLruCache == null) {
            getDiskLruCache(LArtemis.getInstance().getApplication(), name);
        }
        try {
            DiskLruCache.Snapshot snapshot = sMDiskLruCache.get(name);
            if (snapshot != null) {
                InputStream is = snapshot.getInputStream(0);
                ObjectInputStream objInt = new ObjectInputStream(is);
                object = objInt.readObject();
            }else {
                Log.e("tag","数据为空");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return object;
    }
}