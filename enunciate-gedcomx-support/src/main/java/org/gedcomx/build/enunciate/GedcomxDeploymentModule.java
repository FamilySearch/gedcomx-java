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
package org.gedcomx.build.enunciate;

import com.sun.mirror.apt.Messager;
import freemarker.template.ObjectWrapper;
import freemarker.template.TemplateException;
import net.sf.jelly.apt.Context;
import net.sf.jelly.apt.freemarker.APTJellyObjectWrapper;
import org.apache.commons.digester.RuleSet;
import org.codehaus.enunciate.EnunciateException;
import org.codehaus.enunciate.apt.EnunciateFreemarkerModel;
import org.codehaus.enunciate.config.SchemaInfo;
import org.codehaus.enunciate.config.WsdlInfo;
import org.codehaus.enunciate.contract.jaxb.QNameEnumTypeDefinition;
import org.codehaus.enunciate.contract.jaxb.RootElementDeclaration;
import org.codehaus.enunciate.contract.jaxb.TypeDefinition;
import org.codehaus.enunciate.contract.validation.ValidationException;
import org.codehaus.enunciate.contract.validation.ValidationMessage;
import org.codehaus.enunciate.contract.validation.ValidationResult;
import org.codehaus.enunciate.contract.validation.Validator;
import org.codehaus.enunciate.main.Artifact;
import org.codehaus.enunciate.main.Enunciate;
import org.codehaus.enunciate.main.FileArtifact;
import org.codehaus.enunciate.main.NamedArtifact;
import org.codehaus.enunciate.modules.DeploymentModule;
import org.codehaus.enunciate.modules.DocumentationAwareModule;
import org.codehaus.enunciate.modules.FreemarkerDeploymentModule;
import org.codehaus.enunciate.modules.docs.GenerateExampleXmlMethod;
import org.codehaus.enunciate.modules.docs.SchemaForNamespaceMethod;
import org.codehaus.enunciate.modules.objc.ObjCDeploymentModule;
import org.codehaus.enunciate.template.freemarker.IsDefinedGloballyMethod;
import org.codehaus.enunciate.template.freemarker.UniqueContentTypesMethod;
import org.gedcomx.build.enunciate.rdf.RDFProcessor;
import org.gedcomx.rt.*;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.*;

/**
 * The GEDCOM X deployment module handles the generation of the GEDCOM X documentation, validates common patterns, and
 * supplies needed metadata for the namespaces.
 *
 * @author Ryan Heaton
 */
public class GedcomxDeploymentModule extends FreemarkerDeploymentModule implements DocumentationAwareModule {

  private String projectId = GedcomxConstants.GEDCOMX_PROJECT_ID;
  private String projectLabelModifier = null;
  private final Map<String, String> baseProjectUris = new HashMap<String, String>();
  private RDFProcessor rdfProcessor;
  private final Map<String, String> primaryNav = new LinkedHashMap<String, String>();
  private boolean disableProcessing = true;
  private String stylesheet;

  /**
   * @return "gedcomx"
   */
  @Override
  public String getName() {
    return "gedcomx";
  }

  /**
   * @return 100
   */
  @Override
  public int getOrder() {
    return 100;
  }

  /**
   * The title of the documentation.
   *
   * @return The title of the documentation.
   */
  public String getTitle() {
    return "GEDCOMX";
  }

  /**
   * The title of the documentation.
   *
   * @param title The title of the documentation.
   */
  public void setTitle(String title) {
  }

  /**
   * Set the title for this project iff it hasn't already been set.
   *
   * @param title The title.
   */
  public void setTitleConditionally(String title) {
  }

  /**
   * The subdirectory in the web application where the documentation will be put.
   *
   * @return The subdirectory in the web application where the documentation will be put.
   */
  public String getDocsDir() {
    return null;
  }

  /**
   * The name of the index page.
   *
   * @return The name of the index page.
   */
  public String getIndexPageName() {
    return "index.html";
  }

  /**
   * The name of the index page.
   *
   * @param indexPageName The name of the index page.
   */
  public void setIndexPageName(String indexPageName) {
  }

  /**
   * The subdirectory in the web application where the documentation will be put.
   *
   * @param docsDir The subdirectory in the web application where the documentation will be put.
   */
  public void setDocsDir(String docsDir) {
  }

  /**
   * Whether to disable processing.
   *
   * @return Whether to disable processing.
   */
  public boolean isDisableProcessing() {
    return disableProcessing;
  }

