package com.sleepythread;

import java.util.HashMap;
import java.util.Map;

public class HiveTypeToJavaTypeMapper {

  private Map<String,Class<?>> map;

  public HiveTypeToJavaTypeMapper(){
    map = new HashMap<String, Class<?>>();
    map.put("string",String.class);
    map.put("int",Integer.class);
  }

  public Class<?> getJavaType(String typeName){
    return map.get(typeName);
  }

}
