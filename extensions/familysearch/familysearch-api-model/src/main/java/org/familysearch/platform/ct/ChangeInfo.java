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

import org.codehaus.enunciate.json.JsonIgnore;
import org.codehaus.enunciate.qname.XmlQNameEnumRef;
import org.gedcomx.common.ResourceReference;
import org.gedcomx.common.URI;
import org.gedcomx.rt.json.JsonElementWrapper;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;


/**
 * Information about a change.
 */
@XmlRootElement
@JsonElementWrapper ( name = "changeInfo" )
@XmlType ( name = "ChangeInfo" )
public class ChangeInfo {

  private URI operation;
  private URI objectType;
  private URI objectModifier;
  private String reason;
  private ResourceReference parent;
  private ResourceReference resulting;
  private ResourceReference original;
  private ResourceReference removed;

  public ChangeInfo() {
  }

  public ChangeInfo(ChangeType type) {
    this.operation = type.getOperation().toQNameURI();
    this.objectType = type.getObjectType().toQNameURI();
    this.objectModifier = type.getObjectModifier() != null ? type.getObjectModifier().toQNameURI() : null;
  }

  /**
   * The operation of the change.
   *
   * @return The operation of the change.
   */
  @XmlAttribute
  @XmlQNameEnumRef(ChangeOperation.class)
  public URI getOperation() {
    return operation;
  }

  /**
   * The operation of the change.
   *
   * @param operation The operation of the change.
   */
  public void setOperation(URI operation) {
    this.operation = operation;
  }

  /**
   * The enum referencing the known operation of the change.
   *
   * @return The enum referencing the known operation of the change.
   */
  @XmlTransient
  @JsonIgnore
  @com.fasterxml.jackson.annotation.JsonIgnore
  public ChangeOperation getKnownOperation() {
    return getOperation() == null ? null : ChangeOperation.fromQNameURI(getOperation());
  }

  /**
   * Set the operation of this change from a known enumeration of change operations.
   *
   * @param knownOperation the change operation.
   */
  @JsonIgnore
  @com.fasterxml.jackson.annotation.JsonIgnore
  public void setKnownOperation(ChangeOperation knownOperation) {
    setOperation(knownOperation == null ? null : knownOperation.toQNameURI());
  }

  /**
   * The type of the object to which the operation applies.
   *
   * @return The type of the object to which the operation applies.
   */
  @XmlAttribute
  @XmlQNameEnumRef(ChangeObjectType.class)
  public URI getObjectType() {
    return objectType;
  }

  /**
   * The type of the object to which the operation applies.
   *
   * @param objectType The type of the object to which the operation applies.
   */
  public void setObjectType(URI objectType) {
    this.objectType = objectType;
  }

  /**
   * The enum referencing the known object type of the change.
   *
   * @return The enum referencing the known object type of the change.
   */
  @XmlTransient
  @JsonIgnore
  @com.fasterxml.jackson.annotation.JsonIgnore
  public ChangeObjectType getKnownObjectType() {
    return getObjectType() == null ? null : ChangeObjectType.fromQNameURI(getObjectType());
  }

  /**
   * Set the object of this change from a known enumeration of change objects.
   *
   * @param knownObject the change object.
   */
  @JsonIgnore
  @com.fasterxml.jackson.annotation.JsonIgnore
  public void setKnownObjectType(ChangeObjectType knownObject) {
    setObjectType(knownObject == null ? null : knownObject.toQNameURI());
  }

  /**
   * An optional modifier for the object to which the operation applies. For example, if the object is a <tt>Fact</tt>, a
   * modifier could be applied to indicate that fact applies to a person, couple, or child-and-parents relationship.
   *
   * @return An optional modifier for the object to which the operation applies.
   */
  @XmlAttribute
  @XmlQNameEnumRef(ChangeObjectModifier.class)
  public URI getObjectModifier() {
    return objectModifier;
  }

  /**
   * An optional modifier for the object to which the operation applies. For example, if the object is a <tt>Fact</tt>, a
   * modifier could be applied to indicate that fact applies to a person, couple, or child-and-parents relationship.
   *
   * @param objectModifier An optional modifier for the object to which the operation applies.
   */
  public void setObjectModifier(URI objectModifier) {
    this.objectModifier = objectModifier;
  }

  /**
   * The enum referencing the known object modifier of the change.
   *
   * @return The enum referencing the known object modifier of the change.
   */
  @XmlTransient
  @JsonIgnore
  @com.fasterxml.jackson.annotation.JsonIgnore
  public ChangeObjectModifier getKnownObjectModifier() {
    return getObjectModifier() == null ? null : ChangeObjectModifier.fromQNameURI(getObjectModifier());
  }

  /**
   * Set the object of this change from a known enumeration of change objects.
   *
   * @param knownObject the change object.
   */
  @JsonIgnore
  @com.fasterxml.jackson.annotation.JsonIgnore
  public void setKnownObjectModifier(ChangeObjectModifier knownObject) {
    setObjectModifier(knownObject == null ? null : knownObject.toQNameURI());
  }

  /**
   * The reason for the change.
   *
   * @return The reason for the change.
   */
  @XmlAttribute
  public String getReason() {
    return reason;
  }

  /**
   * The reason for the change.
   *
   * @param reason The reason for the change.
   */
  public void setReason( String reason ) {
    this.reason = reason;
  }

  /**
   * The parent change that triggered, caused, or included this change.
   *
   * @return The parent change that triggered, caused, or included this change.
   */
  public ResourceReference getParent() {
    return parent;
  }

  /**
   * The parent change that triggered, caused, or included this change.
   *
   * @param parent The parent change that triggered, caused, or included this change.
   */
  public void setParent(ResourceReference parent) {
    this.parent = parent;
  }

  /**
   * The subject representing the result of the change.
   *
   * @return The subject representing the result of the change.
   */
  public ResourceReference getResulting() {
    return resulting;
  }

  /**
   * The subject representing the result of the change.
   *
   * @param resulting The subject representing the result of the change.
   */
  public void setResulting(ResourceReference resulting) {
    this.resulting = resulting;
  }

  /**
   * The subject representing the original value(s) that existed before the change.
   *
   * @return The subject representing the original value(s) that existed before the change.
   */
  public ResourceReference getOriginal() {
    return original;
  }

  public void setOriginal(ResourceReference original) {
    this.original = original;
  }


  /**
   * The subject representing the removed value(s) that existed before the change.
   *
   * @return The subject representing the removed value(s) that existed before the change.
   */
  public ResourceReference getRemoved() {
    return removed;
  }

  public void setRemoved(ResourceReference removed) {
    this.removed = removed;
  }
}
