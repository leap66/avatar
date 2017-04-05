package com.leap.mini.interactor.model.user;

import com.leap.mini.interactor.model.entity.BStandardEntity;

/**
 * <p>
 * </> Created by weiyaling on 2017/3/7.
 */

public class BUserPayChannel extends BStandardEntity {
  private static final long serialVersionUID = 6658592066707928737L;

  private String user;
  private String payment;
  private String channel;
  private String payConfig;
  private String materialConfig;
  private boolean open;

  private String worksheetState;
  private String worksheetNum;

  private boolean agent;

  public String getPayment() {
    return payment;
  }

  public void setPayment(String payment) {
    this.payment = payment;
  }

  public String getChannel() {
    return channel;
  }

  public void setChannel(String channel) {
    this.channel = channel;
  }

  public String getMaterialConfig() {
    return materialConfig;
  }

  public void setMaterialConfig(String materialConfig) {
    this.materialConfig = materialConfig;
  }

  public boolean isOpen() {
    return open;
  }

  public void setOpen(boolean open) {
    this.open = open;
  }

  public String getUser() {
    return user;
  }

  public void setUser(String user) {
    this.user = user;
  }

  public String getPayConfig() {
    return payConfig;
  }

  public void setPayConfig(String payConfig) {
    this.payConfig = payConfig;
  }

  public String getWorksheetState() {
    return worksheetState;
  }

  public void setWorksheetState(String worksheetState) {
    this.worksheetState = worksheetState;
  }

  public String getWorksheetNum() {
    return worksheetNum;
  }

  public void setWorksheetNum(String worksheetNum) {
    this.worksheetNum = worksheetNum;
  }

  public boolean isAgent() {
    return agent;
  }

  public void setAgent(boolean agent) {
    this.agent = agent;
  }

}
