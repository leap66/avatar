/**
 * 版权所有(C)，上海海鼎信息工程股份有限公司，2016，所有权利保留。
 * 
 * 项目名：	dpos-web
 * 文件名：	BSessionPosCfg.java
 * 模块说明：	
 * 修改历史：
 * 2016年8月17日 - liuxinlei - 创建。
 */
package com.leap.avatar.model.entity;

import java.io.Serializable;

/**
 * <p>
 * </> Created by weiyaling on 2017/3/7.
 */

public class BSessionPosCfg implements Serializable {

  private static final long serialVersionUID = 5121581789324771086L;

  private int posNo;
  private boolean printSaleTicket = false;
  private boolean printSaleTag = false;
  private String saleTagPrinter;
  private boolean openCashDrawer = false;
  private String cashDrawerCmd;
  private String machineCode;
  private String printServerAddress;

  public int getPosNo() {
    return posNo;
  }

  public void setPosNo(int posNo) {
    this.posNo = posNo;
  }

  public boolean isPrintSaleTicket() {
    return printSaleTicket;
  }

  public void setPrintSaleTicket(boolean printSaleTicket) {
    this.printSaleTicket = printSaleTicket;
  }

  public boolean isPrintSaleTag() {
    return printSaleTag;
  }

  public void setPrintSaleTag(boolean printSaleTag) {
    this.printSaleTag = printSaleTag;
  }

  public boolean isOpenCashDrawer() {
    return openCashDrawer;
  }

  public void setOpenCashDrawer(boolean openCashDrawer) {
    this.openCashDrawer = openCashDrawer;
  }

  public String getCashDrawerCmd() {
    return cashDrawerCmd;
  }

  public void setCashDrawerCmd(String cashDrawerCmd) {
    this.cashDrawerCmd = cashDrawerCmd;
  }

  public String getMachineCode() {
    return machineCode;
  }

  public void setMachineCode(String machineCode) {
    this.machineCode = machineCode;
  }

  public String getSaleTagPrinter() {
    return saleTagPrinter;
  }

  public void setSaleTagPrinter(String saleTagPrinter) {
    this.saleTagPrinter = saleTagPrinter;
  }

  public String getPrintServerAddress() {
    return printServerAddress;
  }

  public void setPrintServerAddress(String printServerAddress) {
    this.printServerAddress = printServerAddress;
  }
}
