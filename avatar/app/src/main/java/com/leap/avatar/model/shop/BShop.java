/**
 * 版权所有(C)，上海海鼎信息工程股份有限公司，2016，所有权利保留。
 * 
 * 项目名：	dpos-web
 * 文件名：	BShop.java
 * 模块说明：	
 * 修改历史：
 * 2016年5月21日 - linzhu - 创建。
 */
package com.leap.avatar.model.shop;

import java.util.ArrayList;
import java.util.List;

import com.leap.avatar.model.entity.BStandardEntity;
import com.leap.avatar.model.user.BUser;

/**
 * <p>
 * </> Created by weiyaling on 2017/3/7.
 */

public class BShop extends BStandardEntity {

  private static final long serialVersionUID = 4407191615271431762L;

  private String name;
  private String shortName;
  private String address;
  private BShopAddress shopAddress;
  private BUser owner;
  private String businessType;
  private boolean enabled; // 是否激活
  private String joinCode;
  private String licenseNo;
  private List<String> verifiedFields;
  private String domain; // 域地址

  // 营业执照照片
  private List<BShopImage> licenseImages = new ArrayList<BShopImage>();
  // 店招照片
  private List<BShopImage> bannerImages = new ArrayList<BShopImage>();

  private String tranId;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public BShopAddress getShopAddress() {
    return shopAddress;
  }

  public void setShopAddress(BShopAddress shopAddress) {
    this.shopAddress = shopAddress;
  }

  public String getBusinessType() {
    return businessType;
  }

  public void setBusinessType(String businessType) {
    this.businessType = businessType;
  }

  public BUser getOwner() {
    return owner;
  }

  public void setOwner(BUser owner) {
    this.owner = owner;
  }

  public boolean isEnabled() {
    return enabled;
  }

  public void setEnabled(boolean enabled) {
    this.enabled = enabled;
  }

  public String getJoinCode() {
    return joinCode;
  }

  public void setJoinCode(String joinCode) {
    this.joinCode = joinCode;
  }

  public String getDomain() {
    return domain;
  }

  public void setDomain(String domain) {
    this.domain = domain;
  }

  public String getShortName() {
    return shortName;
  }

  public void setShortName(String shortName) {
    this.shortName = shortName;
  }

  public String getLicenseNo() {
    return licenseNo;
  }

  public void setLicenseNo(String licenseNo) {
    this.licenseNo = licenseNo;
  }

  public List<String> getVerifiedFields() {
    return verifiedFields;
  }

  public void setVerifiedFields(List<String> verifiedFields) {
    this.verifiedFields = verifiedFields;
  }

  public List<BShopImage> getLicenseImages() {
    return licenseImages;
  }

  public void setLicenseImages(List<BShopImage> licenseImages) {
    this.licenseImages = licenseImages;
  }

  public List<BShopImage> getBannerImages() {
    return bannerImages;
  }

  public void setBannerImages(List<BShopImage> bannerImages) {
    this.bannerImages = bannerImages;
  }

  public String getTranId() {
    return tranId;
  }

  public void setTranId(String tranId) {
    this.tranId = tranId;
  }

}
