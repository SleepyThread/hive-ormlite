package com.sleepythread.columnparser;


public class HiveFloatParser implements HiveColumnParser<Float> {
  @Override
  public Float parseColumn(String columnString) {
    Float number = null;
    try {
      number = Float.parseFloat(columnString);
    }catch (NumberFormatException e){}
    return number;
  }
}
