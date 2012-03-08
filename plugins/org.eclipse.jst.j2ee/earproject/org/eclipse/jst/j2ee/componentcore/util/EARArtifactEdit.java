/*******************************************************************************
 * Copyright (c) 2003, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/

package org.eclipse.jst.j2ee.componentcore.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.jem.util.emf.workbench.WorkbenchResourceHelperBase;
import org.eclipse.jst.j2ee.application.Application;
import org.eclipse.jst.j2ee.application.ApplicationFactory;
import org.eclipse.jst.j2ee.application.ApplicationResource;
import org.eclipse.jst.j2ee.application.Module;
import org.eclipse.jst.j2ee.application.WebModule;
import org.eclipse.jst.j2ee.commonarchivecore.internal.Archive;
import org.eclipse.jst.j2ee.commonarchivecore.internal.CommonarchiveFactory;
import org.eclipse.jst.j2ee.commonarchivecore.internal.exception.OpenFailureException;
import org.eclipse.jst.j2ee.commonarchivecore.internal.helpers.ArchiveOptions;
import org.eclipse.jst.j2ee.componentcore.EnterpriseArtifactEdit;
import org.eclipse.jst.j2ee.internal.J2EEConstants;
import org.eclipse.jst.j2ee.internal.archive.operations.EARComponentLoadStrategyImpl;
import org.eclipse.jst.j2ee.internal.common.XMLResource;
import org.eclipse.jst.j2ee.internal.plugin.IJ2EEModuleConstants;
import org.eclipse.jst.j2ee.model.IEARModelProvider;
import org.eclipse.jst.j2ee.model.IModelProvider;
import org.eclipse.jst.j2ee.project.EarUtilities;
import org.eclipse.jst.j2ee.project.JavaEEProjectUtilities;
import org.eclipse.jst.j2ee.project.facet.IJ2EEFacetConstants;
import org.eclipse.wst.common.componentcore.ArtifactEdit;
import org.eclipse.wst.common.componentcore.ComponentCore;
import org.eclipse.wst.common.componentcore.ModuleCoreNature;
import org.eclipse.wst.common.componentcore.UnresolveableURIException;
import org.eclipse.wst.common.componentcore.internal.ArtifactEditModel;
import org.eclipse.wst.common.componentcore.internal.ReferencedComponent;
import org.eclipse.wst.common.componentcore.internal.StructureEdit;
import org.eclipse.wst.common.componentcore.internal.impl.ModuleURIUtil;
import org.eclipse.wst.common.componentcore.internal.util.IArtifactEditFactory;
import org.eclipse.wst.common.componentcore.internal.util.IModuleConstants;
import org.eclipse.wst.common.componentcore.resources.IVirtualComponent;
import org.eclipse.wst.common.componentcore.resources.IVirtualReference;

/**
 * <p>
 * EARArtifactEdit obtains an {@see org.eclipse.jst.j2ee.application.Application}&nbsp;metamodel.
 * The {@see org.eclipse.jst.j2ee.application.ApplicationResource}&nbsp; which stores the metamodel
 * is retrieved from the {@see org.eclipse.wst.common.modulecore.ArtifactEditModel}&nbsp;using a
 * cached constant (@see
 * org.eclipse.jst.j2ee.commonarchivecore.internal.helpers.ArchiveConstants#APPLICATION_DD_URI). The
 * defined methods extract data or manipulate the contents of the underlying resource.
 * </p>
 */

public class EARArtifactEdit extends EnterpriseArtifactEdit implements IArtifactEditFactory, IEARModelProvider {

	private static final String EAR_CONTENT_TYPE = "org.eclipse.jst.j2ee.earDD"; //$NON-NLS-1$

	public static final Class ADAPTER_TYPE = EARArtifactEdit.class;
	
	private static final IVirtualReference[] NO_REFERENCES = new IVirtualReference[0];

	/**
	 * 
	 */
	public EARArtifactEdit() {
		super();
	}

	/**
	 * @param aHandle
	 * @param toAccessAsReadOnly
	 * @throws IllegalArgumentException
	 */
	public EARArtifactEdit(IProject aProject, boolean toAccessAsReadOnly) throws IllegalArgumentException {
		super(aProject, toAccessAsReadOnly);
	}

	// This should be reverted to protected when 115924 is fixed
	public EARArtifactEdit(IProject aProject, boolean toAccessAsReadOnly, boolean forCreate) throws IllegalArgumentException {
		super(aProject, toAccessAsReadOnly, forCreate, IJ2EEFacetConstants.ENTERPRISE_APPLICATION);
	}

