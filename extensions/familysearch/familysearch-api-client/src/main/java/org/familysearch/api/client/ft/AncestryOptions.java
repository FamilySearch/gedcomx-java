package org.familysearch.api.client.ft;

/**
 * @author Ryan Heaton
 */
public class AncestryOptions {

  private boolean includePersonDetails;
  private boolean includeMarriageDetails;

  public boolean isIncludePersonDetails() {
    return includePersonDetails;
  }

  public void setIncludePersonDetails(boolean includePersonDetails) {
    this.includePersonDetails = includePersonDetails;
  }

  public boolean isIncludeMarriageDetails() {
    return includeMarriageDetails;
  }

  public void setIncludeMarriageDetails(boolean includeMarriageDetails) {
    this.includeMarriageDetails = includeMarriageDetails;
  }
}
