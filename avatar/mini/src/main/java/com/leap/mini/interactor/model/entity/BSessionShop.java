package com.leap.mini.interactor.model.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * <p>
 * </> Created by weiyaling on 2017/3/7.
 */

public class BSessionShop implements Serializable {

  private static final long serialVersionUID = -1229184913897748367L;
  private String id;
  private String name;
  private String shortName;
  private String address;
  private String domain;
  private String owner;
  private String printSaleTpl;
  private String saleTagTpl;
  private String businessType;
  private Integer changesMode; // 0.不抹零 | 1.抹去分 | 2.抹去角
  private Set<String> onlinePayments = new HashSet<String>();

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
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

  public Set<String> getOnlinePayments() {
    return onlinePayments;
  }

  public void setOnlinePayments(Set<String> onlinePayments) {
    this.onlinePayments = onlinePayments;
  }

  public String getPrintSaleTpl() {
    return printSaleTpl;
  }

  public void setPrintSaleTpl(String printSaleTpl) {
    this.printSaleTpl = printSaleTpl;
  }

  public String getSaleTagTpl() {
    return saleTagTpl;
  }

  public void setSaleTagTpl(String saleTagTpl) {
    this.saleTagTpl = saleTagTpl;
  }

  public Integer getChangesMode() {
    return changesMode;
  }

  public void setChangesMode(Integer changesMode) {
    this.changesMode = changesMode;
  }

  public static long getSerialVersionUID() {
    return serialVersionUID;
  }

  public String getBusinessType() {
    return businessType;
  }

  public void setBusinessType(String businessType) {
    this.businessType = businessType;
  }
}
