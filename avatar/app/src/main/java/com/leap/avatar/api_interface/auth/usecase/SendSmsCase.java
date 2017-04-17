package com.leap.avatar.api_interface.auth.usecase;

import com.leap.avatar.api_interface.auth.AuthServiceApi;
import com.leap.mini.interactor.BaseUseCase;

import rx.Observable;

/**
 * 发送验证码
 * <p>
 * </> Created by weiyaling on 17/3/7.
 */

public class SendSmsCase extends BaseUseCase<AuthServiceApi> {
  private String mobile;

  public SendSmsCase(String mobile) {
    this.mobile = mobile;
  }

  @Override
  protected Observable buildUseCaseObservable() {
    return platformApiClient().getSmsCode(mobile);
  }
}
