/*
 * Copyright © 2020 Intellectual Reserve, Inc. All Rights reserved.
 */

package org.gedcomx.conclusion;

import java.util.List;
import java.util.stream.Collectors;

import org.assertj.core.util.Lists;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;


/**
 * @author LandonVG
 */
public class HasFactsTest {

  private final class TestHasFacts implements HasFacts {
    List<Fact> facts;

    @Override
    public void setFacts(List<Fact> facts) {
      this.facts = facts;
    }

    @Override
    public List<Fact> getFacts() {
      return facts;
    }
  }

  private final HasFacts test = new TestHasFacts();

  @Test
  public void facts() {
    final List<Fact> facts = Lists.newArrayList(new Fact());
    test.setFacts(facts);

    assertThat(test.facts().collect(Collectors.toList())).isEqualTo(facts);
  }

  @Test
  public void facts_Empty() {
    assertThat(test.facts()).isEmpty();
  }
}
