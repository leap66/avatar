package com.leap.mini.interactor.model.user;

import java.io.Serializable;

import com.leap.mini.interactor.model.entity.BStandardEntity;

/**
 * <p>
 * </> Created by weiyaling on 2017/3/7.
 */

public class BUser extends BStandardEntity implements Serializable {

  private String mobile;
  private String name;
  private String idCard;
  private boolean enabled;
  private String recommendedBy;
  private String remark;
  private Integer education;
  private String secondLastLoginTime;
  private String secondLastLoginIp;
  private BUserPhoto userPhoto;

  private String verifiedFields;

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

  public boolean isEnabled() {
    return enabled;
  }

  public void setEnabled(boolean enabled) {
    this.enabled = enabled;
  }

  public String getRecommendedBy() {
    return recommendedBy;
  }

  public void setRecommendedBy(String recommendedBy) {
    this.recommendedBy = recommendedBy;
  }

  public String getRemark() {
    return remark;
  }

  public void setRemark(String remark) {
    this.remark = remark;
  }

  public Integer getEducation() {
    return education;
  }

  public void setEducation(Integer education) {
    this.education = education;
  }

  public String getSecondLastLoginTime() {
    return secondLastLoginTime;
  }

  public void setSecondLastLoginTime(String secondLastLoginTime) {
    this.secondLastLoginTime = secondLastLoginTime;
  }

  public String getSecondLastLoginIp() {
    return secondLastLoginIp;
  }

  public void setSecondLastLoginIp(String secondLastLoginIp) {
    this.secondLastLoginIp = secondLastLoginIp;
  }

  public BUserPhoto getUserPhoto() {
    return userPhoto;
  }

  public void setUserPhoto(BUserPhoto userPhoto) {
    this.userPhoto = userPhoto;
  }

  public String getVerifiedFields() {
    return verifiedFields;
  }

  public void setVerifiedFields(String verifiedFields) {
    this.verifiedFields = verifiedFields;
  }

}
