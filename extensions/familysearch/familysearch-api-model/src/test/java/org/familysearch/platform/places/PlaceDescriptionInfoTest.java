package org.familysearch.platform.places;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PlaceDescriptionInfoTest {

  @Test
  void zoomLevel() {
    final Integer zoomLevel = 7;

    PlaceDescriptionInfo result = new PlaceDescriptionInfo();
    result.setZoomLevel(zoomLevel);
    assertEquals(result.getZoomLevel(), zoomLevel);

    result = new PlaceDescriptionInfo().zoomLevel(zoomLevel);
    assertEquals(result.getZoomLevel(), zoomLevel);
  }

  @Test
  void relatedPlaceDescriptionType() {
    final String type = "ASSOCIATED";

    PlaceDescriptionInfo result = new PlaceDescriptionInfo();
    result.setRelatedType(type);
    assertEquals(result.getRelatedType(), type);

    result = new PlaceDescriptionInfo().type(type);
    assertEquals(result.getRelatedType(), type);
  }

  @Test
  void relatedPlaceDescriptionSubType() {
    final String subType = "Political";

    PlaceDescriptionInfo result = new PlaceDescriptionInfo();
    result.setRelatedSubType(subType);
    assertEquals(result.getRelatedSubType(), subType);

    result = new PlaceDescriptionInfo().subType(subType);
    assertEquals(result.getRelatedSubType(), subType);
  }

}
