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
package org.gedcomx.util;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Object for documentation purposes only―captures what a list of identifiers looks like when serialized to JSON.
 */
public class JsonIdentifiers {
  
  private String[] persistent;
  private String[] primary;

  @JsonProperty("http://gedcomx.org/Persistent")
  public String[] getPersistent() {
    return persistent;
  }

  public void setPersistent(String[] persistent) {
    this.persistent = persistent;
  }

  @JsonProperty("http://gedcomx.org/Primary")
  public String[] getPrimary() {
    return primary;
  }

  public void setPrimary(String[] primary) {
    this.primary = primary;
  }
}
