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
import org.gedcomx.common.ExtensibleData;
import org.gedcomx.common.ResourceReference;

/**
 * @author Ryan Heaton
 */
public class ChangeEntry extends Entry {

  private final Entry entry;
  private final ChangeInfo changeInfo;

  public ChangeEntry(Entry entry) {
    this.entry = entry;
    this.changeInfo = this.entry.findExtensionOfType(ChangeInfo.class);
  }

  public ChangeInfo getChangeInfo() {
    return this.changeInfo;
  }

  public Entry getEntry() {
    return entry;
  }

  public ChangeOperation getOperation() {
    return changeInfo == null ? null : changeInfo.getKnownOperation();
  }

  public ChangeObjectType getObjectType() {
    return changeInfo == null ? null : changeInfo.getKnownObjectType();
  }

  public ChangeObjectModifier getObjectModifier() {
    return changeInfo == null ? null : changeInfo.getKnownObjectModifier();
  }

  public String getReason() {
    return changeInfo == null ? null : changeInfo.getReason();
  }

  public ExtensibleData getOriginalValue() {
    ChangeInfo changeInfo = getChangeInfo();
    if (changeInfo != null && getEntry().getContent() != null && getEntry().getContent().getGedcomx() != null) {
      ResourceReference original = changeInfo.getOriginal();
      if (original != null) {
        return FamilySearchPlatformLocalReferenceResolver.resolve(original, getEntry().getContent().getGedcomx());
      }
    }
    
    return null;
  }

  public ExtensibleData getResultingValue() {
    ChangeInfo changeInfo = getChangeInfo();
    if (changeInfo != null && getEntry().getContent() != null && getEntry().getContent().getGedcomx() != null) {
      ResourceReference resulting = changeInfo.getResulting();
      if (resulting != null) {
        return FamilySearchPlatformLocalReferenceResolver.resolve(resulting, getEntry().getContent().getGedcomx());
      }
    }
    
    return null;
  }

  public ExtensibleData getRemovedValue() {
    ChangeInfo changeInfo = getChangeInfo();
    if (changeInfo != null && getEntry().getContent() != null && getEntry().getContent().getGedcomx() != null) {
      ResourceReference removed = changeInfo.getRemoved();
      if (removed != null) {
        return FamilySearchPlatformLocalReferenceResolver.resolve(removed, getEntry().getContent().getGedcomx());
      }
    }
    
    return null;
  }


}
