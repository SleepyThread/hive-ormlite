package com.sleepythread;


import org.apache.hadoop.io.Text;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import com.sleepythread.sample.Student;

public class HiveRecordToObjectFactoryTest {

  Answer<Class<?>> stringClass;
  Answer<Class<?>> integerClass;

  @Before
  public void setUp(){
    stringClass = new Answer<Class<?>>() {
      @Override
      public Class<?> answer(InvocationOnMock invocation) throws Throwable {
        return String.class;
      }
    };
    integerClass = new Answer<Class<?>>() {
      @Override
      public Class<?> answer(InvocationOnMock invocation) throws Throwable {
        return Integer.class;
      }
    };
  }

  @Test
  public void shouldParseTheGivenRecordAndAssociateItToVariableInClass() throws IllegalAccessException {
    ObjectTableInfo objectTableInfo = mock(ObjectTableInfo.class);
    Field[] declaredFields = Student.class.getDeclaredFields();
    List<Field> fields = Arrays.asList(declaredFields);

    when(objectTableInfo.getFieldList()).thenReturn(fields);

    Text record = new Text("Akash,7777,25");
    HiveTable hiveTable = mock(HiveTable.class);
    when(hiveTable.getDelimeter()).thenReturn(",");
    HashMap<String, Class<?>> nameToTypeMap = new HashMap<String, Class<?>>();
    nameToTypeMap.put("name", String.class);
    nameToTypeMap.put("phoneno", String.class);
    nameToTypeMap.put("age", Integer.class);
    HashMap<String, Integer> nameToPositionMap = new HashMap<String, Integer>();
    nameToPositionMap.put("name",0);
    nameToPositionMap.put("phoneno",1);
    nameToPositionMap.put("age",2);
    
    when(hiveTable.getColumnToTypeMap()).thenReturn(nameToTypeMap);
    when(hiveTable.getColumnToPositionMap()).thenReturn(nameToPositionMap);

    HiveRecordToObjectFactory<Student> factory = new HiveRecordToObjectFactory<Student>(objectTableInfo, hiveTable, record);


    Student student = new Student("Yello", "8888", 26);
    student = factory.getObject(student);

    assertThat(student.getName(),is("Akash"));
    assertThat(student.getPhoneNo(),is("7777"));
    assertThat(student.getAge(),is(25));
  }

  @Test
  public void shouldAssignNullValueToTheElementsIfSplitIsMissing() throws IllegalAccessException {
    ObjectTableInfo objectTableInfo = mock(ObjectTableInfo.class);
    Field[] declaredFields = Student.class.getDeclaredFields();
    List<Field> fields = Arrays.asList(declaredFields);

    when(objectTableInfo.getFieldList()).thenReturn(fields);

    Text record = new Text("Akash,7777");
    HiveTable hiveTable = mock(HiveTable.class);
    when(hiveTable.getDelimeter()).thenReturn(",");
    HashMap<String, Class<?>> nameToTypeMap = new HashMap<String, Class<?>>();
    nameToTypeMap.put("name", String.class);
    nameToTypeMap.put("phoneno", String.class);
    HashMap<String, Integer> nameToPositionMap = new HashMap<String, Integer>();
    nameToPositionMap.put("name",0);
    nameToPositionMap.put("phoneno",1);

    when(hiveTable.getColumnToTypeMap()).thenReturn(nameToTypeMap);
    when(hiveTable.getColumnToPositionMap()).thenReturn(nameToPositionMap);

    HiveRecordToObjectFactory<Student> factory = new HiveRecordToObjectFactory<Student>(objectTableInfo, hiveTable, record);


    Student student = new Student("Yello", "8888", 0);
    student = factory.getObject(student);

    assertThat(student.getName(),is("Akash"));
    assertThat(student.getPhoneNo(),is("7777"));
    assertNull(student.getAge());

  }


  @Test
  public void shouldAssignNullValueToTheElementsIfSplitIsLessThan() throws IllegalAccessException {
    ObjectTableInfo objectTableInfo = mock(ObjectTableInfo.class);
    Field[] declaredFields = Student.class.getDeclaredFields();
    List<Field> fields = Arrays.asList(declaredFields);

    when(objectTableInfo.getFieldList()).thenReturn(fields);

    Text record = new Text("Akash,,");
    HiveTable hiveTable = mock(HiveTable.class);
    when(hiveTable.getDelimeter()).thenReturn(",");
    HashMap<String, Class<?>> nameToTypeMap = new HashMap<String, Class<?>>();
    nameToTypeMap.put("name", String.class);
    nameToTypeMap.put("phoneno", String.class);
    HashMap<String, Integer> nameToPositionMap = new HashMap<String, Integer>();
    nameToPositionMap.put("name",0);
    nameToPositionMap.put("phoneno",1);

    when(hiveTable.getColumnToTypeMap()).thenReturn(nameToTypeMap);
    when(hiveTable.getColumnToPositionMap()).thenReturn(nameToPositionMap);

    HiveRecordToObjectFactory<Student> factory = new HiveRecordToObjectFactory<Student>(objectTableInfo, hiveTable, record);


    Student student = new Student("Yello", "8888", 0);
    student = factory.getObject(student);

    assertThat(student.getName(),is("Akash"));
    assertNull(student.getPhoneNo());
    assertNull(student.getAge());

  }
}
