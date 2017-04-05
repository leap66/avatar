package com.leap.avatar;

import com.leap.mini.cmp.SessionMgr;
import com.leap.mini.cmp.StorageMgr;
import com.leap.mini.cmp.TokenMgr;
import com.leap.mini.interactor.network.ApiClient;

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
  }
}
