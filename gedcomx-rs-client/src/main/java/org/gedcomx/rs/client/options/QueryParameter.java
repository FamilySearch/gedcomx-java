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

import jakarta.ws.rs.core.UriBuilder;

/**
 * @author Ryan Heaton
 */
public class QueryParameter implements StateTransitionOption {

  public static final String ACCESS_TOKEN = "access_token";
  public static final String COUNT = "count";
  public static final String GENERATIONS = "generations";
  public static final String SEARCH_QUERY = "q";
  public static final String START = "start";

  private final boolean replace;
  private final String name;
  private final String[] value;

  public QueryParameter(String name, String... value) {
    this(false, name, value);
  }

  public QueryParameter(boolean replace, String name, String... value) {
    this.replace = replace;
    this.name = name;
    this.value = value.length > 0 ? value : new String[] {""};
  }

  @Override
  public void apply(ClientRequest request) {
    UriBuilder builder = UriBuilder.fromUri(request.getURI());
    builder = this.replace ? builder.replaceQueryParam(this.name, this.value) : builder.queryParam(this.name, this.value);
    request.setURI(builder.build());
  }

  public static QueryParameter accessToken(String value) {
    return new QueryParameter(true, ACCESS_TOKEN, value);
  }

  public static QueryParameter count(int value) {
    return new QueryParameter(true, COUNT, String.valueOf(value));
  }

  public static QueryParameter generations(int value) {
    return new QueryParameter(true, GENERATIONS, String.valueOf(value));
  }

  public static QueryParameter searchQuery(String value) {
    return new QueryParameter(true, SEARCH_QUERY, value);
  }

  public static QueryParameter start(int value) {
    return new QueryParameter(true, START, String.valueOf(value));
  }
}
