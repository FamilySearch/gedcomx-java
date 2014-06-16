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

import org.codehaus.enunciate.Facet;
import org.codehaus.enunciate.json.JsonName;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonProperty;
import org.gedcomx.common.ExtensibleData;
import org.gedcomx.common.ResourceReference;
import org.gedcomx.common.TextValue;
import org.gedcomx.common.URI;
import org.gedcomx.conclusion.Identifier;
import org.gedcomx.links.HypermediaEnabledData;
import org.gedcomx.links.Link;
import org.gedcomx.rt.GedcomxConstants;
import org.gedcomx.rt.GedcomxModelVisitor;
import org.gedcomx.rt.json.JsonElementWrapper;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;


/**
 * An agent, e.g. person, organization, or group. In genealogical research, an agent often
 * takes the role of a contributor.
 *
 * @see <a href="http://xmlns.com/foaf/spec/#term_Agent">foaf:Agent</a>
 * @author Ryan Heaton
 */
@XmlRootElement
@XmlType ( name = "Agent" )
@JsonElementWrapper ( name = "agents" )
public class Agent extends HypermediaEnabledData {

  private Boolean anchor;
  private List<TextValue> names;
  private List<Identifier> identifiers;
  private ResourceReference homepage;
  private ResourceReference openid;
  private List<OnlineAccount> accounts;
  private List<ResourceReference> emails;
  private List<ResourceReference> phones;
  private List<Address> addresses;

  @Override
  public Agent id(String id) {
    return (Agent) super.id(id);
  }

  @Override
  public Agent link(String rel, URI href) {
    return (Agent) super.link(rel, href);
  }

  @Override
  public Agent link(Link link) {
    return (Agent) super.link(link);
  }

  /**
   * Whether this subject has been identified as the "anchor".
   *
   * @return Whether this subject has been identified as "anchor".
   */
  @XmlAttribute
  @Facet ( name = GedcomxConstants.FACET_GEDCOMX_RS )
  public Boolean getAnchor() {
    return anchor;
  }

  /**
   * Whether this subject has been identified as the "anchor".
   *
   * @param anchor Whether this subject has been identified as "anchor".
   */
  public void setAnchor(Boolean anchor) {
    this.anchor = anchor;
  }

  /**
   * Build up this subject with an anchor flag.
   *
   * @param anchor The anchor flag.
   * @return this.
   */
  public Agent anchor(Boolean anchor) {
    setAnchor(anchor);
    return this;
  }

  /**
   * The preferred name for this agent.
   *
   * @return The preferred name for this agent.
   */
  @XmlTransient
  @JsonIgnore
  public TextValue getName() {
    return this.names == null || this.names.isEmpty() ? null : this.names.get(0);
  }

  /**
   * The list of names for the agent.
   *
   * @return The list of names for the agent.
   */
  @XmlElement (name="name")
  @JsonProperty ("names")
  @JsonName ("names")
  public List<TextValue> getNames() {
    return names;
  }

  /**
   * The list of names for the agent.
   *
   * @param names The list of names for the agent.
   */
  @JsonProperty ("names")
  public void setNames(List<TextValue> names) {
    this.names = names;
  }

  /**
   * Build up this agent with a name.
   * 
   * @param name The name.
   * @return this.
   */
  public Agent name(TextValue name) {
    addName(name);
    return this;
  }

  /**
   * Build up this agent with a name.
   *
   * @param name The name.
   * @return this.
   */
  public Agent name(String name) {
    return name(new TextValue(name));
  }

  /**
   * Add a name.
   * 
   * param name The name.
   */
  public void addName(TextValue name) {
    if (this.names == null) {
      this.names = new ArrayList<TextValue>();
    }

    this.names.add(name);
  }

  /**
   * The list of identifiers for the agent.
   *
   * @return The list of identifiers for the agent.
   */
  @XmlElement (name="identifier")
  @JsonProperty ("identifiers")
  @JsonName ("identifiers")
  public List<Identifier> getIdentifiers() {
    return identifiers;
  }

  /**
   * The list of identifiers of the agent.
   *
   * @param identifiers The list of identifiers of the agent.
   */
  @JsonProperty ("identifiers")
  public void setIdentifiers(List<Identifier> identifiers) {
    this.identifiers = identifiers;
  }

