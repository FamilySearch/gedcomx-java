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

import com.sun.xml.bind.marshaller.NamespacePrefixMapper;
import org.gedcomx.rt.json.JsonElementWrapper;

import javax.xml.XMLConstants;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchema;
import javax.xml.bind.annotation.XmlType;
import javax.xml.namespace.QName;
import java.beans.Introspector;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.*;

/**
 * A nice namespace prefix mapper that can be used to make XML and JSON output as pretty as it can be.
 *
 * @author Ryan Heaton
 */
public class GedcomNamespaceManager extends NamespacePrefixMapper {

  private final String defaultns;
  private final Map<String, String> ns2prefix;

  private static boolean INITIALIZED = false;
  private static final Map<String, String> KNOWN_PREFIXES = new HashMap<String, String>();
  private static final Map<String, String> RUNTIME_VERSIONS = new HashMap<String, String>();
  private static final Map<String, Class<?>> WRAPPED_JSON_TYPES_BY_NAME = new HashMap<String, Class<?>>();
  private static final Map<QName, String> QNAME_WRAPPER_JSON_NAMES = new HashMap<QName, String>();
  private static final Map<String, Class<?>> KNOWN_JSON_TYPES_BY_NAME = new HashMap<String, Class<?>>();
  private static final Map<String, Class<?>> KNOWN_JSON_TYPES_BY_TYPE_ID = new HashMap<String, Class<?>>();

  public GedcomNamespaceManager(Class<?> rootClass) {
    this(getDefaultNamespace(rootClass));
  }

  private static String getDefaultNamespace(Class<?> rootClass) {
    DefaultNamespace defaultNs = rootClass.getAnnotation(DefaultNamespace.class);
    if (defaultNs == null && rootClass.getPackage() != null) {
      defaultNs = rootClass.getPackage().getAnnotation(DefaultNamespace.class);
    }

    String defaultNamespace = "";
    if (defaultNs != null) {
      defaultNamespace = defaultNs.value();
    }
    else {
      XmlRootElement rootElement = rootClass.getAnnotation(XmlRootElement.class);
      if (rootElement != null) {
        if ("##default".equals(rootElement.namespace())) {
          if (rootClass.getPackage() != null) {
            XmlSchema xmlSchema = rootClass.getPackage().getAnnotation(XmlSchema.class);
            if (xmlSchema != null) {
              defaultNamespace = xmlSchema.namespace();
            }
          }
        }
        else {
          defaultNamespace = rootElement.namespace();
        }
      }
    }

    return defaultNamespace;
  }

  public GedcomNamespaceManager(Map<String, String> overrides, String defaultns) {
    Map<String, String> ns2prefix = getKnownPrefixes();
    if (overrides != null) {
      ns2prefix.putAll(overrides);
    }
    this.ns2prefix = Collections.unmodifiableMap(ns2prefix);
    if (defaultns == null) {
      defaultns = "";
    }
    this.defaultns = defaultns;
  }

  public GedcomNamespaceManager(String defaultns) {
    this(null, defaultns);
  }

  public GedcomNamespaceManager(Map<String, String> overrides) {
    this(overrides, null);
  }

  public GedcomNamespaceManager() {
    this(null, null);
  }

  /**
   * The known set of namespace-to-prefix mappings.
   *
   * @return The known set of namespace-to-prefix mappings.
   */
  public static Map<String, String> getKnownPrefixes() {
    if (INITIALIZED) {
      return KNOWN_PREFIXES;    // if already initialized no need to wait for a synchronized method to tell us it is initialized - just return it immediately.
    }
    init(Thread.currentThread().getContextClassLoader());
    return KNOWN_PREFIXES;
  }

  protected static synchronized void init(ClassLoader loader) {
    if (INITIALIZED) {
      return;
    }

    Map<String, String> namespacePrefixes = new HashMap<String, String>();
    namespacePrefixes.put("http://www.w3.org/2001/XMLSchema-instance", "xsi");
    namespacePrefixes.put("http://gedcomx.org/record/v1/", "gxr");
    namespacePrefixes.put("http://gedcomx.org/conclusion/v1/", "gxc");

    Map<QName, String> wrapperJsonNames = new HashMap<QName, String>();
    Map<String, Class<?>> wrappedJsonTypes = new HashMap<String, Class<?>>();

    Set<Class<?>> modelClasses = new HashSet<Class<?>>();

    try {
      Enumeration<URL> resources = loader.getResources("META-INF/gedcomx.models");
      while (resources.hasMoreElements()) {
        try {
          URL resource = resources.nextElement();
          BufferedReader reader = new BufferedReader(new InputStreamReader(resource.openStream()));
          String classname = reader.readLine();
          while (classname != null) {
            modelClasses.add(Class.forName(classname, true, loader));
            classname = reader.readLine();
          }
        }
        catch (Exception e) {
          //no-op...
        }
      }
    }
    catch (IOException e) {
      //no-op.
    }

    for (Class<?> modelClass : modelClasses) {
      MediaTypeDefinition mediaTypeInfo = modelClass.getAnnotation(MediaTypeDefinition.class);
      for (Model model : mediaTypeInfo.models()) {
        namespacePrefixes.put(model.namespace(), model.id());
      }
    }

    KNOWN_PREFIXES.putAll(namespacePrefixes);
    QNAME_WRAPPER_JSON_NAMES.putAll(wrapperJsonNames);
    WRAPPED_JSON_TYPES_BY_NAME.putAll(wrappedJsonTypes);
    INITIALIZED = true;
  }

