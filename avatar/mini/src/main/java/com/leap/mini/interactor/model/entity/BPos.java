/**
 * 版权所有(C)，上海海鼎信息工程股份有限公司，2016，所有权利保留。
 * 
 * 项目名：	dpos-web
 * 文件名：	BPos.java
 * 模块说明：	
 * 修改历史：
 * 2016年6月22日 - linzhu - 创建。
 */
package com.leap.mini.interactor.model.entity;

/**
 * <p>
 * </> Created by weiyaling on 2017/3/7.
 */

public class BPos extends BStandardEntity {

  private static final long serialVersionUID = -8342318753657096168L;

  private String shop;
  private String machineCode;
  private int posNo;
  private boolean printSaleTicket = false;
  private boolean printSaleTag = false;
  private String saleTagPrinter;
  private boolean openCashDrawer = false;
  private String cashDrawerCmd;
  private String printServerAddress;

  public String getPrintServerAddress() {
    return printServerAddress;
  }

  public void setPrintServerAddress(String printServerAddress) {
    this.printServerAddress = printServerAddress;
  }

  public String getShop() {
    return shop;
  }

  public void setShop(String shop) {
    this.shop = shop;
  }

  public String getMachineCode() {
    return machineCode;
  }

  public void setMachineCode(String machineCode) {
    this.machineCode = machineCode;
  }

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

  public boolean isPrintSaleTag() {
    return printSaleTag;
  }

  public void setPrintSaleTag(boolean printSaleTag) {
    this.printSaleTag = printSaleTag;
  }

  public String getSaleTagPrinter() {
    return saleTagPrinter;
  }

  public void setSaleTagPrinter(String saleTagPrinter) {
    this.saleTagPrinter = saleTagPrinter;
  }

}
