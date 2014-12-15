package in.sleepythread.columnparser;


import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class HiveStringParserTest {

  @Test
  public void shouldReturnTheExactString(){
    HiveStringParser hiveStringParser = new HiveStringParser();
    assertThat(hiveStringParser.parseColumn("Hello"),is("Hello"));
  }
}
