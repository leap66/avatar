/**
 * 版权所有(C)，上海海鼎信息工程股份有限公司，2016，所有权利保留。
 * <p>
 * 项目名：	dpos-web
 * 文件名：	BLoginResult.java
 * 模块说明：
 * 修改历史：
 * 2016年5月21日 - linzhu - 创建。
 */
package com.leap.mini.interactor.model.auth;

import java.io.Serializable;

import com.leap.mini.interactor.model.user.BUser;

/**
 * <p>
 * </> Created by weiyaling on 2017/3/7.
 */

public class BLoginResult implements Serializable {

  private BUser user;
  private String bindShopDomain;

  public BUser getUser() {
    return user;
  }

  public void setUser(BUser user) {
    this.user = user;
  }

  public String getBindShopDomain() {
    return bindShopDomain;
  }

  public void setBindShopDomain(String bindShopDomain) {
    this.bindShopDomain = bindShopDomain;
  }
}
