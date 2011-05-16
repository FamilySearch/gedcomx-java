/**
 * Copyright 2011 Intellectual Reserve, Inc.
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
 * The <b>types</b> profile provides the standard set of types of genealogical data.
 */
@XmlSchema(
  namespace = TypeConstants.GEDCOMX_TYPES_NAMESPACE,
  elementFormDefault = XmlNsForm.QUALIFIED,
  xmlns = {
    @XmlNs ( prefix = TypeConstants.GEDCOMX_TYPES_NAMESPACE_PREFIX, namespaceURI = TypeConstants.GEDCOMX_TYPES_NAMESPACE)
  }
)
package org.gedcomx.types;
//todo: figure out all the valid types
//todo: figure out user-defined types: come up with conventions for extending known types (e.g. qname ns/localpart should resolve to a description of the type)

import javax.xml.bind.annotation.XmlNs;
import javax.xml.bind.annotation.XmlNsForm;
import javax.xml.bind.annotation.XmlSchema;