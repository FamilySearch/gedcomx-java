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
package org.gedcomx.rt;

import com.webcohesion.enunciate.metadata.qname.XmlQNameEnum;
import com.webcohesion.enunciate.metadata.qname.XmlQNameEnumValue;
import com.webcohesion.enunciate.metadata.qname.XmlUnknownQNameEnumValue;
import org.gedcomx.common.URI;

import java.lang.reflect.Field;
import java.util.EnumMap;

/**
 * Map for maintaining enum URIs that avoids the use of Enunciate's XmlQNameEnumUtil, which
 * relies on JAXB annotations that Android can't handle.
 *
 * @author Ryan Heaton
 */
public class EnumURIMap<K extends Enum<K>> extends EnumMap<K, String> {

  private K unknownValue;
  private java.net.URI defaultNamespace;

  public EnumURIMap(Class<K> keyType) {
    this(keyType, GedcomxConstants.GEDCOMX_TYPES_NAMESPACE);
  }

  public EnumURIMap(Class<K> keyType, String defaultNamespace) {
    super(keyType);

    defaultNamespace = loadOverriddenDefaultNamespace(keyType, defaultNamespace);
    K unknownValue = loadOverrides(keyType, defaultNamespace);

    for (K constant : keyType.getEnumConstants()) {
      if (constant != unknownValue && !containsKey(constant)) {
        this.put(constant, defaultNamespace + constant.name());
      }
    }

    this.unknownValue = unknownValue;
    this.defaultNamespace = defaultNamespace == null ? null : java.net.URI.create(defaultNamespace);
  }

  private String loadOverriddenDefaultNamespace(Class<K> clazz, String defaultNamespace) {
    XmlQNameEnum enumInfo = clazz.getAnnotation(XmlQNameEnum.class);
    if (enumInfo == null) {
      throw new IllegalArgumentException(String.format("Class %s isn't a QName enum.", clazz.getName()));
    }
    else if (enumInfo.base() != XmlQNameEnum.BaseType.URI) {
      throw new IllegalArgumentException("Class " + clazz.getName() + " is supposed to be converted from a URI (not QName).");
    }

    String namespace = enumInfo.namespace();
    if (!"##default".equals(namespace)) {
      defaultNamespace = namespace;
    }

    return defaultNamespace;
  }

  private K loadOverrides(Class<K> clazz, String defaultNamespace) {
    K unknownValue = null;
    Field[] fields = clazz.getDeclaredFields();
    METADATA : for (Field field : fields) {
      for (K e : clazz.getEnumConstants()) {
        if (field.isEnumConstant() && field.getName().equals(e.name())) {
          if (field.getAnnotation(XmlUnknownQNameEnumValue.class) != null) {
            if (unknownValue != null) {
              throw new IllegalArgumentException(e.getDeclaringClass().getName() + "." + e.name() + " conflicts with " + e.getDeclaringClass().getName() + "." + unknownValue.name() + " as an unknown qname value.");
            }
            else {
              unknownValue = e;
            }
            continue METADATA;
          }

          XmlQNameEnumValue enumValueInfo = field.getAnnotation(XmlQNameEnumValue.class);
          String ns = defaultNamespace;
          String localPart = field.getName();
          if (enumValueInfo != null && !enumValueInfo.exclude()) {
            if (!enumValueInfo.exclude()) {
              if (!"##default".equals(enumValueInfo.namespace())) {
                ns = enumValueInfo.namespace();
              }
              if (!"##default".equals(enumValueInfo.localPart())) {
                localPart = enumValueInfo.localPart();
              }
            }
          }

          this.put(e, ns + localPart);
          continue METADATA;
        }
      }
    }

    return unknownValue;
  }

  public K fromURIValue(URI uri) {
    String token = uri.toString();
    for (Entry<K, String> entry : entrySet()) {
      if (token.equals(entry.getValue())) {
        return entry.getKey();
      }
    }

    //not found; maybe it's a relative uri.
    if (this.defaultNamespace != null) {
      try {
        String resolved = this.defaultNamespace.resolve(token).toString();
        for (Entry<K, String> entry : entrySet()) {
          if (resolved.equals(entry.getValue())) {
            return entry.getKey();
          }
        }
      }
      catch (Exception e) {
        //any failures to resolve a relative uri should assume it's not a valid token.
      }
    }

    //still not found; return the unknown value.
    return unknownValue;
  }

  public URI toURIValue(K constant) {
    String value = this.get(constant);
    if (value == null) {
      throw new IllegalStateException("Unable to find URI value for " + constant.getDeclaringClass().getName() + "." + constant.name() + ".");
    }
    return URI.create(value);
  }

}
