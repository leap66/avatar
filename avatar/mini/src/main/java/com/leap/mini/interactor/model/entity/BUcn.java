package com.leap.mini.interactor.model.entity;

import java.io.Serializable;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * <p>
 * </> Created by weiyaling on 2017/3/7.
 */

public class BUcn implements Parcelable, Serializable {
  private String id;
  private String code;
  private String name;
  private boolean newUcn;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public boolean isNewUcn() {
    return newUcn;
  }

  public void setNewUcn(boolean newUcn) {
    this.newUcn = newUcn;
  }

  @Override
  public int describeContents() {
    return 0;
  }

  @Override
  public void writeToParcel(Parcel dest, int flags) {
    dest.writeString(this.id);
    dest.writeString(this.code);
    dest.writeString(this.name);
    dest.writeByte(this.newUcn ? (byte) 1 : (byte) 0);
  }

  public BUcn() {
  }

  public BUcn(String id, String code, String name, boolean newUcn) {
    this.id = id;
    this.code = code;
    this.name = name;
    this.newUcn = newUcn;
  }

  protected BUcn(Parcel in) {
    this.id = in.readString();
    this.code = in.readString();
    this.name = in.readString();
    this.newUcn = in.readByte() != 0;
  }

  public static final Creator<BUcn> CREATOR = new Creator<BUcn>() {
    @Override
    public BUcn createFromParcel(Parcel source) {
      return new BUcn(source);
    }

    @Override
    public BUcn[] newArray(int size) {
      return new BUcn[size];
    }
  };

}
