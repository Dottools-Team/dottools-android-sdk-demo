package com.lem.nicetools.baasdemo.bean;

public class InfoContent {
  private String title;
  private String content;

  public InfoContent() {
  }

  public InfoContent(String title, String content) {
    this.title = title;
    this.content = content;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  @Override public String toString() {
    return "标题：" + title + '\n' + "内容" + content + '\n';
  }
}
