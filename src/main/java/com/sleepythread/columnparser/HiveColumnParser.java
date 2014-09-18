package com.sleepythread.columnparser;


public interface HiveColumnParser<T> {

  public T parseColumn(String columnString);
}
