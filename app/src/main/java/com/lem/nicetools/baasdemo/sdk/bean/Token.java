package com.lem.nicetools.baasdemo.sdk.bean;

public class Token {
  private String id;
  private String token;
  private String refreshToken;
  private String expiration;

  public Token() {
  }

  public Token(String id, String token, String refreshToken, String expiration) {
    this.id = id;
    this.token = token;
    this.refreshToken = refreshToken;
    this.expiration = expiration;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getToken() {
    return token;
  }

  public void setToken(String token) {
    this.token = token;
  }

  public String getRefreshToken() {
    return refreshToken;
  }

  public void setRefreshToken(String refreshToken) {
    this.refreshToken = refreshToken;
  }

  public String getExpiration() {
    return expiration;
  }

  public void setExpiration(String expiration) {
    this.expiration = expiration;
  }

  @Override public String toString() {
    return "Token{" +
        "id='" + id + '\'' +
        ", token='" + token + '\'' +
        ", refreshToken='" + refreshToken + '\'' +
        ", expiration='" + expiration + '\'' +
        '}';
  }
}
