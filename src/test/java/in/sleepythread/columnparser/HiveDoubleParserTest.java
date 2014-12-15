package in.sleepythread.columnparser;


import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

public class HiveDoubleParserTest {

  @Test
  public void shouldReturnDoubleObjectForValidString(){
    HiveDoubleParser hiveDoubleParser = new HiveDoubleParser();

    assertThat(hiveDoubleParser.parseColumn("221.32"),is(new Double(221.32d)));
  }

  @Test
  public void shouldReturnNullForInvalidString(){
    HiveDoubleParser hiveDoubleParser = new HiveDoubleParser();

    assertNull(hiveDoubleParser.parseColumn("a2"));
  }
}
