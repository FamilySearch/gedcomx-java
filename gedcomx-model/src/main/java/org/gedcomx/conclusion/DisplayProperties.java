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
package org.gedcomx.conclusion;

import org.codehaus.enunciate.Facet;
import org.gedcomx.common.ExtensibleData;
import org.gedcomx.rt.GedcomxConstants;

import javax.xml.bind.annotation.XmlType;


/**
 * A set of display properties for the convenience of quick display, such as for
 * a Web-based application. All display properties are provided in the default locale for the current
 * application context and are NOT considered canonical for the purposes of data exchange.
 */
@XmlType ( name = "DisplayProperties" )
@Facet ( name = GedcomxConstants.FACET_GEDCOMX_RS )
public class DisplayProperties extends ExtensibleData {

  private String name;
  private String gender;
  private String lifespan;
  private String birthDate;
  private String birthPlace;
  private String deathDate;
  private String deathPlace;
  private String marriageDate;
  private String marriagePlace;
  private String ascendancyNumber;
  private String descendancyNumber;

  @Override
  public DisplayProperties id(String id) {
    return (DisplayProperties) super.id(id);
  }

  /**
   * The displayable name of the person.
   *
   * @return The displayable name of the person.
   */
  public String getName() {
    return name;
  }

  /**
   * The displayable name of the person.
   *
   * @param name The displayable name of the person.
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * Build up these properties with a name.
   * 
   * @param name The name.
   * @return this.
   */
  public DisplayProperties name(String name) {
    setName(name);
    return this;
  }

  /**
   * The displayable label for the gender of the person.
   *
   * @return The displayable label for the gender of the person.
   */
  public String getGender() {
    return gender;
  }

  /**
   * Displayable form of the gender.
   *
   * @param gender Displayable form of the gender.
   */
  public void setGender(String gender) {
    this.gender = gender;
  }

  /**
   * Build up these properties with a gender.
   *
   * @param gender The gender.
   * @return this.
   */
  public DisplayProperties gender(String gender) {
    setGender(gender);
    return this;
  }

  /**
   * The displayable label for the lifespan of the person.
   *
   * @return The displayable label for the lifespan of the person.
   */
  public String getLifespan() {
    return lifespan;
  }

  /**
   * The displayable label for the lifespan of the person.
   *
   * @param lifespan The displayable label for the lifespan of the person.
   */
  public void setLifespan(String lifespan) {
    this.lifespan = lifespan;
  }

  /**
   * Build up these properties with a lifespan.
   *
   * @param lifespan The lifespan.
   * @return this.
   */
  public DisplayProperties lifespan(String lifespan) {
    setLifespan(lifespan);
    return this;
  }

  /**
   * The displayable label for the birth date of the person.
   * 
   * @return The displayable label for the birth date of the person.
   */
  public String getBirthDate() {
    return birthDate;
  }

  /**
   * The displayable label for the birth date of the person.
   * 
   * @param birthDate The displayable label for the birth date of the person.
   */
  public void setBirthDate(String birthDate) {
    this.birthDate = birthDate;
  }

  /**
   * Build up these properties with a birth date.
   *
   * @param birthdate The birth date.
   * @return this.
   */
  public DisplayProperties birthDate(String birthdate) {
    setBirthDate(birthdate);
    return this;
  }

  /**
   * The displayable label for the birth place of the person.
   * 
   * @return The displayable label for the birth place of the person.
   */
  public String getBirthPlace() {
    return birthPlace;
  }

  /**
   * The displayable label for the birth place of the person.
   * 
   * @param birthPlace The displayable label for the birth place of the person.
   */
  public void setBirthPlace(String birthPlace) {
    this.birthPlace = birthPlace;
  }

  /**
   * Build up these properties with a birth place.
   *
   * @param birthplace The birth place.
   * @return this.
   */
  public DisplayProperties birthPlace(String birthplace) {
    setBirthPlace(birthplace);
    return this;
  }

  /**
   * The displayable label for the death date of the person.
   * 
   * @return The displayable label for the death date of the person.
   */
  public String getDeathDate() {
    return deathDate;
  }

  /**
   * The displayable label for the death date of the person.
   * 
   * @param deathDate The displayable label for the death date of the person.
   */
  public void setDeathDate(String deathDate) {
    this.deathDate = deathDate;
  }

  /**
   * Build up these properties with a death date.
   *
   * @param deathdate The death date.
   * @return this.
   */
  public DisplayProperties deathDate(String deathdate) {
    setDeathDate(deathdate);
    return this;
  }

  /**
   * The displayable label for the death place of the person.
   * 
   * @return The displayable label for the death place of the person.
   */
  public String getDeathPlace() {
    return deathPlace;
  }

  /**
   * The displayable label for the death place of the person.
   * 
   * @param deathPlace The displayable label for the death place of the person.
   */
  public void setDeathPlace(String deathPlace) {
    this.deathPlace = deathPlace;
  }

