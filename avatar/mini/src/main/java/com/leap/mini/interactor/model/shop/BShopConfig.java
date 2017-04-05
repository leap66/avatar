package com.leap.mini.interactor.model.shop;

import com.leap.mini.interactor.model.entity.BStandardEntity;

/**
 * <p>
 * </> Created by weiyaling on 2017/3/7.
 */

public class BShopConfig extends BStandardEntity {

  private static final long serialVersionUID = -3756970229310734098L;

  private String shop;
  private String saleTagTpl;
  private Integer changesMode; // 0.不抹零 | 1.抹去分 | 2.抹去角

  public String getShop() {
    return shop;
  }

  public void setShop(String shop) {
    this.shop = shop;
  }

  public String getSaleTagTpl() {
    return saleTagTpl;
  }

  public void setSaleTagTpl(String saleTagTpl) {
    this.saleTagTpl = saleTagTpl;
  }

  public Integer getChangesMode() {
    return changesMode;
  }

  public void setChangesMode(Integer changesMode) {
    this.changesMode = changesMode;
  }

}
