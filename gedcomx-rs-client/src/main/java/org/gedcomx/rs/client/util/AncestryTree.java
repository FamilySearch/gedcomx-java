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
package org.gedcomx.rs.client.util;

import org.gedcomx.Gedcomx;
import org.gedcomx.conclusion.DisplayProperties;
import org.gedcomx.conclusion.Person;

import java.util.ArrayList;

/**
* @author Ryan Heaton
*/
public class AncestryTree {

  private final ArrayList<Person> ancestry;

  public AncestryTree(Gedcomx gx) {
    this.ancestry = buildArray(gx);
  }

  protected ArrayList<Person> buildArray(Gedcomx gx) {
    ArrayList<Person> ancestry = new ArrayList<Person>();
    if (gx.getPersons() != null) {
      for (Person person : gx.getPersons()) {
        DisplayProperties display = person.getDisplayExtension();
        if (display != null && display.getAscendancyNumber() != null) {
          try {
            int number = Integer.parseInt(display.getAscendancyNumber());
            while (ancestry.size() < number) {
              ancestry.add(null);
            }
            ancestry.set(number - 1, person);
          }
          catch (NumberFormatException e) {
            //fall through...
          }
        }
      }
    }
    return ancestry;
  }

  public AncestryNode getRoot() {
    return getAncestor(1);
  }

  public AncestryNode getAncestor(int number) {
    return ancestry.size() < number ? null : new AncestryNode(number);
  }

  public class AncestryNode {

    private final int number;

    public AncestryNode(int number) {
      this.number = number;
    }

    public Person getPerson() {
      return ancestry.get(this.number - 1);
    }

    public AncestryNode getFather() {
      return getAncestor(this.number * 2);
    }

    public AncestryNode getMother() {
      return getAncestor((this.number * 2) + 1);
    }
  }

}
