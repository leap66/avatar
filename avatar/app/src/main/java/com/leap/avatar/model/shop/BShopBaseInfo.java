package com.leap.avatar.model.shop;

import java.io.Serializable;

import com.leap.avatar.model.entity.BEntity;

/**
 * <p>
 * </> Created by weiyaling on 2017/3/7.
 */

public class BShopBaseInfo extends BEntity implements Serializable {

  private String name;
  private String shortName;
  private String address;
  private String licenseNo;
  private boolean enabled;

  private String domain;

  private String owner;
  private String ownerName;
  private String ownerMobile;

  // 是否是邀请返回的店铺信息，还是正常的店铺信息
  private boolean isInvite;

  public BShopBaseInfo() {
  }

  public BShopBaseInfo(String name, String owner, boolean isInvite) {
    this.name = name;
    this.owner = owner;
    this.isInvite = isInvite;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getShortName() {
    return shortName;
  }

  public void setShortName(String shortName) {
    this.shortName = shortName;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public String getLicenseNo() {
    return licenseNo;
  }

  public void setLicenseNo(String licenseNo) {
    this.licenseNo = licenseNo;
  }

  public String getDomain() {
    return domain;
  }

  public void setDomain(String domain) {
    this.domain = domain;
  }

  public String getOwner() {
    return owner;
  }

  public void setOwner(String owner) {
    this.owner = owner;
  }

  public String getOwnerName() {
    return ownerName;
  }

  public void setOwnerName(String ownerName) {
    this.ownerName = ownerName;
  }

  public String getOwnerMobile() {
    return ownerMobile;
  }

  public void setOwnerMobile(String ownerMobile) {
    this.ownerMobile = ownerMobile;
  }

  public boolean getEnabled() {
    return enabled;
  }

  public void setEnabled(boolean enabled) {
    this.enabled = enabled;
  }

  public boolean isEnabled() {
    return enabled;
  }

  public boolean isInvite() {
    return isInvite;
  }

  public void setInvite(boolean invite) {
    isInvite = invite;
  }

}
