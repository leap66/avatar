package com.leap.mini.interactor.api_interface.auth.usecase;

import com.leap.mini.interactor.api_interface.BaseUseCase;
import com.leap.mini.interactor.api_interface.auth.AuthServiceApi;

import android.content.Context;

import rx.Observable;

/**
 * 验证手机号是否已被注册
 * <p>
 * </> Created by weiyaling on 2017/3/7.
 */

public class CheckMobileCase extends BaseUseCase<AuthServiceApi> {

  private String mobile;

  public CheckMobileCase(Context context, String mobile) {
    this.context = context;
    this.mobile = mobile;
  }

  @Override
  protected Observable buildUseCaseObservable() {
    return platformApiClient().checkMobile(mobile);
  }
}
