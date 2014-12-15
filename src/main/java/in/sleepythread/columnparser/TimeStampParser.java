package in.sleepythread.columnparser;


import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeStampParser implements HiveColumnParser<Timestamp> {


  @Override
  public Timestamp parseColumn(String columnString) {
    Timestamp timestamp = null;
    try{
      SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
      Date parsedDate = dateFormat.parse(columnString);
      timestamp = new Timestamp(parsedDate.getTime());
    }catch(Exception e){
      timestamp = null;
    }
    return timestamp;
  }
}
