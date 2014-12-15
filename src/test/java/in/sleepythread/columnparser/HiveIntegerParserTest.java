package in.sleepythread.columnparser;


import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

public class HiveIntegerParserTest {

  @Test
  public void shouldConvertValidIntergerStringToInteger(){
    HiveIntegerParser hiveIntegerParser = new HiveIntegerParser();

    assertThat(hiveIntegerParser.parseColumn("123"),is(123));
  }

  @Test
  public void shouldReturnNullIfThereIsNoValidIntegerString(){
    HiveIntegerParser hiveIntegerParser = new HiveIntegerParser();

    assertNull(hiveIntegerParser.parseColumn("12A"));
  }
}
