package org.gedcomx.common;

import java.util.ArrayList;
import java.util.List;

import org.gedcomx.Gedcomx;
import org.gedcomx.rt.GedcomNamespaceManager;
import org.junit.jupiter.api.Test;

import static org.gedcomx.rt.SerializationUtil.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for Jackson 3.x JSON serialization.
 */
class JsonCustomization3Test {

  /**
   * tests keyed items json with Jackson 3
   */
  @Test
  void keyedItemsJson() throws Exception {
    CustomEntity custom = new CustomEntity();
    custom.setRefToSomething(new org.gedcomx.common.URI("uri:hello"));

    custom.setUniqueKeyedItems(new ArrayList<>());
    UniqueCustomKeyedItem item1 = new UniqueCustomKeyedItem();
    item1.setVal1("1");
    item1.setVal2("2");
    String key1 = item1.getKey();
    custom.getUniqueKeyedItems().add(item1);
    UniqueCustomKeyedItem item2 = new UniqueCustomKeyedItem();
    item2.setVal1("one");
    item2.setVal2("two");
    String key2 = item2.getKey();
    custom.getUniqueKeyedItems().add(item2);

    custom.setKeyedItems(new ArrayList<>());
    CustomKeyedItem item3 = new CustomKeyedItem();
    item3.setVal1("1");
    item3.setVal2("2");
    String key3 = item3.getKey();
    custom.getKeyedItems().add(item3);
    CustomKeyedItem item4 = new CustomKeyedItem();
    item4.setVal1("one");
    item4.setVal2("two");
    item4.setKey(key3);
    custom.getKeyedItems().add(item4);

    custom = processThroughJson(custom, CustomEntity.class,
      org.gedcomx.rt.json.jackson3.GedcomJackson3Module.createObjectMapper(CustomEntity.class));

    assertEquals("uri:hello", custom.getRefToSomething().toString());
    assertEquals(2, custom.getUniqueKeyedItems().size());
    for (UniqueCustomKeyedItem keyedItem : custom.getUniqueKeyedItems()) {
      if ("1".equals(keyedItem.getVal1())) {
        assertEquals("2", keyedItem.getVal2());
        assertEquals(key1, keyedItem.getKey());
      }
      else if ("one".equals(keyedItem.getVal1())) {
        assertEquals("two", keyedItem.getVal2());
        assertEquals(key2, keyedItem.getKey());
      }
      else {
        fail("Unknown keyed item.");
      }
    }
    assertEquals(2, custom.getKeyedItems().size());
    for (CustomKeyedItem keyedItem : custom.getKeyedItems()) {
      if ("1".equals(keyedItem.getVal1())) {
        assertEquals("2", keyedItem.getVal2());
        assertEquals(key3, keyedItem.getKey());
      }
      else if ("one".equals(keyedItem.getVal1())) {
        assertEquals("two", keyedItem.getVal2());
        assertEquals(key3, keyedItem.getKey());
      }
      else {
        fail("Unknown keyed item.");
      }
    }
  }

  @Test
  void keyedItemsAsExtensionsJson() throws Exception {
    Gedcomx set = new Gedcomx();
    UniqueCustomKeyedItem item1 = new UniqueCustomKeyedItem();
    item1.setVal1("1");
    item1.setVal2("2");
    String key1 = item1.getKey();
    set.addExtensionElement(item1);
    UniqueCustomKeyedItem item2 = new UniqueCustomKeyedItem();
    item2.setVal1("one");
    item2.setVal2("two");
    String key2 = item2.getKey();
    set.addExtensionElement(item2);

    CustomKeyedItem item3 = new CustomKeyedItem();
    item3.setVal1("1");
    item3.setVal2("2");
    String key3 = item3.getKey();
    set.addExtensionElement(item3);

    CustomKeyedItem item4 = new CustomKeyedItem();
    item4.setVal1("one");
    item4.setVal2("two");
    item4.setKey(key3);
    set.addExtensionElement(item4);

    GedcomNamespaceManager.registerKnownJsonType(CustomKeyedItem.class);
    GedcomNamespaceManager.registerKnownJsonType(UniqueCustomKeyedItem.class);
    set = processThroughJson(set, Gedcomx.class,
      org.gedcomx.rt.json.jackson3.GedcomJackson3Module.createObjectMapper(Gedcomx.class));

    List<UniqueCustomKeyedItem> keyedItems = set.findExtensionsOfType(UniqueCustomKeyedItem.class);
    assertEquals(2, keyedItems.size());
    for (UniqueCustomKeyedItem keyedItem : keyedItems) {
      if ("1".equals(keyedItem.getVal1())) {
        assertEquals("2", keyedItem.getVal2());
        assertEquals(key1, keyedItem.getKey());
      }
      else if ("one".equals(keyedItem.getVal1())) {
        assertEquals("two", keyedItem.getVal2());
        assertEquals(key2, keyedItem.getKey());
      }
      else {
        fail("Unknown keyed item.");
      }
    }
    List<CustomKeyedItem> keyedItems2 = set.findExtensionsOfType(CustomKeyedItem.class);
    assertEquals(2, keyedItems2.size());
    for (CustomKeyedItem keyedItem : keyedItems2) {
      if ("1".equals(keyedItem.getVal1())) {
        assertEquals("2", keyedItem.getVal2());
        assertEquals(key3, keyedItem.getKey());
      }
      else if ("one".equals(keyedItem.getVal1())) {
        assertEquals("two", keyedItem.getVal2());
        assertEquals(key3, keyedItem.getKey());
      }
      else {
        fail("Unknown keyed item.");
      }
    }
  }

}
