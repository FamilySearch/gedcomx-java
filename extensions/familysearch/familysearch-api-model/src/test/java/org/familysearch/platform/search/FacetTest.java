package org.familysearch.platform.search;


import org.junit.Test;

import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;


public class FacetTest {
  String name="name";
  String countString="100.070";
  String params="f.sex=female";
  long count=100_070L;

  @Test
  public void displayName() {
    Facet facet = new Facet().displayName(name);
    assertEquals(name,facet.getDisplayName());
  }

  @Test
  public void displayCount() {
    Facet facet = new Facet().displayCount(countString);
    assertEquals(countString, facet.getDisplayCount());
  }

  @Test
  public void params() {
    Facet facet = new Facet().params(params);
    assertEquals(params,facet.getParams());
  }

  @Test
  public void count() {
    Facet facet = new Facet().count(count);
    assertEquals(count, facet.getCount());
  }

  @Test
  public void facets() {
    List<Facet> facets=Collections.singletonList(new Facet(name, countString, params, count, null));
    Facet facet = new Facet().facets(facets);
    Stream<Facet> stream = facet.facets();
    assertEquals(name,stream.map(Facet::getDisplayName).findFirst().orElse("failed to find it"));
    assertEquals(name,facet.getFacets().stream().map(Facet::getDisplayName).findFirst().orElse("failed to find it"));
  }

}
