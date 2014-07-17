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

/**
 * Representation of a list of VocabElement objects
 */
public class VocabElementList {

  private String title;
  private String description;
  private URI uri;
  private String id;
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
