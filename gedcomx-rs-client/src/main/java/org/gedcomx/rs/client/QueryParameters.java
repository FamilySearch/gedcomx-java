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
package org.gedcomx.rs.client;

import java.util.Map;
import java.util.TreeMap;

/**
 * @author Ryan Heaton
 */
public class QueryParameters {

  public static final String START = "start";
  public static final String COUNT = "count";
  public static final String GENERATIONS = "generations";

  protected final Map<String, String> params = new TreeMap<String, String>();

  public Map<String, String> getParams() {
    return params;
  }

  public QueryParameters param(String name, String value) {
    this.params.put(name, value);
    return this;
  }

  public QueryParameters start(int start) {
    return param(START, String.valueOf(start));
  }

  public QueryParameters count(int count) {
    return param(COUNT, String.valueOf(count));
  }

  public QueryParameters generations(int generations) {
    return param(GENERATIONS, String.valueOf(generations));
  }
}
