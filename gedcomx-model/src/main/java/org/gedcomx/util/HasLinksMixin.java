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
package org.gedcomx.util;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.webcohesion.enunciate.metadata.Facet;
import com.webcohesion.enunciate.metadata.Ignore;
import org.gedcomx.links.Link;
import org.gedcomx.rt.GedcomxConstants;

import java.util.Map;

/**
 * Class is used for documentation purposes only.
 *
 * @author Ryan Heaton
 */
@Ignore
abstract class HasLinksMixin {

  /**
   * The list of hypermedia links. Links are not specified by GEDCOM X core, but as extension elements by GEDCOM X RS.
   *
   * @return The list of hypermedia links. Links are not specified by GEDCOM X core, but as extension elements by GEDCOM X RS.
   */
  @JsonProperty ("links")
  @Facet ( GedcomxConstants.FACET_GEDCOMX_RS )
  public abstract Map<String, Link> getLinks();

  /**
   * The list of hypermedia links. Links are not specified by GEDCOM X core, but as extension elements by GEDCOM X RS.
   *
   * @param links The list of hypermedia links. Links are not specified by GEDCOM X core, but as extension elements by GEDCOM X RS.
   */
  @JsonProperty ("links")
  public abstract void setLinks(Map<String, Link> links);

}