  /**
   * Whether to disable processing.
   *
   * @param disableProcessing Whether to disable processing.
   */
  public void setDisableProcessing(boolean disableProcessing) {
    this.disableProcessing = disableProcessing;
  }

  /**
   * Add a primary nav element.
   *
   * @param label The label for the nav.
   * @param href The href.
   */
  public void addPrimaryNav(String label, String href) {
    this.primaryNav.put(label, href);
  }

  /**
   * The id of the GEDCOM X project to be built.
   *
   * @param projectId The id of the GEDCOM X project to be built.
   */
  public void setProjectId(String projectId) {
    this.projectId = projectId;
  }

  public void setProjectLabelModifier(String projectLabelModifier) {
    this.projectLabelModifier = projectLabelModifier;
  }

  public void setStylesheet(String stylesheet) {
    this.stylesheet = stylesheet;
  }

  protected URL getDocsTemplateURL() {
    return GedcomxDeploymentModule.class.getResource("docs.fmt");
  }

  protected URL getCodeTemplateURL() {
    return GedcomxDeploymentModule.class.getResource("code.fmt");
  }

  protected URL getRDFSchemaTemplateURL() {
    return GedcomxDeploymentModule.class.getResource("rdfschema.fmt");
  }

  protected URL getRDFaTemplateURL() {
    return GedcomxDeploymentModule.class.getResource("rdfa.fmt");
  }

  protected URL getRDFaTypesTemplateURL() {
    return GedcomxDeploymentModule.class.getResource("rdfa-types.fmt");
  }

  @Override
  public void init(Enunciate enunciate) throws EnunciateException {
    super.init(enunciate);

    enunciate.getConfig().getContentTypesToIds().put(GedcomxConstants.GEDCOMX_XML_MEDIA_TYPE, "gedcomx-xml");
    enunciate.getConfig().getContentTypesToIds().put(GedcomxConstants.GEDCOMX_JSON_MEDIA_TYPE, "gedcomx-json");

    SortedSet<DeploymentModule> modules = enunciate.getConfig().getAllModules();
    for (DeploymentModule module : modules) {
      if (module instanceof ObjCDeploymentModule) {
        ((ObjCDeploymentModule)module).setTranslateIdTo("objectId");
      }
    }

    this.rdfProcessor = new RDFProcessor();
  }

