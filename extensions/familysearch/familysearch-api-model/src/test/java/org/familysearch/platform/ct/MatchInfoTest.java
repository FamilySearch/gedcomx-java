package org.familysearch.platform.ct;

import org.gedcomx.common.URI;

import org.junit.Test;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class MatchInfoTest {

  @Test
  public void testMatchInfo() throws Exception {

    MatchInfo matchInfo = new MatchInfo();
    assertNull(matchInfo.getCollection());
    assertNull(matchInfo.getStatus());
    assertNull(matchInfo.getAddsPerson());
    assertNull(matchInfo.getAddsPerson110YearRule());
    assertNull(matchInfo.getAddsFact());
    assertNull(matchInfo.getAddsDateOrPlace());
    assertNull(matchInfo.getHasFourOrMorePeople());
    assertNull(matchInfo.getAddsFather110YearRule());
    assertNull(matchInfo.getAddsMother110YearRule());
    assertNull(matchInfo.getAddsParentUnknownGender110YearRule());
    assertNull(matchInfo.getAddsSpouse110YearRule());
    assertNull(matchInfo.getAddsSon110YearRule());
    assertNull(matchInfo.getAddsDaughter110YearRule());
    assertNull(matchInfo.getAddsChildUnknownGender110YearRule());
    assertNull(matchInfo.getAddsBirth());
    assertNull(matchInfo.getAddsChristening());
    assertNull(matchInfo.getAddsDeath());
    assertNull(matchInfo.getAddsBurial());
    assertNull(matchInfo.getAddsMarriage());
    assertNull(matchInfo.getAddsOtherFact());
    assertNull(matchInfo.getAddsDate());
    assertNull(matchInfo.getAddsPlace());
    assertNull(matchInfo.getImprovesDate());
    assertNull(matchInfo.getImprovesPlace());

    matchInfo.setKnownCollection(MatchCollection.tree);
    matchInfo.setKnownStatus(MatchStatus.Accepted);
    matchInfo.setAddsPerson(true);
    matchInfo.setAddsPerson110YearRule(true);
    matchInfo.setAddsFact(true);
    matchInfo.setAddsDateOrPlace(true);
    matchInfo.setHasFourOrMorePeople(true);
    matchInfo.setAddsFather110YearRule(true);
    matchInfo.setAddsMother110YearRule(true);
    matchInfo.setAddsParentUnknownGender110YearRule(true);
    matchInfo.setAddsSpouse110YearRule(true);
    matchInfo.setAddsSon110YearRule(true);
    matchInfo.setAddsDaughter110YearRule(true);
    matchInfo.setAddsChildUnknownGender110YearRule(true);
    matchInfo.setAddsBirth(true);
    matchInfo.setAddsChristening(true);
    matchInfo.setAddsDeath(true);
    matchInfo.setAddsBurial(true);
    matchInfo.setAddsMarriage(true);
    matchInfo.setAddsOtherFact(true);
    matchInfo.setAddsDate(true);
    matchInfo.setAddsPlace(true);
    matchInfo.setImprovesDate(true);
    matchInfo.setImprovesPlace(true);

    assertEquals(MatchCollection.tree.toQNameURI(), matchInfo.getCollection());
    assertEquals(MatchStatus.Accepted.toQNameURI(), matchInfo.getStatus());
    assertTrue(matchInfo.getAddsPerson());
    assertTrue(matchInfo.getAddsPerson110YearRule());
    assertTrue(matchInfo.getAddsFact());
    assertTrue(matchInfo.getAddsDateOrPlace());
    assertTrue(matchInfo.getHasFourOrMorePeople());
    assertTrue(matchInfo.getAddsFather110YearRule());
    assertTrue(matchInfo.getAddsMother110YearRule());
    assertTrue(matchInfo.getAddsParentUnknownGender110YearRule());
    assertTrue(matchInfo.getAddsSpouse110YearRule());
    assertTrue(matchInfo.getAddsSon110YearRule());
    assertTrue(matchInfo.getAddsDaughter110YearRule());
    assertTrue(matchInfo.getAddsChildUnknownGender110YearRule());
    assertTrue(matchInfo.getAddsBirth());
    assertTrue(matchInfo.getAddsChristening());
    assertTrue(matchInfo.getAddsDeath());
    assertTrue(matchInfo.getAddsBurial());
    assertTrue(matchInfo.getAddsMarriage());
    assertTrue(matchInfo.getAddsOtherFact());
    assertTrue(matchInfo.getAddsDate());
    assertTrue(matchInfo.getAddsPlace());
    assertTrue(matchInfo.getImprovesDate());
    assertTrue(matchInfo.getImprovesPlace());
  }

}