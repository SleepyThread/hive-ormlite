package in.sleepythread.columnparser;


public class HiveDoubleParser implements HiveColumnParser<Double>{
  @Override
  public Double parseColumn(String columnString) {
    Double number = null;
    try{
      number = Double.parseDouble(columnString);
    }catch (NumberFormatException e){
    }
    return number;
  }
}