	/**
	 * <p>
	 * Returns an instance facade to manage the underlying edit model for the given
	 * {@see WorkbenchComponent}. Instances of ArtifactEdit that are returned through this method
	 * must be {@see #dispose()}ed of when no longer in use.
	 * </p>
	 * <p>
	 * Use to acquire an ArtifactEdit facade for a specific {@see WorkbenchComponent}&nbsp;that
	 * will not be used for editing. Invocations of any save*() API on an instance returned from
	 * this method will throw exceptions.
	 * </p>
	 * <p>
	 * <b>The following method may return null. </b>
	 * </p>
	 * 
	 * @param aModule
	 *            A valid {@see WorkbenchComponent}&nbsp;with a handle that resolves to an
	 *            accessible project in the workspace
	 * @return An instance of ArtifactEdit that may only be used to read the underlying content
	 *         model
	 */
	public static EARArtifactEdit getEARArtifactEditForRead(IProject aProject) {
		EARArtifactEdit artifactEdit = null;
		try {
			if (isValidEARModule(ComponentCore.createComponent(aProject)))
				artifactEdit = new EARArtifactEdit(aProject, true, false);
		} catch (Exception iae) {
			artifactEdit = null;
		}
		return artifactEdit;
	}

	/**
	 * <p>
	 * Returns an instance facade to manage the underlying edit model for the given
	 * {@see WorkbenchComponent}. Instances of ArtifactEdit that are returned through this method
	 * must be {@see #dispose()}ed of when no longer in use.
	 * </p>
	 * <p>
	 * Use to acquire an ArtifactEdit facade for a specific {@see WorkbenchComponent}&nbsp;that
	 * will be used for editing.
	 * </p>
	 * <p>
	 * <b>The following method may return null. </b>
	 * </p>
	 * 
	 * @param aModule
	 *            A valid {@see WorkbenchComponent}&nbsp;with a handle that resolves to an
	 *            accessible project in the workspace
	 * @return An instance of ArtifactEdit that may be used to modify and persist changes to the
	 *         underlying content model
	 */
	public static EARArtifactEdit getEARArtifactEditForWrite(IProject aProject) {
		EARArtifactEdit artifactEdit = null;
		try {
			if (isValidEARModule(ComponentCore.createComponent(aProject)))
				artifactEdit = new EARArtifactEdit(aProject, false, false);
		} catch (Exception iae) {
			artifactEdit = null;
		}
		return artifactEdit;
	}

	/**
	 * <p>
	 * Returns an instance facade to manage the underlying edit model for the given
	 * {@see WorkbenchComponent}. Instances of EARArtifactEdit that are returned through this
	 * method must be {@see #dispose()}ed of when no longer in use.
	 * </p>
	 * <p>
	 * Use to acquire an EARArtifactEdit facade for a specific {@see WorkbenchComponent}&nbsp;that
	 * will not be used for editing. Invocations of any save*() API on an instance returned from
	 * this method will throw exceptions.
	 * </p>
	 * <p>
	 * <b>This method may return null. </b>
	 * </p>
	 * <p>
	 * Note: This method is for internal use only. Clients should not call this method.
	 * </p>
	 * 
	 * @param aModule
	 *            A valid {@see WorkbenchComponent}&nbsp;with a handle that resolves to an
	 *            accessible project in the workspace
	 * @return An instance of EARArtifactEdit that may only be used to read the underlying content
	 *         model
	 * @throws UnresolveableURIException
	 *             could not resolve uri.
	 */
	public static EARArtifactEdit getEARArtifactEditForRead(IVirtualComponent aModule) {
		if (aModule == null)
			return null;
		return getEARArtifactEditForRead(aModule.getProject());
	}


	/**
	 * <p>
	 * Returns an instance facade to manage the underlying edit model for the given
	 * {@see WorkbenchComponent}. Instances of WebArtifactEdit that are returned through this
	 * method must be {@see #dispose()}ed of when no longer in use.
	 * </p>
	 * <p>
	 * Use to acquire an EARArtifactEdit facade for a specific {@see WorkbenchComponent}&nbsp;that
	 * will be used for editing.
	 * </p>
	 * <p>
	 * <b>This method may return null. </b>
	 * </p>
	 * <p>
	 * Note: This method is for internal use only. Clients should not call this method.
	 * </p>
	 * 
	 * @param aModule
	 *            A valid {@see WorkbenchComponent}&nbsp;with a handle that resolves to an
	 *            accessible project in the workspace
	 * @return An instance of EARArtifactEdit that may be used to modify and persist changes to the
	 *         underlying content model
	 */
	public static EARArtifactEdit getEARArtifactEditForWrite(IVirtualComponent aModule) {
		if (aModule == null)
			return null;
		return getEARArtifactEditForWrite(aModule.getProject());
	}

