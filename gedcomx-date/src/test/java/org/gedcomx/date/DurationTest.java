package org.gedcomx.date;

import org.testng.annotations.Test;

import static org.fest.assertions.api.Assertions.assertThat;
import static org.fest.assertions.api.Assertions.fail;

/**
 * @author John Clark.
 */
public class DurationTest {

  @Test
  public void errorOnMissingP() {
    try {
      new GedcomxDateDuration("1000Y");
      fail("GedcomxDateException expected because duration must start with P");
    } catch(GedcomxDateException e) {
      assertThat(e.getMessage()).isEqualTo("Invalid Duration - Must start with P");
    }
  }

  @Test
  public void errorOnMissingValuesAfterP() {
    try {
      new GedcomxDateDuration("P");
      fail("GedcomxDateException expected because P must be followed by something");
    } catch(GedcomxDateException e) {
      assertThat(e.getMessage()).isEqualTo("Invalid Duration - You must have a duration value");
    }
  }

  @Test
  public void errorOnSpaceAfterP() {
    try {
      new GedcomxDateDuration("P100 years");
      fail("GedcomxDateException expected because no spaces allowed in duration");
    } catch(GedcomxDateException e) {
      assertThat(e.getMessage()).isEqualTo("Not Implemented - Non normalized durations are not implemented yet");
    }
  }
}
