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

import org.codehaus.enunciate.qname.XmlQNameEnumRef;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.gedcomx.common.URI;
import org.gedcomx.rt.json.JsonElementWrapper;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

/**
 * FamilySearch-specific metadata about an artifact.
 *
 * @author Ryan Heaton
 */
@XmlRootElement
@JsonElementWrapper (name = "artifactMetadata")
@XmlType ( name = "ArtifactMetadata" )
public class ArtifactMetadata
{

  private String filename;
  private URI artifactType;
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
   * The type of the gender.
   *
   * @return The type of the gender.
   */
  @XmlAttribute
  @XmlQNameEnumRef (ArtifactType.class)
  public URI getArtifactType() {
    return artifactType;
  }

  /**
   * The type of the gender.
   *
   * @param artifactType The type of the gender.
   */
  public void setArtifactType(URI artifactType) {
    this.artifactType = artifactType;
  }

  /**
   * The known type of the gender.
   *
   * @return The type of the gender.
   */
  @XmlTransient
  @JsonIgnore
  public ArtifactType getKnownType() {
    return getArtifactType() == null ? null : ArtifactType.fromQNameURI( getArtifactType() );
  }

  /**
   * The type of the gender.
   *
   * @param type The type of the gender.
   */
  @JsonIgnore
  public void setKnownType(ArtifactType type) {
    setArtifactType(type == null ? null : URI.create(org.codehaus.enunciate.XmlQNameEnumUtil.toURIValue(type)));
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
