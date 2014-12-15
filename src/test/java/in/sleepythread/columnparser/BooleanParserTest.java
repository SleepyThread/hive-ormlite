package in.sleepythread.columnparser;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class BooleanParserTest {

  @Test
  public void shouldReturnTrueBooleanObjectForTrueString(){
    BooleanParser booleanParser = new BooleanParser();

    Boolean trueObject = Boolean.TRUE;
    assertThat(booleanParser.parseColumn("true"), is(trueObject));
  }

  @Test
  public void shouldReturnFalseBooleanObjectForFalseString(){
    BooleanParser booleanParser = new BooleanParser();

    Boolean falseObject = Boolean.FALSE;
    assertThat(booleanParser.parseColumn("false"), is(falseObject));
  }

  @Test
  public void shouldReturnFalseBooleanObjectForInvalidString(){
    BooleanParser booleanParser = new BooleanParser();

    Boolean falseObject = Boolean.FALSE;
    assertThat(booleanParser.parseColumn("fals"), is(falseObject));
  }

}
