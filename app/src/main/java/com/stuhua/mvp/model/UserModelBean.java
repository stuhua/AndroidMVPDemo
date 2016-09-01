package com.stuhua.mvp.model;

/**
 * Created by llh on 2016/9/1.
 * 一个用户对象，有姓名，年龄，性别
 */
public class UserModelBean {
  String name;
  String age;
  String sex;

  public UserModelBean(String name, String age, String sex) {
    this.name = name;
    this.age = age;
    this.sex = sex;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getAge() {
    return age;
  }

  public void setAge(String age) {
    this.age = age;
  }

  public String getSex() {
    return sex;
  }

  public void setSex(String sex) {
    this.sex = sex;
  }


}
