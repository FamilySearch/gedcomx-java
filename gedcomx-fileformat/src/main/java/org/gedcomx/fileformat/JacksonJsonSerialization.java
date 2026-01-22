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

import java.io.InputStream;
import java.io.OutputStream;

import org.gedcomx.Gedcomx;
import org.gedcomx.rt.json.GedcomJacksonModule;
import tools.jackson.core.JsonEncoding;
import tools.jackson.core.JsonGenerator;
import tools.jackson.core.ObjectWriteContext;
import tools.jackson.databind.json.JsonMapper;

/**
 * A class for creating instances of <code>JAXBContext</code> appropriate for reading and writing GEDCOM X files.
 */
public class JacksonJsonSerialization implements GedcomxEntrySerializer, GedcomxEntryDeserializer {

  private final JsonMapper mapper;

  public JacksonJsonSerialization(Class<?>... classes) {
    this(true, classes);
  }

  public JacksonJsonSerialization(boolean pretty, Class<?>... classes) {
    this(createJsonMapper(pretty, classes));
  }

  public JacksonJsonSerialization(JsonMapper mapper) {
    this.mapper = mapper;
  }

  public static JsonMapper createJsonMapper(boolean pretty, Class<?>... classes) {
    return GedcomJacksonModule.createJsonMapper(classes);
  }

  @Override
  public Object deserialize(InputStream in, String mediaType) {
    Class<?> clazz = findClass(mediaType);
    return clazz == null ? in : this.mapper.readValue(in, clazz);
  }

  protected Class<?> findClass(String mediaType) {
    return mediaType == null || mediaType.endsWith("json") ? Gedcomx.class : null;
  }

  @Override
  public void serialize(Object resource, OutputStream out) {
    JsonGenerator generator = this.mapper.tokenStreamFactory().createGenerator(ObjectWriteContext.empty(), out, JsonEncoding.UTF8); //we're creating a generator so that the stream doesn't get auto-closed
    this.mapper.writeValue(generator, resource);
  }

  @Override
  public String suggestFilenameExtension() {
    return ".json";
  }

}
