package com.sleepythread;


import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class ObjectTableInfo {

  private String databaseName;
  private String tableName;
  private Map<String, Class<?>> instanceVariableToTypeMap;

  public <T> ObjectTableInfo(Class<T> className) {
    HiveDbConfig annotation = className.getAnnotation(HiveDbConfig.class);
    databaseName = annotation.db();
    tableName = annotation.table();
    instanceVariableToTypeMap = new HashMap<String, Class<?>>();
    Field[] fields = className.getDeclaredFields();
    for(Field field: fields){
      String name = field.getName();
      Class<?> type = field.getType();
      instanceVariableToTypeMap.put(name,type);
    }
  }

  public String getDatabaseName() {
    return databaseName;
  }

  public String getTableName() {
    return tableName;
  }

  public Map<String, Class<?>> getInstanceVariableToTypeMap() {
    return instanceVariableToTypeMap;
  }
}
