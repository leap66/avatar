package com.leap.mini.interactor.model.shop;

import com.leap.mini.interactor.model.entity.BEntity;

/**
 * <p>
 * </> Created by weiyaling on 2017/3/7.
 */

public class BShopImage extends BEntity {

  private static final long serialVersionUID = 5946411377776098520L;

  private String storageId;
  private String url;

  public String getStorageId() {
    return storageId;
  }

  public void setStorageId(String storageId) {
    this.storageId = storageId;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

}
