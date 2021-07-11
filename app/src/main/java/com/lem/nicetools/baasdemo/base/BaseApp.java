package com.lem.nicetools.baasdemo.base;

import android.app.Application;
import com.lem.nicetools.baasdemo.sdk.SDK;

public class BaseApp extends Application {

  public static String UserToken = null;

  @Override public void onCreate() {
    super.onCreate();
    SDK.init("fa169ffc7543ad5f3661e77830d3f3b4oVM", "4c8d566d58659cfdf3a9d2ea8cba0980");
  }
}
