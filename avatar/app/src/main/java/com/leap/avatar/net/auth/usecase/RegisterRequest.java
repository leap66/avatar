package com.leap.avatar.net.auth.usecase;

/**
 * 注册参数实体类
 * <p>
 * </> Created by weiyaling on 17/3/7.
 */

public class RegisterRequest {
  private String id;
  private String mobile;
  private String name;
  private String idCard;
  private String password;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getMobile() {
    return mobile;
  }

  public void setMobile(String mobile) {
    this.mobile = mobile;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getIdCard() {
    return idCard;
  }

  public void setIdCard(String idCard) {
    this.idCard = idCard;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }
}
