package com.sleepythread;

import com.sleepythread.columnparser.HiveColumnParser;
import org.apache.hadoop.io.Text;

import java.lang.reflect.Field;
import java.util.*;


public class HiveRecordToObjectFactory<T> {

  private final Text record;
  private final String delimeter;
  private HashMap<String, Integer> hivePositionToColumnMap;
  private List<Field> instanceVariables;
  private Map<String,Class<?>> columnToTypeMap;

  public HiveRecordToObjectFactory(ObjectTableInfo objectTableInfo, HiveTable hiveTable, Text record) {
    this.record = record;
    this.delimeter = hiveTable.getDelimeter();
    columnToTypeMap = hiveTable.getColumnToTypeMap();
    instanceVariables = objectTableInfo.getFieldList();
    hivePositionToColumnMap = new HashMap<String, Integer>();
    for(Field variable: instanceVariables){
      String variableName = variable.getName().toLowerCase();
      Integer position = hiveTable.getColumnToPositionMap().get(variableName);
      hivePositionToColumnMap.put(variableName, position);
    }
  }

  public T getObject(T t) throws IllegalAccessException {

    List<String> split = Arrays.asList(record.toString().split(delimeter));

    for(Field varible : instanceVariables){
      String varibleName = varible.getName().toLowerCase();
      Integer position = hivePositionToColumnMap.get(varibleName);
      Class<?> typeClass = columnToTypeMap.get(varibleName);
      HiveColumnParser parser = new HiveColumnParserFactory().getParser(typeClass);
      Object o = null;
      if(position != null)
        parser.parseColumn(split.get(position));
      varible.setAccessible(true);
      varible.set(t, o);
    }
    return t;
  }
}
