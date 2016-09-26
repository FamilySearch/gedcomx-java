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
package org.familysearch.platform.artifacts;

import org.codehaus.enunciate.qname.XmlQNameEnum;
import org.gedcomx.common.URI;
import org.gedcomx.rt.ControlledVocabulary;
import org.gedcomx.rt.EnumURIMap;
import org.gedcomx.rt.GedcomxConstants;

/**
 * Enumeration of known artifact screening states.
 *
 * @author chapmantk
 */
@XmlQNameEnum(
    base = XmlQNameEnum.BaseType.URI
)
public enum ArtifactScreeningState implements ControlledVocabulary {

  /**
   * Un-screened.
   */
  Unscreened,

  /**
   * First screening.
   */
  FirstScreening,

  /**
   * First screening done.
   */
  FirstDone,

  /**
   * Second screening.
   */
  SecondScreening,

  /**
   * Second screening done.
   */
  SecondDone,

  /**
   * Third screening.
   */
  ThirdScreening,

  /**
   * Third screening done.
   */
  ThirdDone,

  /**
   * Fourth screening.
   */
  FourthScreening,

  /**
   * Fourth screening done.
   */
  FourthDone,

  /**
   * Fifth screening.
   */
  FifthScreening,

  /**
   * Fifth screening done.
   */
  FifthDone,

  /**
   * Sixth screening.
   */
  SixthScreening,

  /**
   * Sixth screening done.
   */
  SixthDone,

  /**
   * Seventh screening.
   */
  SeventhScreening,

  /**
   * Seventh screening done.
   */
  SeventhDone,

  /**
   * High priority.
   */
  Escalate,

  /**
   * Approved.
   */
  Approved,

  /**
   * Approved, but with restrictions.
   */
  Restricted;

  private static final EnumURIMap<ArtifactScreeningState> URI_MAP =
      new EnumURIMap<ArtifactScreeningState>(ArtifactScreeningState.class, GedcomxConstants.GEDCOMX_TYPES_NAMESPACE);

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
  public static ArtifactScreeningState fromQNameURI(URI qname) {
    return URI_MAP.fromURIValue(qname);
  }

}

