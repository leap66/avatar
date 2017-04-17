package com.leap.avatar.net.auth;

import com.leap.avatar.net.auth.usecase.RegisterRequest;
import com.leap.avatar.model.auth.BLoginResult;
import com.leap.avatar.model.user.BUser;
import com.leap.mini.net.network.subscriber.Response;

import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

/**
 * 登录认证接口
 * <p>
 * </> Created by weiyaling on 17/3/7.
 */

public interface AuthServiceApi {

  /**
   * 登录
   *
   * @param mobile
   *          手机号
   * @param password
   *          密码
   */
  @POST("app/auth/login")
  Observable<Response<BLoginResult>> login(@Query("mobile") String mobile,
      @Query("password") String password);

  /**
   * 注册
   *
   * @param authCode
   *          验证码
   * @param params
   *          数据
   */
  @POST("app/auth/register")
  Observable<Response<BUser>> register(@Query("authCode") String authCode,
      @Body RegisterRequest params);

  /**
   * 发送短信
   *
   * @param mobile
   *          手机号
   */
  @POST("app/sms/send")
  Observable<Response> getSmsCode(@Query("mobile") String mobile);

  /**
   * 检查短信验证码
   */
  @POST("app/sms/check")
  Observable<Response<Boolean>> checkSms(@Query("mobile") String mobile,
      @Query("code") String code);

  /**
   * 检查手机号是否已注册
   */
  @POST("app/auth/mobilenotexist/check")
  Observable<Response> checkMobile(@Query("mobile") String mobile);
}
