package com.leap.avatar.net.auth.usecase;

import com.leap.avatar.net.auth.AuthServiceApi;
import com.leap.mini.net.BaseUseCase;

import android.content.Context;

import rx.Observable;

/**
 * 检查短信验证码
 * <p>
 * </> Created by weiyaling on 2017/3/7.
 */
public class CheckCodeCase extends BaseUseCase<AuthServiceApi> {

  private String code;
  private String mobile;

  public CheckCodeCase(Context context, String mobile, String code) {
    this.context = context;
    this.mobile = mobile;
    this.code = code;
  }

  @Override
  protected Observable buildUseCaseObservable() {
    return platformApiClient().checkSms(mobile, code);
  }
}
