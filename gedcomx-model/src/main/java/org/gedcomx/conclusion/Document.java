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
package org.gedcomx.conclusion;

import org.codehaus.enunciate.Facet;
import org.codehaus.enunciate.qname.XmlQNameEnumRef;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.gedcomx.common.*;
import org.gedcomx.links.Link;
import org.gedcomx.rt.GedcomxConstants;
import org.gedcomx.rt.GedcomxModelVisitor;
import org.gedcomx.rt.json.JsonElementWrapper;
import org.gedcomx.source.SourceDescription;
import org.gedcomx.source.SourceReference;
import org.gedcomx.types.ConfidenceLevel;
import org.gedcomx.types.DocumentType;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;


/**
 * An abstract document that contains derived (conclusionary) text -- for example, a transcription or researcher analysis.
 */
@XmlRootElement
@JsonElementWrapper (name = "documents")
@XmlType(name = "Document", propOrder = { "text" })
@Facet ( name = GedcomxConstants.FACET_FS_FT_UNSUPPORTED )
public class Document extends Conclusion implements HasText, Attributable {
  
  public static final String TEXT_TYPE_PLAIN = "plain";
  public static final String TEXT_TYPE_XHTML = "xhtml";

  private Boolean extracted;
  private URI type;
  private String textType;
  private String text;

  @Override
  public Document id(String id) {
    return (Document) super.id(id);
  }

  @Override
  public Document extensionElement(Object element) {
    return (Document) super.extensionElement(element);
  }

  @Override
  public Document link(String rel, URI href) {
    return (Document) super.link(rel, href);
  }

  @Override
  public Document link(Link link) {
    return (Document) super.link(link);
  }

  @Override
  public Document lang(String lang) {
    return (Document) super.lang(lang);
  }

  @Override
  public Document confidence(URI confidence) {
    return (Document) super.confidence(confidence);
  }

  @Override
  public Document confidence(ConfidenceLevel confidence) {
    return (Document) super.confidence(confidence);
  }

  @Override
  public Document source(SourceReference sourceReference) {
    return (Document) super.source(sourceReference);
  }

  @Override
  public Document source(SourceDescription source) {
    return (Document) super.source(source);
  }

  @Override
  public Document note(Note note) {
    return (Document) super.note(note);
  }

  @Override
  public Document attribution(Attribution attribution) {
    return (Document) super.attribution(attribution);
  }

  @Override
  public Document analysis(ResourceReference analysis) {
    return (Document) super.analysis(analysis);
  }

  @Override
  public Document analysis(Document analysis) {
    return (Document) super.analysis(analysis);
  }

  @Override
  public Document analysis(URI analysis) {
    return (Document) super.analysis(analysis);
  }

  @Override
  public Document sortKey(String sortKey) {
    return (Document) super.sortKey(sortKey);
  }

  /**
   * Whether this document has been identified as "extracted", meaning it captures information extracted from a single source.
   *
   * @return Whether this document has been identified as "extracted".
   */
  @XmlAttribute
  public Boolean getExtracted() {
    return extracted;
  }

  /**
   * Whether this document has been identified as "extracted", meaning it captures information extracted from a single source.
   *
   * @param extracted Whether this document has been identified as "extracted".
   */
  public void setExtracted(Boolean extracted) {
    this.extracted = extracted;
  }

  /**
   * Build up this document with an extracted flag.
   * @param extracted The extracted flag.
   * @return this.
   */
  public Document extracted(Boolean extracted) {
    setExtracted(extracted);
    return this;
  }

  /**
   * The type of the document.
   *
   * @return The type of the document.
   */
  @XmlAttribute
  @XmlQNameEnumRef (DocumentType.class)
  public URI getType() {
    return type;
  }

  /**
   * The type of the document.
   *
   * @param type The type of the document.
   */
  public void setType(URI type) {
    this.type = type;
  }

  /**
   * Build up this document with a type.
   *
   * @param type The type.
   * @return this.
   */
  public Document type(URI type) {
    setType(type);
    return this;
  }

  /**
   * Build up this document with a type.
   *
   * @param type The type.
   * @return this.
   */
  public Document type(DocumentType type) {
    setKnownType(type);
    return this;
  }

  /**
   * Whether the text of the document is to be interpreted as plain text (as opposed to XHTML).
   * 
   * @return Whether the text of the document is to be interpreted as plain text (as opposed to XHTML).
   */
  @XmlTransient
  @JsonIgnore
  @org.codehaus.enunciate.json.JsonIgnore
  public boolean isPlainText() {
    return this.textType == null || TEXT_TYPE_PLAIN.equals(this.textType);
  }

  /**
   * Whether the text of the document is to be interpreted as XHTML text (as opposed to plain text).
   * 
   * @return Whether the text of the document is to be interpreted as XHTML text (as opposed to plain text).
   */
  @XmlTransient
  @JsonIgnore
  @org.codehaus.enunciate.json.JsonIgnore
  public boolean isXhtmlText() {
    return TEXT_TYPE_XHTML.equals(this.textType);
  }

  /**
   * The text type of the document.
   * 
   * @return The text type of the document.
   */
  @XmlAttribute
  public String getTextType() {
    return textType;
  }

  /**
   * The text type of the document.
   * 
   * @param textType The text type of the document.
   */
  public void setTextType(String textType) {
    this.textType = textType;
  }

  /**
   * Build up this document with a text type.
   *
   * @param textType The text type.
   * @return this.
   */
  public Document textType(String textType) {
    setTextType(textType);
    return this;
  }

  /**
   * The enum referencing the known type of the document, or {@link org.gedcomx.types.DocumentType#OTHER} if not known.
   *
   * @return The enum referencing the known type of the document, or {@link org.gedcomx.types.DocumentType#OTHER} if not known.
   */
  @XmlTransient
  @JsonIgnore
  public org.gedcomx.types.DocumentType getKnownType() {
    return getType() == null ? null : DocumentType.fromQNameURI(getType());
  }

  /**
   * Set the type of this document from a known enumeration of document types.
   *
   * @param knownType the document type.
   */
  @JsonIgnore
  public void setKnownType(org.gedcomx.types.DocumentType knownType) {
    setType(knownType == null ? null : knownType.toQNameURI());
  }

  /**
   * The document text.
   *
   * @return The document text.
   */
  public String getText() {
    return text;
  }

  /**
   * The document text.
   *
   * @param text The document text.
   */
  public void setText(String text) {
    this.text = text;
  }

  /**
   * Build up this document with some text.
   *
   * @param text The text.
   * @return this.
   */
  public Document text(String text) {
    setText(text);
    return this;
  }

  /**
   * Accept a visitor.
   *
   * @param visitor The visitor.
   */
  public void accept(GedcomxModelVisitor visitor) {
    visitor.visitDocument(this);
  }

  /**
   * Embed a document.
   *
   * @param document The document to embed.
   */
  public void embed(Document document) {
    this.extracted = this.extracted == null ? document.extracted : this.extracted;
    this.type = this.type == null ? document.type : this.type;
    this.textType = this.textType == null ? document.textType : this.textType;
    this.text = this.text == null ? document.text : this.text;
    super.embed(document);
  }
}