  public static String nameFromQName(String namespaceUri, String localPart) {
    if (namespaceUri == null) {
      namespaceUri = "";
    }

    StringBuilder nameBuilder = new StringBuilder(namespaceUri);
    if (XMLConstants.XML_NS_URI.equals(namespaceUri)) {
      nameBuilder.append('#');
    }
    nameBuilder.append(localPart);
    return nameBuilder.toString();
  }

  @Override
  public String getPreferredPrefix(String namespaceUri, String suggestion, boolean requirePrefix) {
    String choice = this.ns2prefix.get(namespaceUri);
    if (!requirePrefix && this.defaultns.equals(namespaceUri)) {
      //(01/12/12, heatonra) I looked into why 'requirePrefix' is always 'true'. It's because
      // attributeFormDefault = "qualified" so attributes always have to have a prefix, so
      // requirePrefix is always true.
      return "";
    }
    return choice;
  }

  /**
   * Get the json wrapper name for the specified XML element name.
   *
   * @param wrapperName The identifier for the wrapper name.
   * @return The json wrapper name, or null if none.
   */
  public static String getJsonNameForWrapperName(QName wrapperName) {
    init(Thread.currentThread().getContextClassLoader());
    return QNAME_WRAPPER_JSON_NAMES.get(wrapperName);
  }

  /**
   * Get the wrapper name for the specified json name.
   *
   * @param jsonName The json name.
   * @return The wrapper qname.
   */
  public static QName findWrapperNameForJsonName(String jsonName) {
    init(Thread.currentThread().getContextClassLoader());
    for (Map.Entry<QName, String> entry : QNAME_WRAPPER_JSON_NAMES.entrySet()) {
      if (entry.getValue().equals(jsonName)) {
        return entry.getKey();
      }
    }

    return null;
  }

  /**
   * Get the type of the wrapped object for the specified json name.
   *
   * @param jsonName The json name.
   * @return The wrapped type.
   */
  public static Class<?> getWrappedTypeForJsonName(String jsonName) {
    init(Thread.currentThread().getContextClassLoader());
    return WRAPPED_JSON_TYPES_BY_NAME.get(jsonName);
  }

  /**
   * Register a known JSON type. It must be annotated with either @JsonElementWrapper or @XmlRootElement.
   *
   * @param type the type to register.
   */
  public static void registerKnownJsonType(Class<?> type) {
    String jsonName = getJsonName(type);
    if (jsonName != null) {
      KNOWN_JSON_TYPES_BY_NAME.put(jsonName, type);
    }
    KNOWN_JSON_TYPES_BY_TYPE_ID.put(getTypeIdName(type), type);
  }

  /**
   * Get the JSON name for the specified type.
   *
   * @param type The type.
   * @return The json name.
   */
  public static String getJsonName(Class<?> type) {
    if (type.isAnnotationPresent(JsonElementWrapper.class)) {
      //support custom json element name
      JsonElementWrapper ext = type.getAnnotation(JsonElementWrapper.class);
      return nameFromQName(ext.namespace(), ext.name());
    }
    else if (type.isAnnotationPresent(XmlRootElement.class)) {
      XmlRootElement rootElement = type.getAnnotation(XmlRootElement.class);
      if (rootElement != null) {
        String localPart = rootElement.name();
        if ("##default".equals(localPart)) {
          localPart = Introspector.decapitalize(type.getSimpleName());
        }
        String namespaceURI = rootElement.namespace();
        if ("##default".equals(namespaceURI)) {
          Package pkg = type.getPackage();
          if (pkg != null && pkg.isAnnotationPresent(XmlSchema.class)) {
            namespaceURI = pkg.getAnnotation(XmlSchema.class).namespace();
          }
          else {
            namespaceURI = "";
          }
        }

        return nameFromQName(namespaceURI, localPart);
      }
    }

    return null;
  }

  /**
   * Get the id for the specified type.
   *
   * @param type The type.
   * @return The type id.
   */
  public static String getTypeIdName(Class<?> type) {
    String ns = "";
    if (type.getPackage() != null && type.getPackage().isAnnotationPresent(XmlSchema.class)) {
      ns = type.getPackage().getAnnotation(XmlSchema.class).namespace();
    }

    String name = Introspector.decapitalize(type.getSimpleName());
    if (type.isAnnotationPresent(XmlType.class)) {
      XmlType typeMeta = type.getAnnotation(XmlType.class);
      if (!"##default".equals(typeMeta.name())) {
        name = typeMeta.name();
      }

      if (!"##default".equals(typeMeta.namespace())) {
        ns = typeMeta.namespace();
      }
    }

    return ns + name;
  }

  /**
   * Get the known type for the given JSON name.
   *
   * @param jsonName The json name.
   * @return The known type, or null if not known.
   */
  public static Class<?> getKnownJsonType(String jsonName) {
    return KNOWN_JSON_TYPES_BY_NAME.get(jsonName);
  }

  /**
   * Get the known type for the given data type.
   *
   * @param typeId The type id.
   * @return The known type, or null if not known.
   */
  public static Class<?> getKnownTypeById(String typeId) {
    return KNOWN_JSON_TYPES_BY_TYPE_ID.get(typeId);
  }

}
