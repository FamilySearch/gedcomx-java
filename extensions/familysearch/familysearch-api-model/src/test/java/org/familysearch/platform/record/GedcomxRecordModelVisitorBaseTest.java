package org.familysearch.platform.record;

import org.familysearch.platform.records.AlternateDate;
import org.familysearch.platform.records.AlternatePlaceReference;
import org.familysearch.platform.records.GedcomxRecordModelVisitorBase;
import org.gedcomx.conclusion.Date;
import org.gedcomx.conclusion.Fact;
import org.gedcomx.conclusion.PlaceReference;
import org.junit.Assert;
import org.junit.Test;


public class GedcomxRecordModelVisitorBaseTest {
  

  @Test
  public void testVisitingExtensionElements() {
    final int[] numberOfTimesVisitPlaceCalled = new int[]{0};
    final int[] numberOfTimesVisitDateCalled = new int[]{0};
    AlternateDate alternateDate = new AlternateDate();
    AlternatePlaceReference alternatePlaceReference = new AlternatePlaceReference();
    GedcomxRecordModelVisitorBase visitor = new GedcomxRecordModelVisitorBase() {
      @Override
      public void visitPlaceReference(PlaceReference place) {
        numberOfTimesVisitPlaceCalled[0]++;
        Assert.assertSame(alternatePlaceReference, place);
      }

      @Override
      public void visitDate(Date date) {
        numberOfTimesVisitDateCalled[0]++;
        Assert.assertSame(alternateDate, date);
      }
    };
    Fact fact = new Fact().extensionElement(alternateDate).extensionElement(alternatePlaceReference);
    visitor.visitFact(fact);
    Assert.assertEquals(1, numberOfTimesVisitDateCalled[0]);
    Assert.assertEquals(1, numberOfTimesVisitPlaceCalled[0]);
  }
}
