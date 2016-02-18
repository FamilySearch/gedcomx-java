package org.familysearch.platform.ordinances;

import org.gedcomx.common.URI;

public enum OrdinanceRoleType {
  Principal,
  Father,
  Mother,
  Spouse,
  OTHER;

  /**
   * Return the QName value for this enum.
   *
   * @return The QName value for this enum.
   */
  public URI toQNameURI() {
    return URI.create(org.codehaus.enunciate.XmlQNameEnumUtil.toURIValue(this));
  }

  /**
   * Get the enumeration from the QName.
   *
   * @param qname The qname.
   * @return The enumeration.
   */
  public static OrdinanceRoleType fromQNameURI(URI qname) {
    return org.codehaus.enunciate.XmlQNameEnumUtil.fromURIValue(qname.toString(), OrdinanceRoleType.class);
  }

}
