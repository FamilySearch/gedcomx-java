package org.gedcomx.date;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import static org.fest.assertions.api.Assertions.assertThat;
import static org.fest.assertions.api.Assertions.fail;

/**
 * @author John Clark.
 */
class RecurringTest {

  @Test
  void errorOnNull() {
    try {
      new GedcomxDateRecurring(null);
      fail("GedcomxDateException expected because date must not be null");
    } catch(GedcomxDateException e) {
      assertThat(e.getMessage()).isEqualTo("Invalid Recurring Date");
    }
  }

  @Test
  void errorOnBlankString() {
    try {
      new GedcomxDateRecurring("");
      fail("GedcomxDateException expected because date must not be empty");
    } catch(GedcomxDateException e) {
      assertThat(e.getMessage()).isEqualTo("Invalid Recurring Date");
    }
  }

  @Test
  void errorOnMissingR() {
    try {
      new GedcomxDateRecurring("32/+1000/P10Y");
      fail("GedcomxDateException expected because R is required");
    } catch(GedcomxDateException e) {
      assertThat(e.getMessage()).isEqualTo("Invalid Recurring Date: Must start with R");
    }
  }

  @Test
  void errorOnToFewParts() {
    try {
      new GedcomxDateRecurring("R+1000/P1");
      fail("GedcomxDateException expected because not enough parts");
    } catch(GedcomxDateException e) {
      assertThat(e.getMessage()).isEqualTo("Invalid Recurring Date: Must contain 3 parts");
    }
  }

  @Test
  void errorOnToManyParts() {
    try {
      new GedcomxDateRecurring("R3/+1000/P1/stuff");
      fail("GedcomxDateException expected because to many parts");
    } catch(GedcomxDateException e) {
      assertThat(e.getMessage()).isEqualTo("Invalid Recurring Date: Must contain 3 parts");
    }
  }

  @Test
  void errorOnMissingRangeChunk() {
    try {
      new GedcomxDateRecurring("R3//P1Y");
      fail("GedcomxDateException expected because missing range chunk");
    } catch(GedcomxDateException e) {
      assertThat(e.getMessage()).isEqualTo("Invalid Recurring Date: Range must have a start and an end");
    }
  }

  @Test
  void errorOnInvalidCount() {
    try {
      new GedcomxDateRecurring("RR3/+1000/P1Y");
      fail("GedcomxDateException expected because invalid count");
    } catch(GedcomxDateException e) {
      assertThat(e.getMessage()).isEqualTo("Invalid Recurring Date: Malformed Count");
    }
  }

  @Test
  void errorOnNegativeCount() {
    try {
      new GedcomxDateRecurring("R-3/+1000/P1Y");
      fail("GedcomxDateException expected because invalid count");
    } catch(GedcomxDateException e) {
      assertThat(e.getMessage()).isEqualTo("Invalid Recurring Date: Malformed Count");
    }
  }

  @Test
  void errorOnBadRange() {
    try {
      new GedcomxDateRecurring("R3/+1000-/P1Y");
      fail("GedcomxDateException expected because invalid range");
    } catch(GedcomxDateException e) {
      assertThat(e.getMessage()).isEqualTo("Invalid Date: Month must be 2 digits in Range Start Date in Recurring Range");
    }
  }

  @Test
  void shouldHaveNoEnd() {
    GedcomxDateRecurring recurring = new GedcomxDateRecurring("R/+1000/P1Y");

    assertThat(recurring.getCount()).isEqualTo(null);
    assertThat(recurring.getEnd()).isEqualTo(null);
  }

  @Test
  void shouldHaveAnEnd() {
    GedcomxDateRecurring recurring = new GedcomxDateRecurring("R13/+1000/P1Y");

    assertThat(recurring.getCount()).isEqualTo(13);
    assertThat(recurring.getStart()).isNotEqualTo(null);
    assertThat(recurring.getEnd()).isNotEqualTo(null);
    assertThat(recurring.getDuration()).isNotEqualTo(null);

    GedcomxDateSimple start = recurring.getStart();
    assertThat(start.getYear()).isEqualTo(1000);
    assertThat(start.getMonth()).isEqualTo(null);

    GedcomxDateDuration duration = recurring.getDuration();
    assertThat(duration.getYears()).isEqualTo(1);
    assertThat(duration.getMonths()).isEqualTo(null);

    GedcomxDateSimple end = recurring.getEnd();
    assertThat(end.getYear()).isEqualTo(1013);
    assertThat(end.getMonth()).isEqualTo(null);
  }

  /**
   * Other Methods
   */

  @Test
  void getCount() {
    GedcomxDateRecurring recurring;

    recurring = new GedcomxDateRecurring("R1/+1000/P1Y");
    assertThat(recurring.getCount()).isEqualTo(1);

    recurring = new GedcomxDateRecurring("R/+1000/P1Y");
    assertThat(recurring.getCount()).isEqualTo(null);
  }

  @Test
  void getRange() {
    GedcomxDateRecurring recurring;

    recurring = new GedcomxDateRecurring("R1/+1000/P1Y");
    assertThat(recurring.getRange().toFormalString()).isEqualTo("+1000/P1Y");
  }

  @Test
  void getStart() {
    GedcomxDateRecurring recurring;

    recurring = new GedcomxDateRecurring("R1/+1000/P1Y");
    assertThat(recurring.getStart().toFormalString()).isEqualTo("+1000");
  }

  @Test
  void getDuration() {
    GedcomxDateRecurring recurring;

    recurring = new GedcomxDateRecurring("R1/+1000/P1Y");
    assertThat(recurring.getDuration().toFormalString()).isEqualTo("P1Y");
  }

  @Test
  void getEnd() {
    GedcomxDateRecurring recurring;

    recurring = new GedcomxDateRecurring("R1/+1000/P1Y");
    assertThat(recurring.getEnd().toFormalString()).isEqualTo("+1001");
  }

  @Test
  void getNth() {
    GedcomxDateRecurring recurring;

    recurring = new GedcomxDateRecurring("R1/+1000/P1Y");
    assertThat(recurring.getNth(1).toFormalString()).isEqualTo("+1001");
    assertThat(recurring.getNth(13).toFormalString()).isEqualTo("+1013");
  }

  @Test
  void getType() {
    GedcomxDateRecurring recurring = new GedcomxDateRecurring("R1/+1000/P1Y");
    assertThat(recurring.getType()).isEqualTo(GedcomxDateType.RECURRING);
  }

  @Test
  void isApproximate() {
    GedcomxDateRecurring recurring = new GedcomxDateRecurring("R1/+1000/P1Y");
    assertThat(recurring.isApproximate()).isEqualTo(false);
  }

  @Test
  void toFormalString() {
    List<String> tests = Arrays.asList("R1/+1000/P1Y","R/+1000/P1Y");
    for(String test: tests) {
      GedcomxDateRecurring recurring = new GedcomxDateRecurring(test);
      assertThat(recurring.toFormalString()).isEqualTo(test);
    }
  }
}
