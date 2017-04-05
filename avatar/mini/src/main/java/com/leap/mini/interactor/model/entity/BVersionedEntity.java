package com.leap.mini.interactor.model.entity;

/**
 * <p>
 * </> Created by weiyaling on 2017/3/7.
 */

public class BVersionedEntity extends BEntity {

  private long version;

  public long getVersion() {
    return version;
  }

  public void setVersion(long version) {
    this.version = version;
  }
}