  @Override
  public void initModel(EnunciateFreemarkerModel model) {
    super.initModel(model);

    Iterator<RootElementDeclaration> it = model.getRootElementDeclarations().iterator();
    while (it.hasNext()) {
      if (it.next().getAnnotation(DocIgnoreXmlRootElement.class) != null) {
        //remove any root elements that are requested to be kept separate from the docs.
        it.remove();
      }
    }

    Map<String,String> knownPrefixes = GedcomNamespaceManager.getKnownPrefixes();
    model.getNamespacesToPrefixes().putAll(knownPrefixes);
    for (SchemaInfo schemaInfo : model.getNamespacesToSchemas().values()) {
      String knownPrefix = knownPrefixes.get(schemaInfo.getNamespace());
      if (knownPrefix != null) {
        schemaInfo.setId(knownPrefix);
      }

      it = schemaInfo.getGlobalElements().iterator();
      while (it.hasNext()) {
        if (it.next().getAnnotation(DocIgnoreXmlRootElement.class) != null) {
          //remove any root elements that are requested to be kept separate from the docs.
          it.remove();
        }
      }
    }

    List<MediaTypeDeclaration> mediaTypeDeclarations = new ArrayList<MediaTypeDeclaration>();
    Collection<RootElementDeclaration> mediaTypeRoots = gatherMediaTypeRoots(model);
    Map<String, String> prefix_version_to_ns = new HashMap<String, String>();
    for (RootElementDeclaration rootElement : mediaTypeRoots) {
      info("Found media type root declaration at %s.", rootElement.getQualifiedName());
      MediaTypeDefinition mediaTypeInfo = rootElement.getAnnotation(MediaTypeDefinition.class);
      MediaTypeDeclaration decl = new MediaTypeDeclaration(mediaTypeInfo.id(), mediaTypeInfo.name(), mediaTypeInfo.description(), mediaTypeInfo.version(), mediaTypeInfo.xmlMediaType(), mediaTypeInfo.jsonMediaType(), mediaTypeInfo.projectId(), rootElement, mediaTypeDeclarations);
      for (Model m : mediaTypeInfo.models()) {
        String id = m.id();
        SchemaInfo schemaInfo = model.getNamespacesToSchemas().get(m.namespace());
        model.getNamespacesToPrefixes().put(m.namespace(), id);
        if (schemaInfo != null) {
          schemaInfo.setId(id);
          String previousNamespace = prefix_version_to_ns.put(id, schemaInfo.getNamespace());
          if (previousNamespace != null && !previousNamespace.equals(schemaInfo.getNamespace())) {
            String message = rootElement.getPosition() == null ?
              String.format("Id '%s' is already being used by model %s.", id, previousNamespace) :
              String.format("%s: Id '%s' is already being used by model %s.", rootElement.getQualifiedName(), id, previousNamespace);
            throw new ValidationException(rootElement.getPosition(), message);
          }

          String label = m.label();
          if ("".equals(label)) {
            label = "\"" + id + "\" Model";
          }
          schemaInfo.setProperty("label", label);

          String description = m.description();
          if ("".equals(description)) {
            description = null;
          }
          schemaInfo.setProperty("description", description);
          schemaInfo.setProperty("definesRDFSchema", m.definesRDFSchema());
          schemaInfo.setProperty("projectId", mediaTypeInfo.projectId());
          schemaInfo.setProperty("mediaType", decl);

          //ensure the correct filenames are used for the schemas.
          schemaInfo.setProperty("filename", id + ".xsd");
          schemaInfo.setProperty("location", id + ".xsd");

          decl.getSchemas().add(schemaInfo);
        }
      }
      mediaTypeDeclarations.add(decl);
    }
    model.put("mediaTypeDeclarations", mediaTypeDeclarations);

    ValidationResult validationResult = this.rdfProcessor.processModel(model);
    Messager messager = Context.getCurrentEnvironment().getMessager();
    if (validationResult.hasWarnings()) {
      warn("Warnings while processing RDF and resource services.");
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

  protected Collection<RootElementDeclaration> gatherMediaTypeRoots(EnunciateFreemarkerModel model) {
    ArrayList<RootElementDeclaration> mediaTypeDecls = new ArrayList<RootElementDeclaration>();
    for (SchemaInfo schemaInfo : model.getNamespacesToSchemas().values()) {
      for (RootElementDeclaration declaration : schemaInfo.getGlobalElements()) {
        if (declaration.getAnnotation(MediaTypeDefinition.class) != null) {
          mediaTypeDecls.add(declaration);
        }
      }
    }
    return mediaTypeDecls;
  }

  public void doFreemarkerGenerate() throws EnunciateException, IOException, TemplateException {
    //no-op...
  }

  @Override
  protected void doBuild() throws EnunciateException, IOException {
    if (!getEnunciate().isUpToDateWithSources(getBuildDir())) {
      new File(getBuildDir(), "v1").mkdirs();
      new File(getBuildDir(), "types").mkdirs();
      Set<Artifact> downloadableArtifacts = buildBase();
      EnunciateFreemarkerModel model = getModel();
      model.setFileOutputDirectory(getBuildDir());
      model.put("downloads", downloadableArtifacts);
      model.setVariable("uniqueContentTypes", new UniqueContentTypesMethod());
      model.setVariable("schemaForNamespace", new SchemaForNamespaceMethod(model.getNamespacesToSchemas()));
      model.put("isDefinedGlobally", new IsDefinedGloballyMethod());
      model.put("defaultDate", new Date());
      model.setVariable("generateExampleJson", new GenerateExampleJsonMethod(model));
      model.setVariable("generateExampleXml", new GenerateExampleXmlMethod(null, model));
      model.setVariable("typeName", new TypeNameMethod(model.getNamespacesToSchemas()));
      model.setVariable("jsonExtensionElementName", new JsonExtensionElementNameMethod());
      model.put("rdfschema", this.rdfProcessor.getRdfSchema());
      model.put("primaryNav", this.primaryNav);
      model.put("projectId", this.projectId);
      model.put("gxTypeDefinitions", model.getNamespacesToSchemas().get(GedcomxConstants.GEDCOMX_NAMESPACE).getTypeDefinitions());
      Collection<TypeDefinition> types = model.getNamespacesToSchemas().get(GedcomxConstants.GEDCOMX_TYPES_NAMESPACE).getTypeDefinitions();
      List<QNameEnumTypeDefinition> enumTypes = new ArrayList<QNameEnumTypeDefinition>();
      for (TypeDefinition type : types) {
        if (type instanceof QNameEnumTypeDefinition) {
          enumTypes.add((QNameEnumTypeDefinition) type);
        }
      }
      model.put("qnameTypeDefinitions", enumTypes);
      if (this.projectLabelModifier != null) {
        model.put("projectLabelModifier", this.projectLabelModifier);
      }
      model.put("isOfProject", new IsOfProjectMethod(getModelInternal().getNamespacesToSchemas(), projectId));
      model.put("baseProjectUris", getBaseProjectUris());
      model.put("baseProjectUri", new BaseProjectUriMethod(getBaseProjectUris(), getModelInternal().getNamespacesToSchemas()));
      try {
        for (SchemaInfo schemaInfo : model.getNamespacesToSchemas().values()) {
          String namespace = schemaInfo.getNamespace();
          if (Boolean.TRUE.equals(schemaInfo.getProperty("definesRDFSchema"))) {
            if (!this.rdfProcessor.isKnownRDFNamespace(namespace)) {
              model.put("schema", schemaInfo);
              if (!isDisableProcessing()) {
                processTemplate(getRDFSchemaTemplateURL(), model);
              }
              schemaInfo.setProperty("rdfSchemaLocation", schemaInfo.getId() + ".rdf.xml");
            }
            else {
              schemaInfo.setProperty("rdfSchemaLocation", namespace);
            }
          }
        }

        if (!isDisableProcessing()) {
          processTemplate(getDocsTemplateURL(), model);
          processTemplate(getCodeTemplateURL(), model);
          processTemplate(getRDFaTemplateURL(), model);
          processTemplate(getRDFaTypesTemplateURL(), model);
        }
      }
      catch (TemplateException e) {
        throw new EnunciateException(e);
      }
    }
    else {
      info("Skipping build of documentation as everything appears up-to-date...");
    }

    //export the generated documentation as an artifact.
    getEnunciate().addArtifact(new FileArtifact(getName(), "gedcomx-docs", getBuildDir()));
  }

  /**
   * Builds the base output directory.
   *
   * @return The set of artifacts available for download.
   */
  protected Set<Artifact> buildBase() throws IOException {
    Enunciate enunciate = getEnunciate();
    File buildDir = getBuildDir();
    buildDir.mkdirs();

    enunciate.extractBase(GedcomxDeploymentModule.class.getResourceAsStream("/docs.base.zip"), buildDir);
    if (this.stylesheet != null) {
      File styleSheet = enunciate.resolvePath(stylesheet);
      enunciate.copyFile(styleSheet, new File(new File(buildDir, "css"), "style.css"));
    }

    for (SchemaInfo schemaInfo : getModel().getNamespacesToSchemas().values()) {
      if (schemaInfo.getProperty("file") != null) {
        File from = (File) schemaInfo.getProperty("file");
        String filename = schemaInfo.getProperty("filename") != null ? (String) schemaInfo.getProperty("filename") : from.getName();
        File to = new File(buildDir, filename);
        enunciate.copyFile(from, to);
      }
    }

    for (WsdlInfo wsdlInfo : getModel().getNamespacesToWSDLs().values()) {
      if (wsdlInfo.getProperty("file") != null) {
        File from = (File) wsdlInfo.getProperty("file");
        String filename = wsdlInfo.getProperty("filename") != null ? (String) wsdlInfo.getProperty("filename") : from.getName();
        File to = new File(buildDir, filename);
        enunciate.copyFile(from, to);
      }
    }

    File wadlFile = getModelInternal().getWadlFile();
    if (wadlFile != null) {
      enunciate.copyFile(wadlFile, new File(buildDir, wadlFile.getName()));
    }

    Set<Artifact> downloads = new TreeSet<Artifact>();
    for (Artifact artifact : enunciate.getArtifacts()) {
      if (artifact instanceof NamedArtifact && artifact.isPublic()) {
        downloads.add(artifact);
      }
    }

    for (Artifact download : downloads) {
      debug("Exporting %s to directory %s.", download.getId(), buildDir);
      download.exportTo(buildDir, enunciate);
    }

    return downloads;
  }

  @Override
  protected ObjectWrapper getObjectWrapper() {
    return new APTJellyObjectWrapper();
  }

  @Override
  public Validator getValidator() {
    return new GedcomxValidator();
  }

  @Override
  public RuleSet getConfigurationRules() {
    return new GedcomxRuleSet();
  }

  public Map<String, String> getBaseProjectUris() {
    return baseProjectUris;
  }

  public void putProjectBase(String id, String uri) {
    this.baseProjectUris.put(id, uri);
  }

}
