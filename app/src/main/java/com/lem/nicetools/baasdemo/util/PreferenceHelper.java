/**
 *
 */
package com.lem.nicetools.baasdemo.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Base64;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.StreamCorruptedException;

public class PreferenceHelper {
  private static final String TAG = "PreferenceHelper";
  private static PreferenceHelper instance = null;

  SharedPreferences settings;

  private PreferenceHelper(Context context) {
    settings = context.getApplicationContext().getSharedPreferences(
        "settings", Context.MODE_PRIVATE);
  }

  public static PreferenceHelper getInstance(Context context) {
    if (instance == null) {
      instance = new PreferenceHelper(context);
    }
    return instance;
  }

  public void setIntValue(String flag, int value) {
    settings.edit().putInt(flag, value).commit();
  }

  public int getIntValue(String flag) {
    return settings.getInt(flag, 0);
  }

  public int getIntValue(String flag, int def) {
    return settings.getInt(flag, def);
  }

  public void setLongValue(String flag, long value) {
    settings.edit().putLong(flag, value).commit();
  }

  public long getLongValue(String flag) {
    return settings.getLong(flag, 0);
  }

  public void setFloatValue(String flag, float value) {
    settings.edit().putFloat(flag, value).commit();
  }

  public float getFloatValue(String flag) {
    return settings.getFloat(flag, 0);
  }

  public float getFloatValue(String flag, float defValue) {
    return settings.getFloat(flag, defValue);
  }

  public void setStringValue(String flag, String value) {
    settings.edit().putString(flag, value).commit();
  }

  public String getStringValue(String flag) {
    return settings.getString(flag, "").trim();
  }

  public <T> void setBeanValue(String flag, T value) {
    // 创建字节输出流
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    try {
      // 创建对象输出流，并封装字节流
      ObjectOutputStream oos = new ObjectOutputStream(baos);
      // 将对象写入字节流
      oos.writeObject(value);
      // 将字节流编码成base64的字符窜

      String str_Base64 = Base64.encodeToString(baos.toByteArray(), Base64.DEFAULT);
      //            KLog.e(str_Base64);
      setStringValue(flag, str_Base64);
    } catch (IOException e) {
      // TODO Auto-generated
    }

    //        KLog.d("成功");
  }

  public <T> T getBeanValue(String flag) {
    T t = null;
    String str_Base64 = getStringValue(flag);
    //读取字节
    //        KLog.e(str_Base64);
    byte[] base64 = Base64.decode(str_Base64, Base64.DEFAULT);
    //封装到字节流
    ByteArrayInputStream bais = new ByteArrayInputStream(base64);
    try {
      //再次封装
      ObjectInputStream bis = new ObjectInputStream(bais);
      try {
        //读取对象
        t = (T) bis.readObject();
      } catch (ClassNotFoundException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
    } catch (StreamCorruptedException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    return t;
  }

  public void setBooleanValue(String flag, Boolean value) {
    settings.edit().putBoolean(flag, value).commit();
  }

  public Boolean getBooleanValue(String flag) {
    return settings.getBoolean(flag, false);
  }

  public Boolean getBooleanValue(String flag, boolean defValue) {
    return settings.getBoolean(flag, defValue);
  }

  public void clear() {
    settings.edit().clear().commit();
  }
}
