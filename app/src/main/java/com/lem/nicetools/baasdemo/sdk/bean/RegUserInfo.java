package com.lem.nicetools.baasdemo.sdk.bean;

public class RegUserInfo {

  private Integer type;
  private String username;
  private String email;
  private String phone;
  private Integer role;
  private String passwd;

  public RegUserInfo() {
  }

  public RegUserInfo(Integer type, String username, String email, String phone,
      Integer role, String passwd) {
    this.type = type;
    this.username = username;
    this.email = email;
    this.phone = phone;
    this.role = role;
    this.passwd = passwd;
  }

  public Integer getType() {
    return type;
  }

  public void setType(Integer type) {
    this.type = type;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public Integer getRole() {
    return role;
  }

  public void setRole(Integer role) {
    this.role = role;
  }

  public String getPasswd() {
    return passwd;
  }

  public void setPasswd(String passwd) {
    this.passwd = passwd;
  }

  @Override public String toString() {
    return "RegUserInfo{" +
        "type=" + type +
        ", username='" + username + '\'' +
        ", email='" + email + '\'' +
        ", phone='" + phone + '\'' +
        ", role=" + role +
        ", passwd='" + passwd + '\'' +
        '}';
  }
}
