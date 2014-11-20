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

import org.codehaus.enunciate.json.JsonName;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonProperty;
import org.gedcomx.common.Qualifier;
import org.gedcomx.rt.json.JsonElementWrapper;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;
import java.util.List;

/**
 * FamilySearch-specific metadata about an artifact.
 *
 * @author Ryan Heaton
 */
@XmlRootElement
@JsonElementWrapper (name = "artifactMetadata")
@XmlType ( name = "ArtifactMetadata" )
public class ArtifactMetadata {

  private String filename;
  private List<Qualifier> qualifiers;
  private Integer width;
  private Integer height;

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
  @JsonName ( "qualifiers" )
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
}
