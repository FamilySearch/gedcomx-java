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
package org.familysearch.platform.ct;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.webcohesion.enunciate.metadata.qname.XmlQNameEnumRef;
import org.gedcomx.common.URI;
import org.gedcomx.rt.json.JsonElementWrapper;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlTransient;
import jakarta.xml.bind.annotation.XmlType;

/**
 * Information about a match.
 */
@XmlRootElement
@JsonElementWrapper ( name = "matchInfo" )
@XmlType ( name = "MatchInfo" )
@JsonInclude ( JsonInclude.Include.NON_NULL )
@Schema(description = "Information about a match.")
public class MatchInfo {

  @Schema(description = "The collection in which this match was found.")
  private URI collection;

  @Schema(description = "The way this match has been resolved.")
  private URI status;

  @Schema(description = "True if the match would add a person to the target system; false otherwise.")
  private Boolean addsPerson;

  @Schema(description = "True if the match would add a person to the target system who passes the 110-year rule; false otherwise.")
  private Boolean addsPerson110YearRule;

  @Schema(description = "True if the match would add a vital fact to the target system; false otherwise.")
  private Boolean addsFact;

  @Schema(description = "True if the match would add a date or place to an existing vital fact; false otherwise.")
  private Boolean addsDateOrPlace;

  @Schema(description = "True if the destination has four or more people in the immediate family; false otherwise.")
  private Boolean hasFourOrMorePeople;

  @Schema(description = "True if the match would add a father to the target system who passes the 110-year rule; false otherwise.")
  private Boolean addsFather110YearRule;

  @Schema(description = "True if the match would add a mother to the target system who passes the 110-year rule; false otherwise.")
  private Boolean addsMother110YearRule;

  @Schema(description = "True if the match would add a parent of unknown gender to the target system who passes the 110-year rule; false otherwise.")
  private Boolean addsParentUnknownGender110YearRule;

  @Schema(description = "True if the match would add a spouse to the target system who passes the 110-year rule; false otherwise.")
  private Boolean addsSpouse110YearRule;

  @Schema(description = "True if the match would add a son to the target system who passes the 110-year rule; false otherwise.")
  private Boolean addsSon110YearRule;

  @Schema(description = "True if the match would add a daughter to the target system who passes the 110-year rule; false otherwise.")
  private Boolean addsDaughter110YearRule;

  @Schema(description = "True if the match would add a child of unknown gender to the target system who passes the 110-year rule; false otherwise.")
  private Boolean addsChildUnknownGender110YearRule;

  @Schema(description = "True if the match would add a birth fact to the target system; false otherwise.")
  private Boolean addsBirth;

  @Schema(description = "True if the match would add a christening fact to the target system; false otherwise.")
  private Boolean addsChristening;

  @Schema(description = "True if the match would add a death fact to the target system; false otherwise.")
  private Boolean addsDeath;

  @Schema(description = "True if the match would add a burial fact to the target system; false otherwise.")
  private Boolean addsBurial;

  @Schema(description = "True if the match would add a marriage fact to the target system; false otherwise.")
  private Boolean addsMarriage;

  @Schema(description = "True if the match would add a non-vital fact to the target system; false otherwise.")
  private Boolean addsOtherFact;

  @Schema(description = "True if the match would add a date an existing vital fact; false otherwise.")
  private Boolean addsDate;

  @Schema(description = "True if the match would add a place an existing vital fact; false otherwise.")
  private Boolean addsPlace;

  @Schema(description = "True if the match would improve a date an existing vital fact; false otherwise.")
  private Boolean improvesDate;

  @Schema(description = "True if the match would improve a place an existing vital fact; false otherwise.")
  private Boolean improvesPlace;

  public MatchInfo() {
  }

  /**
   * The collection in which this match was found.
   *
   * @return The collection in which this match was found.
   */
  @XmlAttribute
  public URI getCollection() {
    return collection;
  }

  /**
   * The collection in which this match was found.
   *
   * @param collection The collection in which this match was found.
   */
  public void setCollection(URI collection) {
    this.collection = collection;
  }

  /**
   * The system in which this match was found.
   *
   * @return The system in which this match was found.
   * @deprecated Use get/setCollection
   */
  @XmlTransient
  @JsonIgnore
  public URI getSystem() {
    return getCollection();
  }

