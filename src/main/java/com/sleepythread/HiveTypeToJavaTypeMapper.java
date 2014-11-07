package com.sleepythread;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class HiveTypeToJavaTypeMapper {

  private Map<String,Class<?>> map;

  public HiveTypeToJavaTypeMapper(){
    map = new HashMap<String, Class<?>>();
    map.put("int",Integer.class);
    map.put("tinyint",Integer.class);
    map.put("bigint",Integer.class);
    map.put("float",Float.class);
    map.put("double",Double.class);
    map.put("decimal",BigDecimal.class);
    map.put("timestamp",Timestamp.class);
    map.put("date",Date.class);
    map.put("string",String.class);
    map.put("varchar",String.class);
    map.put("char",String.class);
    map.put("boolean",Boolean.class);
    map.put("binary",Boolean.class);
  }

  public Class<?> getJavaType(String typeName){
    return map.get(typeName);
  }

}
