package in.sleepythread.columnparser;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class DateParser implements HiveColumnParser<Date> {
  @Override
  public Date parseColumn(String columnString) {
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
    Date parsedDate= null;
    try {
      parsedDate = formatter.parse(columnString);
      if(!formatter.format(parsedDate).equals(columnString)){
        parsedDate = null;
      }
    } catch (ParseException e) {
    }
    return parsedDate;
  }
}
