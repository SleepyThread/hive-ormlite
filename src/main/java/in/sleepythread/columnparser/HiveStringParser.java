package in.sleepythread.columnparser;


public class HiveStringParser implements HiveColumnParser<String>{
  @Override
  public String parseColumn(String columnString) {
    return columnString;
  }

}
