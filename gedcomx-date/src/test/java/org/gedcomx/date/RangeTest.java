package org.gedcomx.date;

import org.testng.annotations.Test;

import static org.fest.assertions.api.Assertions.assertThat;
import static org.fest.assertions.api.Assertions.fail;

/**
 * @author John Clark.
 */
public class RangeTest {

  @Test
  public void errorOnBlankString() {
    try {
      new GedcomxDateRange("");
      fail("GedcomxDateException expected because date must not be empty");
    } catch(GedcomxDateException e) {
      assertThat(e.getMessage()).isEqualTo("Invalid Range");
    }
  }

  @Test
   public void errorOnOnePart() {
    try {
      new GedcomxDateRange("A+1000");
      fail("GedcomxDateException expected because range must have a /");
    } catch(GedcomxDateException e) {
      assertThat(e.getMessage()).isEqualTo("Invalid Range: / is required");
    }
  }

  @Test
  public void errorOnJustSlash() {
    try {
      new GedcomxDateRange("/");
      fail("GedcomxDateException expected because range must have something other than a /");
    } catch(GedcomxDateException e) {
      assertThat(e.getMessage()).isEqualTo("Invalid Range: One or two parts are required");
    }
  }

  @Test
  public void errorOnManySlashes() {
    try {
      new GedcomxDateRange("+1000/+2000/+3000");
      fail("GedcomxDateException expected because range must have only 1 slash");
    } catch(GedcomxDateException e) {
      assertThat(e.getMessage()).isEqualTo("Invalid Range: One or two parts are required");
    }
  }

  @Test
  public void errorOnInvalidPart1() {
    try {
      new GedcomxDateRange("+1000_10/");
      fail("GedcomxDateException expected because range has invalid part 1");
    } catch(GedcomxDateException e) {
      assertThat(e.getMessage()).isEqualTo("Invalid Date: Invalid Year-Month Separator in Range Start Date");
    }
  }

  @Test
  public void errorOnDurationOnly() {
    try {
      new GedcomxDateRange("/P100Y");
      fail("GedcomxDateException expected because range only has a duration");
    } catch(GedcomxDateException e) {
      assertThat(e.getMessage()).isEqualTo("Invalid Range: A range may not end with a duration if missing a start date");
    }
  }

  @Test
  public void errorOnInvalidDuration() {
    try {
      new GedcomxDateRange("+1000/P100Q");
      fail("GedcomxDateException expected because range only has a duration");
    } catch(GedcomxDateException e) {
      assertThat(e.getMessage()).isEqualTo("Invalid Duration: Unknown letter Q in Range End Duration");
    }
  }

  @Test
  public void errorOnInvalidPart2() {
    try {
      new GedcomxDateRange("/+1000_10");
      fail("GedcomxDateException expected because range has invalid part 2");
    } catch(GedcomxDateException e) {
      assertThat(e.getMessage()).isEqualTo("Invalid Date: Invalid Year-Month Separator in Range End Date");
    }
  }

  // TODO test calculating duration and end date

  // TODO test other methods

}
