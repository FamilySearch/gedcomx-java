package org.familysearch.platform.ct;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TreeTest {
  /**
   * Method under test: {@link Tree#id(String)}
   */
  @Test
  void testId() {
    // Given
    Tree tree = new Tree();

    // When
    Tree actualIdResult = tree.id("42");

    // Then
    assertEquals("42", actualIdResult.getId());
    assertSame(tree, actualIdResult);
  }

  /**
   * Method under test: {@link Tree#name(String)}
   */
  @Test
  void testName() {
    // Given
    Tree tree = new Tree();

    // When
    Tree actualNameResult = tree.name("Name");

    // Then
    assertEquals("Name", actualNameResult.getName());
    assertSame(tree, actualNameResult);
  }

  /**
   * Method under test: {@link Tree#description(String)}
   */
  @Test
  void testDescription() {
    // Given
    Tree tree = new Tree();

    // When
    Tree actualDescriptionResult = tree.description("The characteristics of someone or something");

    // Then
    assertEquals("The characteristics of someone or something", actualDescriptionResult.getDescription());
    assertSame(tree, actualDescriptionResult);
  }

  /**
   * Method under test: {@link Tree#startingPersonId(String)}
   */
  @Test
  void testStartingPersonId() {
    // Given
    Tree tree = new Tree();

    // When
    Tree actualStartingPersonIdResult = tree.startingPersonId("42");

    // Then
    assertEquals("42", actualStartingPersonIdResult.getStartingPersonId());
    assertSame(tree, actualStartingPersonIdResult);
  }

  /**
   * Method under test: {@link Tree#setTreeHidden(Boolean)}
   */
  @Test
  void testSetTreeHidden() {
    // Given
    Tree tree = new Tree();

    // When
    Tree actualSetTreeHiddenResult = tree.setTreeHidden(true);

    // Then
    assertTrue(actualSetTreeHiddenResult.isHidden());
    assertSame(tree, actualSetTreeHiddenResult);
  }

  /**
   * Methods under test:
   *
   * <ul>
   *   <li>{@link Tree#setDescription(String)}
   *   <li>{@link Tree#setHidden(Boolean)}
   *   <li>{@link Tree#setId(String)}
   *   <li>{@link Tree#setName(String)}
   *   <li>{@link Tree#setStartingPersonId(String)}
   *   <li>{@link Tree#getDescription()}
   *   <li>{@link Tree#getId()}
   *   <li>{@link Tree#getName()}
   *   <li>{@link Tree#getStartingPersonId()}
   *   <li>{@link Tree#isHidden()}
   * </ul>
   */
  @Test
  void testGettersAndSetters() {
    // Given
    Tree tree = new Tree();

    // When
    tree.setDescription("The characteristics of someone or something");
    tree.setHidden(true);
    tree.setId("42");
    tree.setName("Name");
    tree.setStartingPersonId("42");
    String actualDescription = tree.getDescription();
    String actualId = tree.getId();
    String actualName = tree.getName();
    String actualStartingPersonId = tree.getStartingPersonId();

    // Then
    assertEquals("42", actualId);
    assertEquals("42", actualStartingPersonId);
    assertEquals("Name", actualName);
    assertEquals("The characteristics of someone or something", actualDescription);
    assertTrue(tree.isHidden());
  }
}
