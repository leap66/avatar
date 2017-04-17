package com.leap.avatar.net.shop;

import com.leap.avatar.model.entity.BDposSession;
import com.leap.mini.net.network.subscriber.Response;

import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * 会话消息接口
 * <p>
 * </> Created by weiyaling on 17/3/7.
 */

public interface ShopServiceApi {

  /**
   * 获取用户，门店，服务信息
   *
   * @param shop
   *          门店 id
   * @param machineCode
   *          机器码
   * @return @
   */
  @GET("app/{shop}/session/get")
  Observable<Response<BDposSession>> getSession(@Path("shop") String shop,
      @Query("machineCode") String machineCode);

}
