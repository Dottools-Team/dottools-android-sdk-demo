package com.lem.nicetools.baasdemo.sdk.bean;

public class SimpleConfig {
  private String title;
  private String msg;

  public SimpleConfig() {
  }

  public SimpleConfig(String title, String msg) {
    this.title = title;
    this.msg = msg;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getMsg() {
    return msg;
  }

  public void setMsg(String msg) {
    this.msg = msg;
  }

  @Override public String toString() {
    return "SimpleConfig{" +
        "title='" + title + '\'' +
        ", msg='" + msg + '\'' +
        '}';
  }
}
