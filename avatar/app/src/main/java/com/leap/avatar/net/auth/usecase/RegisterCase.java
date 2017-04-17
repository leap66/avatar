package com.leap.avatar.net.auth.usecase;

import com.leap.avatar.net.auth.AuthServiceApi;
import com.leap.avatar.model.user.BUser;
import com.leap.mini.net.BaseUseCase;
import com.leap.mini.net.network.subscriber.Response;

import android.content.Context;

import rx.Observable;

/**
 * 注册
 * <p>
 * </> Created by weiyaling on 17/3/7.
 */

public class RegisterCase extends BaseUseCase<AuthServiceApi> {

  private RegisterRequest req;
  private String authCode;

  public RegisterCase(Context context, String authCode, RegisterRequest req) {
    this.req = req;
    this.context = context;
    this.authCode = authCode;
  }

  @Override
  protected Observable<Response<BUser>> buildUseCaseObservable() {
    return platformApiClient().register(authCode, req);
  }
}
