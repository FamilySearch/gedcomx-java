/**
 * Copyright 2011-2012 Intellectual Reserve, Inc.
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

import com.sun.mirror.apt.Messager;
import com.sun.mirror.declaration.TypeDeclaration;
import freemarker.template.TemplateException;
import net.sf.jelly.apt.Context;
import org.codehaus.enunciate.EnunciateException;
import org.codehaus.enunciate.apt.EnunciateFreemarkerModel;
import org.codehaus.enunciate.apt.EnunciateTypeDeclarationListener;
import org.codehaus.enunciate.contract.validation.ValidationMessage;
import org.codehaus.enunciate.contract.validation.ValidationResult;
import org.codehaus.enunciate.main.Enunciate;
import org.codehaus.enunciate.main.FileArtifact;
import org.codehaus.enunciate.modules.FreemarkerDeploymentModule;
import org.gedcomx.build.enunciate.GedcomxDeploymentModule;
import org.gedcomx.rt.rs.ResourceDefinition;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * The GEDCOM X RS deployment module handles the generation of the GEDCOM X RS documentation.
 *
 * @author Ryan Heaton
 */
public class GedcomxRSDeploymentModule extends FreemarkerDeploymentModule implements EnunciateTypeDeclarationListener {

  private final Map<String, TypeDeclaration> knownRsdDeclarations = new HashMap<String, TypeDeclaration>();
  private ResourceServiceProcessor resourceServiceProcessor;

  /**
   * @return "gedcomx-rs"
   */
  @Override
  public String getName() {
    return "gedcomx-rs";
  }

  /**
   * @return 100
   */
  @Override
  public int getOrder() {
    return 101;
  }

  public void onTypeDeclarationInspected(TypeDeclaration typeDeclaration) {
    if (typeDeclaration.getAnnotation(ResourceDefinition.class) != null) {
      this.knownRsdDeclarations.put(typeDeclaration.getQualifiedName(), typeDeclaration);
    }
  }

  protected URL getDocsTemplateURL() {
    return GedcomxDeploymentModule.class.getResource("rsdocs.fmt");
  }

  @Override
  public void init(Enunciate enunciate) throws EnunciateException {
    super.init(enunciate);

    this.resourceServiceProcessor = new ResourceServiceProcessor();
  }

  public ResourceServiceProcessor getResourceServiceProcessor() {
    return resourceServiceProcessor;
  }

  @Override
  public void initModel(EnunciateFreemarkerModel model) {
    super.initModel(model);

    ValidationResult validationResult = this.resourceServiceProcessor.processModel(model, this.knownRsdDeclarations.values());
    Messager messager = Context.getCurrentEnvironment().getMessager();
    if (validationResult.hasWarnings()) {
      warn("Warnings while processing RS.");
      for (ValidationMessage warning : validationResult.getWarnings()) {
        StringBuilder text = new StringBuilder();
        if (warning.getLabel() != null) {
          text.append('[').append(warning.getLabel()).append("] ");
        }
        text.append(warning.getText());

        if (warning.getPosition() != null) {
          messager.printWarning(warning.getPosition(), text.toString());
        }
        else {
          messager.printWarning(text.toString());
        }
      }
    }

    if (validationResult.hasErrors()) {
      for (ValidationMessage error : validationResult.getErrors()) {
        StringBuilder text = new StringBuilder();
        if (error.getLabel() != null) {
          text.append('[').append(error.getLabel()).append("] ");
        }
        text.append(error.getText());

        if (error.getPosition() != null) {
          messager.printError(error.getPosition(), text.toString());
        }
        else {
          messager.printError(text.toString());
        }
      }

      throw new RuntimeException("Errors while processing RDF and resource services.");
    }
  }

  public void doFreemarkerGenerate() throws EnunciateException, IOException, TemplateException {
    //no-op...
  }

  @Override
  protected void doBuild() throws EnunciateException, IOException {
    if (!getEnunciate().isUpToDateWithSources(getBuildDir())) {
      EnunciateFreemarkerModel model = getModel();
      model.setFileOutputDirectory(getBuildDir());
      Collection<ResourceDefinitionDeclaration> resourceDefinitions = this.resourceServiceProcessor.getResourceDefinitions();
      Map<String, Collection<ResourceDefinitionDeclaration>> resourceDefinitionsByNamespace = new HashMap<String, Collection<ResourceDefinitionDeclaration>>();
      for (ResourceDefinitionDeclaration definition : resourceDefinitions) {
        model.addNamespace(definition.getNamespace());
        Collection<ResourceDefinitionDeclaration> listByNamespace = resourceDefinitionsByNamespace.get(definition.getNamespace());
        if (listByNamespace == null) {
          listByNamespace = new ArrayList<ResourceDefinitionDeclaration>();
          resourceDefinitionsByNamespace.put(definition.getNamespace(), listByNamespace);
        }
        listByNamespace.add(definition);
      }
      model.put("resourceDefinitions", resourceDefinitions);
      model.put("resourceDefinitionsByNamespace", resourceDefinitionsByNamespace);

      Map<String, ResourceBinding> bindingsByPath = this.resourceServiceProcessor.getBindingsByPath();
      Map<String, Collection<ResourceBinding>> bindingsByNamespace = new HashMap<String, Collection<ResourceBinding>>();
      for (ResourceBinding binding : bindingsByPath.values()) {
        model.addNamespace(binding.getNamespace());
        Collection<ResourceBinding> list = bindingsByNamespace.get(binding.getNamespace());
        if (list == null) {
          list = new ArrayList<ResourceBinding>();
          bindingsByNamespace.put(binding.getNamespace(), list);
        }
        list.add(binding);
      }
      model.put("resourceBindingsByPath", bindingsByPath);
      model.put("resourceBindingsByNamespace", bindingsByNamespace);

      model.put("findTargetResource", new FindTargetResourceMethod(this.resourceServiceProcessor));

      try {
        processTemplate(getDocsTemplateURL(), model);
      }
      catch (TemplateException e) {
        throw new EnunciateException(e);
      }
    }
    else {
      info("Skipping build of documentation as everything appears up-to-date...");
    }

    //export the generated documentation as an artifact.
    getEnunciate().addArtifact(new FileArtifact(getName(), "gedcomx-rs-docs", getBuildDir()));
  }

}
