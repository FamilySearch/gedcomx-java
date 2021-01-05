package org.familysearch.platform.rt;

import org.gedcomx.common.URI;
import org.gedcomx.rt.EnumURIMap;
import org.gedcomx.rt.GedcomxConstants;
import org.gedcomx.types.ResourceType;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

public class EnumURIMapTest {

  @Test
  public void testLookUpURIWithFragment() {
    EnumURIMap<ResourceType> uriMap = new EnumURIMap<>(ResourceType.class, GedcomxConstants.GEDCOMX_TYPES_NAMESPACE);
    URI uriWithoutFragment = ResourceType.DigitalArtifact.toQNameURI();
    Assert.assertEquals(ResourceType.DigitalArtifact, uriMap.fromURIValue(uriWithoutFragment));
    URI uriWithFragment = URI.create(ResourceType.DigitalArtifact.toQNameURI().toString()+"#uriFragment");
    Assert.assertEquals(ResourceType.DigitalArtifact, uriMap.fromURIValue(uriWithFragment));


  }
}
