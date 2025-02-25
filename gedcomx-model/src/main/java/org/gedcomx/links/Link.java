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

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.webcohesion.enunciate.metadata.Facet;
import org.gedcomx.common.URI;
import org.gedcomx.rt.GedcomxConstants;
import org.gedcomx.rt.json.HasJsonKey;
import org.gedcomx.rt.json.JsonElementWrapper;

import javax.xml.XMLConstants;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.xml.bind.annotation.*;
import java.util.Arrays;
import java.util.Set;
import java.util.TreeSet;

/**
 * A hypermedia link, used to drive the state of a hypermedia-enabled genealogical data application.
 *
 * @author Ryan Heaton
 * @see <a href="http://tools.ietf.org/html/rfc5988">RFC 5988</a>
 * @see <a href="http://tools.ietf.org/html/rfc4287#section-4.2.7">Atom Syndication Format, Section 4.2.7</a>
 */
@XmlRootElement
@XmlType ( name = "Link", propOrder = {"rel", "href", "template", "title", "type", "accept", "allow", "hreflang"})
@JsonElementWrapper ( name = "links" )
@SuppressWarnings("gedcomx:no_id")
@Facet ( GedcomxConstants.FACET_GEDCOMX_RS )
@JsonInclude ( JsonInclude.Include.NON_NULL )
@Schema(description = "Data representation of a node - transfer object.")
public class Link implements HasJsonKey {

  /**
   * The list of link rels that support multi-valued links. Be careful about editing this because
   * it might break backwards-compatibility with JSON clients.
   */
  public static final Set<String> NON_UNIQUE_RELS = new TreeSet<String>(Arrays.asList("alternate", "bookmark", "related", "item"));

  @Schema(description = "The link relationship.")
  private String rel;

  @Schema(description = "The target URI of the link.")
  private URI href;

  @Schema(description = "A URI template per RFC 6570, used to link to a range of URIs, such as for the purpose of linking to a query.")
  private String template;

  @Schema(description = "Metadata about the available media type(s) of the resource being linked to. The value of the \"type\" attribute is as " +
      "defined by the HTTP specification, RFC 2616, Section 3.7. Note that this attribute can be considered an \"Update Control (CU)\" per Amundsen, M. (2011). Hypermedia APIs with HTML5 and Node. O'Reilly.")
  private String type;

  @Schema(description = "Metadata about the available media type(s) of the resource being linked to update (i.e. change the state of) the resource being " +
      "linked to. The value of the \"accept\" attribute is as defined by the HTTP specification, RFC 2616, Section 3.7. Note that this attribute can be considered an \"Read Control (CR)\" per Amundsen, M. (2011). Hypermedia APIs with HTML5 and Node. O'Reilly.")
  private String accept;

  @Schema(description = "Metadata about the allowable methods that can be used to transition to the resource being linked. The value of the \"allow\" " +
      "attribute is as defined by the HTTP specification, RFC 2616, Section 14.7. Note that this attribute can be considered an \"Method Control (CM)\" per Amundsen, M. (2011). Hypermedia APIs with HTML5 and Node. O'Reilly.")
  private String allow;

  @Schema(description = "The language of the resource being linked to. Note that this attribute can be considered an \"Update Control (CU)\" per Amundsen, " +
      "M. (2011). Hypermedia APIs with HTML5 and Node. O'Reilly.")
  private String hreflang;

  @Schema(description = "Human-readable information about the link.")
  private String title;

  @Schema(description = "The number of elements in the page, if this link refers to a page of resources.")
  private Integer count;

  @Schema(description = "The index of the offset of the page, if this link refers to a page of resources.")
  private Integer offset;

  @Schema(description = "The total number of results in the page to which this links, if this link refers to a page of resources.")
  private Integer results;

  public Link() {
  }

  public Link(String rel, URI href) {
    this.rel = rel;
    this.href = href;
  }
  
  public Link(Link copy) {
    this.rel = copy.rel;
    this.href = copy.href;
    this.template = copy.template;
    this.type = copy.type;
    this.accept = copy.accept;
    this.allow = copy.allow;
    this.hreflang = copy.hreflang;
    this.title = copy.title;
    this.count = copy.count;
    this.offset = copy.offset;
    this.results = copy.results;
  }

