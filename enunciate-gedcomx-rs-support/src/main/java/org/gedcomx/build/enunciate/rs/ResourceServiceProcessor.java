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

import com.sun.mirror.apt.AnnotationProcessorEnvironment;
import com.sun.mirror.declaration.*;
import com.sun.mirror.type.InterfaceType;
import com.sun.mirror.type.MirroredTypesException;
import com.sun.mirror.util.Declarations;
import net.sf.jelly.apt.Context;
import net.sf.jelly.apt.decorations.declaration.DecoratedDeclaration;
import net.sf.jelly.apt.decorations.declaration.DecoratedMethodDeclaration;
import org.codehaus.enunciate.apt.EnunciateFreemarkerModel;
import org.codehaus.enunciate.config.SchemaInfo;
import org.codehaus.enunciate.contract.jaxb.ElementDeclaration;
import org.codehaus.enunciate.contract.jaxrs.Resource;
import org.codehaus.enunciate.contract.jaxrs.ResourceMethod;
import org.codehaus.enunciate.contract.jaxrs.RootResource;
import org.codehaus.enunciate.contract.jaxrs.SubResource;
import org.codehaus.enunciate.contract.validation.ValidationResult;
import org.codehaus.enunciate.util.ResourceMethodPathComparator;
import org.gedcomx.build.enunciate.MediaTypeDeclaration;
import org.gedcomx.rt.rs.ResourceDefinition;
import org.gedcomx.rt.rs.StateTransitions;
import org.gedcomx.rt.rs.StatusCodes;
import org.gedcomx.rt.rs.Warnings;

import javax.ws.rs.Path;
import java.util.*;

/**
 * Basic processor for validating integration the model with RDF.
 *
 * @author Ryan Heaton
 */
public class ResourceServiceProcessor {

  private final List<ResourceDefinitionDeclaration> resourceDefinitions = new ArrayList<ResourceDefinitionDeclaration>();
  private Map<String, ResourceBinding> bindingsByPath;

  public Collection<ResourceDefinitionDeclaration> getResourceDefinitions() {
    return resourceDefinitions;
  }

  public Map<String, ResourceBinding> getBindingsByPath() {
    return bindingsByPath;
  }

