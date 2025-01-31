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

package org.familysearch.platform.ct;

import com.webcohesion.enunciate.metadata.qname.XmlQNameEnum;
import com.webcohesion.enunciate.metadata.qname.XmlUnknownQNameEnumValue;

import org.familysearch.platform.FamilySearchPlatform;
import org.gedcomx.common.URI;
import org.gedcomx.rt.ControlledVocabulary;
import org.gedcomx.rt.EnumURIMap;

/**
 * Identifiers for a collection that might contain search results.
 */
@XmlQNameEnum(
    base = XmlQNameEnum.BaseType.URI,
    namespace = "https://familysearch.org/platform/collections/"
)
public enum SearchCollection implements ControlledVocabulary {

  /**
   * The FamilySearch Public Family Tree.
   */
  tree
      {
        @Override
        public String getId() {
          return "0";
        }
      },

  /**
   * The FamilySearch CETs.
   */
  cet
      {
        @Override
        public String getId() {
          return "10";
        }
      },

  @XmlUnknownQNameEnumValue
  OTHER;

  private static final EnumURIMap<SearchCollection> URI_MAP = new EnumURIMap<SearchCollection>(SearchCollection.class, FamilySearchPlatform.NAMESPACE);

  /**
   * Return the QName value for this enum.
   *
   * @return The QName value for this enum.
   */
  public URI toQNameURI() {
    return URI_MAP.toURIValue(this);
  }

  /**
   * Return the CollectionId for the collection.
   *
   * @return The CollectionId for this enum.  Each enum constant must override this method.
   */
  public String getId() {
   throw new UnsupportedOperationException("This method must be overridden by the enum constants.");
  }

  /**
   * Get the enumeration from the QName.
   *
   * @param qname The qname.
   * @return The enumeration.
   */
  public static SearchCollection fromQNameURI(URI qname) {
    return URI_MAP.fromURIValue(qname);
  }
}
