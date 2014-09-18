package com.sleepythread;

import com.sleepythread.sample.Student;
import org.junit.Test;


import java.lang.reflect.Field;
import java.util.*;

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

    List<Field> fieldList = objectTableInfo.getFieldList();
    HashMap<String, Class<?>> stringClassHashMap = new HashMap<String, Class<?>>();

    for(Field field : fieldList){
      stringClassHashMap.put(field.getName(),field.getType());
    }

    Set<String> instanceVariables = stringClassHashMap.keySet();

    assertThat(instanceVariables.size(),is(3));

    assert(instanceVariables.contains("name"));
    assert(instanceVariables.contains("phoneNo"));
    assert(instanceVariables.contains("age"));

    assertThat(stringClassHashMap.get("name").getName(),is("java.lang.String"));
    assertThat(stringClassHashMap.get("phoneNo").getName(),is("java.lang.String"));
    assertThat(stringClassHashMap.get("age").getName(), is("int"));

  }
}
