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
package org.familysearch.platform.users;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.familysearch.platform.rt.FamilySearchPlatformModelVisitor;
import org.gedcomx.links.HypermediaEnabledData;
import org.gedcomx.rt.json.JsonElementWrapper;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;

/**
 * Family Tree User Service User
 */
@XmlRootElement
@XmlType ( name = "User" )
@JsonElementWrapper ( name = "users" )
@JsonInclude ( JsonInclude.Include.NON_NULL )
@Schema(description = "Family Tree User Service User")
public class User extends HypermediaEnabledData {

  @Schema(description = "The contact name of the user.")
  private String contactName;

  @Schema(description = "The helper access pin of the user.")
  private String helperAccessPin;

  @Schema(description = "The full name of the user.")
  private String fullName;

  @Schema(description = "The given name of the user.")
  private String givenName;

  @Schema(description = "The family name of the user.")
  private String familyName;

  @Schema(description = "The email of the user.")
  private String email;

  @Schema(description = "The alternate email of the user.")
  private String alternateEmail;

  @Schema(description = "The country of the user.")
  private String country;

  @Schema(description = "The gender of the user.")
  private String gender;

  @Schema(description = "The birth date of the user.")
  private String birthDate;

  @Schema(description = "The phone number of the user.")
  private String phoneNumber;

  @Schema(description = "The mobile phone number of the user.")
  private String mobilePhoneNumber;

  @Schema(description = "The mailing address of the user.")
  private String mailingAddress;

  @Schema(description = "The preferred language of the user.")
  private String preferredLanguage;

  @Schema(description = "The display name of the user.")
  private String displayName;

  @Schema(description = "The person id of the user.")
  private String personId;

  @Schema(description = "The tree user id of the user.")
  private String treeUserId;

  public String getContactName() {
    return contactName;
  }

  public void setContactName(String contactName) {
    this.contactName = contactName;
  }

  public String getHelperAccessPin() {
    return helperAccessPin;
  }

  public void setHelperAccessPin(String helperAccessPin) {
    this.helperAccessPin = helperAccessPin;
  }

  public String getFullName() {
    return fullName;
  }

  public void setFullName(String fullName) {
    this.fullName = fullName;
  }

  public String getGivenName() {
    return givenName;
  }

  public void setGivenName(String givenName) {
    this.givenName = givenName;
  }

  public String getFamilyName() {
    return familyName;
  }

  public void setFamilyName(String familyName) {
    this.familyName = familyName;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getAlternateEmail() {
    return alternateEmail;
  }

  public void setAlternateEmail(String alternateEmail) {
    this.alternateEmail = alternateEmail;
  }

  public String getCountry() {
    return country;
  }

  public void setCountry(String country) {
    this.country = country;
  }

  public String getGender() {
    return gender;
  }

  public void setGender(String gender) {
    this.gender = gender;
  }

  public String getBirthDate() {
    return birthDate;
  }

  public void setBirthDate(String birthDate) {
    this.birthDate = birthDate;
  }

  public String getPhoneNumber() {
    return phoneNumber;
  }

  public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }

  public String getMobilePhoneNumber() {
    return mobilePhoneNumber;
  }

  public void setMobilePhoneNumber(String mobilePhoneNumber) {
    this.mobilePhoneNumber = mobilePhoneNumber;
  }

  public String getMailingAddress() {
    return mailingAddress;
  }

  public void setMailingAddress(String mailingAddress) {
    this.mailingAddress = mailingAddress;
  }

  public String getPreferredLanguage() {
    return preferredLanguage;
  }

  public void setPreferredLanguage(String preferredLanguage) {
    this.preferredLanguage = preferredLanguage;
  }

  public String getDisplayName() {
    return displayName;
  }

  public void setDisplayName(String displayName) {
    this.displayName = displayName;
  }

  public String getPersonId() {
    return personId;
  }

  public void setPersonId(String personId) {
    this.personId = personId;
  }

  public String getTreeUserId() {
    return treeUserId;
  }

  public void setTreeUserId(String treeUserId) {
    this.treeUserId = treeUserId;
  }

  /**
   * Accept a visitor.
   *
   * @param visitor The visitor to accept.
   */
  public void accept(FamilySearchPlatformModelVisitor visitor) {
    visitor.visitUser(this);
  }
}