  @XmlTransient
  @JsonIgnore
  @Override
  public boolean isHasUniqueKey() {
    return this.rel != null && !NON_UNIQUE_RELS.contains(this.rel);
  }

  /**
   * The link relationship.
   *
   * @return The link relationship.
   */
  @XmlAttribute
  @JsonIgnore
  public String getRel() {
    return rel;
  }

  /**
   * The link relationship.
   *
   * @param rel The link relationship.
   */
  @JsonIgnore
  public void setRel(String rel) {
    this.rel = rel;
  }

  /**
   * Build out this link with a rel.
   *
   * @param rel The rel.
   * @return this.
   */
  public Link rel(String rel) {
    setRel(rel);
    return this;
  }

  /**
   * The json key that is used define this link in a map.
   *
   * @return The json key that is used define this link in a map.
   */
  @XmlTransient
  @JsonIgnore
  @Override
  public String getJsonKey() {
    return getRel();
  }

  /**
   * The json key that is used define this link in a map.
   *
   * @param jsonKey The json key that is used define this link in a map.
   */
  @Override
  @JsonIgnore
  public void setJsonKey(String jsonKey) {
    setRel(jsonKey);
  }

  /**
   * The target IRI of the link.
   *
   * @return The target IRI of the link.
   */
  @XmlAttribute
  @XmlSchemaType (name = "anyURI", namespace = XMLConstants.W3C_XML_SCHEMA_NS_URI)
  public URI getHref() {
    return href;
  }

  /**
   * The link URI.
   *
   * @param href The link URI.
   */
  public void setHref(URI href) {
    this.href = href;
  }

  /**
   * Build out this link with an href.
   *
   * @param href The href.
   * @return this.
   */
  public Link href(URI href) {
    setHref(href);
    return this;
  }

  /**
   * A URI template per <a href="http://tools.ietf.org/html/rfc6570">RFC 6570</a>, used to link to a range of
   * URIs, such as for the purpose of linking to a query. A link specifying a template can be formally referred to
   * as a "Templated Link (LT)" per Amundsen, M. (2011). <i>Hypermedia APIs with HTML5 and Node</i>. O'Reilly.
   *
   * @return A URI template per <a href="http://tools.ietf.org/html/rfc6570">RFC 6570</a>, used to link to a range of
   * URIs, such as for the purpose of linking to a query.
   */
  @XmlAttribute
  public String getTemplate() {
    return template;
  }

  /**
   * A URI template per <a href="http://tools.ietf.org/html/rfc6570">RFC 6570</a>, used to link to a range of
   * URIs, such as for the purpose of linking to a query. A link specifying a template can be formally referred to
   * as a "Templated Link (LT)" per Amundsen, M. (2011). <i>Hypermedia APIs with HTML5 and Node</i>. O'Reilly.
   *
   * @param template A URI template per <a href="http://tools.ietf.org/html/rfc6570">RFC 6570</a>, used to link to a range of
   * URIs, such as for the purpose of linking to a query.
   */
  public void setTemplate(String template) {
    this.template = template;
  }

  /**
   * Build out this link with a template.
   * @param template The template
   * @return this.
   */
  public Link template(String template) {
    setTemplate(template);
    return this;
  }

  /**
   * Metadata about the available media type(s) of the resource being linked to. The value of the "type" attribute is
   * as defined by the HTTP specification, <a href="http://www.ietf.org/rfc/rfc2616.txt">RFC 2616</a>, Section 3.7.
   *
   * Note that this attribute can be considered an "Update Control (CU)" per Amundsen, M. (2011). <i>Hypermedia APIs with HTML5 and Node</i>. O'Reilly.
   *
   * @return Metadata about the available media type(s) of the resource being linked to.
   */
  @XmlAttribute
  public String getType() {
    return type;
  }

