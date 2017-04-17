package com.leap.avatar.model.user;

/**
 * <p>
 * </> Created by weiyaling on 2017/3/7.
 */

public class BUserInfo {
  private BUser user;
  private BUserPayChannel aliPayChannel;
  private BUserPayChannel weiXinChannel;
  private BUserPayChannel bestPayChannel;

  public BUser getUser() {
    return user;
  }

  public void setUser(BUser user) {
    this.user = user;
  }

  public BUserPayChannel getAliPayChannel() {
    return aliPayChannel;
  }

  public void setAliPayChannel(BUserPayChannel aliPayChannel) {
    this.aliPayChannel = aliPayChannel;
  }

  public BUserPayChannel getWeiXinChannel() {
    return weiXinChannel;
  }

  public void setWeiXinChannel(BUserPayChannel weiXinChannel) {
    this.weiXinChannel = weiXinChannel;
  }

  public BUserPayChannel getBestPayChannel() {
    return bestPayChannel;
  }

  public void setBestPayChannel(BUserPayChannel bestPayChannel) {
    this.bestPayChannel = bestPayChannel;
  }
}
