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
package org.familysearch.platform.ct;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.fasterxml.jackson.annotation.JsonInclude;

import org.gedcomx.rt.json.JsonElementWrapper;

/**
 * Extra information about a person.
 */
@XmlRootElement
@JsonElementWrapper( name = "personInfo" )
@XmlType( name = "PersonInfo" )
@JsonInclude( JsonInclude.Include.NON_NULL )
public class PersonInfo {
  private Boolean readOnly = false;

  public PersonInfo() {
  }

  public PersonInfo(Boolean readOnly) {
    this.readOnly = readOnly;
  }

  /**
   * If this person is a readOnly person
   *
   * @return The readOnly URI if this person is readOnly
   */
  @XmlAttribute
  public Boolean isReadOnly() {
    return readOnly;
  }

  /**
   * If this person is a readOnly person
   *
   * @param readOnly If this person is a readOnly person
   */
  public void setReadOnly(Boolean readOnly) {
    this.readOnly = readOnly;
  }
}
