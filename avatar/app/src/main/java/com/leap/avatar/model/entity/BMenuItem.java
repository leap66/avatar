package com.leap.avatar.model.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * </> Created by weiyaling on 2017/3/7.
 */

public class BMenuItem {
  private String text;
  private String action;
  private String view;
  private boolean leaf;
  private String iconCls;
  private String permission;

  private List<BMenuItem> children = new ArrayList<BMenuItem>();

  public String getText() {
    return text;
  }

  public void setText(String text) {
    this.text = text;
  }

  public String getAction() {
    return action;
  }

  public void setAction(String action) {
    this.action = action;
  }

  public String getView() {
    return view;
  }

  public void setView(String view) {
    this.view = view;
  }

  public boolean isLeaf() {
    return leaf;
  }

  public void setLeaf(boolean leaf) {
    this.leaf = leaf;
  }

  public String getIconCls() {
    return iconCls;
  }

  public void setIconCls(String iconCls) {
    this.iconCls = iconCls;
  }

  public String getPermission() {
    return permission;
  }

  public void setPermission(String permission) {
    this.permission = permission;
  }

  public List<BMenuItem> getChildren() {
    return children;
  }

  public void setChildren(List<BMenuItem> children) {
    this.children = children;
  }
}
