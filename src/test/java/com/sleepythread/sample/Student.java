package com.sleepythread.sample;


import com.sleepythread.HiveDbConfig;

@HiveDbConfig(db="test",table ="student")
public class Student {

  private String name;
  private String phoneNo;
  private int age;

  public Student(String name, String phoneNo, int age) {
    this.name = name;
    this.phoneNo = phoneNo;
    this.age = age;
  }

  public String getName() {
    return name;
  }

  public String getPhoneNo() {
    return phoneNo;
  }

  public int getAge() {
    return age;
  }
}

