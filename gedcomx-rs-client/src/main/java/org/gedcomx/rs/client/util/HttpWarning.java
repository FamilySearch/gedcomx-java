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
package org.gedcomx.rs.client.util;

import java.util.StringTokenizer;

/**
 * @author Ryan Heaton
 */
public class HttpWarning {

  private final int code;
  private final String application;
  private final String message;

  public static HttpWarning parse(String headerValue) {
    Integer code = null;
    String application = null;
    StringBuilder message = new StringBuilder();
    for (StringTokenizer tokens = new StringTokenizer(headerValue, " "); tokens.hasMoreTokens(); ) {
      String token = tokens.nextToken();
      if (code == null) {
        try {
          code = Integer.parseInt(token);
        }
        catch (NumberFormatException e) {
          code = -1;
        }
      }
      else if (application == null) {
        application = token;
      }
      else {
        message.append(' ').append(token);
      }
    }

    code = code == null ? new Integer(-1) : code;
    application = application == null ? "" : application;
    return new HttpWarning(code, application, message.toString());
  }

  public HttpWarning(int code, String application, String message) {
    this.code = code;
    this.application = application;
    this.message = message;
  }

  public int getCode() {
    return code;
  }

  public String getApplication() {
    return application;
  }

  public String getMessage() {
    return message;
  }
}
