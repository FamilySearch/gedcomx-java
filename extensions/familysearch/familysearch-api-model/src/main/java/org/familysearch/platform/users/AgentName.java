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
package org.familysearch.platform.users;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.gedcomx.common.TextValue;
import org.gedcomx.common.URI;

import jakarta.xml.bind.annotation.XmlAttribute;


@JsonInclude ( JsonInclude.Include.NON_NULL )
public class AgentName extends TextValue {
  private URI type;

  public AgentName() {
  }

  public AgentName(URI type, String name) {
    this (type, name, null);
  }

  public AgentName(URI type, String name, String lang) {
    this.setType(type);
    this.setLang(lang);
    this.setValue(name);
  }

  @XmlAttribute
  public URI getType() {
    return type;
  }

  public void setType(URI type) {
    this.type = type;
  }
}
