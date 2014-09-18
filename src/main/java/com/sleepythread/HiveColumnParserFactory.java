package com.sleepythread;


import com.sleepythread.columnparser.HiveColumnParser;
import com.sleepythread.columnparser.HiveIntegerParser;
import com.sleepythread.columnparser.HiveStringParser;

import java.util.HashMap;

public class HiveColumnParserFactory {

  private HashMap<Class<?>, HiveColumnParser> classHiveColumnParserHashMap;

  public HiveColumnParserFactory() {
    classHiveColumnParserHashMap = new HashMap<Class<?>, HiveColumnParser>();
    classHiveColumnParserHashMap.put(String.class, new HiveStringParser());
    classHiveColumnParserHashMap.put(Integer.class,new HiveIntegerParser());
  }

  public HiveColumnParser getParser(Class<?> classType) {
    return classHiveColumnParserHashMap.get(classType);
  }
}
