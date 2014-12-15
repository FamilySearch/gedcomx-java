package org.gedcomx.types;

import org.codehaus.enunciate.Facet;
import org.codehaus.enunciate.qname.XmlQNameEnum;
import org.codehaus.enunciate.qname.XmlUnknownQNameEnumValue;
import org.gedcomx.common.URI;
import org.gedcomx.rt.GedcomxConstants;

/**
 * Class representing the status of a FieldValue. Using a status avoids having to use special strings in the
 *   value itself, such as "_Blank_".
 * User: Randy Wilson
 * Date: 11/25/2014
 * Time: 12:04 PM
 */
@XmlQNameEnum(
        base = XmlQNameEnum.BaseType.URI
)
@Facet( name = GedcomxConstants.FACET_GEDCOMX_RECORD )
public enum FieldValueStatusType {
  /**
   * "Intentionally left blank:
   * - For an Original field value, this means the field itself was blank.
   * - For an Interpreted field value, it means that the Original value was bogus or meaningless, so
   *   the field should be treated as if blank.
   */
  Blank,
  Unreadable, // The field couldn't be read.


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
  public static FieldValueStatusType fromQNameURI(URI qname) {
    return org.codehaus.enunciate.XmlQNameEnumUtil.fromURIValue(qname.toString(), FieldValueStatusType.class);
  }
}
