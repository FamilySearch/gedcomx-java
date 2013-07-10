/**
 * Copyright 2011-2012 Intellectual Reserve, Inc.
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
package org.gedcomx.atom;

import org.codehaus.enunciate.Facet;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.gedcomx.Gedcomx;
import org.gedcomx.atom.rt.AtomModelVisitor;
import org.gedcomx.rt.GedcomxConstants;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * @author Ryan Heaton
 */
@XmlType ( name = "Content" )
@SuppressWarnings("gedcomx:no_id")
@Facet ( name = GedcomxConstants.FACET_GEDCOMX_RS )
public final class Content {

  private String type = GedcomxConstants.GEDCOMX_XML_MEDIA_TYPE;
  private Gedcomx gedcomx;

  /**
   * The type of the content.
   *
   * @return The type of the content.
   */
  @XmlAttribute
  @JsonIgnore
  public String getType() {
    return type;
  }

  /**
   * The type of the content.
   *
   * @param type The type of the content.
   */
  @JsonIgnore
  public void setType(String type) {
    this.type = type;
  }

  /**
   * The genealogical data associated with this entry.
   *
   * @return The genealogical data associated with this entry.
   */
  @XmlElement ( name = "gedcomx", namespace = GedcomxConstants.GEDCOMX_NAMESPACE )
  public Gedcomx getGedcomx() {
    return gedcomx;
  }

  /**
   * The genealogical data associated with this entry.
   *
   * @param gedcomx The genealogical data associated with this entry.
   */
  public void setGedcomx(Gedcomx gedcomx) {
    this.gedcomx = gedcomx;
  }

  /**
   * Accept a visitor.
   *
   * @param visitor The visitor to accept.
   */
  public void accept(AtomModelVisitor visitor) {
    visitor.visitAtomContent(this);
  }
}
