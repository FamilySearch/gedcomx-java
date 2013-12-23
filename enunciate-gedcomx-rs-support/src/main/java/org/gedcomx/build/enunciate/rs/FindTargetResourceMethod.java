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

import freemarker.ext.beans.BeansWrapper;
import freemarker.template.TemplateMethodModelEx;
import freemarker.template.TemplateModel;
import freemarker.template.TemplateModelException;

import java.util.Collection;
import java.util.List;

/**
 * Gets the RootElement for the supplied TypeDefinition
 *
 * @author Ryan Heaton
 */
public class FindTargetResourceMethod implements TemplateMethodModelEx {

  private final ResourceServiceProcessor processor;

  public FindTargetResourceMethod(ResourceServiceProcessor processor) {
    this.processor = processor;
  }

  /**
   * Gets the client-side package for the type, type declaration, package, or their string values.
   *
   * @param list The arguments.
   * @return The string value of the client-side package.
   */
  public Object exec(List list) throws TemplateModelException {
    if (list.size() < 1) {
      throw new TemplateModelException("The findTargetResourceMethod method must have a state transition as a parameter.");
    }

    TemplateModel from = (TemplateModel) list.get(0);
    Object unwrapped = BeansWrapper.getDefaultInstance().unwrap(from);
    if (!(unwrapped instanceof StateTransition)) {
      throw new TemplateModelException("A state transition must be provided.");
    }

    StateTransition link = (StateTransition) unwrapped;
    if (link.getTargetResourceQualifiedName() != null) {
      Collection<ResourceDefinitionDeclaration> defs = this.processor.getResourceDefinitions();
      for (ResourceDefinitionDeclaration def : defs) {
        if (link.getTargetResourceQualifiedName().equals(def.getQualifiedName())) {
          return def;
        }
      }
    }

    return null;
  }

}