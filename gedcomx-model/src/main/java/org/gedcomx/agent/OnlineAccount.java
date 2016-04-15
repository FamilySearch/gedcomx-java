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
import org.gedcomx.common.ResourceReference;

import javax.xml.bind.annotation.XmlType;

/**
 * An online account for a web application.
 *
 * @author Ryan Heaton
 */
@XmlType( name = "OnlineAccount" )
public class OnlineAccount extends ExtensibleData {

  private ResourceReference serviceHomepage;
  private String accountName;

  @Override
  public OnlineAccount id(String id) {
    return (OnlineAccount) super.id(id);
  }

  @Override
  public OnlineAccount extensionElement(Object element) {
    return (OnlineAccount) super.extensionElement(element);
  }

  /**
   * The homepage of the service that provides this account.
   *
   * @return The homepage of the service that provides this account.
   */
  public ResourceReference getServiceHomepage() {
    return serviceHomepage;
  }

  /**
   * The homepage of the service that provides this account.
   *
   * @param serviceHomepage The homepage of the service that provides this account.
   */
  public void setServiceHomepage(ResourceReference serviceHomepage) {
    this.serviceHomepage = serviceHomepage;
  }

  /**
   * Build up this online account with a service homepage.
   *
   * @param serviceHomepage The service homepage.
   * @return this.
   */
  public OnlineAccount serviceHomepage(ResourceReference serviceHomepage) {
    this.serviceHomepage = serviceHomepage;
    return this;
  }

  /**
   * The name associated the holder of this account with the account.
   *
   * @return The name associated the holder of this account with the account.
   */
  public String getAccountName() {
    return accountName;
  }

  /**
   * The name associated the holder of this account with the account.
   *
   * @param accountName The name associated the holder of this account with the account.
   */
  public void setAccountName(String accountName) {
    this.accountName = accountName;
  }

  /**
   * Build up this online account with an account name.
   *
   * @param accountName The account name.
   */
  public OnlineAccount accountName(String accountName) {
    this.accountName = accountName;
    return this;
  }
}
