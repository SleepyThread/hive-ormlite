package in.sleepythread.columnparser;


public class BooleanParser implements HiveColumnParser<Boolean>{
  @Override
  public Boolean parseColumn(String columnString) {
    return Boolean.parseBoolean(columnString);
  }
}
