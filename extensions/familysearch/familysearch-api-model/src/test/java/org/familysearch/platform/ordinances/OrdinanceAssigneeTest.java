package org.familysearch.platform.ordinances;

import org.junit.Test;

import static org.junit.Assert.assertSame;

@SuppressWarnings ( "unchecked" )
// todo GenericOrdinanceTerms ordinances  This class should be deleted
public class OrdinanceAssigneeTest {

  @Test
  public void testToFromUri() throws Exception {
    assertSame(OrdinanceAssignee.LdsChurch, OrdinanceAssignee.fromQNameURI(OrdinanceAssignee.LdsChurch.toQNameURI()));
  }

}
