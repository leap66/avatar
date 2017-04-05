package com.leap.mini.interactor.api_interface.update.usecase;

import com.leap.mini.interactor.api_interface.BaseUseCase;
import com.leap.mini.interactor.api_interface.update.UpdateServiceApi;

import rx.Observable;

/**
 * App 版本安装统计
 * <p>
 * </> Created by weiyaling on 17/3/7.
 */

public class InstallStatisticsCase extends BaseUseCase<UpdateServiceApi> {

  private String appToken;
  private String pid;
  private String appId;
  private String appKey;
  private String versionCode;

  /**
   * 检查新版本
   *
   * @param appKey
   *          时间撮
   * @param pid
   *          应用类型 android平台传1 iOS平台传2
   * @param appId
   *          应用内部标识
   * @param versionCode
   *          应用版本号
   */
  public InstallStatisticsCase(String appToken, String appKey, String pid, String appId,
      String versionCode) {
    this.appToken = appToken;
    this.pid = pid;
    this.appKey = appKey;
    this.appId = appId;
    this.versionCode = versionCode;
  }

  @Override
  protected Observable buildUseCaseObservable() {
    return updateClient().installStatistics(appToken, appKey, pid, appId, versionCode);
  }
}
