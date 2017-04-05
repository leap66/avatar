package com.leap.mini.cmp.update;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import com.leap.mini.interactor.api_interface.update.usecase.CheckVersionCase;
import com.leap.mini.interactor.model.update.UpdateModel;
import com.leap.mini.interactor.network.UpdateClient;
import com.leap.mini.interactor.network.subscriber.PureSubscriber;
import com.leap.mini.interactor.network.subscriber.Response;
import com.leap.mini.util.IsEmpty;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Build;
import android.support.v4.content.FileProvider;
import android.util.DisplayMetrics;

/**
 * 更新 管理器
 * <p>
 * </> Created by weiyaling on 17/3/7.
 */

public class UpdateMgr {
  static String UPDATE_MODE_BLOCK = "BLOCK";
  private static String UPDATE_MODE_SILENT = "SILENT";
  private static String UPDATE_FILE_NAME = "";
  private static String UPDATE_INTENT = "updateModel";
  private static int UPDATE_PERIOD = 1;

  private static UpdateMgr instance;

  private Context context;
  private UpdateModel model;
  private int icon;
  private String appId;
  private String versionCode;
  private IUpdateListener listener;
  private UpdateDialog updateDialog;

  public static UpdateMgr getInstance() {
    if (instance == null)
      instance = new UpdateMgr();
    return instance;
  }

  private UpdateMgr() {
  }

  public void init(String url, String appId, String versionCode, int icon,
      IUpdateListener listener) {
    UpdateClient.setBaseUrl(url);
    this.icon = icon;
    this.appId = appId;
    this.versionCode = versionCode;
    this.listener = listener;

  }

  // 检查新版本
  public void check(Context context) {
    this.context = context;
    String time = String.valueOf(System.currentTimeMillis());
    new CheckVersionCase(null, "change", time, "1", appId, versionCode)
        .execute(new PureSubscriber<UpdateModel>() {
          @Override
          public void onFailure(String errorMsg, Response<UpdateModel> response) {
            if (listener != null) {
              if (IsEmpty.string(errorMsg)) {
                listener.onCancel(IUpdateListener.UPDATE_CODE_FAIL);
              } else {
                listener.onCancel(IUpdateListener.UPDATE_CODE_FAIL, errorMsg);
              }
            }
          }

          @Override
          public void onSuccess(Response<UpdateModel> response) {
            model = response.getData();
            // 已是最新版本
            if (!model.upgradable) {
              if (listener != null)
                listener.onCancel(IUpdateListener.UPDATE_CODE_NEWEST);
            } else {
              update();
            }
          }
        });
  }

  // 更新，并下载
  private void update() {
    // 获取下载文件的md5
    UPDATE_FILE_NAME = model.downloadUrl.substring(model.downloadUrl.lastIndexOf("/") + 1);
    File file = new File(getFilePath());
    // 如果文件已经存在
    if (file.exists()) {
      String currentMd5 = "-1";
      try {
        currentMd5 = calculateMD5ofFile(getFilePath());
      } catch (Exception e) {
        e.printStackTrace();
      }
      // 已经下载完成，显示对话框
      if (currentMd5.equals(model.md5)) {
        showDialog(true, file.length());
      } else {
        // 未完成，断点续传
        // UpdateMgr.download(file.length());
        showDialog(false, file.length());
      }
    } else {
      // 文件不存在，静默下载
      // UpdateMgr.download(file.length());
      showDialog(false, file.length());
    }
  }

  public String getAppId() {
    return appId;
  }

  public void setAppId(String appId) {
    this.appId = appId;
  }

  public String getVersionCode() {
    return versionCode;
  }

  public void setVersionCode(String versionCode) {
    this.versionCode = versionCode;
  }

  public UpdateDialog getUpdateDialog() {
    return updateDialog;
  }

  public void cleanUpdateDialog() {
    updateDialog = null;
  }

  String getFilePath() {
    if (android.os.Environment.getExternalStorageState()
        .equals(android.os.Environment.MEDIA_MOUNTED)) {
      return context.getExternalCacheDir() + "/" + UpdateMgr.UPDATE_FILE_NAME;
    } else {
      return context.getCacheDir() + "/" + UpdateMgr.UPDATE_FILE_NAME;
    }
  }

  // Wifi下，及静默更新，后台下载文件，完成后提示更新
  private static void download(final long position) {
    // if (NetworkUtil.isWifiConnected(context) &&
    // model.updateMode.equals(UPDATE_MODE_SILENT)) {
    // final UpdateTask updateTask = new UpdateTask(context, position,
    // getFilePath());
    // updateTask.setSuccessListener(new UpdateTask.OnSuccessListener<String>()
    // {
    // @Override
    // public void onSuccess(String filePath) {
    // UpdateMgr.showDialog(true, position);
    // }
    // });
    // updateTask.execute(model.downloadUrl);
    // } else {
    // UpdateMgr.showDialog(false, position);
    // }
  }

  // 显示更新对话框
  private void showDialog(boolean already, long position) {
    model.already = already;
    model.position = position;
    updateDialog = new UpdateDialog(context);
    updateDialog.init(model);
    updateDialog.setUpdateListener(listener);

    if (!((Activity) context).isFinishing())
      updateDialog.show();
  }

  // 生成md5
  private static String calculateMD5ofFile(String filePath)
      throws IOException, NoSuchAlgorithmException {
    FileInputStream fs = new FileInputStream(filePath);
    MessageDigest md = MessageDigest.getInstance("MD5");
    byte[] buffer = new byte[2048];
    int bytes = 0;
    do {
      bytes = fs.read(buffer, 0, 2048);
      if (bytes > 0)
        md.update(buffer, 0, bytes);

    } while (bytes > 0);
    byte[] Md5Sum = md.digest();
    fs.close();
    return ByteArray2HexString(Md5Sum);
  }

  private static String ByteArray2HexString(byte[] bytes) {
    StringBuilder hexString = new StringBuilder();
    for (int i = 0; i < bytes.length; i++) {
      String hex = Integer.toHexString(bytes[i] & 0xFF);
      if (hex.length() == 1) {
        hexString.append('0');
      }
      hexString.append(hex);
    }
    return hexString.toString();
  }

  // 安装apk
  void installApk(String apkPath) {
    Intent intent = new Intent();
    intent.setAction(Intent.ACTION_VIEW);
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
      intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
      Uri contentUri = FileProvider.getUriForFile(context,
          context.getPackageName() + ".fileProvider", new File(apkPath));
      intent.setDataAndType(contentUri, "application/vnd.android.package-archive");
    } else {
      intent.setDataAndType(Uri.fromFile(new File(apkPath)),
          "application/vnd.android.package-archive");

    }
    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    context.startActivity(intent);

  }

  static int getScreenWidth() {
    DisplayMetrics metrics = Resources.getSystem().getDisplayMetrics();
    return metrics.widthPixels;
  }

  static int getScreenHeight() {
    DisplayMetrics metrics = Resources.getSystem().getDisplayMetrics();
    return metrics.heightPixels;
  }

}
