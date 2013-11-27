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

import com.sun.mirror.declaration.Declaration;
import com.sun.mirror.declaration.PackageDeclaration;
import com.sun.mirror.declaration.TypeDeclaration;
import net.sf.jelly.apt.decorations.declaration.DecoratedDeclaration;
import org.codehaus.enunciate.contract.jaxrs.ResourceMethod;
import org.codehaus.enunciate.contract.jaxrs.ResourceParameter;
import org.codehaus.enunciate.util.TypeDeclarationComparator;
import org.gedcomx.rt.rs.RSMetadata;
import org.gedcomx.rt.rs.StateTransitionParameter;

import java.util.*;

/**
 * @author Ryan Heaton
 */
public class ResourceBinding extends DecoratedDeclaration {

  private final String path;
  private final Set<ResourceDefinitionDeclaration> definitions = new TreeSet<ResourceDefinitionDeclaration>(new TypeDeclarationComparator());
  private final List<ResourceMethod> methods = new ArrayList<ResourceMethod>();
  private final Set<ResponseCode> statusCodes = new HashSet<ResponseCode>();
  private final Set<ResponseCode> warnings = new HashSet<ResponseCode>();
  private final Set<StateTransition> links = new TreeSet<StateTransition>();
  private final Set<ResourceParameter> resourceParameters = new TreeSet<ResourceParameter>(new Comparator<ResourceParameter>() {
    @Override
    public int compare(ResourceParameter param1, ResourceParameter param2) {
      int comparison = param1.getParameterName().compareTo(param2.getParameterName());
      if (comparison == 0) {
        comparison = param1.getTypeName().compareTo(param2.getTypeName());
      }
      return comparison;
    }
  });

  final String name;
  final String namespace;
  final String projectId;

  public ResourceBinding(Declaration delegate, String path, org.gedcomx.rt.rs.ResourceBinding metadata) {
    super(delegate);
    this.path = path;
    this.name = metadata == null || "##default".equals(metadata.value()) ? null : metadata.value();
    String namespace = null;
    String projectId = null;
    PackageDeclaration pkg = (delegate instanceof TypeDeclaration) ? ((TypeDeclaration)delegate).getPackage() : null;
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
  }

  @Override
  public String getDocValue() {
    String docValue = super.getDocValue();
    if (docValue == null || "".equals(docValue.trim())) {
      docValue = getPrimaryDefinition().getDocValue();
    }
    return docValue;
  }

  public String getName() {
    return this.name == null ? getPrimaryDefinition().getName() : this.name;
  }

  public String getSystemId() {
    return getName().replace(' ', '_');
  }

  public String getNamespace() {
    return this.namespace;
  }

  public String getProjectId() {
    return this.projectId;
  }

  public String getPath() {
    return path;
  }

  public ResourceDefinitionDeclaration getPrimaryDefinition() {
    //the "primary" definition is the definition that is being implemented that is not embedded.
    for (ResourceDefinitionDeclaration definition : definitions) {
      if (!definition.isEmbedded()) {
        return definition;
      }
    }

    //didn't find a non-embedded definition. we'll use the first embedded one, then.
    if (this.definitions.size() > 0) {
      return this.definitions.iterator().next();
    }

    return null;
  }

  public Set<ResourceDefinitionDeclaration> getDefinitions() {
    return definitions;
  }

  public Set<ResponseCode> getStatusCodes() {
    return statusCodes;
  }

  public Set<ResponseCode> getWarnings() {
    return warnings;
  }

  public Set<StateTransition> getLinks() {
    return links;
  }

  public List<ResourceMethod> getMethods() {
    return methods;
  }

  public Set<ResourceParameter> getResourceParameters() {
    return resourceParameters;
  }

  public Set<String> getProduces() {
    TreeSet<String> produces = new TreeSet<String>();
    for (ResourceMethod method : getMethods()) {
      produces.addAll(method.getProducesMime());
    }
    return produces;
  }

  public Set<String> getConsumes() {
    TreeSet<String> produces = new TreeSet<String>();
    for (ResourceMethod method : getMethods()) {
      produces.addAll(method.getConsumesMime());
    }
    return produces;
  }

  public Properties getTransitionTemplateProperties() {
    Properties properties = new Properties();
    String path = getPath();
    StringBuilder queryParams = new StringBuilder();
    boolean appendComma = false;
    for (ResourceParameter parameter : getResourceParameters()) {
      if (parameter.isPathParam() || parameter.isQueryParam()) {
        String parameterName = parameter.getParameterName();

        if (parameter.isQueryParam()) {
          if (appendComma) {
            queryParams.append(',');
          }
          queryParams.append(parameterName);
          appendComma = true;
        }

        boolean optional = true;
        String variableName = parameterName;
        StateTransitionParameter transitionParameter = parameter.getAnnotation(StateTransitionParameter.class);
        if (transitionParameter != null) {
          optional = transitionParameter.optional();
          if (!"##default".equals(transitionParameter.name())) {
            variableName = transitionParameter.name();
          }
        }

        properties.setProperty(path + "." + parameterName + ".optional", String.valueOf(optional));
        properties.setProperty(path + "." + parameterName + ".variableName", variableName);
      }
    }

    properties.setProperty(path + ".queryParams", queryParams.toString());
    properties.setProperty(path + ".namespace", getNamespace());
    ResourceDefinitionDeclaration primaryDefinition = getPrimaryDefinition();
    if (primaryDefinition != null) {
      properties.setProperty(path + ".title", primaryDefinition.getName());
    }

    Iterator<String> it = getConsumes().iterator();
    StringBuilder accept = new StringBuilder();
    while (it.hasNext()) {
      String consumes = it.next();
      accept.append(consumes);
      if (it.hasNext()) {
        accept.append(',');
      }
    }
    properties.setProperty(path + ".accept", accept.toString());

    it = getProduces().iterator();
    StringBuilder type = new StringBuilder();
    while (it.hasNext()) {
      String produces = it.next();
      type.append(produces);
      if (it.hasNext()) {
        type.append(',');
      }
    }
    properties.setProperty(path + ".type", type.toString());

    StringBuilder allow = new StringBuilder();
    appendComma = false;
    for (ResourceMethod method : getMethods()) {
      for (String op : method.getHttpMethods()) {
        if (appendComma) {
          allow.append(',');
        }
        allow.append(op);
        appendComma = true;
      }
    }
    properties.setProperty(path + ".allow", allow.toString());
    return properties;
  }
}
