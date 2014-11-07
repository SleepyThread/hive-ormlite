package com.sleepythread.columnparser;


import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

public class DateParserTest {

  @Test
  public void shouldReturnDateObjectForValidDateString() throws ParseException {
    DateParser dateParser = new DateParser();

    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");


    Date date = formatter.parse("2013-11-29");
    assertThat(dateParser.parseColumn("2013-11-29").equals(date), is(true));
  }

  @Test
  public void shouldReturnNullForInValidDateString(){
    DateParser dateParser = new DateParser();

    assertNull(dateParser.parseColumn("2012-31-01"));
  }

}
