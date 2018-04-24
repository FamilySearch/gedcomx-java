package org.familysearch.platform.places;

import org.testng.Assert;
import org.testng.annotations.Test;

public class RelatedPlaceDescriptionTypeTest {
  private static final String DELETED_VALUE = "DELETED";
  private static final String SAME_NAME_VLAUE = "SAME_NAME";
  private static final String SAME_PLACE_VALUE = "SAME_PLACE";
  private static final String ASSOCIATED_VALUE = "ASSOCIATED";

  @Test
  public void testValueOf() {
    Assert.assertEquals(RelatedPlaceDescriptionType.valueOf(DELETED_VALUE), RelatedPlaceDescriptionType.DELETED);
    Assert.assertEquals(RelatedPlaceDescriptionType.valueOf(SAME_NAME_VLAUE), RelatedPlaceDescriptionType.SAME_NAME);
    Assert.assertEquals(RelatedPlaceDescriptionType.valueOf(SAME_PLACE_VALUE), RelatedPlaceDescriptionType.SAME_PLACE);
    Assert.assertEquals(RelatedPlaceDescriptionType.valueOf(ASSOCIATED_VALUE), RelatedPlaceDescriptionType.ASSOCIATED);
  }

  @Test
  public void testToString() {
    Assert.assertEquals(RelatedPlaceDescriptionType.DELETED.toString(), DELETED_VALUE);
    Assert.assertEquals(RelatedPlaceDescriptionType.SAME_NAME.toString(), SAME_NAME_VLAUE);
    Assert.assertEquals(RelatedPlaceDescriptionType.SAME_PLACE.toString(), SAME_PLACE_VALUE);
    Assert.assertEquals(RelatedPlaceDescriptionType.ASSOCIATED.toString(), ASSOCIATED_VALUE);
  }
}
