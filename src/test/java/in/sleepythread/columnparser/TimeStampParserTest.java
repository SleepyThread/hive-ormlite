package in.sleepythread.columnparser;

import org.junit.Test;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

public class TimeStampParserTest {

  @Test
  public void shouldReturnTimeStampForValidString() throws ParseException {
    TimeStampParser timeStampParser = new TimeStampParser();

    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
    Timestamp expectedDate = new Timestamp(dateFormat.parse("2014-08-29 12:12:12.423").getTime());

    assertThat(timeStampParser.parseColumn("2014-08-29 12:12:12.423").equals(expectedDate),is(true));
  }

  @Test
  public void shouldReturnNullForInValidString(){
    TimeStampParser timeStampParser = new TimeStampParser();

    assertNull(timeStampParser.parseColumn("2014-29-01 12:12:12"));
  }

}
