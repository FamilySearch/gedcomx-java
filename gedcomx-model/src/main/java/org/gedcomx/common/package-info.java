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

/**
 * The id model defines the standard mechanisms for identifying genealogical entities.
 */
@XmlSchema(
  namespace = GedcomxConstants.GEDCOMX_NAMESPACE,
  elementFormDefault = XmlNsForm.QUALIFIED
)
@XmlSchemaTypes ({
  @XmlSchemaType (type=URI.class, name = "anyURI", namespace = XMLConstants.W3C_XML_SCHEMA_NS_URI)
})
package org.gedcomx.common;

import org.gedcomx.rt.GedcomxConstants;

import javax.xml.XMLConstants;
import javax.xml.bind.annotation.XmlNsForm;
import javax.xml.bind.annotation.XmlSchema;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlSchemaTypes;