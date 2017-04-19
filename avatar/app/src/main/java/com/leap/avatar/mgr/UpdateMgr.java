package com.leap.avatar.mgr;

import java.io.File;

import com.leap.avatar.net.update.usecase.CheckVersionCase;
import com.leap.mini.mgr.updata.IUpdateListener;
import com.leap.mini.mgr.updata.UpdataUtil;
import com.leap.mini.mgr.updata.UpdateDialog;
import com.leap.mini.mgr.updata.UpdateTask;
import com.leap.mini.model.UpdateModel;
import com.leap.mini.net.PureSubscriber;
import com.leap.mini.net.network.UpdateClient;
import com.leap.mini.net.network.subscriber.Response;
import com.leap.mini.util.IsEmpty;
import com.leap.mini.util.NetworkUtil;

import android.app.Activity;
import android.content.Context;

/**
 * 更新管理器
 * <p>
 * </>Created by weiyaling on 2017/2/22.
 */
public class UpdateMgr {
  private static UpdateMgr instance;
  private static String updateFileName = "";
  private String filePath;
  private Context context;
  private UpdateModel model;
  private String appId;
  private String versionCode;
  private IUpdateListener listener;
  private UpdateDialog updateDialog;
  private String appToken;

  public static UpdateMgr getInstance() {
    if (instance == null)
      instance = new UpdateMgr();
    return instance;
  }

  private UpdateMgr() {
  }

  public void init(String url, String appId, String versionCode, IUpdateListener listener) {
    UpdateClient.setBaseUrl(url);
    this.appId = appId;
    this.versionCode = versionCode;
    this.listener = listener;
  }

  // 检查新版本
  public void check(Context context) {
    this.context = context;
    String time = String.valueOf(System.currentTimeMillis());
    // appToken = TokenGenerator.generate(appId, time);
    new CheckVersionCase(null, appToken, time, "1", appId, versionCode)
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
    updateFileName = model.downloadUrl.substring(model.downloadUrl.lastIndexOf("/") + 1);
    filePath = getFilePath();
    File file = new File(filePath);
    // 如果文件已经存在
    if (file.exists()) {
      String currentMd5 = "-1";
      try {
        currentMd5 = UpdataUtil.calculateMD5ofFile(filePath);
      } catch (Exception e) {
        e.printStackTrace();
      }
      // 已经下载完成，显示对话框
      if (currentMd5.equals(model.md5)) {
        UpdataUtil.installApk(context, filePath);
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

  private String getFilePath() {
    if (android.os.Environment.getExternalStorageState()
        .equals(android.os.Environment.MEDIA_MOUNTED)) {
      return context.getExternalCacheDir() + "/" + updateFileName;
    } else {
      return context.getCacheDir() + "/" + updateFileName;
    }
  }

  // 显示更新对话框
  private void showDialog(boolean already, long position) {
    model.already = already;
    model.position = position;
    updateDialog = new UpdateDialog(context);
    updateDialog.init(model);
    updateDialog.setUpdateListener(listener);
    updateDialog.setFilePath(filePath);
    if (!((Activity) context).isFinishing())
      updateDialog.show();
  }

  // Wifi下，及静默更新，后台下载文件，完成后提示更新
  private void download(final long position) {
    if (NetworkUtil.isWifiConnected(context)
        && model.updateMode.equals(UpdataUtil.UPDATE_MODE_SILENT)) {
      final UpdateTask updateTask = new UpdateTask(context, position, getFilePath());
      updateTask.setSuccessListener(new UpdateTask.OnSuccessListener<String>() {
        @Override
        public void onSuccess(String filePath) {
          showDialog(true, position);
        }
      });
      updateTask.execute(model.downloadUrl);
    } else {
      showDialog(false, position);
    }
  }
}
