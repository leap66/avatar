package com.leap.mini.interactor.model.shop;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * </> Created by weiyaling on 2017/3/7.
 */

public class BShopBaseInfos {

  /** 已加入的门店 */
  private List<BShopBaseInfo> shops = new ArrayList<BShopBaseInfo>();
  /** 邀请中的门店 */
  private List<BShopBaseInfo> inviteShops = new ArrayList<BShopBaseInfo>();

  public List<BShopBaseInfo> getShops() {
    return shops;
  }

  public void setShops(List<BShopBaseInfo> shops) {
    this.shops = shops;
  }

  public List<BShopBaseInfo> getInviteShops() {
    return inviteShops;
  }

  public void setInviteShops(List<BShopBaseInfo> inviteShops) {
    this.inviteShops = inviteShops;
  }

}
