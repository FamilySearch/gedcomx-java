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
package org.familysearch.platform.records;

import org.gedcomx.conclusion.Fact;
import org.gedcomx.rt.GedcomxModelVisitorBase;

public class GedcomxRecordModelVisitorBase extends GedcomxModelVisitorBase{

  /**
   * We must ensure to visit these extension element types when visiting facts
   * @param fact fact found in the gedcomx
   */
  @Override
  public void visitFact(Fact fact) {
    super.visitFact(fact);
    if (fact.getExtensionElements() != null) {
      fact.getExtensionElements().forEach(extensionElement-> {
        if (extensionElement instanceof AlternatePlaceReference) {
          visitPlaceReference((AlternatePlaceReference)extensionElement);
        }
        else if (extensionElement instanceof AlternateDate) {
          visitDate((AlternateDate)extensionElement);
        }
      });
    }
  }
}
