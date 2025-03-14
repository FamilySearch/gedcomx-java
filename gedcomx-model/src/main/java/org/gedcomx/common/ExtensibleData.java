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
package org.gedcomx.common;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import org.gedcomx.rt.SupportsExtensionElements;

import jakarta.xml.bind.JAXBElement;
import jakarta.xml.bind.annotation.*;
import java.util.*;

/**
 * A set of data that supports extension elements.
 *
 * @author Ryan Heaton
 */
@XmlType( name = "ExtensibleData" )
@Schema(description = "A set of data that supports extension elements.")
public abstract class ExtensibleData implements SupportsExtensionElements, HasTransientProperties {

  @Schema(description = "A local, context-specific id for the data.")
  private String id;

  @Schema(description = "Custom extension elements for a conclusion.")
  protected List<Object> extensionElements;

  protected final Map<String, Object> transientProperties = new TreeMap<String, Object>();

  protected ExtensibleData() {
  }

  protected ExtensibleData(ExtensibleData copy) {
    this.id = copy.id;
    this.extensionElements = copy.extensionElements == null ? null : new ArrayList<>(copy.extensionElements);
    //transient properties are transient and won't get copied.
    ///this.transientProperties.putAll(copy.transientProperties);
  }

  /**
   * A local, context-specific id for the data.
   *
   * @return A local, context-specific id for the data.
   */
  @XmlAttribute
  public String getId() {
    return id;
  }

  /**
   * A local, context-specific id for the data.
   *
   * @param id A local, context-specific id for the data.
   */
  public void setId(String id) {
    this.id = id;
  }

  /**
   * Build up this object with an id.
   *
   * @param id The id.
   * @return this.
   */
  public ExtensibleData id(String id) {
    this.id = id;
    return this;
  }

  /**
   * Custom extension elements for a conclusion.
   *
   * @return Custom extension elements for a conclusion.
   */
  @XmlAnyElement (lax = true)
  @JsonIgnore
  public List<Object> getExtensionElements() {
    return extensionElements;
  }

  /**
   * Custom extension elements for a conclusion.
   *
   * @param extensionElements Custom extension elements for a conclusion.
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

  public ExtensibleData extensionElement(Object element) {
    addExtensionElement(element);
    return this;
  }

  /**
   * Remove extension elements of a given type.
   *
   * @param clazz The type of extension element to remove.
   * @param <E> The type of extension elements.
   * @return The removed extension elements.
   */
  @SuppressWarnings ( {"unchecked"} )
  public <E> List<E> removeExtensionElements(Class<E> clazz) {
    List<E> removed = new ArrayList<E>();
    if (this.extensionElements != null) {
      Iterator<Object> elements = this.extensionElements.iterator();
      while (elements.hasNext()) {
        E next = (E) elements.next();
        if (clazz.isInstance(next)) {
          removed.add(next);
          elements.remove();
        }
      }
    }
    return removed;
  }

  /**
   * Sets an extension element by first removing all previous elements of the same type, then adding it to the list.
   *
   * @param element The element to set.
   */
  public void setExtensionElement(Object element) {
    removeExtensionElements(element.getClass());
    addExtensionElement(element);
  }

  /**
   * Finds the first extension of a specified type.
   *
   * @param clazz The type.
   * @param <E> The type of extension elements.
   * @return The extension, or null if none found.
   */
  @SuppressWarnings ( {"unchecked"} )
  public <E> E findExtensionOfType(Class<E> clazz) {
    List<E> candidates = findExtensionsOfType(clazz);

    if (candidates.size() > 0) {
      return candidates.get(0);
    }

    return null;
  }

  /**
   * Find the extensions of a specified type.
   *
   * @param clazz The type.
   * @param <E> The type.
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

  /**
   * Finds the first extension of a specified type in the given name and namespace.
   *
   * @param clazz The type.
   * @param <E> The type of extension elements.
   * @param name The name of the extension element.
   * @param namespace The namespace of the extension element.
   * @return The extension, or null if none found.
   */
  @SuppressWarnings ( {"unchecked"} )
  public <E> E findExtensionOfType(Class<E> clazz, String name, String namespace) {
    List<E> candidates = findExtensionsOfType(clazz, name, namespace);

    if (candidates.size() > 0) {
      return candidates.get(0);
    }

    return null;
  }

  /**
   * Find the extension elements of a specified type in the given name and namespace.
   *
   * @param clazz The type of the extension element.
   * @param <E> The type of extension elements.
   * @param name The name of the extension element.
   * @param namespace The namespace of the extension element.
   * @return The extensions, possibly empty but not null.
   */
  @SuppressWarnings ( {"unchecked"} )
  public <E> List<E> findExtensionsOfType(Class<E> clazz, String name, String namespace) {
    List<E> ext = new ArrayList<E>();
    if (this.extensionElements != null) {
      for (Object extension : extensionElements) {
        if (JAXBElement.class.isInstance(extension)) {
          JAXBElement<E> element = (JAXBElement<E>) extension;
          if (clazz.isInstance(element.getValue()) && element.getName().getLocalPart().equals(name) && element.getName().getNamespaceURI().equals(namespace)) {
            ext.add(element.getValue());
          }
        }
      }
    }

    return ext;
  }

  /**
   * Get the transient properties.
   *
   * @return the transient properties.
   */
  @JsonIgnore
  @XmlTransient
  @Override
  public Map<String, Object> getTransientProperties() {
    return Collections.unmodifiableMap(transientProperties);
  }

  /**
   * Get a transient (non-serialized) property.
   *
   * @param name The name of the property.
   * @return The property.
   */
  @Override
  public Object getTransientProperty(String name) {
    return this.transientProperties.get(name);
  }

  /**
   * Set a transient (non-serialized) property.
   *
   * @param name the name of the property.
   * @param value the property value.
   */
  @Override
  public void setTransientProperty(String name, Object value) {
    this.transientProperties.put(name, value);
  }

  protected void embed(ExtensibleData data) {
    if (data.extensionElements != null) {
      this.extensionElements = this.extensionElements == null ? new ArrayList<Object>() : this.extensionElements;
      this.extensionElements.addAll(data.extensionElements);
    }
  }

  /**
   * Provide a simple toString() method.
   */
  @Override
  public String toString() {
    return "id: " + id;
  }
}
