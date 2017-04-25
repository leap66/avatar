package com.leap.avatar.mgr;

import com.leap.avatar.BuildConfig;
import com.leap.avatar.net.update.usecase.CheckVersionCase;
import com.leap.mini.mgr.updata.UpdataUtil;
import com.leap.mini.mgr.updata.UpdateDialog;
import com.leap.mini.mgr.updata.UpdateTask;
import com.leap.mini.model.UpdateModel;
import com.leap.mini.net.PureSubscriber;
import com.leap.mini.net.network.UpdateClient;
import com.leap.mini.net.network.subscriber.Response;
import com.leap.mini.util.NetworkUtil;
import com.leap.mini.util.ToastUtil;
import com.qianfan123.app.TokenGenerator;

import android.content.Context;

/**
 * 更新管理器
 * <p>
 * </>Created by weiyaling on 2017/2/22.
 */
public class UpdateMgr {
  private static UpdateMgr instance;
  private Context context;
  private UpdateModel model;
  private String appId;
  private String versionCode;
  private UpdateDialog updateDialog;

  public static UpdateMgr getInstance() {
    if (instance == null)
      instance = new UpdateMgr();
    return instance;
  }

  private UpdateMgr() {
    UpdateClient.setBaseUrl(BuildConfig.UPDATE_URL);
    appId = BuildConfig.APPLICATION_ID;
    versionCode = "0";
    appId = "com.qianfan123.minya";
  }

  // 检查新版本
  public void checkTask(final Context context) {
    this.context = context;
    String time = String.valueOf(System.currentTimeMillis());
    String appToken;
    appToken = TokenGenerator.generate(appId, time);
    new CheckVersionCase(null, appToken, time, "1", appId, versionCode)
        .execute(new PureSubscriber<UpdateModel>() {
          @Override
          public void onFailure(String errorMsg, Response<UpdateModel> response) {
            ToastUtil.showFailure(context, errorMsg);
          }

          @Override
          public void onSuccess(Response<UpdateModel> response) {
            model = response.getData();
            // 已是最新版本
            if (model.upgradable) {
              model.fileName = model.downloadUrl.substring(model.downloadUrl.lastIndexOf("/") + 1);
              showDialog();
            }
          }
        });
  }

  private String getFilePath(String updateFileName) {
    if (android.os.Environment.getExternalStorageState()
        .equals(android.os.Environment.MEDIA_MOUNTED)) {
      return context.getExternalCacheDir() + "/" + updateFileName;
    } else {
      return context.getCacheDir() + "/" + updateFileName;
    }
  }

  // 显示更新对话框
  private void showDialog() {
    updateDialog = new UpdateDialog(context, model);
    updateDialog.setFilePath(getFilePath(model.fileName));
    updateDialog.show();
  }

  private void cancelDialog() {
    if (updateDialog.isShowing()) {
      updateDialog.dismiss();
      updateDialog = null;
    }
  }

  // Wifi下，及静默更新，后台下载文件，完成后提示更新
  private void download(final long position) {
    if (NetworkUtil.isWifiConnected(context)
        && UpdataUtil.UPDATE_MODE_SILENT.equals(model.updateMode)) {
      UpdateTask updateTask = new UpdateTask(context, position, getFilePath(model.fileName));
      updateTask.setSuccessListener(new UpdateTask.OnSuccessListener<String>() {
        @Override
        public void onSuccess(String filePath) {
          showDialog();
        }
      });
      updateTask.execute(model.downloadUrl);
    } else {
      showDialog();
    }
  }
}
