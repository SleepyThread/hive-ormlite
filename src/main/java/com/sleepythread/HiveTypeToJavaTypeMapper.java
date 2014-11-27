package com.sleepythread;

import org.datanucleus.store.types.backed.ArrayList;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HiveTypeToJavaTypeMapper {

  private Map<HiveDataType,Class<?>> map;

  private enum HiveDataType{
    HIVEINT("int"),
    TINYINT("tinyint"),
    BIGINT("bigint"),
    HIVEFLOAT("float"),
    HIVEDOUBLE("double"),
    DECIMAL("decimal"),
    TIMESTAMP("timestamp"),
    DATE("date"),
    HIVESTRING("string"),
    VARCHAR("varchar"),
    CHAR("char"),
    BOOLEAN("boolean"),
    BINARY("binary"),
    MAP("map"),
    STRUCT("struct"),
    UNIONTYPE("uniontype"),
    ARRAY("array");

    private String typeName;

    HiveDataType(String typeName){
      this.typeName = typeName;
    }

    public static HiveDataType fromString(String text) {
      if (text != null) {
        for (HiveDataType b : HiveDataType.values()) {
          if (text.equalsIgnoreCase(b.typeName)) {
            return b;
          }
        }
      }
      return null;
    }

  }

  public HiveTypeToJavaTypeMapper(){
    map = new HashMap<HiveDataType, Class<?>>();
    map.put(HiveDataType.HIVEINT,Integer.class);
    map.put(HiveDataType.TINYINT,Integer.class);
    map.put(HiveDataType.BIGINT,Integer.class);
    map.put(HiveDataType.HIVEFLOAT,Float.class);
    map.put(HiveDataType.HIVEDOUBLE,Double.class);
    map.put(HiveDataType.DECIMAL,BigDecimal.class);
    map.put(HiveDataType.TIMESTAMP,Timestamp.class);
    map.put(HiveDataType.DATE,Date.class);
    map.put(HiveDataType.HIVESTRING,String.class);
    map.put(HiveDataType.VARCHAR,String.class);
    map.put(HiveDataType.CHAR,String.class);
    map.put(HiveDataType.BOOLEAN,Boolean.class);
    map.put(HiveDataType.BINARY,Boolean.class);
    map.put(HiveDataType.MAP,String.class);
    map.put(HiveDataType.STRUCT,String.class);
    map.put(HiveDataType.UNIONTYPE,String.class);
    map.put(HiveDataType.ARRAY,String.class);

  }

  public Class<?> getJavaType(String typeName){
    if(isComplexType(typeName)){
       typeName = HiveDataType.HIVESTRING.typeName;
    }
    return map.get(HiveDataType.fromString(typeName));
  }

  private boolean isComplexType(String typeName) {
    List<HiveDataType> hiveComplexDataTypes = new java.util.ArrayList<HiveDataType>();
    hiveComplexDataTypes.add(HiveDataType.MAP);
    hiveComplexDataTypes.add(HiveDataType.STRUCT);
    hiveComplexDataTypes.add(HiveDataType.UNIONTYPE);
    hiveComplexDataTypes.add(HiveDataType.ARRAY);

    for(HiveDataType type : hiveComplexDataTypes)
      if(typeName.matches("^"+type.typeName+"<.*>"))
        return true;

    return false;
  }

}
