package com.lem.nicetools.baasdemo.base;

import android.app.Application;
import com.lem.nicetools.baasdemo.sdk.SDK;

public class BaseApp extends Application {

  public static String UserToken = null;

  @Override public void onCreate() {
    super.onCreate();
    SDK.init("e59842aa2d0648cbdcb6809abf7eed1fOP0", "ffc02d1d6fb00a5ee86ae58f1eef4768");
  }
}
