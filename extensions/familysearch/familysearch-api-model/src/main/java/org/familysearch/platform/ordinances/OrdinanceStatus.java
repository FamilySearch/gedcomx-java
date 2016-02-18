package org.familysearch.platform.ordinances;

import org.gedcomx.common.URI;

public enum OrdinanceStatus {
  Not_Set,
  Ready,
  Not_Ready,
  Reserved,
  Need_More_Information,
  Not_Available,
  Completed,
  Not_Needed,
  In_Progress,
  Need_Permission,
  Cancelled,
  Deleted,
  Invalid,
  Born_In_The_Covenant,
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
  public static OrdinanceStatus fromQNameURI(URI qname) {
    return org.codehaus.enunciate.XmlQNameEnumUtil.fromURIValue(qname.toString(), OrdinanceStatus.class);
  }

}
