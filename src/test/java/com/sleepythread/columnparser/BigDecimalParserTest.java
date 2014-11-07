package com.sleepythread.columnparser;


import org.junit.Test;

import java.math.BigDecimal;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

public class BigDecimalParserTest {

  @Test
  public void shouldReturnBigDecimalObjectForValidBigDecimalString(){
    BigDecimalParser bigDecimalParser = new BigDecimalParser();

    BigDecimal parsedDecimal = bigDecimalParser.parseColumn("234112321331.12312312");
    assertThat(parsedDecimal.toString(), is("234112321331.12312312"));
  }

  @Test
  public void shouldReturnNullBigDecimalObjectForInvlaidBigDecimalString(){
    BigDecimalParser bigDecimalParser = new BigDecimalParser();

    assertNull(bigDecimalParser.parseColumn("as"));
  }
}
