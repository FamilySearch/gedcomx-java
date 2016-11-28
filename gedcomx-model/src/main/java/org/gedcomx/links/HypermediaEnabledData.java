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
package org.gedcomx.links;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.webcohesion.enunciate.metadata.Facet;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.gedcomx.common.ExtensibleData;
import org.gedcomx.common.URI;
import org.gedcomx.rt.GedcomxConstants;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.List;

/**
 * An data type that supports hypermedia controls (i.e. links).
 *
 * @author Ryan Heaton
 */
@XmlType ( name = "HypermediaEnabledData" )
@JsonInclude ( JsonInclude.Include.NON_NULL )
public abstract class HypermediaEnabledData extends ExtensibleData implements SupportsLinks {

  private List<Link> links;

  /**
   * The list of hypermedia links. Links are not specified by GEDCOM X core, but as extension elements by GEDCOM X RS.
   *
   * @return The list of hypermedia links. Links are not specified by GEDCOM X core, but as extension elements by GEDCOM X RS.
   */
  @Override
  @XmlElement (name = "link")
  @JsonProperty ("links") @org.codehaus.jackson.annotate.JsonProperty ("links")
  @Facet ( GedcomxConstants.FACET_GEDCOMX_RS )
  public List<Link> getLinks() {
    return links;
  }

  /**
   * The list of hypermedia links. Links are not specified by GEDCOM X core, but as extension elements by GEDCOM X RS.
   *
   * @param links The list of hypermedia links. Links are not specified by GEDCOM X core, but as extension elements by GEDCOM X RS.
   */
  @Override
  @JsonProperty ("links") @org.codehaus.jackson.annotate.JsonProperty ("links")
  public void setLinks(List<Link> links) {
    this.links = links;
  }

  /**
   * Build up this data with a link.
   *
   * @param link The link.
   * @return this.
   */
  public HypermediaEnabledData link(Link link) {
    addLink(link);
    return this;
  }

  /**
   * Build up this data with a link.
   *
   * @param rel The rel.
   * @param href The href.
   * @return this.
   */
  public HypermediaEnabledData link(String rel, URI href) {
    addLink(rel, href);
    return this;
  }

  /**
   * Add a hypermedia link. Links are not specified by GEDCOM X core, but as extension elements by GEDCOM X RS.
   *
   * @param link The hypermedia link. Links are not specified by GEDCOM X core, but as extension elements by GEDCOM X RS.
   */
  @Override
  public void addLink(Link link) {
    if (this.links == null) {
      setLinks(new ArrayList<Link>());
    }

    this.links.add(link);
  }

  /**
   * Add a hypermedia link.
   *
   * @param rel The link rel.
   * @param href The target URI.
   */
  @Override
  public void addLink(String rel, URI href) {
    addLink(new Link(rel, href));
  }

  /**
   * Add a templated link.
   *
   * @param rel The link rel.
   * @param template The link template.
   */
  @Override
  public void addTemplatedLink(String rel, String template) {
    Link link = new Link();
    link.setRel(rel);
    link.setTemplate(template);
    addLink(link);
  }

  /**
   * Get a link by its rel. Links are not specified by GEDCOM X core, but as extension elements by GEDCOM X RS.
   *
   * @param rel The link rel.
   * @return The link by rel.
   */
  @Override
  public Link getLink(String rel) {
    List<Link> links = getLinks(rel);
    Link link = null;
    if (!links.isEmpty()) {
      link = links.get(0);
    }
    return link;
  }

  /**
   * Get a list of links by rel. Links are not specified by GEDCOM X core, but as extension elements by GEDCOM X RS.
   *
   * @param rel The rel of the links.
   * @return The link.
   */
  @Override
  public List<Link> getLinks(String rel) {
    ArrayList<Link> links = new ArrayList<Link>();
    if (this.links != null) {
      for (Link link : getLinks()) {
        if (rel.equals(link.getRel())) {
          links.add(link);
        }
      }
    }
    return links;
  }

  protected void embed(HypermediaEnabledData data) {
    super.embed(data);
  }
}
