package org.gedcomx.types;

import org.codehaus.enunciate.qname.XmlQNameEnum;
import org.codehaus.enunciate.qname.XmlUnknownQNameEnumValue;
import org.gedcomx.common.URI;

/**
 * Class representing the status of a resource (such as a person, historical record or image).
 * User: Randy Wilson
 * Date: 11/25/2014
 * Time: 9:58 AM
 */
@XmlQNameEnum(
        base = XmlQNameEnum.BaseType.URI
)
public enum ResourceStatusType {
  Deleted,    // (May be 'replacedBy' another resource, as in the case of a merge).
  Deprecated, // Still available, but no longer the latest or best version. Often 'replacedBy' another resource.
  Duplicate,  // Duplicate of another resource (like a film that has a picture of the same page twice), and therefore can be ignored.
  Blank,      // Blank (like an image of a blank page in a book, or a black 'filler' image).
  NoData,     // Not blank, but no extractable or relevant data (like an image of a table of contents).
  Unreadable, // Resource appears to contain data, but it is unreadable (like a terribly underexposed page).


  /**
   * Custom
   */
  @XmlUnknownQNameEnumValue
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
  public static ResourceStatusType fromQNameURI(URI qname) {
    return org.codehaus.enunciate.XmlQNameEnumUtil.fromURIValue(qname.toString(), ResourceStatusType.class);
  }
}
