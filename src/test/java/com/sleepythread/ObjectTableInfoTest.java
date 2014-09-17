package com.sleepythread;

import com.sleepythread.sample.Student;
import org.junit.Test;


import java.util.Map;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;


public class ObjectTableInfoTest {

  @Test
  public void shouldInstantiateTheValueOfDbAndTableName(){
    Class<Student> studentClass = Student.class;
    ObjectTableInfo objectTableInfo = new ObjectTableInfo(studentClass);

    assertThat(objectTableInfo.getDatabaseName(),is("test"));
    assertThat(objectTableInfo.getTableName(),is("student"));

  }

  @Test
  public void shouldInstantiateVariableToTypeMapping(){
    Class<Student> studentClass = Student.class;
    ObjectTableInfo objectTableInfo = new ObjectTableInfo(studentClass);

    Map<String,Class<?>> instanceVariableToTypeMap = objectTableInfo.getInstanceVariableToTypeMap();

    assertThat(instanceVariableToTypeMap.size(),is(3));

    assert(instanceVariableToTypeMap.keySet().contains("name"));
    assert(instanceVariableToTypeMap.keySet().contains("phoneNo"));
    assert(instanceVariableToTypeMap.keySet().contains("age"));

    assertThat(instanceVariableToTypeMap.get("name").getName(),is("java.lang.String"));
    assertThat(instanceVariableToTypeMap.get("phoneNo").getName(),is("java.lang.String"));
    assertThat(instanceVariableToTypeMap.get("age").getName(),is("int"));

  }
}
