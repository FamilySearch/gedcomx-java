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
package org.gedcomx.build.enunciate.rs;

import com.sun.mirror.type.MirroredTypeException;
import com.sun.mirror.type.MirroredTypesException;
import net.sf.jelly.apt.freemarker.FreemarkerModel;
import org.codehaus.enunciate.apt.EnunciateFreemarkerModel;
import org.codehaus.enunciate.contract.jaxb.TypeDefinition;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 * @author Ryan Heaton
 */
public final class StateTransition implements Comparable<StateTransition> {

  final String rel;
  final String description;
  final Set<String> scope;
  final String targetResourceQualifiedName;
  final String targetHref;
  final ResourceServiceProcessor processor;
  final boolean template;
  final boolean conditional;

  public StateTransition(org.gedcomx.rt.rs.StateTransition meta, ResourceServiceProcessor processor) {
    this.processor = processor;
    this.rel = meta.rel();
    this.description = meta.description();
    this.template = meta.template();
    this.conditional = meta.conditional();
    String targetHref = meta.targetHref();
    this.targetHref = "##default".equals(targetHref) ? null : targetHref;

    String targetResourceName;
    try {
      targetResourceName = meta.targetResource().getName();
    }
    catch (MirroredTypeException e) {
      targetResourceName = e.getQualifiedName();
    }

    if ("org.gedcomx.rt.rs.StateTransition.DEFAULT".equals(targetResourceName)) {
      targetResourceName = null;
    }

    this.targetResourceQualifiedName = targetResourceName;

    this.scope = new TreeSet<String>();
    try {
      Class<?>[] classes = meta.scope();
      for (Class<?> clazz : classes) {
        scope.add(clazz.getName());
      }
    }
    catch (MirroredTypesException e) {
      scope.addAll(e.getQualifiedNames());
    }
  }

  public String getRel() {
    return rel;
  }

  public String getTargetResourceQualifiedName() {
    return targetResourceQualifiedName;
  }

  public String getTargetHref() {
    return targetHref;
  }

  public String getDescription() {
    return description;
  }

  public boolean isTemplate() {
    return template;
  }

  public boolean isConditional() {
    return conditional;
  }

  public List<TypeDefinition> getScope() {
    EnunciateFreemarkerModel model = (EnunciateFreemarkerModel) FreemarkerModel.get();

    ArrayList<TypeDefinition> typeDefs = new ArrayList<TypeDefinition>();
    for (TypeDefinition typeDef : model.getTypeDefinitions()) {
      if (this.scope.contains(typeDef.getQualifiedName())) {
        typeDefs.add(typeDef);
      }
    }
    return typeDefs;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    StateTransition that = (StateTransition) o;

    if (rel != null ? !rel.equals(that.rel) : that.rel != null) {
      return false;
    }

    if (targetHref != null ? !targetHref.equals(that.targetHref) : that.targetHref != null) {
      return false;
    }

    if (targetResourceQualifiedName != null ? !targetResourceQualifiedName.equals(that.targetResourceQualifiedName) : that.targetResourceQualifiedName != null) {
      return false;
    }

    if (scope != null ? !scope.equals(that.scope) : that.scope != null) {
      return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    int result = rel != null ? rel.hashCode() : 0;
    result = 31 * result + (scope != null ? scope.hashCode() : 0);
    result = 31 * result + (targetHref != null ? targetHref.hashCode() : 0);
    result = 31 * result + (targetResourceQualifiedName != null ? targetResourceQualifiedName.hashCode() : 0);
    return result;
  }

  private String buildComparisonString() {
    StringBuilder base = new StringBuilder(this.rel);
    for (String scope : this.scope) {
      base.append(scope);
    }
    if (this.targetHref != null) {
      base.append(this.targetHref);
    }
    if (this.targetResourceQualifiedName != null) {
      base.append(this.targetResourceQualifiedName);
    }
    return base.toString();
  }

  @Override
  public int compareTo(StateTransition o) {
    return buildComparisonString().compareTo(o.buildComparisonString());
  }
}
