package com.leap.avatar;

import com.leap.avatar.mgr.SessionMgr;
import com.leap.mini.mgr.StorageMgr;
import com.leap.mini.mgr.TokenMgr;
import com.leap.mini.mgr.logger.CrashHandler;
import com.leap.mini.mgr.logger.LogStashDescription;
import com.leap.mini.mgr.logger.Logger;
import com.leap.mini.net.ApiClient;
import com.leap.mini.util.listener.ActivityLifecycleListener;

import android.app.Activity;
import android.support.multidex.MultiDexApplication;
import android.util.Log;

/**
 * Application
 * <p>
 * </>Created by weiyaling on 2017/4/5.
 */

public class AcatarApp extends MultiDexApplication {
  private boolean isBackground = true;

  @Override
  public void onCreate() {
    super.onCreate();
    ApiClient.init(this, BuildConfig.SERVER_URL);
    StorageMgr.init(this);
    SessionMgr.init(this);
    TokenMgr.init();

    // 日志服务
    LogStashDescription description = new LogStashDescription(this, BuildConfig.LOG_NAME,
        BuildConfig.LOG_PASSWORD, BuildConfig.LOG_URL);
    Logger.addDestination(description);
    description.sendNow();
    CrashHandler.getInstance().init();
    listenForForeground();
  }

  private void listenForForeground() {
    registerActivityLifecycleCallbacks(new ActivityLifecycleListener() {
      @Override
      public void onActivityResumed(Activity activity) {
        if (isBackground) {
          isBackground = false;
          notifyForeground();
        }
      }
    });
  }

  private void notifyForeground() {
    Log.e("listenForForeground: ", "notifyForeground");
  }

  private void notifyBackground() {
    Log.e("listenForForeground: ", "notifyBackground");
  }

  @Override
  public void onTrimMemory(int level) {
    super.onTrimMemory(level);
    if (level == TRIM_MEMORY_UI_HIDDEN) {
      isBackground = true;
      notifyBackground();
    }
  }
}
