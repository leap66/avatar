package com.leap.mini.interactor.api_interface.auth.usecase;

import com.leap.mini.cmp.SessionMgr;
import com.leap.mini.interactor.api_interface.BaseUseCase;
import com.leap.mini.interactor.api_interface.auth.AuthServiceApi;
import com.leap.mini.interactor.model.auth.BLoginResult;
import com.leap.mini.interactor.model.user.BUser;
import com.leap.mini.interactor.network.subscriber.Response;

import android.content.Context;

import rx.Observable;
import rx.functions.Func1;

/**
 * 登录
 * <p>
 * </> Created by weiyaling on 17/3/7.
 */

public class LoginCase extends BaseUseCase<AuthServiceApi> {
  private String mobile;
  private String password;

  public LoginCase(Context context, String mobile, String password) {
    this.context = context;
    this.mobile = mobile;
    this.password = password;
  }

  @Override
  public Observable<Response> buildUseCaseObservable() {
    return platformApiClient().login(mobile, password)
        .map(new Func1<Response<BLoginResult>, Response>() {
          @Override
          public Response call(Response<BLoginResult> o) {
            if (o.isSuccess()) {
              BUser user = o.getData().getUser();
              SessionMgr.updateUser(user);
              SessionMgr.updateShopDomain(o.getData().getBindShopDomain());
            }
            return o;
          }
        });
  }
}
