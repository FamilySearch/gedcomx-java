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

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.webcohesion.enunciate.metadata.Facet;
import com.webcohesion.enunciate.metadata.qname.XmlQNameEnumRef;
import org.gedcomx.common.Attribution;
import org.gedcomx.common.Note;
import org.gedcomx.common.ResourceReference;
import org.gedcomx.common.URI;
import org.gedcomx.links.Link;
import org.gedcomx.rt.GedcomxConstants;
import org.gedcomx.rt.GedcomxModelVisitor;
import org.gedcomx.rt.json.JsonElementWrapper;
import org.gedcomx.source.SourceDescription;
import org.gedcomx.source.SourceReference;
import org.gedcomx.types.ConfidenceLevel;
import org.gedcomx.types.NamePartType;
import org.gedcomx.types.NameType;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.xml.bind.annotation.*;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Stream;


/**
 * A name conclusion.
 *
 * @author Ryan Heaton
 */
@XmlType ( name = "Name", propOrder = { "preferred", "date", "nameForms"} )
@XmlRootElement
@JsonElementWrapper ( name = "names" )
@JsonInclude ( JsonInclude.Include.NON_NULL )
@Schema(description = "A name conclusion.")
public class Name extends Conclusion {

  /**
   * @see org.gedcomx.types.NameType
   */
  @Schema(description = "The type of the name.")
  private URI type;

  @Schema(description = "The date the name was first applied or adopted.")
  private Date date;

  @Schema(description = "Alternate forms of the name, such as the romanized form of a non-latin name.")
  private List<NameForm> nameForms;

  @Schema(description = "Whether the conclusion is preferred above other conclusions of the same type. Useful, for example, for display purposes.")
  private Boolean preferred;

  public Name() {
  }

  public Name(String fullText, NamePart... parts) {
    addNameForm(new NameForm(fullText, parts));
  }

  public Name(Name copy) {
    super(copy);
    this.type = copy.type;
    this.date = copy.date == null ? null : new Date(copy.date);
    this.nameForms = copy.nameForms == null ? null : new ArrayList<>(copy.nameForms.stream().map(NameForm::new).toList());
    this.preferred = copy.preferred;
  }

  @Override
  public Name id(String id) {
    return (Name) super.id(id);
  }

  @Override
  public Name extensionElement(Object element) {
    return (Name) super.extensionElement(element);
  }

  @Override
  public Name link(String rel, URI href) {
    return (Name) super.link(rel, href);
  }

  @Override
  public Name link(Link link) {
    return (Name) super.link(link);
  }

  @Override
  public Name lang(String lang) {
    return (Name) super.lang(lang);
  }

  @Override
  public Name confidence(URI confidence) {
    return (Name) super.confidence(confidence);
  }

  @Override
  public Name confidence(ConfidenceLevel confidence) {
    return (Name) super.confidence(confidence);
  }

  @Override
  public Name source(SourceReference sourceReference) {
    return (Name) super.source(sourceReference);
  }

  @Override
  public Name source(SourceDescription source) {
    return (Name) super.source(source);
  }

  @Override
  public Name note(Note note) {
    return (Name) super.note(note);
  }

  @Override
  public Name attribution(Attribution attribution) {
    return (Name) super.attribution(attribution);
  }

  @Override
  public Name analysis(ResourceReference analysis) {
    return (Name) super.analysis(analysis);
  }

  @Override
  public Name analysis(Document analysis) {
    return (Name) super.analysis(analysis);
  }

  @Override
  public Name analysis(URI analysis) {
    return (Name) super.analysis(analysis);
  }

  @Override
  public Name sortKey(String sortKey) {
    return (Name) super.sortKey(sortKey);
  }

  /**
   * The type of the name.
   *
   * @return The type of the name.
   */
  @XmlAttribute
  @XmlQNameEnumRef (NameType.class)
  public URI getType() {
    return type;
  }

  /**
   * The type of the name.
   *
   * @param type The type of the name.
   */
  public void setType(URI type) {
    this.type = type;
  }

  /**
   * Build up this name with a type.
   * @param type The type.
   * @return this.
   */
  public Name type(URI type) {
    setType(type);
    return this;
  }

  /**
   * Build up this name with a type.
   * @param type The type.
   * @return this.
   */
  public Name type(NameType type) {
    setKnownType(type);
    return this;
  }

