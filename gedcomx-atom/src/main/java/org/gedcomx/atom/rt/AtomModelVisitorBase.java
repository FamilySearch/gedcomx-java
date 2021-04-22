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
package org.gedcomx.atom.rt;

import org.gedcomx.Gedcomx;
import org.gedcomx.atom.Content;
import org.gedcomx.atom.Entry;
import org.gedcomx.atom.Feed;
import org.gedcomx.atom.Person;
import org.gedcomx.records.Field;
import org.gedcomx.rt.GedcomxModelVisitorBase;

import javax.xml.bind.annotation.XmlTransient;
import java.util.List;

/**
 * @author Ryan Heaton
 */
@XmlTransient
public class AtomModelVisitorBase extends GedcomxModelVisitorBase implements AtomModelVisitor {

  @Override
  public void visitFeed(Feed feed) {
    this.contextStack.push(feed);
    List<Entry> entries = feed.getEntries();
    if (entries != null) {
      for (Entry entry : entries) {
        if (entry != null) {
          entry.accept(this);
        }
      }
    }

    List<Person> authors = feed.getAuthors();
    if (authors != null) {
      for (Person author : authors) {
        if (author != null) {
          author.accept(this);
        }
      }
    }

    List<Person> contributors = feed.getContributors();
    if (contributors != null) {
      for (Person contributor : contributors) {
        if (contributor != null) {
          contributor.accept(this);
        }
      }
    }

    this.contextStack.pop();
  }

  @Override
  public void visitAtomPerson(Person author) {
    //no-op...
  }

  @Override
  public void visitEntry(Entry entry) {
    this.contextStack.push(entry);
    Content content = entry.getContent();
    if (content != null) {
      content.accept(this);
    }

    List<Person> authors = entry.getAuthors();
    if (authors != null) {
      for (Person author : authors) {
        if (author != null) {
          author.accept(this);
        }
      }
    }

    List<Person> contributors = entry.getContributors();
    if (contributors != null) {
      for (Person contributor : contributors) {
        if (contributor != null) {
          contributor.accept(this);
        }
      }
    }
    this.contextStack.pop();
  }

  @Override
  public void visitAtomContent(Content content) {
    this.contextStack.push(content);
    Gedcomx gedcomx = content.getGedcomx();
    if (gedcomx != null) {
      gedcomx.accept(this);
    }
    this.contextStack.pop();
  }
}