	/**
	 * @param module
	 *            A {@see WorkbenchComponent}
	 * @return True if the supplied module
	 *         {@see ArtifactEdit#isValidEditableModule(WorkbenchComponent)}and the moduleTypeId is
	 *         a JST module
	 */
	public static boolean isValidEARModule(IVirtualComponent aModule) throws UnresolveableURIException {
		if (!isValidEditableModule(aModule))
			return false;
		return JavaEEProjectUtilities.isEARProject(aModule.getProject());
	}

	/**
	 * <p>
	 * Creates an instance facade for the given {@see ArtifactEditModel}.
	 * </p>
	 * 
	 * @param anArtifactEditModel
	 */
	public EARArtifactEdit(ArtifactEditModel model) {
		super(model);
	}

	/**
	 * <p>
	 * Creates an instance facade for the given {@see ArtifactEditModel}
	 * </p>
	 * <p>
	 * Note: This method is for internal use only. Clients should not call this method.
	 * </p>
	 * 
	 * @param aNature
	 *            A non-null {@see ModuleCoreNature}for an accessible project
	 * @param aModule
	 *            A non-null {@see WorkbenchComponent}pointing to a module from the given
	 *            {@see ModuleCoreNature}
	 */


	protected EARArtifactEdit(ModuleCoreNature aNature, IVirtualComponent aModule, boolean toAccessAsReadOnly) {
		super(aNature, aModule, toAccessAsReadOnly);
	}

	/**
	 * <p>
	 * Retrieves J2EE version information from ApplicationResource.
	 * </p>
	 * 
	 * @return an integer representation of a J2EE Spec version
	 * 
	 */
	@Override
	public int getJ2EEVersion() {
		verifyOperationSupported();
		return getApplicationXmiResource().getJ2EEVersionID();
	}

	/**
	 * 
	 * @return ApplicationResource from (@link getDeploymentDescriptorResource())
	 * 
	 */

	public ApplicationResource getApplicationXmiResource() {
		verifyOperationSupported();
		return (ApplicationResource) getDeploymentDescriptorResource();
	}

	/**
	 * <p>
	 * Obtains the Application {@see Application}root object from the {@see ApplicationResource},
	 * the root object contains all other resource defined objects.
	 * </p>
	 * 
	 * @return Application
	 * 
	 */

	public Application getApplication() {
		verifyOperationSupported();
		return (Application) getDeploymentDescriptorRoot();
	}

	/**
	 * <p>
	 * Retrieves the resource from the {@see ArtifactEditModel}
	 * </p>
	 * 
	 * @return Resource
	 * 
	 */

	@Override
	public Resource getDeploymentDescriptorResource() {
		verifyOperationSupported();
		return getArtifactEditModel().getResource(J2EEConstants.APPLICATION_DD_URI_OBJ);
	}


	/**
	 * <p>
	 * Creates a deployment descriptor root object (Application) and populates with data. Adds the
	 * root object to the deployment descriptor resource.
	 * </p>
	 * 
	 * <p>
	 * 
	 * @param aModule
	 *            A non-null pointing to a {@see XMLResource}
	 * @param version
	 *            Version to be set on resource....if null default is taken
	 * 
	 * Note: This method is typically used for JUNIT - move?
	 * </p>
	 */
	protected void addApplicationIfNecessary(XMLResource aResource) {
		verifyOperationSupported();
		if (aResource != null) {
			if (aResource.getContents() == null || aResource.getContents().isEmpty()) {
				Application newApp = ApplicationFactory.eINSTANCE.createApplication();
				aResource.getContents().add(newApp);
			}
			Application application = (Application) aResource.getContents().get(0);
			URI moduleURI = getArtifactEditModel().getModuleURI();
			try {
				application.setDisplayName(StructureEdit.getDeployedName(moduleURI));
			} catch (UnresolveableURIException e) {
				// Ignore
			}
			aResource.setID(application, J2EEConstants.APPL_ID);
			// TODO add more mandatory elements
		}
	}