  public ValidationResult processModel(EnunciateFreemarkerModel model, Collection<TypeDeclaration> resourceServiceDefinitions) {
    ValidationResult result = new ValidationResult();

    //todo: error if setter-based resource parameters in the implementation don't carry the annotations defined in the definition, binding...
    for (TypeDeclaration resourceServiceDefinition : resourceServiceDefinitions) {
      ResourceDefinition rsdInfo = resourceServiceDefinition.getAnnotation(ResourceDefinition.class);
      if (rsdInfo != null) {
        List<String> resourceElementsFqn = new ArrayList<String>();

        try {
          for (Class<?> clazz : rsdInfo.resourceElement()) {
            resourceElementsFqn.add(clazz.getName());
          }
        }
        catch (MirroredTypesException e) {
          resourceElementsFqn.addAll(e.getQualifiedNames());
        }

        AnnotationProcessorEnvironment ape = Context.getCurrentEnvironment();
        ArrayList<ElementDeclaration> resourceElements = new ArrayList<ElementDeclaration>();
        for (String resourceElementFqn : resourceElementsFqn) {
          TypeDeclaration declaration = ape.getTypeDeclaration(resourceElementFqn);
          ElementDeclaration resourceElement = null;
          if (declaration instanceof ClassDeclaration) {
            resourceElement = model.findElementDeclaration((ClassDeclaration) declaration);
          }

          if (resourceElement == null) {
            result.addWarning(resourceServiceDefinition, "Unable to find element declaration for " + resourceElementFqn + ".");
          }
          else {
            resourceElements.add(resourceElement);
          }
        }

        ResourceDefinitionDeclaration rsd = new ResourceDefinitionDeclaration(resourceServiceDefinition, resourceElements, this);
        Map<String, ResourceMethod> methodsByOp = new HashMap<String, ResourceMethod>();
        for (ResourceMethod method : rsd.getResourceMethods()) {
          if (method.getHttpMethods().size() > 1) {
            result.addError(method, "Resource definition methods may only apply to one HTTP operation.");
          }
          else {
            String op = method.getHttpMethods().iterator().next();
            ResourceMethod conflict = methodsByOp.put(op, method);
            if (conflict != null) {
              result.addError(method, String.format("HTTP operation %s is already defined by method %s.", op, conflict.getSimpleName()));
            }
          }
        }

        this.resourceDefinitions.add(rsd);
        model.addNamespace(rsd.getNamespace());
      }
      else {
        result.addWarning(resourceServiceDefinition, String.format("No @%s annotation found.", ResourceDefinition.class.getName()));
      }
    }

    TreeMap<String, ResourceBinding> bindingsByPath = new TreeMap<String, ResourceBinding>(new ResourceMethodPathComparator());
    for (RootResource rootResource : model.getRootResources()) {
      List<ResourceMethod> resourceMethods = rootResource.getResourceMethods(true);
      for (ResourceMethod resourceMethod : resourceMethods) {
        resourceMethod.putMetaData("statusCodes", extractStatusCodes(resourceMethod.getParent(), resourceMethod));
        resourceMethod.putMetaData("warnings", extractWarnings(resourceMethod.getParent(), resourceMethod));

        Resource declaringResource = resourceMethod.getParent();
        ResourceDefinitionDeclaration rsd = findDefiningResourceDefinition(resourceMethod);
        if (rsd != null) {
          resourceMethod.putMetaData("definedBy", rsd);
          String path = resourceMethod.getFullpath();
          ResourceBinding binding = bindingsByPath.get(path);
          if (binding == null) {
            Declaration bindingDeclaration = declaringResource instanceof SubResource ? ((SubResource) declaringResource).getLocator() : declaringResource;
            org.gedcomx.rt.rs.ResourceBinding metadata = declaringResource instanceof SubResource ? ((SubResource) declaringResource).getLocator().getAnnotation(org.gedcomx.rt.rs.ResourceBinding.class) : null;
            if (metadata == null) {
              metadata = declaringResource.getAnnotation(org.gedcomx.rt.rs.ResourceBinding.class);
            }

            binding = new ResourceBinding(bindingDeclaration, path, metadata);
            bindingsByPath.put(path, binding);
            rsd.getBindings().add(binding);
          }

          binding.getDefinitions().add(rsd);
          binding.getMethods().add(resourceMethod);
          resourceMethod.putMetaData("boundBy", binding);

          if (declaringResource.getAnnotation(ResourceDefinition.class) == null) {
            //as long as the declaring resource class isn't a resource definition itself,
            //we'll consider it's metadata as binding refinements.

            binding.getStatusCodes().addAll(extractStatusCodes(declaringResource));
            binding.getWarnings().addAll(extractWarnings(declaringResource));
            binding.getLinks().addAll(extractLinks(declaringResource));
            binding.getResourceParameters().addAll(declaringResource.getResourceParameters());
          }

          model.addNamespace(binding.getNamespace());
        }
      }
    }

    Map<String, ResourceBinding> bindingsByName = new TreeMap<String, ResourceBinding>();
    for (ResourceBinding binding : bindingsByPath.values()) {
      ResourceBinding duplicate = bindingsByName.put(binding.getName(), binding);
      if (duplicate != null) {
        result.addError(binding, String.format("Resource binding name %s of %s conflicts %s.", binding.getName(), binding.getSimpleName(), duplicate.getSimpleName()));
      }

      Set<ResourceDefinitionDeclaration> definitions = binding.getDefinitions();
      ResourceDefinitionDeclaration primary = null;
      for (ResourceDefinitionDeclaration definition : definitions) {
        if (!definition.isEmbedded()) {
          if (primary != null) {
            result.addWarning(binding, String.format("Binding is implementing multiple primary (non-embedded) resources (%s and %s). Only one resource being bound may be embedded.", primary.getQualifiedName(), definition.getQualifiedName()));
          }
          primary = definition;
        }
        for (ElementDeclaration resourceElement : definition.getResourceElements()) {
          SchemaInfo schemaInfo = model.getNamespacesToSchemas().get(resourceElement.getNamespace());
          MediaTypeDeclaration declaration = (MediaTypeDeclaration) schemaInfo.getProperties().get("mediaType");
          if (declaration != null) {
            if (declaration.getXmlMediaType() != null) {
              if (!binding.getConsumes().isEmpty() && !binding.getConsumes().contains("*/*") && !binding.getConsumes().contains(declaration.getXmlMediaType())) {
                result.addWarning(binding, String.format("Binding doesn't consume %s, even though resource element %s is of that media type.", declaration.getXmlMediaType(), resourceElement.getQname()));
              }

              if (!binding.getProduces().contains(declaration.getXmlMediaType())) {
                result.addError(binding, String.format("Binding doesn't produce %s, even though resource element %s is of that media type.", declaration.getXmlMediaType(), resourceElement.getQname()));
              }
            }

            if (declaration.getJsonMediaType() != null) {
              if (!binding.getConsumes().isEmpty() && !binding.getConsumes().contains("*/*") && !binding.getConsumes().contains(declaration.getJsonMediaType())) {
                result.addWarning(binding, String.format("Binding doesn't consume %s, even though resource element %s is of that media type.", declaration.getJsonMediaType(), resourceElement.getQname()));
              }
              if (declaration.getJsonMediaType() != null && !binding.getProduces().contains(declaration.getJsonMediaType())) {
                result.addError(binding, String.format("Binding doesn't produce %s, even though resource element %s is of that media type.", declaration.getJsonMediaType(), resourceElement.getQname()));
              }
            }
          }
        }
      }
    }

    this.bindingsByPath = bindingsByPath;

    return result;
  }

