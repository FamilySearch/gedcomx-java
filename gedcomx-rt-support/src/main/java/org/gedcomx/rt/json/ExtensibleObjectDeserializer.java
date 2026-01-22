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
package org.gedcomx.rt.json;

import java.util.ArrayList;
import java.util.List;

import javax.xml.XMLConstants;
import javax.xml.namespace.QName;

import jakarta.xml.bind.JAXBElement;
import org.gedcomx.rt.GedcomNamespaceManager;
import org.gedcomx.rt.SupportsExtensionAttributes;
import org.gedcomx.rt.SupportsExtensionElements;
import tools.jackson.core.JsonParser;
import tools.jackson.core.JsonToken;
import tools.jackson.databind.DatabindException;
import tools.jackson.databind.DeserializationContext;
import tools.jackson.databind.deser.bean.BeanDeserializer;

/**
 * Custom JSON serializer for @XmlAnyElement fields/properties
 *
 * @author Ryan Heaton
 */
public class ExtensibleObjectDeserializer extends BeanDeserializer {

  public ExtensibleObjectDeserializer(BeanDeserializer src) {
    super(src);
  }

  @Override
  protected void handleUnknownProperty(
    JsonParser jp,
    DeserializationContext ctxt,
    Object beanOrClass,
    String propName) {

    if (_ignoreAllUnknown) {
      jp.skipChildren();
      return;
    }

    if (beanOrClass instanceof SupportsExtensionElements target) {
      //first check if it's a known json type
      Class<?> type = GedcomNamespaceManager.getKnownJsonType(propName);
      if (type != null) {
        //it's a known json type.
        if (HasJsonKey.class.isAssignableFrom(type)) {
          for (Object ext : readKeyedMapOf(type, jp)) {
            target.addExtensionElement(ext);
          }
        }
        else {
          //otherwise just deserialize as a list and go.
          for (Object ext : readArrayOf(type, jp)) {
            target.addExtensionElement(ext);
          }
        }
        return;
      }
      else {
        QName wrapper = getWrapperName(propName);
        type = GedcomNamespaceManager.getWrappedTypeForJsonName(propName);
        if (type != null) {
          List<?> objects = readArrayOf(type, jp);
          for (Object ext : objects) {
            target.addExtensionElement(new JAXBElement(wrapper, type, ext));
          }
          return;
        }
      }
    }

    if (beanOrClass instanceof SupportsExtensionAttributes && jp.currentToken().isScalarValue()) {
      ((SupportsExtensionAttributes) beanOrClass).addExtensionAttribute(getWrapperName(propName), jp.getString());
      return;
    }

    jp.skipChildren();
  }

  private QName getWrapperName(String propName) {
    QName qname = GedcomNamespaceManager.findWrapperNameForJsonName(propName);

    if (qname == null && propName.indexOf(':') >= 0) {
      //if the propname has a ':', we'll treat it as a qname, because all qnames I know have a ':' in them.
      List<String> knownNS = new ArrayList<>(GedcomNamespaceManager.getKnownPrefixes().keySet());
      knownNS.add(XMLConstants.XML_NS_URI + "#");
      for (String ns : knownNS) {
        if (propName.startsWith(ns)) {
          String nsURI = propName.substring(0, ns.length());
          String localPart = propName.substring(ns.length());
          if (!localPart.isEmpty()) {
            qname = new QName(nsURI, localPart);
          }
          break;
        }
      }

      if (qname == null && propName.indexOf('#') > 0) {
        //well, it wasn't a known namespace; let's try separating ns from local part with a #
        int hashIndex = propName.indexOf('#');
        String nsURI = propName.substring(0, hashIndex);
        String localPart = propName.substring(hashIndex + 1);
        if (!localPart.isEmpty()) {
          qname = new QName(nsURI, localPart);
        }
      }

      if (qname == null && propName.lastIndexOf('/') > 0) {
        //still haven't found it; let's try separating ns from local part with the last '/'
        int hashIndex = propName.lastIndexOf('/');
        String nsURI = propName.substring(0, hashIndex);
        String localPart = propName.substring(hashIndex + 1);
        if (!localPart.isEmpty()) {
          qname = new QName(nsURI, localPart);
        }
      }
    }

    if (qname == null) {
      qname = new QName("", propName);
    }

    return qname;
  }

  private List<?> readArrayOf(Class<?> type, JsonParser jp) {
    ArrayList<Object> objects = new ArrayList<>();

    if (jp.currentToken() == JsonToken.START_ARRAY) {
      jp.nextToken();
      while (jp.currentToken() != JsonToken.END_ARRAY) {
        objects.add(jp.readValueAs(type));
        jp.nextToken();
      }
    }
    else {
      objects.add(jp.readValueAs(type));
    }

    return objects;
  }

  private List<?> readKeyedMapOf(Class<?> type, JsonParser jp) {
    if (jp.currentToken() == JsonToken.START_OBJECT) {
      return KeyedListDeserializer.deserializeGeneric(jp, type);
    }
    else {
      throw DatabindException.from(jp, "Unable to parse keyed map of " + type.getName() + ": expect start object, but got: " + jp.currentToken().name());
    }
  }

}
