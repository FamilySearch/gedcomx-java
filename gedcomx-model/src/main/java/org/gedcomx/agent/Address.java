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
package org.gedcomx.agent;

import org.gedcomx.common.ExtensibleData;

import javax.xml.bind.annotation.XmlType;


/**
 * An address.
 *
 * @author Ryan Heaton
 */
@XmlType ( name = "Address" )
public class Address extends ExtensibleData {

  private String city;
  private String country;
  private String postalCode;
  private String stateOrProvince;
  private String street;
  private String street2;
  private String street3;
  private String value;

  @Override
  public Address id(String id) {
    return (Address) super.id(id);
  }

  /**
   * The city.
   *
   * @return The city.
   */
  public String getCity() {
    return city;
  }

  /**
   * The city.
   *
   * @param city The city.
   */
  public void setCity(String city) {
    this.city = city;
  }

  /**
   * Build up this address with a city.
   * 
   * @param city The city.
   * @return this.
   */
  public Address city(String city) {
    this.city = city;
    return this;
  }

  /**
   * The country.
   *
   * @return The country.
   */
  public String getCountry() {
    return country;
  }

  /**
   * Build up this address with a country.
   *
   * @param country The country.
   * @return this.
   */
  public Address country(String country) {
    this.country = country;
    return this;
  }

  /**
   * The country.
   *
   * @param country The country.
   */
  public void setCountry(String country) {
    this.country = country;
  }

  /**
   * The postal code.
   *
   * @return The postal code.
   */
  public String getPostalCode() {
    return postalCode;
  }

  /**
   * The postal code.
   *
   * @param postalCode The postal code.
   */
  public void setPostalCode(String postalCode) {
    this.postalCode = postalCode;
  }

  /**
   * Build up this address with a postalcode.
   *
   * @param postalCode The postalcode.
   * @return this.
   */
  public Address postalCode(String postalCode) {
    this.postalCode = postalCode;
    return this;
  }

  /**
   * The state or province.
   *
   * @return The state or province.
   */
  public String getStateOrProvince() {
    return stateOrProvince;
  }

  /**
   * The state or province.
   *
   * @param stateOrProvince The state or province.
   */
  public void setStateOrProvince(String stateOrProvince) {
    this.stateOrProvince = stateOrProvince;
  }

  /**
   * Build up this address with a state or province.
   *
   * @param stateOrProvince The state or province.
   * @return this.
   */
  public Address stateOrProvince(String stateOrProvince) {
    this.stateOrProvince = stateOrProvince;
    return this;
  }

  /**
   * The street.
   *
   * @return The street.
   */
  public String getStreet() {
    return street;
  }

  /**
   * The street.
   *
   * @param street The street.
   */
  public void setStreet(String street) {
    this.street = street;
  }

  /**
   * Build up this address with a street.
   *
   * @param street The street.
   * @return this.
   */
  public Address street(String street) {
    this.street = street;
    return this;
  }

  /**
   * Additional street information.
   *
   * @return Additional street information.
   */
  public String getStreet2() {
    return street2;
  }

  /**
   * Additional street information.
   *
   * @param street2 Additional street information.
   */
  public void setStreet2(String street2) {
    this.street2 = street2;
  }

  /**
   * Build up this address with a street2.
   *
   * @param street2 The street2.
   * @return this.
   */
  public Address street2(String street2) {
    this.street2 = street2;
    return this;
  }

  /**
   * Additional street information.
   *
   * @return Additional street information.
   */
  public String getStreet3() {
    return street3;
  }

  /**
   * Additional street information.
   *
   * @param street3 Additional street information.
   */
  public void setStreet3(String street3) {
    this.street3 = street3;
  }

  /**
   * Build up this address with a street3.
   *
   * @param street3 The street3.
   * @return this.
   */
  public Address street3(String street3) {
    this.street3 = street3;
    return this;
  }

  /**
   * The value of the property, if it can be expressed as a string.
   *
   * @return The value of the property.
   */
  public String getValue() {
    return value;
  }

  /**
   * The value of the property, if it can be expressed as a string.
   *
   * @param value The value of the property.
   */
  public void setValue(String value) {
    this.value = value;
  }

  /**
   * Build up this address with a value.
   *
   * @param value The value.
   * @return this.
   */
  public Address value(String value) {
    this.value = value;
    return this;
  }
}