  /**
   * The system in which this match was found.
   *
   * @param system The system in which this match was found.
   * @deprecated Use get/setCollection
   */
  @JsonIgnore
  public void setSystem(URI system) {
    setCollection(system);
  }

  /**
   * tells if the match would add a person to the target system
   * @return true if the match would add a person to the target system
   */
  @XmlAttribute
  public Boolean getAddsPerson() {
    return addsPerson;
  }

  /**
   * sets whether the match would add a person to the target system
   * @param addsPerson whether the match would add a person to the target system
   */
  public void setAddsPerson(Boolean addsPerson) {
    this.addsPerson = addsPerson;
  }

  /**
   * tells if the match would add a person to the target system who passes the 110-year rule
   * @return true if the match would add a person to the target system who passes the 110-year rule
   */
  @XmlAttribute
  public Boolean getAddsPerson110YearRule() {
    return addsPerson110YearRule;
  }

  /**
   * sets whether the match would add a person to the target system who passes the 110-year rule
   * @param addsPerson110YearRule whether the match would add a person to the target system who passes the 110-year rule
   */
  public void setAddsPerson110YearRule(Boolean addsPerson110YearRule) {
    this.addsPerson110YearRule = addsPerson110YearRule;
  }

  /**
   * tells if the match would add a vital fact to the target system
   * @return true if the match would add a vital fact to the target system
   */
  @XmlAttribute
  public Boolean getAddsFact() {
    return addsFact;
  }

  /**
   * sets whether the match would add a vital fact to the target system
   * @param addsFact whether the match would add a vital fact to the target system
   */
  public void setAddsFact(Boolean addsFact) {
    this.addsFact = addsFact;
  }

  /**
   * tells if the match would add a date or place to an existing vital fact
   * @return true the match would add a date or place to an existing vital fact
   */
  @XmlAttribute
  public Boolean getAddsDateOrPlace() {
    return addsDateOrPlace;
  }

  /**
   * sets whether the match would add a date or place to an existing vital fact
   * @param addsDateOrPlace whether the match would add a date or place to an existing vital fact
   */
  public void setAddsDateOrPlace(Boolean addsDateOrPlace) {
    this.addsDateOrPlace = addsDateOrPlace;
  }

  /**
   * tells if the destination has four or more people in the immediate family
   * @return true if the destination has four or more people in the immediate family
   */
  @XmlAttribute
  public Boolean getHasFourOrMorePeople() {
    return hasFourOrMorePeople;
  }

  /**
   * sets whether the destination has four or more people in the immediate family
   * @param hasFourOrMorePeople whether the destination has four or more people in the immediate family
   */
  public void setHasFourOrMorePeople(Boolean hasFourOrMorePeople) {
    this.hasFourOrMorePeople = hasFourOrMorePeople;
  }

  /**
   * tells if the match would add a father to the target system who passes the 110-year rule
   * @return true if the match would add a father to the target system who passes the 110-year rule
   */
  public Boolean getAddsFather110YearRule() {
    return addsFather110YearRule;
  }

  /**
   * sets whether the match would add a father to the target system who passes the 110-year rule
   * @param addsFather110YearRule whether the match would add a father to the target system who passes the 110-year rule
   */
  public void setAddsFather110YearRule(Boolean addsFather110YearRule) {
    this.addsFather110YearRule = addsFather110YearRule;
  }

  /**
   * tells if the match would add a mother to the target system who passes the 110-year rule
   * @return true if the match would add a mother to the target system who passes the 110-year rule
   */
  public Boolean getAddsMother110YearRule() {
    return addsMother110YearRule;
  }

  /**
   * sets whether the match would add a mother to the target system who passes the 110-year rule
   * @param addsMother110YearRule whether the match would add a mother to the target system who passes the 110-year rule
   */
  public void setAddsMother110YearRule(Boolean addsMother110YearRule) {
    this.addsMother110YearRule = addsMother110YearRule;
  }

  /**
   * tells if the match would add a parent of unknown gender to the target system who passes the 110-year rule
   * @return true if the match would add a parent of unknown gender to the target system who passes the 110-year rule
   */
  public Boolean getAddsParentUnknownGender110YearRule() {
    return addsParentUnknownGender110YearRule;
  }