  /**
   * Metadata about the available media type(s) of the resource being linked to. The value of the "type" attribute is
   * as defined by the HTTP specification, <a href="http://www.ietf.org/rfc/rfc2616.txt">RFC 2616</a>, Section 3.7.
   *
   * Note that this attribute can be considered an "Read Control (CR)" per Amundsen, M. (2011). <i>Hypermedia APIs with HTML5 and Node</i>. O'Reilly.
   *
   * @param type Metadata about the available media type(s) of the resource being linked to.
   */
  public void setType(String type) {
    this.type = type;
  }

  /**
   * Build out this link with a type.
   * @param type The type.
   * @return this.
   */
  public Link type(String type) {
    setType(type);
    return this;
  }

  /**
   * Metadata about the acceptable media type(s) that can be used to update (i.e. change the state of) the resource being linked to. The value of
   * the "accept" attribute is as defined by the HTTP specification, <a href="http://www.ietf.org/rfc/rfc2616.txt">RFC 2616</a>, Section 3.7.
   *
   * Note that this attribute can be considered an "Read Control (CR)" per Amundsen, M. (2011). <i>Hypermedia APIs with HTML5 and Node</i>. O'Reilly.
   *
   * @return Metadata about the available media type(s) of the resource being linked to.
   */
  @XmlAttribute
  public String getAccept() {
    return accept;
  }

  /**
   * Metadata about the media type(s) that can be used to update (i.e. change the state of) the resource being linked to. The value of
   * the "accept" attribute is as defined by the HTTP specification, <a href="http://www.ietf.org/rfc/rfc2616.txt">RFC 2616</a>, Section 3.7.
   *
   * Note that this attribute can be considered an "Update Control (CU)" per Amundsen, M. (2011). <i>Hypermedia APIs with HTML5 and Node</i>. O'Reilly.
   *
   * @param accept Metadata about the available media type(s) of the resource being linked to.
   */
  public void setAccept(String accept) {
    this.accept = accept;
  }

  /**
   * Build out this link with an accept.
   *
   * @param accept The accept.
   * @return this.
   */
  public Link accept(String accept) {
    setAccept(accept);
    return this;
  }

  /**
   * Metadata about the allowable methods that can be used to transition to the resource being linked. The value of
   * the "allow" attribute is as defined by the HTTP specification, <a href="http://www.ietf.org/rfc/rfc2616.txt">RFC 2616</a>, Section 14.7.
   *
   * Note that this attribute can be considered an "Method Control (CM)" per Amundsen, M. (2011). <i>Hypermedia APIs with HTML5 and Node</i>. O'Reilly.
   *
   * @return Metadata about the available media type(s) of the resource being linked to.
   */
  @XmlAttribute
  public String getAllow() {
    return allow;
  }

  /**
   * Metadata about the allowable methods that can be used to transition to the resource being linked. The value of
   * the "allow" attribute is as defined by the HTTP specification, <a href="http://www.ietf.org/rfc/rfc2616.txt">RFC 2616</a>, Section 14.7.
   *
   * Note that this attribute can be considered an "Method Control (CM)" per Amundsen, M. (2011). <i>Hypermedia APIs with HTML5 and Node</i>. O'Reilly.
   *
   * @param allow Metadata about the available media type(s) of the resource being linked to.
   */
  public void setAllow(String allow) {
    this.allow = allow;
  }

  /**
   * Build out this link with an 'allow'.
   *
   * @param allow The allow.
   * @return this.
   */
  public Link allow(String allow) {
    setAllow(allow);
    return this;
  }

  /**
   * The language of the resource being linked to.
   *
   * Note that this attribute can be considered an "Update Control (CU)" per Amundsen, M. (2011). <i>Hypermedia APIs with HTML5 and Node</i>. O'Reilly.
   *
   * @return The language of the resource being linked to.
   */
  @XmlAttribute
  public String getHreflang() {
    return hreflang;
  }

  /**
   * The language of the resource being linked to.
   *
   * Note that this attribute can be considered an "Update Control (CU)" per Amundsen, M. (2011). <i>Hypermedia APIs with HTML5 and Node</i>. O'Reilly.
   *
   * @param hreflang The language of the resource being linked to.
   */
  public void setHreflang(String hreflang) {
    this.hreflang = hreflang;
  }

