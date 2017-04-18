package com.leap.avatar;

import com.leap.avatar.mgr.SessionMgr;
import com.leap.mini.mgr.StorageMgr;
import com.leap.mini.mgr.TokenMgr;
import com.leap.mini.mgr.log.CrashHandler;
import com.leap.mini.mgr.log.LogStashDescription;
import com.leap.mini.mgr.log.Logger;
import com.leap.mini.net.ApiClient;

import android.support.multidex.MultiDexApplication;

/**
 * Application
 * <p>
 * </>Created by weiyaling on 2017/4/5.
 */

public class AcatarApp extends MultiDexApplication {

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

  }
}
