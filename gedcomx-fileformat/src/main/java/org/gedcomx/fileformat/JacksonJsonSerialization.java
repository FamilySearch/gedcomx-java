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
package org.gedcomx.fileformat;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.gedcomx.rt.GedcomxConstants;
import org.gedcomx.rt.json.GedcomJacksonModule;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;


/**
 * A class for creating instances of <code>JAXBContext</code> appropriate for reading and writing GEDCOM X files.
 */
public class JacksonJsonSerialization implements GedcomxEntrySerializer, GedcomxEntryDeserializer {

  private final ObjectMapper mapper;
  private final JsonFactory factory;
  private Set<String> knownContentTypes = new HashSet<String>(Arrays.asList( GedcomxConstants.GEDCOMX_JSON_MEDIA_TYPE));

  public JacksonJsonSerialization(Class<?>... classes) {
    this(true, classes);
  }

  public JacksonJsonSerialization(boolean pretty, Class<?>... classes) {
    this(createObjectMapper(pretty, classes));
  }

  public JacksonJsonSerialization(ObjectMapper mapper) {
    this(mapper, new JsonFactory());
  }

  public JacksonJsonSerialization(JsonFactory factory) {
    this(createObjectMapper(true), factory);
  }

  public JacksonJsonSerialization(ObjectMapper mapper, JsonFactory factory) {
    this.mapper = mapper;
    this.factory = factory;
  }

  public static ObjectMapper createObjectMapper(boolean pretty, Class<?>... classes) {
    return GedcomJacksonModule.createObjectMapper(classes);
  }

  @Override
  public Object deserialize(InputStream in) throws IOException {
    return this.mapper.readValue(factory.createParser(in), (JavaType) null);
  }

  @Override
  public void serialize(Object resource, OutputStream out) throws IOException {
    JsonGenerator generator = factory.createGenerator(out);
    this.mapper.writeValue(generator, resource);
  }

  @Override
  public boolean isKnownContentType(String contentType) {
    return this.knownContentTypes.contains(contentType);
  }

  @Override
  public String suggestFilenameExtension() {
    return ".json";
  }

  public Set<String> getKnownContentTypes() {
    return knownContentTypes;
  }

  public void setKnownContentTypes(Set<String> knownContentTypes) {
    this.knownContentTypes = knownContentTypes;
  }

}
