package com.leap.mini.cmp;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import com.leap.mini.interactor.api_interface.shop.usecase.GetSessionCase;
import com.leap.mini.interactor.model.entity.BApplicationAbout;
import com.leap.mini.interactor.model.entity.BDposSession;
import com.leap.mini.interactor.model.entity.BSessionPosCfg;
import com.leap.mini.interactor.model.entity.BSessionShop;
import com.leap.mini.interactor.model.entity.BSessionUser;
import com.leap.mini.interactor.model.user.BUser;
import com.leap.mini.interactor.network.subscriber.PureSubscriber;
import com.leap.mini.interactor.network.subscriber.Response;
import com.leap.mini.util.DeviceInfoUtil;
import com.leap.mini.util.IsEmpty;

import android.content.Context;

/**
 * 会话消息 管理器
 * <p>
 * </> Created by weiyaling on 17/3/7.
 */

public class SessionMgr {

  // key
  private static String KEY_SESSION = "session";
  private static String KEY_SHOP_DONAIN = "shopDomain";

  private static BDposSession session;// 会话信息
  private static String shopDomain;// 门店终端地址
  private static Timer timer;
  private static Context mContext;

  public static void init(Context context) {
    mContext = context;
    timer = new Timer(true);
    timer.schedule(new Task(), 1000, 1000 * 60 * 60);
    if (IsEmpty.object(SessionMgr.session)) {
      SessionMgr.session = new BDposSession();
    }
    SessionMgr.session = StorageMgr.get(KEY_SESSION, BDposSession.class, StorageMgr.LEVEL_GLOBAL);
    if (IsEmpty.object(SessionMgr.session)) {
      SessionMgr.session = new BDposSession();
    }
    SessionMgr.shopDomain = StorageMgr.get(KEY_SHOP_DONAIN, StorageMgr.LEVEL_GLOBAL);
  }

  // 清除
  public static void clear() {
    StorageMgr.set(KEY_SESSION, null, StorageMgr.LEVEL_GLOBAL);
    StorageMgr.set(KEY_SHOP_DONAIN, null, StorageMgr.LEVEL_GLOBAL);
    SessionMgr.session = new BDposSession();
    SessionMgr.shopDomain = null;
  }

  // 更新用户（登录时）
  public static void updateUser(BUser bUser) {
    BSessionUser user = SessionMgr.session.getUser();
    if (IsEmpty.object(user)) {
      user = new BSessionUser();
    }
    user.setMobile(bUser.getMobile());
    user.setId(bUser.getId());
    user.setName(bUser.getName());
    user.setPhoto(bUser.getUserPhoto().getUrl());
    user.setIdCard(bUser.getIdCard());
    SessionMgr.session.setUser(user);
    StorageMgr.set(KEY_SESSION, SessionMgr.session, StorageMgr.LEVEL_GLOBAL);
  }

  // 更新用户
  public static void updateUser(BSessionUser user) {
    SessionMgr.session.setUser(user);
    StorageMgr.set(KEY_SESSION, SessionMgr.session, StorageMgr.LEVEL_GLOBAL);
  }

  // 更新Session
  public static void updateSession(BDposSession session) {
    SessionMgr.session = session;
    StorageMgr.set(KEY_SESSION, SessionMgr.session, StorageMgr.LEVEL_GLOBAL);
  }

  // 更新门店
  public static void updateShop(BSessionShop shop) {
    SessionMgr.session.setShop(shop);
    StorageMgr.set(KEY_SESSION, SessionMgr.session, StorageMgr.LEVEL_GLOBAL);
  }

  // 更新Pos设置
  public static void updatePosCfg(BSessionPosCfg posCfg) {
    SessionMgr.session.setPosCfg(posCfg);
    StorageMgr.set(KEY_SESSION, SessionMgr.session, StorageMgr.LEVEL_GLOBAL);
  }

  // 更新门店shopDomain
  public static void updateShopDomain(String shopDomain) {
    SessionMgr.shopDomain = shopDomain;
    StorageMgr.set(KEY_SHOP_DONAIN, SessionMgr.shopDomain, StorageMgr.LEVEL_GLOBAL);
  }

  // 以下是对象的方法
  public static BDposSession getSession() {
    return SessionMgr.session;
  }

  public static BSessionUser getUser() {
    return SessionMgr.session.getUser();
  }

  public static BSessionShop getShop() {
    return SessionMgr.session.getShop();
  }

  public static BSessionPosCfg getPosCfg() {
    return SessionMgr.session.getPosCfg();
  }

  public static List<String> getPermissions() {
    return SessionMgr.session.getPermissions();
  }

  public static Integer getDiscount() {
    return SessionMgr.session.getDiscount();
  }

  public static BApplicationAbout getApplicationAbout() {
    return SessionMgr.session.getApplicationAbout();
  }

  public static String getShopDomain() {
    return SessionMgr.shopDomain;
  }

  // 扩展方法
  public static boolean isShopOwner() {
    BSessionUser user = SessionMgr.session.getUser();
    BSessionShop shop = SessionMgr.session.getShop();
    return !IsEmpty.object(user) && !IsEmpty.object(shop) && user.getId().equals(shop.getOwner());
  }

  /**
   * 会话消息任务定时管理器
   */
  private static class Task extends TimerTask {
    @Override
    public void run() {
      if (!TokenMgr.isExpired() && TokenMgr.hasShop()) {
        new GetSessionCase(null, SessionMgr.getShop().getId(), DeviceInfoUtil.getDeviceId(mContext))
            .execute(new PureSubscriber<BDposSession>() {
              @Override
              public void onSuccess(Response<BDposSession> response) {
                SessionMgr.updateSession(response.getData());
              }

              @Override
              public void onFailure(String errorMsg, Response<BDposSession> response) {
              }
            });
      }
    }
  }

  public static void updateCancel() {
    mContext = null;
    timer.cancel();
  }
}
