package com.sleepythread.columnparser;


public class HiveIntegerParser implements HiveColumnParser<Integer> {
  @Override
  public Integer parseColumn(String columnString) {
    Integer integer = null;
    try {
      integer = Integer.parseInt(columnString);
    }catch (NumberFormatException e){

    }
    return integer;
  }

}