  public Set<StateTransition> extractLinks(TypeDeclaration delegate) {
    Set<StateTransition> rels = new TreeSet<StateTransition>();

    org.gedcomx.rt.rs.StateTransition[] stateTransitionInfo = {};
    StateTransitions stateTransitions = delegate.getAnnotation(StateTransitions.class);
    if (stateTransitions != null) {
      stateTransitionInfo = stateTransitions.value();
    }
    for (org.gedcomx.rt.rs.StateTransition transition : stateTransitionInfo) {
      rels.add(new StateTransition(transition, this));
    }

    Collection<InterfaceType> supers = delegate.getSuperinterfaces();
    for (InterfaceType iface : supers) {
      InterfaceDeclaration decl = iface.getDeclaration();
      if (decl != null && decl.getAnnotation(ResourceDefinition.class) == null && decl.getAnnotation(Path.class) == null) {
        rels.addAll(extractLinks(decl));
      }
    }

    return rels;
  }

  public Set<ResponseCode> extractStatusCodes(TypeDeclaration type, final ResourceMethod method) {
    HashSet<ResponseCode> codes = new HashSet<ResponseCode>();

    if (type != null) {
      for (MethodDeclaration methodDeclaration : type.getMethods()) {
        if (refines(methodDeclaration, method)) {
          codes.addAll(extractStatusCodes(methodDeclaration));
        }
      }

      Collection<InterfaceType> superifaces = type.getSuperinterfaces();
      for (InterfaceType superiface : superifaces) {
        InterfaceDeclaration decl = superiface.getDeclaration();
        codes.addAll(extractStatusCodes(decl, method));
      }

      if (type instanceof ClassDeclaration) {
        codes.addAll(extractStatusCodes(((ClassDeclaration) type).getSuperclass().getDeclaration(), method));
      }
    }

    return codes;
  }

  public Set<ResponseCode> extractWarnings(TypeDeclaration type, final ResourceMethod method) {
    HashSet<ResponseCode> codes = new HashSet<ResponseCode>();

    if (type != null) {
      for (MethodDeclaration methodDeclaration : type.getMethods()) {
        if (refines(methodDeclaration, method)) {
          codes.addAll(extractWarnings(methodDeclaration));
        }
      }

      Collection<InterfaceType> superifaces = type.getSuperinterfaces();
      for (InterfaceType superiface : superifaces) {
        InterfaceDeclaration decl = superiface.getDeclaration();
        codes.addAll(extractWarnings(decl, method));
      }

      if (type instanceof ClassDeclaration) {
        codes.addAll(extractWarnings(((ClassDeclaration) type).getSuperclass().getDeclaration(), method));
      }
    }

    return codes;
  }

  private boolean refines(MethodDeclaration m1, MethodDeclaration m2) {
    while (m1 instanceof DecoratedDeclaration) {
      m1 = (MethodDeclaration) ((DecoratedDeclaration) m1).getDelegate();
    }

    while (m2 instanceof DecoratedDeclaration) {
      m2 = (MethodDeclaration) ((DecoratedDeclaration) m2).getDelegate();
    }

    if (m1.equals(m2)) {
      return true;
    }
    else {
      AnnotationProcessorEnvironment env = Context.getCurrentEnvironment();
      Declarations decls = env.getDeclarationUtils();
      if (decls.overrides(m1, m2)) {
        return true;
      }
      else if (decls.overrides(m2, m1)) {
        return true;
      }
    }

    return false;
  }

