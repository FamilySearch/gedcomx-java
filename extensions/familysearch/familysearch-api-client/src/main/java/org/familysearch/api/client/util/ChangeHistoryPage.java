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
package org.familysearch.api.client.util;

import org.familysearch.platform.ct.ChangeInfo;
import org.familysearch.platform.ct.ChangeObjectModifier;
import org.familysearch.platform.ct.ChangeObjectType;
import org.familysearch.platform.ct.ChangeOperation;
import org.familysearch.platform.rt.FamilySearchPlatformLocalReferenceResolver;
import org.gedcomx.atom.Entry;
import org.gedcomx.atom.Feed;
import org.gedcomx.common.ExtensibleData;
import org.gedcomx.common.ResourceReference;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;

/**
 * @author Ryan Heaton
 */
public class ChangeHistoryPage extends Entry {

  private final Feed feed;
  private final List<ChangeEntry> entries;

  public ChangeHistoryPage(Feed feed) {
    this.feed = feed;

    List<Entry> entries = feed.getEntries();
    ArrayList<ChangeEntry> changes = new ArrayList<ChangeEntry>();
    if (entries != null) {
      for (Entry entry : entries) {
        changes.add(new ChangeEntry(entry));
      }
    }

    this.entries = changes;
  }

  public Feed getFeed() {
    return feed;
  }

  public List<ChangeEntry> getEntries() {
    return entries;
  }

  public ChangeEntry findChange(ChangeOperation operation, ChangeObjectType objectType) {
    List<ChangeEntry> changes = findChanges(operation, objectType);
    return changes.size() > 0 ? changes.get(0) : null;
  }

  public List<ChangeEntry> findChanges(ChangeOperation operation, ChangeObjectType objectType) {
    return findChanges(EnumSet.of(operation), EnumSet.of(objectType));
  }

  public List<ChangeEntry> findChanges(Set<ChangeOperation> operations, Set<ChangeObjectType> types) {
    ArrayList<ChangeEntry> changes = new ArrayList<ChangeEntry>();
    for (ChangeEntry entry : this.entries) {
      ChangeOperation operation = entry.getOperation();
      ChangeObjectType type = entry.getObjectType();
      if (operation != null && type != null & operations.contains(operation) && types.contains(type)) {
        changes.add(entry);
      }
    }
    return changes;
  }
}
