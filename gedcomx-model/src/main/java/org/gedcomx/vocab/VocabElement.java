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
package org.gedcomx.vocab;

import org.gedcomx.common.TextValue;
import org.gedcomx.common.URI;

import java.util.ArrayList;
import java.util.List;

/**
 * Representation of a vocabulary element
 */
public class VocabElement implements Comparable<VocabElement> {

  private String id;
  private URI uri;
  private URI subclass;
  private URI type;
  private transient String sortName;
  private List<TextValue> labels = new ArrayList<TextValue>();
  private List<TextValue> descriptions = new ArrayList<TextValue>();

  // This is only present (OPTIONALLY) when used as an "Entries in a List" object
  private URI sublist;
  // This is only present (OPTIONALLY) when used as an "Entries in a List" object
  private transient Integer position;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public URI getUri() {
    return uri;
  }

  public void setUri(URI uri) {
    this.uri = uri;
  }

  public URI getSubclass() {
    return subclass;
  }

  public void setSubclass(URI subclass) {
    this.subclass = subclass;
  }

  public URI getType() {
    return type;
  }

  public void setType(URI type) {
    this.type = type;
  }

  public String getSortName() {
    return sortName;
  }

  public void setSortName(String sortName) {
    this.sortName = sortName;
  }

  public List<TextValue> getLabels() {
    return labels;
  }

  public VocabElement addLabel(String label, String locale) {
    this.labels.add(new TextValue(label).lang(locale));
    return this;
  }

  public List<TextValue> getDescriptions() {
    return descriptions;
  }

  public VocabElement addDescription(String description, String locale) {
    this.descriptions.add(new TextValue(description).lang(locale));
    return this;
  }

  public URI getSublist() {
    return sublist;
  }

  public void setSublist(URI sublist) {
    this.sublist = sublist;
  }

  public Integer getPosition() {
    return position;
  }

  public void setPosition(Integer position) {
    this.position = position;
  }


  @Override
  public int compareTo(VocabElement o) {
    // A position value overrides and trumps sortName
    // Otherwise, compare alphabetically against sortName
    // Then arbitrarily compare on Term Type, Concept, and Id
    int pos = 0;
    Integer oPosition = o.getPosition();
    if (position != null) {
      pos = (oPosition == null) ? position : position - oPosition;
    }
    else if (oPosition != null) {
      pos = oPosition;
    }
    if (pos == 0) { // Either positions are the same or null
      pos = sortName.compareTo(o.getSortName());
    }
    if (pos == 0) {
      pos = type.toString().compareTo(o.getType().toString());
    }
    if (pos == 0) {
      pos = subclass.toString().compareTo(o.getSubclass().toString());
    }
    if (pos == 0) {
      pos = uri.toString().compareTo(o.getUri().toString());
    }

    return pos;
  }

}
