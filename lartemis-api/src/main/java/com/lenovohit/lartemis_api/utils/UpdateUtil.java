package com.lenovohit.lartemis_api.utils;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ProgressBar;

import com.lenovohit.lartemis_api.R;
import com.lenovohit.lartemis_api.model.AppVersion;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by SharkChao on 2017-07-12.
 * 判断是否需要更新
 * 弹出更新日志框
 * 开始下载进度条，可以取消。（断点续传）
 * 下载完成开始安装
 */

public class UpdateUtil {
    private static String sdName = "Park";
    private static Context sContext;
    private static ProgressBar mProgress;
    private static boolean isClickCancle;
    private static AlertDialog sMDownloadDialog;
    private static String sMSavePath;

    /**
     * 判断是不是最新版本
     * @param context
     * @param version
     * @return
     */
    public static boolean isUpdate(Context context,AppVersion version){
        UpdateUtil.sContext=context;
        boolean canUpdate=false;
        if (version!= null){
            if (Integer.parseInt(version.getVersionCode())>CommonUtil.getVersionCode(sContext)){
                //服务器版本号大于客户端的版本号，需要更新
                canUpdate=true;
            }else {
                //不需要更新
                canUpdate=false;
            }
        }
        return canUpdate;
    }

    /**
     * 判断是不是必须更新
     * @param version
     * @return
     */
    public static boolean canCancle(AppVersion version){
        boolean canCancle = false;
        if (version!= null){
            if (version.getAppType().equals("1")){
                canCancle = false;
            }else {
                canCancle = true;
            }
        }
        return canCancle;
    }
    /**
     * 显示软件下载对话框
     */
    public static  void showDownloadDialog(Context mContext,AppVersion version) {
        // 构造软件下载对话框
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        builder.setCancelable(false);
        builder.setTitle("正在更新");
        // 给下载对话框增加进度条
        final LayoutInflater inflater = LayoutInflater.from(mContext);
        View v = inflater.inflate(R.layout.softupdate_progress, null);
        mProgress = (ProgressBar) v.findViewById(R.id.update_progress);
        builder.setView(v);
        // 取消更新
        builder.setNegativeButton("取消",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        // 设置取消状态
                        isClickCancle=true;
                    }
                });
        sMDownloadDialog = builder.create();
        sMDownloadDialog.setCancelable(false);
        sMDownloadDialog.setCanceledOnTouchOutside(false);
        sMDownloadDialog.show();
        // 现在文件
        downloadApk(version);
    }

    /**
     * 下载apk文件
     */
    private static  void downloadApk(AppVersion version) {
        // 启动新线程下载软件
        new downloadApkThread(version).start();
    }

    /**
     * 下载文件线程
     *
     * @author LinHao 439224@qq.com
     * @version 创建时间： 2013-7-30 下午4:25:08
     */
    private static class downloadApkThread extends Thread {
        AppVersion mAppVersion;
        public downloadApkThread(AppVersion version) {
            mAppVersion = version;
        }

        @Override
        public void run() {
            try {
                // 判断SD卡是否存在，并且是否具有读写权限
                if (Environment.getExternalStorageState().equals(
                        Environment.MEDIA_MOUNTED)) {
                    // 获得存储卡的路径
                    String sdpath = Environment.getExternalStorageDirectory()
                            + "/";
                    sMSavePath = sdpath + "download";
                    URL url = new URL(mAppVersion.getDownloadUrl());
                    // 创建连接
                    HttpURLConnection conn = (HttpURLConnection) url
                            .openConnection();
                    conn.connect();
                    // 获取文件大小
                    int length = conn.getContentLength();
                    // 创建输入流
                    InputStream is = conn.getInputStream();

                    File file = new File(sMSavePath);
                    // 判断文件目录是否存在
                    if (!file.exists()) {
                        file.mkdir();
                    }
                    File apkFile = new File(sMSavePath, sdName);
                    FileOutputStream fos = new FileOutputStream(apkFile);
                    int count = 0;
                    // 缓存
                    byte buf[] = new byte[1024];
                    // 写入到文件中
                    do {
                        int numread = is.read(buf);
                        count += numread;
                        // 计算进度条位置
                       int  progress = (int) (((float) count / length) * 100);
                        // 更新进度
                       mProgress.setProgress(progress);
                        if (numread <= 0) {
                            // 下载完成
                            installApk();
                            break;
                        }
                        // 写入文件
                        fos.write(buf, 0, numread);
                    } while (!isClickCancle);// 点击取消就停止下载.
                    fos.close();
                    is.close();
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            // 取消下载对话框显示
            sMDownloadDialog.dismiss();
        }
    };

    /**
     * 安装APK文件
     */
    private static void installApk() {
        File apkfile = new File(sMSavePath, sdName);
        if (!apkfile.exists()) {
            return;
        }
        // 通过Intent安装APK文件
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setDataAndType(Uri.parse("file://" + apkfile.toString()),
                "application/vnd.android.package-archive");
        sContext.startActivity(i);
    }
}
