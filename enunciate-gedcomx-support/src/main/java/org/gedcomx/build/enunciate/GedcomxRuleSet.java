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
package org.gedcomx.build.enunciate;

import org.apache.commons.digester.Digester;
import org.apache.commons.digester.RuleSetBase;

/**
 * @author Ryan Heaton
 */
public class GedcomxRuleSet extends RuleSetBase {

  public void addRuleInstances(Digester digester) {
    digester.addCallMethod("enunciate/modules/gedcomx/nav/a", "addPrimaryNav", 2);
    digester.addCallParam("enunciate/modules/gedcomx/nav/a", 0);
    digester.addCallParam("enunciate/modules/gedcomx/nav/a", 1, "href");

    digester.addCallMethod("enunciate/modules/gedcomx/project", "putProjectBase", 2);
    digester.addCallParam("enunciate/modules/gedcomx/project", 0, "id");
    digester.addCallParam("enunciate/modules/gedcomx/project", 1, "baseUri");
  }
}
