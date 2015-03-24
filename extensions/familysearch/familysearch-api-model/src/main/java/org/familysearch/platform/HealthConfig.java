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

import org.gedcomx.rt.json.JsonElementWrapper;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * @author Mike Gardiner
 */
@XmlRootElement
@JsonElementWrapper ( name = "healthConfig" )
@XmlType ( name = "HealthConfig" )
public class HealthConfig {
  private String databaseVersion;
  private String buildVersion;
  private String buildDate;
  private String platformVersion;

  private String systemName;
  private String runningOnAws;
  private String catalinaBase;
  private String hostname;
  private String username;
  private String osVersion;
  private String javaVersion;



  public String getPlatformVersion() {
    return platformVersion;
  }

  public void setPlatformVersion( String platformVersion ) {
    this.platformVersion = platformVersion;
  }

  public String getDatabaseVersion() {
    return databaseVersion;
  }

  public void setDatabaseVersion(String databaseVersion) {
    this.databaseVersion = databaseVersion;
  }

  public String getBuildVersion() {
    return buildVersion;
  }

  public void setBuildVersion(String buildVersion) {
    this.buildVersion = buildVersion;
  }

  public String getBuildDate() {
    return buildDate;
  }

  public void setBuildDate(String buildDate) {
    this.buildDate = buildDate;
  }

  public String getSystemName() {
    return systemName;
  }

  public void setSystemName(String systemName) {
    this.systemName = systemName;
  }

  public String getRunningOnAws() {
    return runningOnAws;
  }

  public void setRunningOnAws(String runningOnAws) {
    this.runningOnAws = runningOnAws;
  }

  public String getCatalinaBase() {
    return catalinaBase;
  }

  public void setCatalinaBase(String catalinaBase) {
    this.catalinaBase = catalinaBase;
  }

  public String getHostname() {
    return hostname;
  }

  public void setHostname(String hostname) {
    this.hostname = hostname;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getOsVersion() {
    return osVersion;
  }

  public void setOsVersion(String osVersion) {
    this.osVersion = osVersion;
  }

  public String getJavaVersion() {
    return javaVersion;
  }

  public void setJavaVersion(String javaVersion) {
    this.javaVersion = javaVersion;
  }
}
