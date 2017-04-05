package com.leap.mini.interactor.api_interface.update;

import com.leap.mini.interactor.model.update.UpdateModel;
import com.leap.mini.interactor.network.subscriber.Response;

import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

/**
 * App 更新接口
 * <p>
 * </> Created by weiyaling on 17/3/7.
 */

public interface UpdateServiceApi {

  /**
   * 检查新版本
   *
   * @param pid
   *          应用类型 android平台传1 iOS平台传2
   * @param appId
   *          应用内部标识
   * @param versionCode
   *          应用版本号
   */
  @GET("app/version/check")
  Observable<Response<UpdateModel>> checkVersion(@Query("appToken") String appToken,
                                                 @Query("appKey") String appKey, @Query("pid") String pid, @Query("appId") String appId,
                                                 @Query("versionCode") String versionCode);

  /**
   * 版本安装统计
   *
   * @param pid
   *          应用类型 android平台传1 iOS平台传2
   * @param appId
   *          应用内部标识
   * @param versionCode
   *          应用版本号
   */
  @POST("app/version/install")
  Observable<Response> installStatistics(@Query("appToken") String appToken,
                                         @Query("appKey") String appKey, @Query("pid") String pid, @Query("appId") String appId,
                                         @Query("versionCode") String versionCode);
}
