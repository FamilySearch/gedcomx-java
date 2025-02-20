package org.gedcomx.vocab;

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
import org.gedcomx.common.URI;

import java.util.ArrayList;
import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Representation of a list of VocabElement objects
 */
@Schema(description = "Representation of a list of VocabElement objects")
public class VocabElementList {

  @Schema(description = "The title of the list of elements.")
  private String title;

  @Schema(description = "The description of the list of elements.")
  private String description;

  @Schema(description = "The URI of the list of elements.")
  private URI uri;

  @Schema(description = "The id of the list of elements.")
  private String id;

  @Schema(description = "The list of elements.")
  private List<VocabElement> elements = new ArrayList<VocabElement>();

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public URI getUri() {
    return uri;
  }

  public void setUri(URI uri) {
    this.uri = uri;
  }

  public List<VocabElement> getElements() {
    return elements;
  }

  public VocabElementList addElement(VocabElement element) {
    this.elements.add(element);
    return this;
  }

}
