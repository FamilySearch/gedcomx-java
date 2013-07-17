/**
 * Copyright 2011-2012 Intellectual Reserve, Inc.
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
package org.gedcomx.atom;

import org.codehaus.enunciate.Facet;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.gedcomx.common.HasTransientProperties;
import org.gedcomx.rt.GedcomxConstants;
import org.gedcomx.rt.SupportsExtensionElements;

import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;
import java.util.*;

/**
 * @author Ryan Heaton
 */
@XmlType ( name = "ExtensibleElement" )
@Facet ( name = GedcomxConstants.FACET_GEDCOMX_RS )
public abstract class ExtensibleElement extends CommonAttributes implements SupportsExtensionElements, HasTransientProperties {

  private List<Object> extensionElements;
  protected final Map<String, Object> transientProperties = new TreeMap<String, Object>();

  /**
   * Custom extension elements.
   *
   * @return Custom extension elements.
   */
  @XmlAnyElement (lax = true)
  @JsonIgnore
  public List<Object> getExtensionElements() {
    return extensionElements;
  }

  /**
   * Custom extension elements.
   *
   * @param extensionElements Custom extension elements.
   */
  @JsonIgnore
  public void setExtensionElements(List<Object> extensionElements) {
    this.extensionElements = extensionElements;
  }

  /**
   * Add an extension element.
   *
   * @param element The extension element to add.
   */
  public void addExtensionElement(Object element) {
    if (this.extensionElements == null) {
      this.extensionElements = new ArrayList<Object>();
    }

    this.extensionElements.add(element);
  }

  /**
   * Finds the first extension of a specified type.
   *
   * @param clazz The type.
   * @return The extension, or null if none found.
   */
  @SuppressWarnings ( {"unchecked"} )
  public <E> E findExtensionOfType(Class<E> clazz) {
    if (this.extensionElements != null) {
      for (Object extension : extensionElements) {
        if (clazz.isInstance(extension)) {
          return (E) extension;
        }
      }
    }

    return null;
  }

  /**
   * Find the extensions of a specified type.
   *
   * @param clazz The type.
   * @return The extensions, possibly empty but not null.
   */
  @SuppressWarnings ( {"unchecked"} )
  public <E> List<E> findExtensionsOfType(Class<E> clazz) {
    List<E> ext = new ArrayList<E>();
    if (this.extensionElements != null) {
      for (Object extension : extensionElements) {
        if (clazz.isInstance(extension)) {
          ext.add((E) extension);
        }
      }
    }

    return ext;
  }

  @JsonIgnore
  @XmlTransient
  @Override
  public Map<String, Object> getTransientProperties() {
    return Collections.unmodifiableMap(this.transientProperties);
  }

  /**
   * Get a transient (non-serialized) property.
   *
   * @param name The name of the property.
   * @return The property.
   */
  public Object getTransientProperty(String name) {
    return this.transientProperties.get(name);
  }

  /**
   * Set a transient (non-serialized) property.
   *
   * @param name the name of the property.
   * @param value the property value.
   */
  public void setTransientProperty(String name, Object value) {
    this.transientProperties.put(name, value);
  }
}
