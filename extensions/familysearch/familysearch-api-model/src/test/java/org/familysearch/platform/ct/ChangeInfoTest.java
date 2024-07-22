package org.familysearch.platform.ct;

import org.gedcomx.common.ResourceReference;
import org.gedcomx.common.URI;
import org.junit.Test;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;


public class ChangeInfoTest {
  @Test
  public void testGetReason() throws Exception {
    ChangeInfo changeInfo = new ChangeInfo();
    assertNull(changeInfo.getReason());
    assertNull(changeInfo.getKnownOperation());
    assertNull(changeInfo.getObjectType());
    assertNull(changeInfo.getParent());
    assertNull(changeInfo.getPrevious());
    assertNull(changeInfo.getResulting());
    assertNull(changeInfo.getOriginal());
    assertNull(changeInfo.getRemoved());

    changeInfo.setReason("junkReason");
    changeInfo.setKnownOperation(ChangeOperation.Delete);
    changeInfo.setObjectType(URI.create("urn:hi"));
    changeInfo.setParent(new ResourceReference(URI.create("urn:junkParent")));
    assertEquals("junkReason", changeInfo.getReason());
    assertEquals(ChangeOperation.Delete, changeInfo.getKnownOperation());
    assertEquals("urn:hi", changeInfo.getObjectType().toString());
    assertEquals("urn:junkParent", changeInfo.getParent().getResource().toString());
  }

  @Test
  public void testSubjects(){
    ChangeInfo changeInfo = new ChangeInfo();

    String previousURL = "#CHNG-001_PRSN-001" + ChangeInfo.ChangeValueType.PREVIOUS.getSuffix();
    String resultingURL = "#CHNG-001_PRSN-001" + ChangeInfo.ChangeValueType.RESULTING.getSuffix();
    String originalURL = "#CHNG-001_PRSN-001" + ChangeInfo.ChangeValueType.ORIGINAL.getSuffix();
    String removedURL = "#CHNG-001_PRSN-001" + ChangeInfo.ChangeValueType.REMOVED.getSuffix();
    changeInfo.setPrevious(new ResourceReference(URI.create(previousURL)));
    changeInfo.setResulting(new ResourceReference(URI.create(resultingURL)));
    changeInfo.setOriginal(new ResourceReference(URI.create(originalURL)));
    changeInfo.setRemoved(new ResourceReference(URI.create(removedURL)));
    testSubjects(originalURL, previousURL, removedURL, resultingURL, changeInfo);

    previousURL = "#CHNG-001_PRSN-002";
    resultingURL = "#CHNG-001_PRSN-002";
    originalURL = "#CHNG-001_PRSN-002";
    removedURL = "#CHNG-001_PRSN-002";
    changeInfo.setSubject(new ResourceReference(URI.create(resultingURL)), ChangeInfo.ChangeValueType.PREVIOUS);
    changeInfo.setSubject(new ResourceReference(URI.create(resultingURL)), ChangeInfo.ChangeValueType.RESULTING);
    changeInfo.setSubject(new ResourceReference(URI.create(originalURL)), ChangeInfo.ChangeValueType.ORIGINAL);
    changeInfo.setSubject(new ResourceReference(URI.create(removedURL)), ChangeInfo.ChangeValueType.REMOVED);
    testSubjects(originalURL + ChangeInfo.ChangeValueType.ORIGINAL.getSuffix(), previousURL + ChangeInfo.ChangeValueType.PREVIOUS.getSuffix(),
                 removedURL + ChangeInfo.ChangeValueType.REMOVED.getSuffix(), resultingURL + ChangeInfo.ChangeValueType.RESULTING.getSuffix(), changeInfo);

  }


  private void testSubjects(final String originalUrl, final String previousUrl, final String removedUrl, final String resultingUrl, final ChangeInfo changeInfo) {
    assertEquals(previousUrl, changeInfo.getPrevious().getResource().toString());
    assertEquals(resultingUrl, changeInfo.getResulting().getResource().toString());
    assertEquals(originalUrl, changeInfo.getOriginal().getResource().toString());
    assertEquals(removedUrl, changeInfo.getRemoved().getResource().toString());

    assertEquals(previousUrl, changeInfo.getSubject(ChangeInfo.ChangeValueType.PREVIOUS).getResource().toString());
    assertEquals(resultingUrl, changeInfo.getSubject(ChangeInfo.ChangeValueType.RESULTING).getResource().toString());
    assertEquals(originalUrl, changeInfo.getSubject(ChangeInfo.ChangeValueType.ORIGINAL).getResource().toString());
    assertEquals(removedUrl, changeInfo.getSubject(ChangeInfo.ChangeValueType.REMOVED).getResource().toString());
  }
}
