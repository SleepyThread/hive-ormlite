package com.sleepythread.columnparser;


import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.ParseException;

public class BigDecimalParser implements HiveColumnParser<BigDecimal> {
  @Override
  public BigDecimal parseColumn(String columnString) {
    BigDecimal bigDecimal = null;
    DecimalFormat decimalFormat = new DecimalFormat();
    decimalFormat.setParseBigDecimal(true);
    try {
      bigDecimal = (BigDecimal) decimalFormat.parse(columnString);
    }catch (Exception ex){}
    return bigDecimal;
  }
}