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
package org.gedcomx.source;

import org.gedcomx.rt.RDFDomain;
import org.gedcomx.rt.RDFRange;
import org.gedcomx.rt.RDFSubPropertyOf;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.xml.bind.annotation.XmlType;
import java.util.List;


/**
 * Conclusion data that references sources.
 *
 * @author Ryan Heaton
 */
@XmlType ( name = "ReferencesSources" )
@Schema(description = "Conclusion data that references sources.")
public interface ReferencesSources {

  /**
   * The references to the sources of a conclusion resource.
   *
   * @return The references to the sources of a conclusion resource.
   */
  @RDFSubPropertyOf ( "http://purl.org/dc/terms/source")
  @RDFDomain ({}) //any resource can be identified persistently.
  @RDFRange ({}) //any resource can be identified as a source.
  @SuppressWarnings("rdf:no_range")
  List<SourceReference> getSources();

  /**
   * The references to the sources of a conclusion resource.
   *
   * @param notes The references to the sources of a conclusion resource.
   */
  void setSources(List<SourceReference> notes);
}
