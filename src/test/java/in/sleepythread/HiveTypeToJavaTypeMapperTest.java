package in.sleepythread;


import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.eq;

public class HiveTypeToJavaTypeMapperTest {

  @Test
  public void shouldMapBasicHiveDataTypeToTheirMappedClass() {
    HiveTypeToJavaTypeMapper hiveTypeToJavaTypeMapper = new HiveTypeToJavaTypeMapper();

    String classString = hiveTypeToJavaTypeMapper.getJavaType("int").getName();
    assertEquals(classString, "java.lang.Integer");
  }

  @Test
  public void shouldMapArrayDataTypeToStringClass() {
    HiveTypeToJavaTypeMapper hiveTypeToJavaTypeMapper = new HiveTypeToJavaTypeMapper();

    assertEquals(hiveTypeToJavaTypeMapper.getJavaType("array<string>").getName(), "java.lang.String");
  }

  @Test
  public void shouldRetunnStringClassForMapDataType() {
    HiveTypeToJavaTypeMapper hiveTypeToJavaTypeMapper = new HiveTypeToJavaTypeMapper();

    assertEquals(hiveTypeToJavaTypeMapper.getJavaType("map<string,int>").getName(), "java.lang.String");

  }

  @Test
  public void shouldMapStructDataTypeToStringClass() {
    HiveTypeToJavaTypeMapper hiveTypeToJavaTypeMapper = new HiveTypeToJavaTypeMapper();

    assertEquals(hiveTypeToJavaTypeMapper.getJavaType("struct<st:string>").getName(), "java.lang.String");

  }

  @Test
  public void shouldMapUnionDataTypeToStringClass() {
    HiveTypeToJavaTypeMapper hiveTypeToJavaTypeMapper = new HiveTypeToJavaTypeMapper();

    assertEquals(hiveTypeToJavaTypeMapper.getJavaType("uniontype<int,string>").getName(), "java.lang.String");
  }
}