  /**
   * Build up these properties with a death place.
   *
   * @param deathplace The death place.
   * @return this.
   */
  public DisplayProperties deathPlace(String deathplace) {
    setDeathPlace(deathplace);
    return this;
  }

  /**
   * The displayable label for the marriage date of the person.
   * 
   * @return The displayable label for the marriage date of the person.
   */
  public String getMarriageDate() {
    return marriageDate;
  }

  /**
   * The displayable label for the marriage date of the person.
   * 
   * @param marriageDate The displayable label for the marriage date of the person.
   */
  public void setMarriageDate(String marriageDate) {
    this.marriageDate = marriageDate;
  }

  /**
   * Build up these properties with a marriage date.
   *
   * @param marriagedate The marriage date.
   * @return this.
   */
  public DisplayProperties marriageDate(String marriagedate) {
    setMarriageDate(marriagedate);
    return this;
  }

  /**
   * The displayable label for the marriage place of the person.
   * 
   * @return The displayable label for the marriage place of the person.
   */
  public String getMarriagePlace() {
    return marriagePlace;
  }

  /**
   * The displayable label for the marriage place of the person.
   * 
   * @param marriagePlace The displayable label for the marriage place of the person.
   */
  public void setMarriagePlace(String marriagePlace) {
    this.marriagePlace = marriagePlace;
  }

  /**
   * Build up these properties with a marriage place.
   *
   * @param marriageplace The marriage place.
   * @return this.
   */
  public DisplayProperties marriagePlace(String marriageplace) {
    setMarriagePlace(marriageplace);
    return this;
  }

  /**
   * The context-specific ascendancy number for the person in relation to the other persons in the request. The ancestry number is defined using the Ahnentafel numbering system.
   *
   * @return The context-specific ascendancy number for the person in relation to the other persons in the request. The ancestry number is defined using the Ahnentafel numbering system.
   */
  public String getAscendancyNumber() {
    return ascendancyNumber;
  }

  /**
   * The context-specific ascendancy number for the person in relation to the other persons in the request. The ancestry number is defined using the Ahnentafel numbering system.
   *
   * @param ascendancyNumber The context-specific ascendancy number for the person in relation to the other persons in the request. The ancestry number is defined using the Ahnentafel numbering system.
   */
  public void setAscendancyNumber(String ascendancyNumber) {
    this.ascendancyNumber = ascendancyNumber;
  }

  /**
   * Build up these properties with a ascendancy number.
   *
   * @param ascendancynumber The ascendancy number.
   * @return this.
   */
  public DisplayProperties ascendancyNumber(String ascendancynumber) {
    setAscendancyNumber(ascendancynumber);
    return this;
  }

  /**
   * The context-specific descendancy number for the person in relation to the other persons in the request. The descendancy number is defined using the d'Aboville numbering system.
   *
   * @return The context-specific descendancy number for the person in relation to the other persons in the request. The descendancy number is defined using the d'Aboville numbering system.
   */
  public String getDescendancyNumber() {
    return descendancyNumber;
  }

  /**
   * The context-specific descendancy number for the person in relation to the other persons in the request. The descendancy number is defined using the d'Aboville numbering system.
   *
   * @param descendancyNumber The context-specific descendancy number for the person in relation to the other persons in the request. The descendancy number is defined using the d'Aboville numbering system.
   */
  public void setDescendancyNumber(String descendancyNumber) {
    this.descendancyNumber = descendancyNumber;
  }

  /**
   * Build up these properties with a descendancy number.
   *
   * @param descendancynumber The descendancy number.
   * @return this.
   */
  public DisplayProperties descendancyNumber(String descendancynumber) {
    setDescendancyNumber(descendancynumber);
    return this;
  }

  /**
   * Embed a set of display properties.
   *
   * @param data The data to embed.
   */
  public void embed(DisplayProperties data) {
    this.name = this.name == null ? data.name : this.name;
    this.gender = this.gender == null ? data.gender : this.gender;
    this.lifespan = this.lifespan == null ? data.lifespan : this.lifespan;
    this.birthDate = this.birthDate == null ? data.birthDate : this.birthDate;
    this.birthPlace = this.birthPlace == null ? data.birthPlace : this.birthPlace;
    this.deathDate = this.deathDate == null ? data.deathDate : this.deathDate;
    this.deathPlace = this.deathPlace == null ? data.deathPlace : this.deathPlace;
    this.marriageDate = this.marriageDate == null ? data.marriageDate : this.marriageDate;
    this.marriagePlace = this.marriagePlace == null ? data.marriagePlace : this.marriagePlace;
    this.ascendancyNumber = this.ascendancyNumber == null ? data.ascendancyNumber : this.ascendancyNumber;
    this.descendancyNumber = this.descendancyNumber == null ? data.descendancyNumber : this.descendancyNumber;
    super.embed(data);
  }
}
