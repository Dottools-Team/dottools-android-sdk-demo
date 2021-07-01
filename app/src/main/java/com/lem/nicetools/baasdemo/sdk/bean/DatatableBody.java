package com.lem.nicetools.baasdemo.sdk.bean;

public class DatatableBody<T> {
  private String key;
  private T data;

  public DatatableBody() {
  }

  public DatatableBody(String key, T data) {
    this.key = key;
    this.data = data;
  }

  public String getKey() {
    return key;
  }

  public void setKey(String key) {
    this.key = key;
  }

  public T getData() {
    return data;
  }

  public void setData(T data) {
    this.data = data;
  }

  @Override public String toString() {
    return "DataBody{" +
        "key='" + key + '\'' +
        ", data=" + data +
        '}';
  }
}
