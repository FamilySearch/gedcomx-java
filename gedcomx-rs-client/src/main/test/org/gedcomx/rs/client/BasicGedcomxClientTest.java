package org.gedcomx.rs.client;

import org.gedcomx.Gedcomx;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.testng.AssertJUnit.assertNotNull;
import static org.testng.AssertJUnit.assertNull;

/**
 * @author Ryan Heaton
 */
@Test( groups = "integration" )
public class BasicGedcomxClientTest {

  private BasicGedcomxClient client;

  @BeforeClass
  protected void setup() {
    this.client = new BasicGedcomxClient("https://sandbox.familysearch.org/.well-known/app-meta");
    this.client.authenticateViaOAuth2Password("heatonra", "1234cispass", "WCQY-7J1Q-GKVV-7DNM-SQ5M-9Q5H-JX3H-CMJK");
  }

  public void testGetCurrentUser() {
    ApplicationState<Gedcomx> personState = this.client.getPersonForCurrentUser(false);
    assertNotNull(personState.getEntity().getPersons());
    assertNull(personState.getEntity().getRelationships());
  }

  public void testGetFullCurrentUser() {
    ApplicationState<Gedcomx> personState = this.client.getPersonForCurrentUser();
    assertNotNull(personState.getEntity().getPersons());
    assertNotNull(personState.getEntity().getRelationships());
  }
}