  public Set<ResponseCode> extractStatusCodes(Declaration delegate) {
    Set<ResponseCode> codes = new HashSet<ResponseCode>();
    if (delegate != null) {
      org.gedcomx.rt.rs.ResponseCode[] responseCodes = {};
      StatusCodes statusCodesContainer = delegate.getAnnotation(StatusCodes.class);
      if (statusCodesContainer != null) {
        responseCodes = statusCodesContainer.value();
      }
      for (org.gedcomx.rt.rs.ResponseCode responseCode : responseCodes) {
        codes.add(new ResponseCode(responseCode.code(), responseCode.condition()));
      }

      if (delegate instanceof TypeDeclaration) {
        Collection<InterfaceType> supers = ((TypeDeclaration) delegate).getSuperinterfaces();
        for (InterfaceType iface : supers) {
          InterfaceDeclaration decl = iface.getDeclaration();
          if (decl != null && decl.getAnnotation(ResourceDefinition.class) == null && decl.getAnnotation(Path.class) == null) {
            codes.addAll(extractStatusCodes(decl));
          }
        }
      }
    }
    return codes;
  }

  public Set<ResponseCode> extractWarnings(Declaration delegate) {
    Set<ResponseCode> codes = new HashSet<ResponseCode>();

    if (delegate != null) {
      org.gedcomx.rt.rs.ResponseCode[] responseCodes = {};
      Warnings warningsContainer = delegate.getAnnotation(Warnings.class);
      if (warningsContainer != null) {
        responseCodes = warningsContainer.value();
      }
      for (org.gedcomx.rt.rs.ResponseCode responseCode : responseCodes) {
        codes.add(new ResponseCode(responseCode.code(), responseCode.condition()));
      }

      if (delegate instanceof TypeDeclaration) {
        Collection<InterfaceType> supers = ((TypeDeclaration) delegate).getSuperinterfaces();
        for (InterfaceType iface : supers) {
          InterfaceDeclaration decl = iface.getDeclaration();
          if (decl != null && decl.getAnnotation(ResourceDefinition.class) == null && decl.getAnnotation(Path.class) == null) {
            codes.addAll(extractWarnings(decl));
          }
        }
      }
    }

    return codes;
  }

  private ResourceDefinitionDeclaration findResourceDefinition(String fqn) {
    for (ResourceDefinitionDeclaration rsd : this.resourceDefinitions) {
      if (rsd.getQualifiedName().equals(fqn)) {
        return rsd;
      }
    }

    return null;
  }

  public ResourceDefinitionDeclaration findDefiningResourceDefinition(ResourceMethod method) {
    TypeDeclaration declaringType = method.getDeclaringType();
    if (declaringType == null) {
      return null;
    }
    else if (declaringType.getAnnotation(ResourceDefinition.class) != null) {
      //the resource method is declared on a resource definition...
      return findResourceDefinition(declaringType.getQualifiedName());
    }
    else {
      List<TypeDeclaration> candidates = new ArrayList<TypeDeclaration>();
      gatherResourceDefinitions(candidates, declaringType);
      Declarations utils = Context.getCurrentEnvironment().getDeclarationUtils();
      for (TypeDeclaration candidate : candidates) {
        for (MethodDeclaration op : candidate.getMethods()) {
          while (op instanceof DecoratedMethodDeclaration) {
            op = (MethodDeclaration) ((DecoratedMethodDeclaration) op).getDelegate();
          }

          MethodDeclaration implOp = method;
          while (implOp instanceof DecoratedMethodDeclaration) {
            implOp = (MethodDeclaration) ((DecoratedMethodDeclaration) implOp).getDelegate();
          }

          if (utils.overrides(implOp, op)) {
            return findResourceDefinition(candidate.getQualifiedName());
          }
        }
      }
    }

    return null;
  }

  private void gatherResourceDefinitions(List<TypeDeclaration> bucket, TypeDeclaration declaration) {
    Collection<InterfaceType> supers = declaration.getSuperinterfaces();
    for (InterfaceType iface : supers) {
      InterfaceDeclaration idecl = iface.getDeclaration();
      if (idecl != null) {
        ResourceDefinition rsdInfo = idecl.getAnnotation(ResourceDefinition.class);
        if (rsdInfo != null) {
          bucket.add(idecl);
        }

        gatherResourceDefinitions(bucket, idecl);
      }
    }
  }
}
