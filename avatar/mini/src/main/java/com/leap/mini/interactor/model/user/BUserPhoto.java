package com.leap.mini.interactor.model.user;

import java.io.Serializable;

/**
 * <p>
 * </> Created by weiyaling on 2017/3/7.
 */

public class BUserPhoto implements Serializable {

  private String id;
  private String storageId;
  private String url;

  public BUserPhoto() {
  }

  public BUserPhoto(String storageId, String url) {
    this.storageId = storageId;
    this.url = url;
  }

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

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

}
