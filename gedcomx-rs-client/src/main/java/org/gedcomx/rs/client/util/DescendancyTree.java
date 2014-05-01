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
import org.gedcomx.conclusion.Person;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Ryan Heaton
 */
public class DescendancyTree {

  private DescendancyNode root;

  public DescendancyTree(Gedcomx gx) {
    this.root = buildTree(gx);
  }

  protected DescendancyNode buildTree(Gedcomx gx) {
    DescendancyNode root = null;
    if (gx.getPersons() != null && gx.getPersons().size() > 0) {
      List<DescendancyNode> rootArray = new ArrayList<DescendancyNode>();
      for (Person person : gx.getPersons()) {
        if (person.getDisplayExtension() != null && person.getDisplayExtension().getDescendancyNumber() != null) {
          String number = person.getDisplayExtension().getDescendancyNumber();
          boolean spouse = number.endsWith("-S") || number.endsWith("-s");
          if (spouse) {
            number = number.substring(0, number.length() - 2);
          }
          int[] coordinates = parseCoordinates(number);
          List<DescendancyNode> current = rootArray;
          int i = 0;
          DescendancyNode node = null;
          while (current != null) {
            int coordinate = coordinates[i];
            while (current.size() < coordinate) {
              current.add(null);
            }

            node = current.get(coordinate - 1);
            if (node == null) {
              node = new DescendancyNode();
              current.set(coordinate - 1, node);
            }

            if (++i < coordinates.length) {
              //if we still have another generation to descend, make sure the list is initialized.
              List<DescendancyNode> children = node.getChildren();
              if (children == null) {
                children = new ArrayList<DescendancyNode>();
                node.setChildren(children);
              }
              current = children;
            }
            else {
              current = null;
            }
          }

          if (spouse) {
            node.setSpouse(person);
          }
          else {
            node.setPerson(person);
          }
        }
      }

      if (rootArray.size() > 0) {
        root = rootArray.get(0);
      }
    }
    return root;
  }

  public DescendancyNode getRoot() {
    return root;
  }

  protected int[] parseCoordinates(String number) throws NumberFormatException {
    List<StringBuilder> coords = new ArrayList<StringBuilder>();
    StringBuilder current = new StringBuilder();
    for (int i = 0; i < number.length(); i++) {
      char ch = number.charAt(i);
      if (ch == '.') {
        coords.add(current);
        current = new StringBuilder();
      }
      else {
        current.append(ch);
      }
    }
    coords.add(current);
    
    int[] coordinates = new int[coords.size()];
    for (int i = 0; i < coords.size(); i++) {
      StringBuilder num = coords.get(i);
      coordinates[i] = Integer.parseInt(num.toString());
    }
    return coordinates;
  }


  public class DescendancyNode {

    private Person person;
    private Person spouse;
    private List<DescendancyNode> children;

    public Person getPerson() {
      return person;
    }

    public void setPerson(Person person) {
      this.person = person;
    }

    public Person getSpouse() {
      return spouse;
    }

    public void setSpouse(Person spouse) {
      this.spouse = spouse;
    }

    public List<DescendancyNode> getChildren() {
      return children;
    }

    public void setChildren(List<DescendancyNode> children) {
      this.children = children;
    }
  }
}
