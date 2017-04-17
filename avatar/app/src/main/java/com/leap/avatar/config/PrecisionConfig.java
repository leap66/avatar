package com.leap.avatar.config;

/**
 * 精确参数配置
 * <p>
 * </>Created by weiyaling on 2017/3/9.
 */

public class PrecisionConfig {

  public static class User {
    public static int name = 16;
    public static int mobile = 11;
    public static int password = 16;
    public static int idCard = 18;
  }

  public static class Shop {
    public static int name = 30;
    public static int shortName = 8;
    public static int joinCode = 6;
    public static int licenseNo = 20;
    public static int businessType = 16;
    public static int addressStreet = 32;
  }
}
