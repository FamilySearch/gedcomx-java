package org.familysearch.api.client;

import java.net.URI;

/**
 * @author Ryan Heaton
 */
public enum FamilySearchReferenceEnvironment {

  PRODUCTION("https://familysearch.org/platform"),

  BETA("https://beta.familysearch.org/platform"),

  SANDBOX("https://sandbox.familysearch.org/platform");

  private final String base;

  FamilySearchReferenceEnvironment(String base) {
    this.base = base;
  }

  public URI getRootUri() {
    return URI.create(this.base + "/collection");
  }

  public URI getHistoricalRecordsArchiveUri() {
    return URI.create(this.base + "/collections/records");
  }

  public URI getMemoriesUri() {
    return URI.create(this.base + "/collections/memories");
  }

  public URI getPlacesUri() {
    return URI.create(this.base + "/collections/places");
  }

  public URI getFamilyTreeUri() {
    return URI.create(this.base + "/collections/places");
  }
}
