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

import java.util.Collections;

import com.sun.jersey.api.client.ClientRequest;
import org.gedcomx.rs.client.StateTransitionOption;

/**
 */
public class ConnegSetter implements StateTransitionOption {

  private static final String ACCEPT_HEADER = "Accept";
  private static final String CONTENT_TYPE_HEADER = "Content-Type";
  
  private final String connegValue;

  public ConnegSetter(String connegValue) {
    this.connegValue = connegValue;
  }

  @Override
  public void apply(ClientRequest request) {
    request.getHeaders().put(ACCEPT_HEADER, Collections.<Object>singletonList(connegValue));
    request.getHeaders().put(CONTENT_TYPE_HEADER, Collections.<Object>singletonList(connegValue));
  }
}
