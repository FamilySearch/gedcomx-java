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
package org.familysearch.platform;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.gedcomx.rt.json.JsonElementWrapper;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;

/**
 * A common representation of an error on the FamilySearch platform.
 *
 * @author Ryan Heaton
 */
@XmlRootElement
@JsonElementWrapper ( name = "errors")
@XmlType ( name = "Error" )
@JsonInclude( JsonInclude.Include.NON_NULL )
@SuppressWarnings("gedcomx:no_id")
@Schema(description = "A common representation of an error on the FamilySearch platform.")
public class Error {

  @Schema(description = "The error code. Interpreted per [RFC 9110, Section 15 (Status Codes)](https://www.rfc-editor.org/rfc/rfc9110.html#name-status-codes).")
  private Integer code;

  @Schema(description = "A text label associated with the error code.")
  private String label;

  @Schema(description = "A message associated with the error.")
  private String message;

  @Schema(description = "The back-end stack trace associated with the error, useful for debugging.")
  private String stacktrace;

  /**
   * The error code. Interpreted per <a href="https://www.rfc-editor.org/rfc/rfc9110.html#name-status-codes">RFC 9110, Section 15 (Status Codes)</a>.
   *
   * @return The error code. Interpreted per <a href="https://www.rfc-editor.org/rfc/rfc9110.html#name-status-codes">RFC 9110, Section 15 (Status Codes)</a>.
   */
  public Integer getCode() {
    return code;
  }

  /**
   * The error code. Interpreted per <a href="https://www.rfc-editor.org/rfc/rfc9110.html#name-status-codes">RFC 9110, Section 15 (Status Codes)</a>.
   *
   * @param code The error code. Interpreted per <a href="https://www.rfc-editor.org/rfc/rfc9110.html#name-status-codes">RFC 9110, Section 15 (Status Codes)</a>.
   */
  public void setCode(Integer code) {
    this.code = code;
  }

  /**
   * A text label associated with the error code.
   *
   * @return A text label associated with the error code.
   */
  public String getLabel() {
    return label;
  }

  /**
   * A text label associated with the error code.
   *
   * @param label A text label associated with the error code.
   */
  public void setLabel(String label) {
    this.label = label;
  }

  /**
   * A message associated with the error.
   *
   * @return A message associated with the error.
   */
  public String getMessage() {
    return message;
  }

  /**
   * A message associated with the error.
   *
   * @param message A message associated with the error.
   */
  public void setMessage(String message) {
    this.message = message;
  }

  /**
   * The back-end stack trace associated with the error, useful for debugging.
   *
   * @return The back-end stack trace associated with the error, useful for debugging.
   */
  public String getStacktrace() {
    return stacktrace;
  }

  /**
   * The back-end stack trace associated with the error, useful for debugging.
   *
   * @param stacktrace The back-end stack trace associated with the error, useful for debugging.
   */
  public void setStacktrace(String stacktrace) {
    this.stacktrace = stacktrace;
  }
}