  /**
   * sets whether the match would add a parent of unknown gender to the target system who passes the 110-year rule
   * @param addsParentUnknownGender110YearRule true if the match would add a parent of unknown gender to the target system who passes the 110-year rule
   */
  public void setAddsParentUnknownGender110YearRule(Boolean addsParentUnknownGender110YearRule) {
    this.addsParentUnknownGender110YearRule = addsParentUnknownGender110YearRule;
  }

  /**
   * tells if the match would add a spouse to the target system who passes the 110-year rule
   * @return true if the match would add a spouse to the target system who passes the 110-year rule
   */
  public Boolean getAddsSpouse110YearRule() {
    return addsSpouse110YearRule;
  }

  /**
   * sets whether the match would add a spouse to the target system who passes the 110-year rule
   * @param addsSpouse110YearRule whether the match would add a spouse to the target system who passes the 110-year rule
   */
  public void setAddsSpouse110YearRule(Boolean addsSpouse110YearRule) {
    this.addsSpouse110YearRule = addsSpouse110YearRule;
  }

  /**
   * tells if the match would add a son to the target system who passes the 110-year rule
   * @return true if the match would add a son to the target system who passes the 110-year rule
   */
  public Boolean getAddsSon110YearRule() {
    return addsSon110YearRule;
  }

  /**
   * sets whether the match would add a son to the target system who passes the 110-year rule
   * @param addsSon110YearRule whether the match would add a son to the target system who passes the 110-year rule
   */
  public void setAddsSon110YearRule(Boolean addsSon110YearRule) {
    this.addsSon110YearRule = addsSon110YearRule;
  }

  /**
   * tells if the match would add a daughter to the target system who passes the 110-year rule
   * @return true if the match would add a daughter to the target system who passes the 110-year rule
   */
  public Boolean getAddsDaughter110YearRule() {
    return addsDaughter110YearRule;
  }

  /**
   * sets whether the match would add a daughter to the target system who passes the 110-year rule
   * @param addsDaughter110YearRule whether the match would add a daughter to the target system who passes the 110-year rule
   */
  public void setAddsDaughter110YearRule(Boolean addsDaughter110YearRule) {
    this.addsDaughter110YearRule = addsDaughter110YearRule;
  }

  /**
   * tells if the match would add a child of unknown gender to the target system who passes the 110-year rule
   * @return true if the match would add a child of unknown gender to the target system who passes the 110-year rule
   */
  public Boolean getAddsChildUnknownGender110YearRule() {
    return addsChildUnknownGender110YearRule;
  }

  /**
   * sets whether the match would add a child of unknown gender to the target system who passes the 110-year rule
   * @param addsChildUnknownGender110YearRule whether the match would add a daughter to the target system who passes the 110-year rule
   */
  public void setAddsChildUnknownGender110YearRule(Boolean addsChildUnknownGender110YearRule) {
    this.addsChildUnknownGender110YearRule = addsChildUnknownGender110YearRule;
  }

  /**
   * tells if the match would add a birth fact to the target system
   * @return true if the match would add a birth fact to the target system
   */
  public Boolean getAddsBirth() {
    return addsBirth;
  }

  /**
   * sets whether the match would add a birth fact to the target system
   * @param addsBirth whether the match would add a birth fact to the target system
   */
  public void setAddsBirth(Boolean addsBirth) {
    this.addsBirth = addsBirth;
  }

  /**
   * tells if the match would add a christening fact to the target system
   * @return true if the match would add a christening fact to the target system
   */
  public Boolean getAddsChristening() {
    return addsChristening;
  }

  /**
   * sets whether the match would add a christening fact to the target system
   * @param addsChristening whether the match would add a christening fact to the target system
   */
  public void setAddsChristening(Boolean addsChristening) {
    this.addsChristening = addsChristening;
  }

  /**
   * tells if the match would add a death fact to the target system
   * @return true if the match would add a death fact to the target system
   */
  public Boolean getAddsDeath() {
    return addsDeath;
  }

  /**
   * sets whether the match would add a death fact to the target system
   * @param addsDeath whether the match would add a death fact to the target system
   */
  public void setAddsDeath(Boolean addsDeath) {
    this.addsDeath = addsDeath;
  }

  /**
   * tells if the match would add a burial fact to the target system
   * @return true if the match would add a burial fact to the target system
   */
  public Boolean getAddsBurial() {
    return addsBurial;
  }

  /**
   * sets whether the match would add a burial fact to the target system
   * @param addsBurial whether the match would add a burial fact to the target system
   */
  public void setAddsBurial(Boolean addsBurial) {
    this.addsBurial = addsBurial;
  }

