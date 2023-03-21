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
package org.gedcomx.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.webcohesion.enunciate.metadata.Facet;
import org.gedcomx.rt.GedcomxConstants;

import javax.xml.XMLConstants;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlType;
import jakarta.xml.bind.annotation.XmlValue;


/**
 * An element representing a text value that may be in a specific language.
 */
@XmlType ( name = "TextValue" )
@JsonInclude ( JsonInclude.Include.NON_NULL )
public class TextValue {

  private String lang;
  private String value;

  public TextValue() {
  }

  public TextValue(String value) {
    this.value = value;
  }

  /**
   * The language of the text value. See <a href="http://www.w3.org/International/articles/language-tags/">http://www.w3.org/International/articles/language-tags/</a>
   *
   * @return The language of the text value.
   */
  @XmlAttribute( namespace = XMLConstants.XML_NS_URI )
  @Facet ( GedcomxConstants.FACET_FS_FT_READ_ONLY )
  public String getLang() {
    return lang;
  }

  /**
   * The language of the text value. See <a href="http://www.w3.org/International/articles/language-tags/">http://www.w3.org/International/articles/language-tags/</a>
   *
   * @param lang The language of the text value.
   */
  public void setLang(String lang) {
    this.lang = lang;
  }

  /**
   * Build up this text value with a lang.
   *
   * @param lang The lang.
   * @return this.
   */
  public TextValue lang(String lang) {
    setLang(lang);
    return this;
  }

  /**
   * The text value.
   *
   * @return The text value.
   */
  @XmlValue
  public String getValue() {
    return value;
  }

  /**
   * The text value.
   *
   * @param value The text value.
   */
  public void setValue(String value) {
    this.value = value;
  }

  /**
   * Build up this text value with a value.
   *
   * @param value The value.
   * @return this.
   */
  public TextValue value(String value) {
    setValue(value);
    return this;
  }

  @Override
  public boolean equals( Object o ) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    TextValue textValue = (TextValue) o;

    if (lang != null ? !lang.equals( textValue.lang ) : textValue.lang != null) {
      return false;
    }
    if (value != null ? !value.equals( textValue.value ) : textValue.value != null) {
      return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    int result = lang != null ? lang.hashCode() : 0;
    result = 31 * result + (value != null ? value.hashCode() : 0);
    return result;
  }

  @Override
  public String toString() {
    return "TextValue{" +
      "value='" + value + '\'' +
      ", lang='" + lang + '\'' +
      '}';
  }
}
