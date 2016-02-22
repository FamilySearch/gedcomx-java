/**
 * Copyright Intellectual Reserve, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.gedcomx.types;

import org.codehaus.enunciate.Facet;
import org.codehaus.enunciate.qname.XmlQNameEnum;
import org.codehaus.enunciate.qname.XmlUnknownQNameEnumValue;
import org.gedcomx.common.URI;
import org.gedcomx.rt.ControlledVocabulary;
import org.gedcomx.rt.EnumURIMap;
import org.gedcomx.rt.GedcomxConstants;

/**
 * Enumeration of document types.
 *
 * @author Ryan Heaton
 */
@XmlQNameEnum (
  base = XmlQNameEnum.BaseType.URI
)
@Facet ( name = GedcomxConstants.FACET_FS_FT_UNSUPPORTED )
public enum DocumentType implements ControlledVocabulary {

  /**
   * The document is an abstract of a record or document.
   */
  Abstract,

  /**
   * The document is a translation of a record or document.
   */
  Translation,

  /**
   * The document is a transcription (full or partial) of a record or document.
   */
  Transcription,

  /**
   * The document is an analysis done by a researcher, often used as a genealogical proof statement.
   */
  Analysis,

  @XmlUnknownQNameEnumValue
  OTHER;

  private static final EnumURIMap<DocumentType> URI_MAP = new EnumURIMap<DocumentType>(DocumentType.class, GedcomxConstants.GEDCOMX_TYPES_NAMESPACE);

  /**
   * Return the QName value for this enum.
   *
   * @return The QName value for this enum.
   */
  public URI toQNameURI() {
    return URI_MAP.toURIValue(this);
  }

  /**
   * Get the enumeration from the QName.
   *
   * @param qname The qname.
   * @return The enumeration.
   */
  public static DocumentType fromQNameURI(URI qname) {
    return URI_MAP.fromURIValue(qname);
  }

}
