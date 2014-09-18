package com.sleepythread;


import org.apache.hadoop.hive.metastore.api.FieldSchema;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HiveTable {

  private final String databaseName;
  private final String tableName;
  private final String delimeter;
  private Map<String, Integer> columnToPositionMap;
  private Map<String, Class<?>> columnToTypeMapper;


  public HiveTable(String databaseName, String tableName, List<FieldSchema> fields, HiveTypeToJavaTypeMapper typeMapper, String delimeter) {

    this.databaseName = databaseName;
    this.tableName = tableName;
    this.delimeter = delimeter;
    columnToPositionMap = new HashMap<String, Integer>();
    columnToTypeMapper = new HashMap<String, Class<?>>();
    for(int i = 0; i < fields.size(); i++){
      FieldSchema fieldSchema = fields.get(i);
      String type = fieldSchema.getType();
      String name = fieldSchema.getName().toLowerCase();
      columnToPositionMap.put(name,i);
      columnToTypeMapper.put(name, typeMapper.getJavaType(type));

    }
  }

  public Map<String, Integer> getColumnToPositionMap() {
    return columnToPositionMap;
  }

  public Map<String, Class<?>> getColumnToTypeMap() {
    return columnToTypeMapper;
  }

  public String getDelimeter() {
    return delimeter;
  }
}
