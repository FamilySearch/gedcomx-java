package org.familysearch.platform.record;

import org.familysearch.platform.records.AlternateDate;
import org.familysearch.platform.records.AlternatePlaceReference;
import org.familysearch.platform.records.GedcomxRecordModelVisitorBase;
import org.gedcomx.conclusion.Date;
import org.gedcomx.conclusion.Fact;
import org.gedcomx.conclusion.PlaceReference;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;


class GedcomxRecordModelVisitorBaseTest {


  @Test
  void visitingExtensionElements() {
    final int[] numberOfTimesVisitPlaceCalled = new int[]{0};
    final int[] numberOfTimesVisitDateCalled = new int[]{0};
    AlternateDate alternateDate = new AlternateDate();
    AlternatePlaceReference alternatePlaceReference = new AlternatePlaceReference();
    GedcomxRecordModelVisitorBase visitor = new GedcomxRecordModelVisitorBase() {
      @Override
      public void visitPlaceReference(PlaceReference place) {
        numberOfTimesVisitPlaceCalled[0]++;
        assertSame(alternatePlaceReference, place);
      }

      @Override
      public void visitDate(Date date) {
        numberOfTimesVisitDateCalled[0]++;
        assertSame(alternateDate, date);
      }
    };
    Fact fact = new Fact().extensionElement(alternateDate).extensionElement(alternatePlaceReference);
    visitor.visitFact(fact);
    assertEquals(1, numberOfTimesVisitDateCalled[0]);
    assertEquals(1, numberOfTimesVisitPlaceCalled[0]);
  }
}
