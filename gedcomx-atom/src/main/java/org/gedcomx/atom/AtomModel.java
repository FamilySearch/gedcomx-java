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
package org.gedcomx.atom;

import jakarta.xml.bind.annotation.XmlTransient;

/**
 * @author Ryan Heaton
 */
@XmlTransient
public class AtomModel {

  private AtomModel() {}

  public static final String ATOM_NAMESPACE = "http://www.w3.org/2005/Atom";
  public static final String ATOM_XML_MEDIA_TYPE = "application/atom+xml";
  public static final String ATOM_GEDCOMX_JSON_MEDIA_TYPE = "application/x-gedcomx-atom+json";
}
