package in.sleepythread;


import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class ObjectTableInfo {

  private String databaseName;
  private String tableName;
  private List<Field> fieldList;

  public <T> ObjectTableInfo(Class<T> className) {
    HiveDbConfig annotation = className.getAnnotation(HiveDbConfig.class);
    databaseName = annotation.db();
    tableName = annotation.table();
    fieldList = new ArrayList<Field>();
    Field[] fields = className.getDeclaredFields();
    for(Field field: fields){
      Class<?> type = field.getType();
      fieldList.add(field);
    }
  }

  public String getDatabaseName() {
    return databaseName;
  }

  public String getTableName() {
    return tableName;
  }

  public List<Field> getFieldList() {
    return fieldList;
  }
}
