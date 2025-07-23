package org.familysearch.platform.ct;

import org.gedcomx.common.Attribution;
import org.gedcomx.common.ResourceReference;
import org.gedcomx.common.URI;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Unit tests for the TreePersonReference class.
 */
public class TreePersonReferenceTest {

  @Test
  public void testNoArgumentConstructor() {
    TreePersonReference ref = new TreePersonReference();

    assertNull(ref.getTreePerson());
    assertNull(ref.getTree());
    assertNull(ref.getAttribution());
  }

  @Test
  public void testNoArgumentConstructorWithSetters() {
    ResourceReference treePerson = new ResourceReference(URI.create("http://example.com/person/123"));
    ResourceReference tree = new ResourceReference(URI.create("http://example.com/tree/456"));
    Attribution attribution = new Attribution();
    attribution.setContributor(new ResourceReference(URI.create("http://example.com/contributor/789")));

    TreePersonReference ref = new TreePersonReference();

    // Verify initial state
    assertNull(ref.getTreePerson());
    assertNull(ref.getTree());
    assertNull(ref.getAttribution());

    // Set values using setters
    ref.setTreePerson(treePerson);
    ref.setTree(tree);
    ref.setAttribution(attribution);

    // Verify values are set correctly
    assertEquals(treePerson, ref.getTreePerson());
    assertEquals(tree, ref.getTree());
    assertEquals(attribution, ref.getAttribution());
  }

  @Test
  public void testNoArgumentConstructorWithFluentMethods() {
    ResourceReference treePerson = new ResourceReference(URI.create("http://example.com/person/123"));
    ResourceReference tree = new ResourceReference(URI.create("http://example.com/tree/456"));
    Attribution attribution = new Attribution();
    attribution.setContributor(new ResourceReference(URI.create("http://example.com/contributor/789")));

    TreePersonReference ref = new TreePersonReference()
        .treePerson(treePerson)
        .tree(tree)
        .attribution(attribution);

    assertEquals(treePerson, ref.getTreePerson());
    assertEquals(tree, ref.getTree());
    assertEquals(attribution, ref.getAttribution());
  }

  @Test
  public void testGettersAndSetters() {
    ResourceReference treePerson = new ResourceReference(URI.create("http://example.com/person/123"));
    ResourceReference tree = new ResourceReference(URI.create("http://example.com/tree/456"));
    Attribution attribution = new Attribution();
    attribution.setContributor(new ResourceReference(URI.create("http://example.com/contributor/789")));

    TreePersonReference ref = new TreePersonReference().treePerson(treePerson);

    // Test initial state
    assertEquals(treePerson, ref.getTreePerson());
    assertNull(ref.getTree());
    assertNull(ref.getAttribution());

    // Test setters
    ref.setTree(tree);
    ref.setAttribution(attribution);

    assertEquals(treePerson, ref.getTreePerson());
    assertEquals(tree, ref.getTree());
    assertEquals(attribution, ref.getAttribution());

    // Test setter for treePerson
    ResourceReference newTreePerson = new ResourceReference(URI.create("http://example.com/person/999"));
    ref.setTreePerson(newTreePerson);
    assertEquals(newTreePerson, ref.getTreePerson());
  }

  @Test
  public void testFluentTreePersonMethodWithNullReference() {
    ResourceReference treePerson = new ResourceReference(URI.create("http://example.com/person/123"));
    TreePersonReference ref = new TreePersonReference().treePerson(treePerson);

    TreePersonReference result = ref.treePerson(null);

    // Should return the same instance but not change the treePerson
    assertSame(ref, result);
    assertEquals(treePerson, ref.getTreePerson());
  }

  @Test
  public void testFluentTreePersonMethodWithEmptyReference() {
    ResourceReference treePerson = new ResourceReference(URI.create("http://example.com/person/123"));
    ResourceReference emptyRef = new ResourceReference();

    TreePersonReference ref = new TreePersonReference().treePerson(treePerson);
    TreePersonReference result = ref.treePerson(emptyRef);

    // Should return the same instance but not change the treePerson since emptyRef has no resourceId or resource
    assertSame(ref, result);
    assertEquals(treePerson, ref.getTreePerson());
  }

  @Test
  public void testFluentTreeMethodWithNullReference() {
    ResourceReference treePerson = new ResourceReference(URI.create("http://example.com/person/123"));
    TreePersonReference ref = new TreePersonReference().treePerson(treePerson);

    TreePersonReference result = ref.tree(null);

    // Should return the same instance but not change the tree
    assertSame(ref, result);
    assertNull(ref.getTree());
  }

  @Test
  public void testFluentTreeMethodWithEmptyReference() {
    ResourceReference treePerson = new ResourceReference(URI.create("http://example.com/person/123"));
    ResourceReference emptyRef = new ResourceReference();

    TreePersonReference ref = new TreePersonReference().treePerson(treePerson);
    TreePersonReference result = ref.tree(emptyRef);

    // Should return the same instance but not change the tree since emptyRef has no resourceId or resource
    assertSame(ref, result);
    assertNull(ref.getTree());
  }

  @Test
  public void testFluentAttributionMethod() {
    ResourceReference treePerson = new ResourceReference(URI.create("http://example.com/person/123"));
    Attribution attribution = new Attribution();
    attribution.setContributor(new ResourceReference(URI.create("http://example.com/contributor/789")));

    TreePersonReference ref = new TreePersonReference().treePerson(treePerson);
    TreePersonReference result = ref.attribution(attribution);

    // Should return the same instance for method chaining
    assertSame(ref, result);
    assertEquals(attribution, ref.getAttribution());
  }

  @Test
  public void testFluentAttributionMethodWithNull() {
    ResourceReference treePerson = new ResourceReference(URI.create("http://example.com/person/123"));
    TreePersonReference ref = new TreePersonReference().treePerson(treePerson);

    TreePersonReference result = ref.attribution(null);

    // Should return the same instance and set attribution to null
    assertSame(ref, result);
    assertNull(ref.getAttribution());
  }

  @Test
  public void testFluentMethodsWithResourceId() {
    ResourceReference treePersonWithId = new ResourceReference();
    treePersonWithId.setResourceId("person123");

    ResourceReference treeWithId = new ResourceReference();
    treeWithId.setResourceId("tree456");

    TreePersonReference ref = new TreePersonReference().treePerson(new ResourceReference(URI.create("http://example.com/person/original")));

    ref.treePerson(treePersonWithId);
    ref.tree(treeWithId);

    assertEquals(treePersonWithId, ref.getTreePerson());
    assertEquals(treeWithId, ref.getTree());
  }

  @Test
  public void testFluentMethodsWithResource() {
    ResourceReference treePersonWithResource = new ResourceReference();
    treePersonWithResource.setResource(URI.create("http://example.com/person/123"));

    ResourceReference treeWithResource = new ResourceReference();
    treeWithResource.setResource(URI.create("http://example.com/tree/456"));

    TreePersonReference ref = new TreePersonReference().treePerson(new ResourceReference(URI.create("http://example.com/person/original")));

    ref.treePerson(treePersonWithResource);
    ref.tree(treeWithResource);

    assertEquals(treePersonWithResource, ref.getTreePerson());
    assertEquals(treeWithResource, ref.getTree());
  }
}