  /**
   * Build out this link with an href lang.
   * @param hreflang The hreflang.
   * @return this.
   */
  public Link hreflang(String hreflang) {
    setHreflang(hreflang);
    return this;
  }

  /**
   * Human-readable information about the link.
   *
   * @return Human-readable information about the link.
   */
  @XmlAttribute
  public String getTitle() {
    return title;
  }

  /**
   * Human-readable information about the link.
   *
   * @param title Human-readable information about the link.
   */
  public void setTitle(String title) {
    this.title = title;
  }

  /**
   * Build out this link with a title.
   *
   * @param title The title.
   * @return this.
   */
  public Link title(String title) {
    setTitle(title);
    return this;
  }

  /**
   * The number of elements in the page, if this link refers to a page of resources.
   *
   * @return The number of elements in the page, if this link refers to a page of resources.
   */
  @XmlAttribute
  public Integer getCount() {
    return count;
  }

  /**
   * The number of elements in the page, if this link refers to a page of resources.
   *
   * @param count The number of elements in the page, if this link refers to a page of resources.
   */
  public void setCount(Integer count) {
    this.count = count;
  }

  /**
   * Build out this link with a count.
   *
   * @param count The count.
   * @return this.
   */
  public Link count(Integer count) {
    setCount(count);
    return this;
  }

  /**
   * The index of the offset of the page, if this link refers to a page of resources.
   *
   * @return The index of the offset of the page, if this link refers to a page of resources.
   */
  @XmlAttribute
  public Integer getOffset() {
    return offset;
  }

  /**
   * The index of the offset of the page, if this link refers to a page of resources.
   *
   * @param offset The index of the offset of the page, if this link refers to a page of resources.
   */
  public void setOffset(Integer offset) {
    this.offset = offset;
  }

  /**
   * Build out this link with an offset.
   *
   * @param offset The offset.
   * @return this.
   */
  public Link offset(Integer offset) {
    setOffset(offset);
    return this;
  }

  /**
   * The total number of results in the page to which this links, if this link refers to a page of resources.
   *
   * @return The total number of results in the page to which this links, if this link refers to a page of resources.
   */
  @XmlAttribute
  public Integer getResults() {
    return results;
  }

  /**
   * The total number of results in the page to which this links, if this link refers to a page of resources.
   *
   * @param results The total number of results in the page to which this links, if this link refers to a page of resources.
   */
  public void setResults(Integer results) {
    this.results = results;
  }

  /**
   * Build out this link with total results.
   *
   * @param results The total results count.
   * @return this.
   */
  public Link results(Integer results) {
    setResults(results);
    return this;
  }

  /**
   * Format this link as a Link header per RFC 5988.
   *
   * @return The value of this link formatted per RFC 5988.
   */
  @XmlTransient
  @JsonIgnore
  public String getHttpHeaderValue() {
    StringBuilder builder = new StringBuilder("<");

    if (this.href != null) {
      builder.append(this.href.toString());
    }

    builder.append(">");

    if (this.rel != null) {
      builder.append("; rel=\"").append(this.rel).append("\"");
    }

    if (this.template != null) {
      builder.append("; template=\"").append(this.template).append("\"");
    }

    if (this.type != null) {
      builder.append("; type=\"").append(this.type).append("\"");
    }

    if (this.accept != null) {
      builder.append("; accept=\"").append(this.accept).append("\"");
    }

    if (this.allow != null) {
      builder.append("; allow=\"").append(this.allow).append("\"");
    }

    if (this.hreflang != null) {
      builder.append("; hreflang=\"").append(this.hreflang).append("\"");
    }

    if (this.title != null) {
      builder.append("; title=\"").append(this.title).append("\"");
    }

    if (this.count != null) {
      builder.append("; count=\"").append(this.title).append("\"");
    }

    if (this.offset != null) {
      builder.append("; offset=\"").append(this.title).append("\"");
    }

    if (this.results != null) {
      builder.append("; results=\"").append(this.title).append("\"");
    }

    return builder.toString();
  }

  @Override
  public String toString() {
    return getHttpHeaderValue();
  }
}