  /**
   * The enum referencing the known name type, or {@link org.gedcomx.types.NameType#OTHER} if not known.
   *
   * @return The enum referencing the known name type, or {@link org.gedcomx.types.NameType#OTHER} if not known.
   */
  @XmlTransient
  @JsonIgnore
  public NameType getKnownType() {
    return getType() == null ? null : NameType.fromQNameURI(getType());
  }

  /**
   * Set the name type from an enumeration of known name types.
   *
   * @param knownType The known type.
   */
  @JsonIgnore
  public void setKnownType(NameType knownType) {
    setType(knownType == null ? null : knownType.toQNameURI());
  }

  /**
   * The date the name was first applied or adopted.
   *
   * @return The date the name was first applied or adopted.
   */
  @Facet ( GedcomxConstants.FACET_FS_FT_UNSUPPORTED )
  public Date getDate() {
    return date;
  }

  /**
   * The date the name was first applied or adopted.
   *
   * @param date The date the name was first applied or adopted.
   */
  public void setDate(Date date) {
    this.date = date;
  }

  /**
   * Build up this name with a date.
   * @param date The date.
   * @return this.
   */
  public Name date(Date date) {
    setDate(date);
    return this;
  }

  /**
   * Create a stream for the name forms of this name.
   *
   * @return a stream for the name forms of this name.
   */
  public Stream<NameForm> nameForms() {
    return this.nameForms == null ? Stream.empty() : this.nameForms.stream();
  }

  /**
   * Alternate forms of the name, such as the romanized form of a non-latin name.
   *
   * @return Alternate forms of the name, such as the romanized form of a non-latin name.
   */
  @XmlElement (name = "nameForm")
  @JsonProperty ("nameForms")
  public List<NameForm> getNameForms() {
    return nameForms;
  }

  /**
   * The first name form of this name.
   *
   * @return The first name form of this name.
   */
  @JsonIgnore
  @XmlTransient
  public NameForm getNameForm() {
    return this.nameForms != null && this.nameForms.size() > 0 ? this.nameForms.get(0) : null;
  }

  /**
   * Alternate forms of the name, such as the romanized form of a non-latin name.
   *
   * @param nameForms Alternate forms of the name, such as the romanized form of a non-latin name.
   */
  @JsonProperty ("nameForms")
  public void setNameForms(List<NameForm> nameForms) {
    this.nameForms = nameForms;
  }

  /**
   * Build up this name with a name form.
   *
   * @param nameForm The name form.
   * @return this.
   */
  public Name nameForm(NameForm nameForm) {
    addNameForm(nameForm);
    return this;
  }

  /**
   * Add a name form to the list of name forms.
   *
   * @param nameForm The name form to be added.
   */
  public void addNameForm(NameForm nameForm) {
    if (nameForm != null) {
      if (nameForms == null) {
        nameForms = new LinkedList<NameForm>();
      }
      nameForms.add(nameForm);
    }
  }

  /**
   * Whether the conclusion is preferred above other conclusions of the same type. Useful, for example, for display purposes.
   *
   * @return Whether the conclusion is preferred above other conclusions of the same type. Useful, for example, for display purposes.
   */
  public Boolean getPreferred() {
    return preferred;
  }

  /**
   * Whether the conclusion is preferred above other conclusions of the same type. Useful, for example, for display purposes.
   *
   * @param preferred Whether the conclusion is preferred above other conclusions of the same type. Useful, for example, for display purposes.
   */
  public void setPreferred(Boolean preferred) {
    this.preferred = preferred;
  }

  /**
   * Build up this name with a preferred flag.
   * @param preferred The preferred flag.
   * @return this.
   */
  public Name preferred(Boolean preferred) {
    setPreferred(preferred);
    return this;
  }

  /**
   * Gets a specific part of the name
   * @param namePartType specific part of the name to retrieve
   * @return the specific part of the name that matches the given NamePartType
   */
  public String getPart(NamePartType namePartType) {
    if(this.nameForms == null || this.nameForms.isEmpty() ||
      this.nameForms.get(0) == null ||
      this.nameForms.get(0).getParts() == null) {
      return null;
    }

    for(NamePart namePart : this.nameForms.get(0).getParts()) {
      if(namePart.getKnownType() == namePartType) {
        return namePart.getValue();
      }
    }

    return null;
  }

  @Override
  public String toString() {
    return "type=" + getKnownType() + ",nameForms[0]=" + (nameForms == null ? "null" : nameForms.get(0).getFullText()) + ",pref=" + getPreferred();
  }

  /**
   * Accept a visitor.
   *
   * @param visitor The visitor.
   */
  public void accept(GedcomxModelVisitor visitor) {
    visitor.visitName(this);
  }
}
