package com.leap.mini.interactor.model.entity;

import java.io.Serializable;

import com.leap.mini.util.IsEmpty;

/**
 * <p>
 * </> Created by weiyaling on 2017/3/7.
 */

public class BRegion implements Serializable {

  private String id;
  private String text;

  public BRegion() {
    super();
  }

  public BRegion(String id, String text) {
    super();
    this.id = id;
    this.text = text;
  }

  public String getId() {
    if (IsEmpty.string(id)) {
      return "";
    }
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getText() {
    if (IsEmpty.string(text)) {
      return "";
    }
    return text;
  }

  public void setText(String text) {
    this.text = text;
  }

}
