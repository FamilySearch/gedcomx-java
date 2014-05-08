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
package org.gedcomx.rs.client.options;

import com.sun.jersey.api.client.ClientRequest;
import org.gedcomx.rs.client.StateTransitionOption;

import java.util.Arrays;

/**
 * @author Ryan Heaton
 */
public class HeaderParameter implements StateTransitionOption {

  public static final String LANG = "Accept-Language";
  public static final String LOCALE = LANG;
  public static final String IF_NONE_MATCH = "If-None-Match";
  public static final String IF_MODIFIED_SINCE = "If-Modified-Since";

  private final boolean replace;
  private final String name;
  private final String[] value;

  public HeaderParameter(String name, String... value) {
    this(false, name, value);
  }

  public HeaderParameter(boolean replace, String name, String... value) {
    this.replace = replace;
    this.name = name;
    this.value = value.length > 0 ? value : new String[] {""};
  }

  @Override
  public void apply(ClientRequest request) {
    if (this.replace) {
      request.getHeaders().put(this.name, Arrays.<Object>asList(this.value));
    }
    else {
      for (String value : this.value) {
        request.getHeaders().add(this.name, value);
      }
    }
  }

  public static HeaderParameter lang(String value) {
    return new HeaderParameter(true, LANG, value);
  }

  public static HeaderParameter locale(String value) {
    return new HeaderParameter(true, LOCALE, value);
  }

}