  /**
   * Build up this agent with an identifier.
   * 
   * @param identifier The identifier.
   * @return this.
   */
  public Agent identifier(Identifier identifier) {
    addIdentifier(identifier);
    return this;
  }

  /**
   * Add an identifier.
   * 
   * @param identifier The identifier to add.
   */
  public void addIdentifier(Identifier identifier) {
    if (this.identifiers == null) {
      this.identifiers = new ArrayList<Identifier>();
    }

    this.identifiers.add(identifier);
  }

  /**
   * The homepage of the person or organization. Note this is different from the
   * homepage of the service where the person or organization has an account.
   *
   * @return The homepage.
   */
  public ResourceReference getHomepage() {
    return homepage;
  }

  /**
   * The homepage of the person or organization. Note this is different from the
   * homepage of the service where the person or organization has an account.
   *
   * @param homepage The homepage.
   */
  public void setHomepage(ResourceReference homepage) {
    this.homepage = homepage;
  }

  /**
   * Build up this agent with a home page.
   * @param homepage The home page of the agent.
   * @return this.
   */
  public Agent homepage(ResourceReference homepage) {
    this.homepage = homepage;
    return this;
  }

  /**
   * Build up this agent with a home page.
   * @param homepage The home page of the agent.
   * @return this.
   */
  public Agent homepage(URI homepage) {
    return homepage(new ResourceReference(homepage));
  }

  /**
   * The <a href="http://openid.net/">openid</a> of the person or organization.
   *
   * @return The <a href="http://openid.net/">openid</a> of the person or organization.
   */
  public ResourceReference getOpenid() {
    return openid;
  }

  /**
   * The <a href="http://openid.net/">openid</a> of the person or organization.
   *
   * @param openid The <a href="http://openid.net/">openid</a> of the person or organization.
   */
  public void setOpenid(ResourceReference openid) {
    this.openid = openid;
  }

  /**
   * Build up this agent with a open id.
   * @param openid The open id of the agent.
   * @return this.
   */
  public Agent openid(ResourceReference openid) {
    this.openid = openid;
    return this;
  }

  /**
   * Build up this agent with a open id.
   * @param openid The open id of the agent.
   * @return this.
   */
  public Agent openid(URI openid) {
    return openid(new ResourceReference(openid));
  }

  /**
   * The accounts that belong to this person or organization.
   *
   * @return The accounts that belong to this person or organization.
   */
  @XmlElement(name = "account")
  @JsonName ("accounts")
  @JsonProperty ("accounts")
  public List<OnlineAccount> getAccounts() {
    return accounts;
  }

  /**
   * The accounts that belong to this person or organization.
   *
   * @param accounts The accounts that belong to this person or organization.
   */
  @JsonProperty ("accounts")
  public void setAccounts(List<OnlineAccount> accounts) {
    this.accounts = accounts;
  }

  /**
   * Build up this agent with an online account.
   * @param account The account.
   * @return this.
   */
  public Agent account(OnlineAccount account) {
    addAccount(account);
    return this;
  }

  /**
   * Add an account.
   *
   * @param account The account to add.
   */
  public void addAccount(OnlineAccount account) {
    if (this.accounts == null) {
      this.accounts = new ArrayList<OnlineAccount>();
    }

    this.accounts.add(account);
  }

  /**
   * The emails that belong to this person or organization.
   *
   * @return The emails that belong to this person or organization.
   */
  @XmlElement(name = "email")
  @JsonName ("emails")
  @JsonProperty ("emails")
  public List<ResourceReference> getEmails() {
    return emails;
  }

  /**
   * The emails that belong to this person or organization.
   *
   * @param emails The emails that belong to this person or organization.
   */
  @JsonProperty ("emails")
  public void setEmails(List<ResourceReference> emails) {
    this.emails = emails;
  }

  /**
   * Build up this agent with an email address.
   *
   * @param email The email address.
   * @return this.
   */
  public Agent email(ResourceReference email) {
    addEmail(email);
    return this;
  }

  /**
   * Build up this agent with an email address.
   *
   * @param email The email address.
   * @return this.
   */
  public Agent email(URI email) {
    return email(new ResourceReference(email));
  }

