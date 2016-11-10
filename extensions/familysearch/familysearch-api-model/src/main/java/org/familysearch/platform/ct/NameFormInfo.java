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

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.webcohesion.enunciate.metadata.qname.XmlQNameEnumRef;
import org.gedcomx.common.URI;
import org.gedcomx.rt.json.JsonElementWrapper;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

/**
 * Information about a name form.
 */
@XmlRootElement
@JsonElementWrapper ( name = "nameFormInfo" )
@XmlType ( name = "NameFormInfo" )
public class NameFormInfo {

  private URI order;

  public NameFormInfo() {
  }

  /**
   * The ordering of the name form.
   *
   * @return The ordering of the name form.
   */
  @XmlAttribute
  @XmlQNameEnumRef (NameFormOrder.class)
  public URI getOrder() {
    return order;
  }

  /**
   * The ordering of this name form
   *
   * @param order The ordering of this name form.
   */
  public void setOrder(URI order) {
    this.order = order;
  }

  /**
   * The enum referencing the known resolution of this name form order.
   *
   * @return The enum referencing the known resolution of this name form order.
   */
  @XmlTransient
  @JsonIgnore @org.codehaus.jackson.annotate.JsonIgnore
  public NameFormOrder getKnownOrder() {
    return getOrder() == null ? null : NameFormOrder.fromQNameURI(getOrder());
  }

  /**
   * The enum referencing the known resolution of this name form order.
   *
   * @param nameFormOrder The enum referencing the known resolution of this name form order.
   */
  @JsonIgnore @org.codehaus.jackson.annotate.JsonIgnore
  public void setKnownOrder(NameFormOrder nameFormOrder) {
    setOrder(nameFormOrder == null ? null : nameFormOrder.toQNameURI());
  }
}
