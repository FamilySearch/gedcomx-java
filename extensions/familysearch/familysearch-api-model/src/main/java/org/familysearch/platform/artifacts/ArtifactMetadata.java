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

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.webcohesion.enunciate.metadata.qname.XmlQNameEnumRef;
import org.gedcomx.common.Qualifier;
import org.gedcomx.common.URI;
import org.gedcomx.rt.json.JsonElementWrapper;

import jakarta.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * FamilySearch-specific metadata about an artifact.
 *
 * @author Ryan Heaton
 */
@XmlRootElement
@JsonElementWrapper(name = "artifactMetadata")
@XmlType(name = "ArtifactMetadata")
@JsonInclude ( JsonInclude.Include.NON_NULL )
public class ArtifactMetadata {

  private String filename;
  private List<Qualifier> qualifiers;
  private Integer width;
  private Integer height;
  private Long size;
  private URI screeningState;
  private URI displayState;
  private Boolean editable;

  /**
   * The original filename of the memories item.
   *
   * @return The original filename of the memories item.
   */
  public String getFilename() {
    return filename;
  }

  /**
   * The original filename of the memories item.
   *
   * @param filename The original filename of the memories item.
   */
  public void setFilename(String filename) {
    this.filename = filename;
  }

  /**
   * The qualifiers associated with this artifact.
   *
   * @return The qualifiers associated with this artifact.
   */
  @XmlElement ( name = "qualifier" )
  @JsonProperty ( "qualifiers" )
  public List<Qualifier> getQualifiers() {
    return qualifiers;
  }

  /**
   * Set the qualifiers associated with this fact.
   *
   * @param qualifiers qualifiers to associate with this fact.
   */
  @JsonProperty ( "qualifiers" )
  public void setQualifiers(List<Qualifier> qualifiers) {
    this.qualifiers = qualifiers;
  }

  /**
   * The known type of the artifact.
   *
   * @return The type of the artifact.
   */
  @XmlTransient
  @JsonIgnore
  public ArtifactType getKnownType() {
    if (this.qualifiers != null) {
      for (Qualifier qualifier : this.qualifiers) {
        if (qualifier.getName() != null) {
          ArtifactType artifactType = ArtifactType.fromQNameURI(qualifier.getName());
          artifactType = artifactType == ArtifactType.OTHER ? null : artifactType;
          if (artifactType != null) {
            return artifactType;
          }
        }
      }
    }

    return null;
  }

  /**
   * The known type of the artifact.
   *
   * @param type The type of the artifact.
   */
  @JsonIgnore
  public void setKnownType(ArtifactType type) {
    this.qualifiers = new ArrayList<Qualifier>(Collections.singletonList(new Qualifier(type)));
  }

  /**
   * The width of the artifact (presumably an image).
   *
   * @return The width of the artifact (presumably an image).
   */
  public Integer getWidth() {
    return width;
  }

  /**
   * The width of the artifact (presumably an image).
   *
   * @param width The width of the artifact (presumably an image).
   */
  public void setWidth(Integer width) {
    this.width = width;
  }

  /**
   * The height of the artifact (presumably an image).
   *
   * @return The height of the artifact (presumably an image).
   */
  public Integer getHeight() {
    return height;
  }

  /**
   * The height of the artifact (presumably an image).
   *
   * @param height The height of the artifact (presumably an image).
   */
  public void setHeight(Integer height) {
    this.height = height;
  }

  /**
   * The size of the artifact.
   *
   * @return The size of the artifact.
   */
  public Long getSize() {
    return size;
  }

  /**
   * The size of the artifact.
   *
   * @param size The size of the artifact.
   */
  public void setSize(Long size) {
    this.size = size;
  }

  /**
   * The screening state of the artifact.
   *
   * @return The screening state of the artifact.
   */
  @XmlAttribute
  @XmlQNameEnumRef(ArtifactScreeningState.class)
  @Deprecated
  public URI getScreeningState() {
    return screeningState;
  }

  /**
   * The screening state of the artifact.
   *
   * @param screeningState The screening state of the artifact.
   */
  @Deprecated
  public void setScreeningState(URI screeningState) {
    this.screeningState = screeningState;
  }

  /**
   * The known screening state of the artifact.
   *
   * @return The known screening state of the artifact.
   */
  @XmlTransient
  @JsonIgnore
  @Deprecated
  public ArtifactScreeningState getKnownScreeningState() {
    return getScreeningState() == null ? null : ArtifactScreeningState.fromQNameURI(getScreeningState());
  }

  /**
   * The known screening state of the artifact.
   *
   * @param screeningState The known screening state of the artifact.
   */
  @JsonIgnore
  @Deprecated
  public void setKnownScreeningState(ArtifactScreeningState screeningState) {
    setScreeningState(screeningState == null ? null : screeningState.toQNameURI());
  }

  /**
   * The display state of the artifact.
   *
   * @return The display state of the artifact.
   */
  @XmlAttribute
  @XmlQNameEnumRef(ArtifactDisplayState.class)
  public URI getDisplayState() {
    return displayState;
  }

  /**
   * The display state of the artifact.
   *
   * @param displayState The display state of the artifact.
   */
  public void setDisplayState(URI displayState) {
    this.displayState = displayState;
  }

  /**
   * The known display state of the artifact.
   *
   * @return The known display state of the artifact.
   */
  @XmlTransient
  @JsonIgnore
  public ArtifactDisplayState getKnownDisplayState() {
    return getDisplayState() == null ? null : ArtifactDisplayState.fromQNameURI(getDisplayState());
  }

  /**
   * The known display state of the artifact.
   *
   * @param displayState The known display state of the artifact.
   */
  @JsonIgnore
  public void setKnownDisplayState(ArtifactDisplayState displayState) {
    setDisplayState(displayState == null ? null : displayState.toQNameURI());
  }


  /**
   * Whether or not the artifact is editable by the current user.
   *
   * @return True if the artifact is editable by the current user; false otherwise.
   */
  public Boolean isEditable() {
    return editable;
  }

  /**
   * Whether or not the artifact is editable by the current user.
   *
   * @param editable True if the artifact is editable by the current user; false otherwise.
   */
  public void setEditable(Boolean editable) {
    this.editable = editable;
  }
}
