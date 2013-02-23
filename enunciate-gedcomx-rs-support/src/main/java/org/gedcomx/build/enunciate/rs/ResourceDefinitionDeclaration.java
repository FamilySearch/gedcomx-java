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

import com.sun.mirror.declaration.InterfaceDeclaration;
import com.sun.mirror.declaration.PackageDeclaration;
import com.sun.mirror.declaration.TypeDeclaration;
import com.sun.mirror.type.InterfaceType;
import org.codehaus.enunciate.contract.jaxb.ElementDeclaration;
import org.codehaus.enunciate.contract.jaxrs.Resource;
import org.codehaus.enunciate.contract.jaxrs.ResourceMethod;
import org.codehaus.enunciate.contract.jaxrs.ResourceParameter;
import org.gedcomx.rt.rs.RSMetadata;
import org.gedcomx.rt.rs.ResourceDefinition;

import javax.ws.rs.Path;
import java.util.*;

/**
 * @author Ryan Heaton
 */
public class ResourceDefinitionDeclaration extends Resource {

  private final String name;
  private final String description;
  private final List<StateTransition> transitions;
  private final String namespace;
  private final String projectId;
  private final Set<ResponseCode> statusCodes;
  private final Set<ResponseCode> warnings;
  private final List<ElementDeclaration> resourceElements;
  private final List<ResourceBinding> bindings = new ArrayList<ResourceBinding>();
  private final boolean embedded;

  public ResourceDefinitionDeclaration(TypeDeclaration delegate, List<ElementDeclaration> resourceElements, ResourceServiceProcessor processor) {
    super(delegate);

    this.resourceElements = resourceElements;

    ResourceDefinition rsdInfo = delegate.getAnnotation(ResourceDefinition.class);
    this.name = rsdInfo.name();
    this.description = rsdInfo.description();

    String namespace = null;
    String projectId = null;
    PackageDeclaration pkg = delegate.getPackage();
    if (pkg != null) {
      RSMetadata rsm = pkg.getAnnotation(RSMetadata.class);
      if (rsm != null) {
        namespace = "##default".equals(rsm.namespace()) ? namespace : rsm.namespace();
        projectId = "##default".equals(rsm.projectId()) ? projectId : rsm.projectId();
      }
    }

    RSMetadata rsm = delegate.getAnnotation(RSMetadata.class);
    if (rsm != null) {
      namespace = "##default".equals(rsm.namespace()) ? namespace : rsm.namespace();
      projectId = "##default".equals(rsm.projectId()) ? projectId : rsm.projectId();
    }

    this.namespace = namespace;
    this.projectId = projectId;
    this.statusCodes = processor.extractStatusCodes(delegate);
    this.warnings = processor.extractWarnings(delegate);
    this.transitions = new ArrayList<StateTransition>();
    for (org.gedcomx.rt.rs.StateTransition stateTransition : rsdInfo.transitions()) {
      transitions.add(new StateTransition(stateTransition, processor));
    }
    for (ResourceMethod resourceMethod : getResourceMethods()) {
      resourceMethod.putMetaData("statusCodes", processor.extractStatusCodes(resourceMethod));
      resourceMethod.putMetaData("warnings", processor.extractWarnings(resourceMethod));
    }
    this.embedded = rsdInfo.embedded();
  }

  @Override
  protected List<ResourceParameter> getResourceParameters(TypeDeclaration delegate) {
    ArrayList<ResourceParameter> params = new ArrayList<ResourceParameter>(super.getResourceParameters(delegate));
    //in this case, we're going to consider the params on superinterfaces, too.
    Collection<InterfaceType> supers = delegate.getSuperinterfaces();
    for (InterfaceType iface : supers) {
      InterfaceDeclaration decl = iface.getDeclaration();
      if (decl != null && decl.getAnnotation(ResourceDefinition.class) == null && decl.getAnnotation(Path.class) == null) {
        params.addAll(super.getResourceParameters(decl));
      }
    }
    return params;
  }

  @Override
  public String getPath() {
    return "";
  }

  @Override
  public Resource getParent() {
    return null;
  }

  public String getSystemId() {
    return getName().replace(' ', '_');
  }

  public String getName() {
    return this.name;
  }

  public String getNamespace() {
    return namespace;
  }

  public String getDescription() {
    return description;
  }

  public List<StateTransition> getTransitions() {
    return this.transitions;
  }

  public List<ElementDeclaration> getResourceElements() {
    return resourceElements;
  }

  public String getProjectId() {
    return projectId;
  }

  public Set<ResponseCode> getStatusCodes() {
    return statusCodes;
  }

  public Set<ResponseCode> getWarnings() {
    return warnings;
  }

  public List<ResourceBinding> getBindings() {
    return bindings;
  }

  public boolean isEmbedded() {
    return embedded;
  }
}
