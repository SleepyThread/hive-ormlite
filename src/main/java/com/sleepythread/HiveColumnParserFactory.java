package com.sleepythread;


import com.sleepythread.columnparser.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;

public class HiveColumnParserFactory {

  private HashMap<Class<?>, HiveColumnParser> classHiveColumnParserHashMap;

  public HiveColumnParserFactory() {
    classHiveColumnParserHashMap = new HashMap<Class<?>, HiveColumnParser>();
    classHiveColumnParserHashMap.put(String.class, new HiveStringParser());
    classHiveColumnParserHashMap.put(Integer.class,new HiveIntegerParser());
    classHiveColumnParserHashMap.put(Double.class,new HiveDoubleParser());
    classHiveColumnParserHashMap.put(Date.class,new DateParser());
    classHiveColumnParserHashMap.put(BigDecimal.class,new BigDecimalParser());
    classHiveColumnParserHashMap.put(Float.class,new HiveFloatParser());
    classHiveColumnParserHashMap.put(TimeStampParser.class,new TimeStampParser());
    classHiveColumnParserHashMap.put(Boolean.class,new BooleanParser());
  }

  public HiveColumnParser getParser(Class<?> classType) {
    return classHiveColumnParserHashMap.get(classType);
  }
}