  /**
   * tells if the match would add a marriage fact to the target system
   * @return true if the match would add a marriage fact to the target system
   */
  public Boolean getAddsMarriage() {
    return addsMarriage;
  }

  /**
   * sets whether the match would add a marriage fact to the target system
   * @param addsMarriage whether the match would add a marriage fact to the target system
   */
  public void setAddsMarriage(Boolean addsMarriage) {
    this.addsMarriage = addsMarriage;
  }

  /**
   * tells if the match would add a non-vital fact to the target system
   * @return true if the match would add a non-vital fact to the target system
   */
  public Boolean getAddsOtherFact() {
    return addsOtherFact;
  }

  /**
   * sets whether the match would add a non-vital fact to the target system
   * @param addsOtherFact whether the match would add a non-vital fact to the target system
   */
  public void setAddsOtherFact(Boolean addsOtherFact) {
    this.addsOtherFact = addsOtherFact;
  }

  /**
   * tells if the match would add a date to an existing vital fact
   * @return true if the match would add a date to an existing vital fact
   */
  public Boolean getAddsDate() {
    return addsDate;
  }

  /**
   * sets whether the match would add a date to an existing vital fact
   * @param addsDate whether the match would add a date to an existing vital fact
   */
  public void setAddsDate(Boolean addsDate) {
    this.addsDate = addsDate;
  }

  /**
   * tells if the match would add a place to an existing vital fact
   * @return true if the match would add a place to an existing vital fact
   */
  public Boolean getAddsPlace() {
    return addsPlace;
  }

  /**
   * sets whether the match would add a place to an existing vital fact
   * @param addsPlace whether the match would add a place to an existing vital fact
   */
  public void setAddsPlace(Boolean addsPlace) {
    this.addsPlace = addsPlace;
  }

  /**
   * tells if the match would improve a date an existing vital fact
   * @return true if the match would improve a date an existing vital fact
   */
  public Boolean getImprovesDate() {
    return improvesDate;
  }

  /**
   * sets whether the match would improve a date an existing vital fact
   * @param improvesDate whether the match would improve a date an existing vital fact
   */
  public void setImprovesDate(Boolean improvesDate) {
    this.improvesDate = improvesDate;
  }

  /**
   * tells if the match would improve a place an existing vital fact
   * @return true if the match would improve a place an existing vital fact
   */
  public Boolean getImprovesPlace() {
    return improvesPlace;
  }

  /**
   * sets whether the match would improve a place an existing vital fact
   * @param improvesPlace whether the match would improve a place an existing vital fact
   */
  public void setImprovesPlace(Boolean improvesPlace) {
    this.improvesPlace = improvesPlace;
  }

  /**
   * The enum referencing the known collection of this match.
   *
   * @return The enum referencing the known collection of this match.
   */
  @XmlTransient
  @JsonIgnore
  public MatchCollection getKnownCollection() {
    return getCollection() == null ? null : MatchCollection.fromQNameURI(getCollection());
  }

  /**
   * The enum referencing the known collection of this match.
   *
   * @param knownCollection The enum referencing the known collection of this match.
   */
  @JsonIgnore
  public void setKnownCollection(MatchCollection knownCollection) {
    setCollection(knownCollection == null ? null : knownCollection.toQNameURI());
  }

  /**
   * The way this match has been resolved.
   *
   * @return The way this match has been resolved.
   */
  @XmlAttribute
  @XmlQNameEnumRef (MatchStatus.class)
  public URI getStatus() {
    return status;
  }

  /**
   * The way this match has been resolved.
   *
   * @param status The way this match has been resolved.
   */
  public void setStatus(URI status) {
    this.status = status;
  }

  /**
   * The enum referencing the known resolution of this match.
   *
   * @return The enum referencing the known resolution of this match.
   */
  @XmlTransient
  @JsonIgnore
  public MatchStatus getKnownStatus() {
    return getStatus() == null ? null : MatchStatus.fromQNameURI(getStatus());
  }

  /**
   * The enum referencing the known resolution of this match.
   *
   * @param knownResolution The enum referencing the known resolution of this match.
   */
  @JsonIgnore
  public void setKnownStatus(MatchStatus knownResolution) {
    setStatus(knownResolution == null ? null : knownResolution.toQNameURI());
  }
}
