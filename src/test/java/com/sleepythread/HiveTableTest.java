package com.sleepythread;


import org.apache.hadoop.hive.metastore.api.FieldSchema;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Map;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class HiveTableTest {

  ArrayList<FieldSchema> fields;

  @Before
  public void setup() {
    fields = new ArrayList<FieldSchema>();
    fields.add(new FieldSchema("name", "string", null));
    fields.add(new FieldSchema("phoneNo", "string", null));
    fields.add(new FieldSchema("age", "int", null));
  }

  @Test
  public void shouldInstantiateTableColumnToPositionMapper(){
    HiveTable hiveTable = new HiveTable("test", "student", fields,new HiveTypeToJavaTypeMapper(), ",");

    Map<String,Integer> columnToPositionMap = hiveTable.getColumnToPositionMap();
    assertThat(columnToPositionMap.size(),is(3));
    assertThat(columnToPositionMap.get("name"),is(0));
    assertThat(columnToPositionMap.get("phoneNo"),is(1));
    assertThat(columnToPositionMap.get("age"),is(2));

  }

  @Test
  public void shouldInstantiateTableColumnToTypeMapper(){
    HiveTable hiveTable = new HiveTable("test", "student", fields,new HiveTypeToJavaTypeMapper(), ",");

    Map<String, Class<?>> columnToTypeMap = hiveTable.getColumnToTypeMap();

    assertThat(columnToTypeMap.size(),is(3));
    assertThat(columnToTypeMap.get("name").getName(),is("java.lang.String"));
    assertThat(columnToTypeMap.get("phoneNo").getName(),is("java.lang.String"));
    assertThat(columnToTypeMap.get("age").getName(),is("java.lang.Integer"));

  }
}