  /**
   * Build up this agent with an email address.
   *
   * @param email The email address.
   * @return this.
   */
  public Agent email(String email) {
    return email(URI.create("mailto:" + email));
  }

  /**
   * Add an email.
   *
   * @param email The email.
   */
  public void addEmail(ResourceReference email) {
    if (this.emails == null) {
      this.emails = new ArrayList<ResourceReference>();
    }

    this.emails.add(email);
  }

  /**
   * The phones that belong to this person or organization.
   *
   * @return The phones that belong to this person or organization.
   */
  @XmlElement(name = "phone")
  @JsonName ("phones")
  @JsonProperty ("phones")
  public List<ResourceReference> getPhones() {
    return phones;
  }

  /**
   * The phones that belong to this person or organization.
   *
   * @param phones The phones that belong to this person or organization.
   */
  @JsonProperty ("phones")
  public void setPhones(List<ResourceReference> phones) {
    this.phones = phones;
  }

  /**
   * Build up this agent with a phone number.
   * @param phone The phone number.
   * @return this.
   */
  public Agent phone(ResourceReference phone) {
    addPhone(phone);
    return this;
  }

  /**
   * Build up this agent with a phone number.
   * @param phone The phone number.
   * @return this.
   */
  public Agent phone(URI phone) {
    return phone(new ResourceReference(phone));
  }

  /**
   * Build up this agent with a phone number.
   * @param phone The phone number.
   * @return this.
   */
  public Agent phone(String phone) {
    return phone(URI.create("tel:" + phone));
  }

  /**
   * Add a phone.
   *
   * @param phone The phone to add.
   */
  public void addPhone(ResourceReference phone) {
    if (this.phones == null) {
      this.phones = new ArrayList<ResourceReference>();
    }

    this.phones.add(phone);
  }

  /**
   * The addresses that belong to this person or organization.
   *
   * @return The addresses that belong to this person or organization.
   */
  @XmlElement(name = "address")
  @JsonName ("addresses")
  @JsonProperty ("addresses")
  @SuppressWarnings("gedcomx:plural_xml_name")
  public List<Address> getAddresses() {
    return addresses;
  }

  /**
   * The addresses that belong to this person or organization.
   *
   * @param addresses The addresses that belong to this person or organization.
   */
  @JsonProperty ("addresses")
  public void setAddresses(List<Address> addresses) {
    this.addresses = addresses;
  }

  /**
   * Build up this agent with an address.
   * @param address The address.
   * @return this.
   */
  public Agent address(Address address) {
    addAddress(address);
    return this;
  }

  /**
   * Add an address.
   *
   * @param address The address to add.
   */
  public void addAddress(Address address) {
    if (this.addresses == null) {
      this.addresses = new ArrayList<Address>();
    }

    this.addresses.add(address);
  }


  /**
   * Accept a visitor.
   *
   * @param visitor The visitor.
   */
  public void accept(GedcomxModelVisitor visitor) {
    visitor.visitAgent(this);
  }

  /**
   * Embed another agent.
   *
   * @param agent The agent to embed.
   */
  public void embed(Agent agent) {
    if (agent.names != null) {
      this.names = this.names == null ? new ArrayList<TextValue>() : this.names;
      this.names.addAll(agent.names);
    }

    if (agent.identifiers != null) {
      this.identifiers = this.identifiers == null ? new ArrayList<Identifier>() : this.identifiers;
      this.identifiers.addAll(agent.identifiers);
    }

    if (agent.accounts != null) {
      this.accounts = this.accounts == null ? new ArrayList<OnlineAccount>() : this.accounts;
      this.accounts.addAll(agent.accounts);
    }
    
    if (agent.emails != null) {
      this.emails = this.emails == null ? new ArrayList<ResourceReference>() : this.emails;
      this.emails.addAll(agent.emails);
    }
    
    if (agent.phones != null) {
      this.phones = this.phones == null ? new ArrayList<ResourceReference>() : this.phones;
      this.phones.addAll(agent.phones);
    }
    
    if (agent.addresses != null) {
      this.addresses = this.addresses == null ? new ArrayList<Address>() : this.addresses;
      this.addresses.addAll(agent.addresses);
    }

    this.homepage = this.homepage == null ? agent.homepage : this.homepage;
    this.openid = this.openid == null ? agent.openid : this.openid;

    super.embed(agent);
  }
}
