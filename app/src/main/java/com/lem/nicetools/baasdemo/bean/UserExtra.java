package com.lem.nicetools.baasdemo.bean;

public class UserExtra {
  private int score;
  private int money;
  private String tag;

  public UserExtra() {
  }

  public UserExtra(int score, int money, String tag) {
    this.score = score;
    this.money = money;
    this.tag = tag;
  }

  public int getScore() {
    return score;
  }

  public void setScore(int score) {
    this.score = score;
  }

  public int getMoney() {
    return money;
  }

  public void setMoney(int money) {
    this.money = money;
  }

  public String getTag() {
    return tag;
  }

  public void setTag(String tag) {
    this.tag = tag;
  }

  @Override public String toString() {
    return "UserExtra{" +
        "score=" + score +
        ", money=" + money +
        ", tag='" + tag + '\'' +
        '}';
  }
}
