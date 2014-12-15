package in.sleepythread.columnparser;


import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

public class HiveFloatParserTest {

  @Test
  public void shouldReturnFloatObjectForValidString(){
    HiveFloatParser hiveFloatParser = new HiveFloatParser();

    assertThat(hiveFloatParser.parseColumn("214.4"),is(new Float(214.4f)));
  }

  @Test
  public void shouldReturnNullForInvalidString(){
    HiveFloatParser hiveFloatParser = new HiveFloatParser();

    assertNull(hiveFloatParser.parseColumn("a"));
  }
}
