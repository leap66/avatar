package com.leap.avatar.model.entity;

import com.leap.mini.model.BSessionShop;
import com.leap.mini.model.BSessionUser;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * </> Created by weiyaling on 2017/3/7.
 */

public class BDposSession {
  private BSessionUser user;
  private BSessionShop shop;
  private BSessionPosCfg posCfg;
  private List<String> permissions = new ArrayList<String>();
  private Integer discount;
  private BApplicationAbout applicationAbout;

  public BSessionUser getUser() {
    return user;
  }

  public void setUser(BSessionUser user) {
    this.user = user;
  }

  public BSessionShop getShop() {
    return shop;
  }

  public void setShop(BSessionShop shop) {
    this.shop = shop;
  }

  public BSessionPosCfg getPosCfg() {
    return posCfg;
  }

  public void setPosCfg(BSessionPosCfg posCfg) {
    this.posCfg = posCfg;
  }

  public List<String> getPermissions() {
    return permissions;
  }

  public void setPermissions(List<String> permissions) {
    this.permissions = permissions;
  }

  public Integer getDiscount() {
    return discount;
  }

  public void setDiscount(Integer discount) {
    this.discount = discount;
  }

  public BApplicationAbout getApplicationAbout() {
    return applicationAbout;
  }

  public void setApplicationAbout(BApplicationAbout applicationAbout) {
    this.applicationAbout = applicationAbout;
  }
}
