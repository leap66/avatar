package com.leap.avatar.net.shop.usecase;

import com.leap.avatar.net.shop.ShopServiceApi;
import com.leap.mini.net.ShopBaseUserCase;

import android.content.Context;

import rx.Observable;

/**
 * 获取用户，门店，服务会话信息
 * <p>
 * </> Created by weiyaling on 17/3/7.
 */

public class GetSessionCase extends ShopBaseUserCase<ShopServiceApi> {
  private String shop;
  private String machineCode;

  public GetSessionCase(Context context, String shop, String machineCode) {
    this.context = context;
    this.shop = shop;
    this.machineCode = machineCode;
  }

  @Override
  protected Observable createObservable(ShopServiceApi serverApi) {
    return serverApi.getSession(shop, machineCode);
  }
}