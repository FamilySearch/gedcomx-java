package org.gedcomx.conclusion;

import org.gedcomx.common.ResourceReference;
import org.gedcomx.common.URI;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


/**
 * Class for testing the Family class.
 * User: Randy Wilson
 * Date: 15 May 2015
 */
class FamilyTest {

  @Test
  void family() {
    FamilyView family = new FamilyView();

    // Test parents and children
    family.setParent1(new ResourceReference(new URI("#parent1"), "parent1"));
    family.setParent2(new ResourceReference(new URI("#parent2"), "parent2"));
    family.addChild(new ResourceReference(new URI("#child1"), "child1"));
    family.addChild(new ResourceReference(new URI("#child2"), "child2"));
    assertEquals("parent1", family.getParent1().getResourceId());
    assertEquals("#parent1", family.getParent1().getResource().toString());
    assertEquals("#parent2", family.getParent2().getResource().toString());
    assertEquals(2, family.getChildren().size());
    assertEquals("#child1", family.getChildren().get(0).getResource().toString());
    assertEquals("#child2", family.getChildren().get(1).getResource().toString());
  }
}
