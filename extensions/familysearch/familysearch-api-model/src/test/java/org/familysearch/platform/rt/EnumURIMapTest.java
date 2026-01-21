package org.familysearch.platform.rt;

import org.gedcomx.common.URI;
import org.gedcomx.rt.EnumURIMap;
import org.gedcomx.rt.GedcomxConstants;
import org.gedcomx.types.ResourceType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EnumURIMapTest {

  @Test
  void lookUpURIWithFragment() {
    EnumURIMap<ResourceType> uriMap = new EnumURIMap<>(ResourceType.class, GedcomxConstants.GEDCOMX_TYPES_NAMESPACE);
    URI uriWithoutFragment = ResourceType.DigitalArtifact.toQNameURI();
    assertEquals(ResourceType.DigitalArtifact, uriMap.fromURIValue(uriWithoutFragment));
    URI uriWithFragment = URI.create(ResourceType.DigitalArtifact.toQNameURI().toString()+"#uriFragment");
    assertEquals(ResourceType.DigitalArtifact, uriMap.fromURIValue(uriWithFragment));


  }
}
