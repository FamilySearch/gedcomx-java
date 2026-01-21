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
package org.gedcomx.rt.json.jackson3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.namespace.QName;

import jakarta.xml.bind.JAXBElement;
import org.gedcomx.rt.GedcomNamespaceManager;
import org.gedcomx.rt.SupportsExtensionAttributes;
import org.gedcomx.rt.SupportsExtensionElements;
import org.gedcomx.rt.json.HasJsonKey;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import tools.jackson.core.JacksonException;
import tools.jackson.core.JsonGenerator;
import tools.jackson.databind.DatabindException;
import tools.jackson.databind.SerializationContext;
import tools.jackson.databind.ser.BeanPropertyWriter;
import tools.jackson.databind.ser.BeanSerializer;

/**
 * Custom JSON serializer for @XmlAnyElement fields/properties.
 * <p>
 * <b>Note:</b> Requires Jackson 3.x and Java 17+ runtime.
 */
public class ExtensibleObjectSerializer extends BeanSerializer {

  public ExtensibleObjectSerializer(BeanSerializer src) {
    super(src);
  }

  @Override
  protected void _serializePropertiesFiltered(
    Object bean,
    JsonGenerator g,
    SerializationContext provider,
    Object filterId) throws JacksonException {

    super._serializePropertiesFiltered(bean, g, provider, filterId);
    serializeExtensions(bean, g, provider);
  }

  @Override
  protected void _serializePropertiesMaybeView(
    Object bean,
    JsonGenerator g,
    SerializationContext provider,
    BeanPropertyWriter[] props) throws JacksonException {

    super._serializePropertiesMaybeView(bean, g, provider, props);
    serializeExtensions(bean, g, provider);
  }

  @Override
  protected void _serializePropertiesNoView(
    Object bean,
    JsonGenerator gen,
    SerializationContext provider,
    BeanPropertyWriter[] props) throws JacksonException {

    super._serializePropertiesNoView(bean, gen, provider, props);
    serializeExtensions(bean, gen, provider);
  }

  private void serializeExtensions(Object bean, JsonGenerator jgen, SerializationContext provider)
    throws JacksonException {

    if (bean instanceof SupportsExtensionAttributes) {
      serializeExtensionAttributes((SupportsExtensionAttributes) bean, jgen);
    }

    if (bean instanceof SupportsExtensionElements) {
      serializeExtensionElements((SupportsExtensionElements) bean, jgen, provider);
    }
  }

  private void serializeExtensionAttributes(SupportsExtensionAttributes value, JsonGenerator jgen)
    throws JacksonException {

    Map<QName, String> extensionAttributes = value.getExtensionAttributes();
    if (extensionAttributes != null) {
      for (Map.Entry<QName, String> attr : extensionAttributes.entrySet()) {
        jgen.writeStringProperty(attr.getKey().getNamespaceURI() + attr.getKey().getLocalPart(), attr.getValue());
      }
    }
  }

  public void serializeExtensionElements(
    SupportsExtensionElements value,
    JsonGenerator jgen,
    SerializationContext provider) throws JacksonException {

    List<Object> extensionElements = value.getExtensionElements();
    if (extensionElements != null) {
      Map<String, List<Object>> extensionProperties = new HashMap<>();
      for (Object element : extensionElements) {
        if (element != null) {
          String name;
          if (element instanceof Element) {
            Element el = (Element) element;
            name = GedcomNamespaceManager.nameFromQName(el.getNamespaceURI(), el.getLocalName());
          }
          else if (element instanceof JAXBElement) {
            name = GedcomNamespaceManager.getJsonNameForWrapperName(((JAXBElement) element).getName());
            if (name == null) {
              name = GedcomNamespaceManager.nameFromQName(((JAXBElement) element).getName().getNamespaceURI(), ((JAXBElement) element).getName().getLocalPart());
            }
            element = ((JAXBElement) element).getValue();
          }
          else {
            name = GedcomNamespaceManager.getJsonName(element.getClass());
            if (name == null) {
              throw DatabindException.from(jgen, "Unable to serialize custom element " + value +
                                               " because it's not a JAXBElement, DOM element, nor is it annotated with either @JsonElementWrapper or @XmlRootElement.");
            }
          }

          List<Object> propList = extensionProperties.computeIfAbsent(name, k -> new ArrayList<>());

          propList.add(element);
        }
      }

      for (Map.Entry<String, List<Object>> prop : extensionProperties.entrySet()) {
        if (prop.getValue().get(0) instanceof HasJsonKey) {
          //we're serialize out this list as a keyed map.
          jgen.writeName(prop.getKey());
          KeyedListSerializer.serializeGeneric(prop.getValue(), jgen, provider);
        }
        else {
          jgen.writeArrayPropertyStart(prop.getKey());
          for (Object element : prop.getValue()) {
            if (element instanceof Element) {
              serializeElement((Element) element, jgen);
            }
            else {
              provider.findTypedValueSerializer(element.getClass(), true).serialize(element, jgen, provider);
            }
          }
          jgen.writeEndArray();
        }
      }
    }
  }

  private void serializeElement(Element element, JsonGenerator jgen) throws JacksonException {
    boolean startObjectWritten = false;
    boolean writeValue = false;
    StringBuilder value = new StringBuilder();
    for (Node child = element.getFirstChild(); child != null; child = child.getNextSibling()) {
      switch (child.getNodeType()) {
        case Node.ATTRIBUTE_NODE:
          if (!startObjectWritten) {
            jgen.writeStartObject();
            startObjectWritten = true;
          }
          jgen.writeStringProperty(child.getLocalName(), child.getNodeValue());
          break;
        case Node.TEXT_NODE:
        case Node.CDATA_SECTION_NODE:
          writeValue = true;
          value.append(child.getNodeValue());
          break;
        case Node.ELEMENT_NODE:
          if (!startObjectWritten) {
            jgen.writeStartObject();
            startObjectWritten = true;
          }
          jgen.writeName(child.getNodeName());
          serializeElement((Element)child, jgen);
          break;
      }
    }
    if (startObjectWritten) {
      if (writeValue) {
        jgen.writeStringProperty("value", value.toString());
      }
      jgen.writeEndObject();
    }
    else if (writeValue) {
      jgen.writeString(value.toString());
    }
    else {
      //empty object.
      jgen.writeStartObject();
      jgen.writeEndObject();
    }
  }
}
