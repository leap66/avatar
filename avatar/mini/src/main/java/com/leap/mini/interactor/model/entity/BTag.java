package com.leap.mini.interactor.model.entity;

/**
 * <p>
 * </> Created by weiyaling on 2017/3/7.
 */

public class BTag extends BVersionedEntity {

  private String name;

  public BTag() {
  }

  public BTag(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

}
