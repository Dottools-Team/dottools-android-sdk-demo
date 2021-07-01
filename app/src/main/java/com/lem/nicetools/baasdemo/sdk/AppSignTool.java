package com.lem.nicetools.baasdemo.sdk;

import com.lem.nicetools.baasdemo.sdk.encrption.MD5;

public class AppSignTool {

  public static String getAppSign(String secret) {
    return MD5.encryptHex(secret);
  }
}