	/**
	 * Checks if the uri mapping already exists.
	 * 
	 * @param String
	 *            currentURI - The current uri of the module.
	 * @return boolean
	 */
	public boolean uriExists(String currentURI) {
		StructureEdit edit = null;
		try {
			edit = StructureEdit.getStructureEditForRead(getProject());
			if (edit!=null && edit.getComponent()!=null) {
				List referencedComps = edit.getComponent().getReferencedComponents();
				for (int i=0; i<referencedComps.size(); i++) {
					ReferencedComponent ref = (ReferencedComponent) referencedComps.get(i);
					Object module = ref.getDependentObject();
					if (module!=null && module instanceof Module) {
						String existingURI = ((Module)module).getUri();
						if (existingURI!=null && existingURI.equals(currentURI))
							return true;
					}
				}
			}
		} finally {
			if (edit != null)
				edit.dispose();
		}
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.j2ee.internal.modulecore.util.EnterpriseArtifactEdit#createModelRoot()
	 */
	@Override
	public EObject createModelRoot() {
		verifyOperationSupported();
		return createModelRoot(getJ2EEVersion());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.j2ee.internal.modulecore.util.EnterpriseArtifactEdit#createModelRoot(java.lang.Integer)
	 */
	@Override
	public EObject createModelRoot(int version) {
		verifyOperationSupported();
		ApplicationResource res = (ApplicationResource) getDeploymentDescriptorResource();
		res.setModuleVersionID(version);
		addApplicationIfNecessary(res);
		return ((ApplicationResource) getDeploymentDescriptorResource()).getRootObject();
	}

	/**
	 * This method will return the list of IVirtualReferences for all of the utility modules
	 * contained in the EAR application
	 * 
	 * @return - an array of IVirtualReferences for utility modules in the EAR
	 */
	public IVirtualReference[] getUtilityModuleReferences() {  
		verifyOperationSupported();
		List explicitUtilityReferences = 
			getComponentReferencesAsList(Collections.singletonList(IJ2EEFacetConstants.UTILITY));
		
		// fetch other Utility Jars attached to the EAR project 
		List implicitUtilityReferenceTypes =
			Arrays.asList(new String[] {  IModuleConstants.JST_APPCLIENT_MODULE, 
										   IModuleConstants.JST_WEB_MODULE,  
										   IModuleConstants.JST_EJB_MODULE }); 

		List implicitUtilityReferences = 
			getComponentReferencesAsList(implicitUtilityReferenceTypes);
		
		
		Application application = getApplication();
		Module module = null;
		IVirtualReference reference = null;
		for (Iterator referenceItr = implicitUtilityReferences.iterator(); referenceItr.hasNext(); ) {
			reference = (IVirtualReference) referenceItr.next();
			String uri = reference.getArchiveName();
			if(uri != null && reference.getRuntimePath() != null) {
				uri = reference.getRuntimePath().makeRelative().append(uri).toString();
			}
			module = application.getFirstModule(uri);
			if(module != null) 
				referenceItr.remove(); 
		}
		
		List allUtilityModuleReferences = new ArrayList();
		allUtilityModuleReferences.addAll(explicitUtilityReferences);
		allUtilityModuleReferences.addAll(implicitUtilityReferences);
		
		if(allUtilityModuleReferences.size() > 0)
			return (IVirtualReference[]) allUtilityModuleReferences.toArray(new IVirtualReference[allUtilityModuleReferences.size()]);
		return NO_REFERENCES;
		
	}

	public String getModuleURI(IVirtualComponent moduleComp) {
		IVirtualReference [] refs = getComponent().getReferences();
		for(int i=0; i<refs.length; i++){
			if(refs[i].getReferencedComponent().equals(moduleComp)){
				if (refs[i].getRuntimePath().toString().equals("/")){ //$NON-NLS-1$
					return refs[i].getArchiveName();
				}
				return refs[i].getRuntimePath().append(refs[i].getArchiveName()).toString();
			}
		}
		return null;		
	}

//	private String getJarURI(final ReferencedComponent ref, final IVirtualComponent moduleComp) {
//		String uri =  ref.getArchiveName();
//		if( uri == null || uri.length() < 0 ){
//			uri = moduleComp.getName()+IJ2EEModuleConstants.JAR_EXT;
//		}else{
//			String prefix = ref.getRuntimePath().makeRelative().toString();
//			if(prefix.length() > 0){
//				uri = prefix + "/" + uri; //$NON-NLS-1$
//			}
//		}
//		return uri;
//	}
	
	public IVirtualComponent getModuleByManifestURI(final String uri) {
		if (uri == null)
			return null;
		
		IVirtualComponent earComponent = ComponentCore.createComponent(getProject());
		IVirtualReference [] refs = earComponent.getReferences();

		for(int i=0;i<refs.length; i++){
		
			if(refs[i].getRuntimePath() != null &&
			   refs[i].getArchiveName() != null &&
			   uri.equals(refs[i].getRuntimePath().makeRelative().append(refs[i].getArchiveName()).toString())){
				
				return refs[i].getReferencedComponent();
			}
		}
		return null;
	}

	/**
	 * This method will return the an IVirtualComponent for the given module name. The method take
	 * either moduleName or moduleName + ".module_extension" (module_extension = ".jar" || ".war" ||
	 * ".rar") which allows users to get a IVirtualComponent for a given entry in an application.xml
	 * 
	 * @return - a IVirtualComponent for module name
	 * @deprecated - see {@link J2EEProjectUtilities.getModule(IVirtualComponent earComponent, String moduleName)}
	 */
	public IVirtualComponent getModule(String moduleName) {
		if (moduleName == null)
			return null;
		String innerModuleName = moduleName;
		if (innerModuleName.endsWith(IJ2EEModuleConstants.JAR_EXT) || innerModuleName.endsWith(IJ2EEModuleConstants.WAR_EXT) || innerModuleName.endsWith(IJ2EEModuleConstants.RAR_EXT))
			innerModuleName = innerModuleName.substring(0, (innerModuleName.length() - IJ2EEModuleConstants.JAR_EXT.length()));
		IVirtualReference[] references = getComponentReferences();
		for (int i = 0; i < references.length; i++) {
			IVirtualComponent component = references[i].getReferencedComponent();
			if (component.getName().equals(innerModuleName)) {
				return component;
			}
		}
		return null;
	}

	/**
	 * This method will return the list of IVirtualReferences for the J2EE module components
	 * contained in this EAR application.
	 * 
	 * @return - an array of IVirtualReferences for J2EE modules in the EAR
	 * @deprecated - see {@link EarUtilities.getJ2EEModuleReferences(IVirtualComponent earComponent)}
	 */
	public IVirtualReference[] getJ2EEModuleReferences() {
		return EarUtilities.getJ2EEModuleReferences(getComponent());
	}

	/**
	 * This method will return the list of IVirtualReferences for all of the components contained in
	 * this EAR application.
	 * 
	 * @return - an array of IVirtualReferences for components in the EAR
	 * @deprecated - see {@link EarUtilities.getComponentReferences(IVirtualComponent earComponent)}
	 */
	public IVirtualReference[] getComponentReferences() {
		return EarUtilities.getComponentReferences(getComponent());
	}

	/**
	 * This method will return the IVirtualReference to the component of the given name
	 * 
	 * @return - IVirtualReference or null if not found
	 * @deprecated - see {@link EarUtilities.getComponentReference(IVirtualComponent earComponent, String componentName)}
	 */
	public IVirtualReference getComponentReference(String componentName) {
		return EarUtilities.getComponentReference(getComponent(), componentName);
	}

	/**
	 * 
	 * @param componentTypes
	 * @return A List of {@link IVirtualReference}s.
	 * 
	 * A copy of this method is now in J2EEProjectUtilities.  Any bug fixes should occur in both locations.
	 */
	private List getComponentReferencesAsList(List componentTypes) {
		List components = new ArrayList();
		IVirtualComponent earComponent = getComponent();
		if (earComponent != null && JavaEEProjectUtilities.isEARProject(earComponent.getProject())) {
			IVirtualReference[] refComponents = earComponent.getReferences();
			for (int i = 0; i < refComponents.length; i++) {
				IVirtualComponent module = refComponents[i].getReferencedComponent();
				if (module == null)
					continue;
				// if component types passed in is null then return all components
				if (componentTypes == null || componentTypes.size() == 0)
					components.add(refComponents[i]);
				else {
					if (componentTypes.contains(JavaEEProjectUtilities.getJ2EEComponentType(module))) {
						components.add(refComponents[i]);
					}
				}
			}
		}
		return components;
	}

	public ArtifactEdit createArtifactEditForRead(IVirtualComponent aComponent) {

		return getEARArtifactEditForRead(aComponent);
	}

	public ArtifactEdit createArtifactEditForWrite(IVirtualComponent aComponent) {

		return getEARArtifactEditForWrite(aComponent);
	}

	@Override
	public Archive asArchive(boolean includeSource) throws OpenFailureException {
		return asArchive(includeSource, true);
	}
	
	@Override
	public Archive asArchive(boolean includeSource, boolean includeClasspathComponents) throws OpenFailureException {
		return asArchive(includeSource, includeClasspathComponents, false);
	}
	public Archive asArchive(boolean includeSource, boolean includeClasspathComponents, boolean readOnly) throws OpenFailureException {
		verifyOperationSupported();
		EARComponentLoadStrategyImpl loader = new EARComponentLoadStrategyImpl(getComponent(), includeClasspathComponents);
		loader.setReadOnly(readOnly);
		loader.setExportSource(includeSource);
		String uri = ModuleURIUtil.getHandleString(getComponent());
		ArchiveOptions options = new ArchiveOptions();
		options.setLoadStrategy(loader);
		options.setIsReadOnly(readOnly);
		return CommonarchiveFactory.eINSTANCE.openEARFile(options, uri);
	}

	public static void createDeploymentDescriptor(IProject project, int version) {
		EnterpriseArtifactEdit earEdit = new EARArtifactEdit(project, false, true);
		try {
			earEdit.createModelRoot(version);
			earEdit.save(null);
		} finally {  // Make sure new resource is removed  - the uri used for creation shouldn't be cached
			Resource newRes = earEdit.getDeploymentDescriptorResource();
			WorkbenchResourceHelperBase.getResourceSet(project).getResources().remove(newRes);
			newRes.unload();
			earEdit.dispose();
		}
	}

	/**
	 * This method will return the context root in this application for the passed in web project.
	 * 
	 * @param webProject
	 * @return contextRoot String
	 */
	public String getWebContextRoot(IProject webProject) {
		verifyOperationSupported();
		if (webProject == null || !JavaEEProjectUtilities.isDynamicWebProject(webProject))
			return null;
		IVirtualComponent webComp = ComponentCore.createComponent(webProject);
		String webModuleURI = getModuleURI(webComp);
		if (webModuleURI != null) {
			WebModule webModule = (WebModule) getApplication().getModule(webModuleURI, null);
			if (webModule != null)
				return webModule.getContextRoot();
		}
		return null;
	}

	/**
	 * This method will set the context root on the application for the passed in contextRoot. This
	 * must be called in a write artifact edit and be saved for changes to be saved.
	 * 
	 * @param webProject
	 * @param aContextRoot
	 */
	public void setWebContextRoot(IProject webProject, String aContextRoot) {
		verifyOperationSupported();
		if (webProject == null || !JavaEEProjectUtilities.isDynamicWebProject(webProject))
			return;
		IVirtualComponent webComp = ComponentCore.createComponent(webProject);
		String webModuleURI = getModuleURI(webComp);
		if (webModuleURI != null) {
			WebModule webModule = (WebModule) getApplication().getModule(webModuleURI, null);
			if (webModule != null)
				webModule.setContextRoot(aContextRoot);
		}
	}
	@Override
	public IModelProvider create(IProject project) {
		return getEARArtifactEditForRead(project);
	}

	@Override
	public IModelProvider create(IVirtualComponent component) {
		return getEARArtifactEditForRead(component);
	}
	/* (non-Javadoc)
	 * @see org.eclipse.jst.j2ee.componentcore.EnterpriseArtifactEdit#modify(java.lang.Runnable, org.eclipse.core.runtime.IPath)
	 */
	@Override
	public void modify(Runnable runnable, IPath modelPath) {
		setWritableEdit(getEARArtifactEditForWrite(getProject()));
		try {
			runnable.run();
			if( getWritableEdit() != null ){
				// Always save regardless of resource path passed - Artifactedits save resources as a unit
				getWritableEdit().saveIfNecessary( new NullProgressMonitor() );
			}
			
		} finally {
			getWritableEdit().dispose();
			setWritableEdit(null);
		}
	}

	
	@Override
	protected String getContentTypeDescriber() {
		return EAR_CONTENT_TYPE;
	}
	@Override
	protected URI getRootURI() {
		return J2EEConstants.APPLICATION_DD_URI_OBJ;
	}
}
