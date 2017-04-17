package com.leap.avatar.model.shop;

import com.leap.avatar.model.entity.BRegion;

/**
 * <p>
 * </> Created by weiyaling on 2017/3/7.
 */

public class BShopAddress {

  private BRegion province;
  private BRegion city;
  private BRegion district;
  private String street;
  private String zipcode;

  public BRegion getProvince() {
    return province;
  }

  public void setProvince(BRegion province) {
    this.province = province;
  }

  public BRegion getCity() {
    return city;
  }

  public void setCity(BRegion city) {
    this.city = city;
  }

  public BRegion getDistrict() {
    return district;
  }

  public void setDistrict(BRegion district) {
    this.district = district;
  }

  public String getStreet() {
    return street;
  }

  public void setStreet(String street) {
    this.street = street;
  }

  public String getZipcode() {
    return zipcode;
  }

  public void setZipcode(String zipcode) {
    this.zipcode = zipcode;
  }

  @Override
  public String toString() {
    return province.getText() + city.getText() + district.getText() + street
        + (zipcode == null ? "" : " " + zipcode);
  }

}
