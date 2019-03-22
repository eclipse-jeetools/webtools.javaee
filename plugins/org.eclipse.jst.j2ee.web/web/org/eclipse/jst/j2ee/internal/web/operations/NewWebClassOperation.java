/*******************************************************************************
 * Copyright (c) 2008, 2012 SAP AG and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 * Kaloyan Raev, kaloyan.raev@sap.com - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.internal.web.operations;

import static org.eclipse.jst.j2ee.application.internal.operations.IAnnotationsDataModel.USE_ANNOTATIONS;

import java.util.Set;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.IPackageFragment;
import org.eclipse.jst.common.internal.annotations.controller.AnnotationsController;
import org.eclipse.jst.common.internal.annotations.controller.AnnotationsControllerManager;
import org.eclipse.jst.common.internal.annotations.controller.AnnotationsControllerManager.Descriptor;
import org.eclipse.jst.j2ee.internal.common.operations.NewJavaEEArtifactClassOperation;
import org.eclipse.jst.j2ee.internal.web.plugin.WebPlugin;
import org.eclipse.jst.j2ee.project.facet.IJ2EEFacetConstants;
import org.eclipse.jst.j2ee.web.project.facet.WebFacetUtils;
import org.eclipse.wst.common.componentcore.ComponentCore;
import org.eclipse.wst.common.componentcore.datamodel.FacetInstallDataModelProvider;
import org.eclipse.wst.common.componentcore.datamodel.FacetProjectCreationDataModelProvider;
import org.eclipse.wst.common.componentcore.datamodel.properties.IFacetDataModelProperties;
import org.eclipse.wst.common.componentcore.datamodel.properties.IFacetProjectCreationDataModelProperties;
import org.eclipse.wst.common.componentcore.datamodel.properties.IFacetProjectCreationDataModelProperties.FacetDataModelMap;
import org.eclipse.wst.common.componentcore.internal.operation.ArtifactEditProviderOperation;
import org.eclipse.wst.common.componentcore.resources.IVirtualComponent;
import org.eclipse.wst.common.frameworks.datamodel.AbstractDataModelOperation;
import org.eclipse.wst.common.frameworks.datamodel.DataModelFactory;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;
import org.eclipse.wst.common.frameworks.internal.enablement.nonui.WFTWrappedException;
import org.eclipse.wst.common.project.facet.core.IFacetedProject;
import org.eclipse.wst.common.project.facet.core.ProjectFacetsManager;

/**
 * The NewWebClassOperation is an IDataModelOperation following the IDataModel
 * wizard and operation framework.
 * 
 * @see org.eclipse.wst.common.frameworks.datamodel.IDataModelOperation
 * @see org.eclipse.wst.common.frameworks.datamodel.IDataModelProvider
 * 
 * It extends AbstractDataModelOperation to provide web artifact specific java
 * class generation.
 * @see AbstractDataModelOperation
 * 
 * This operation is used by the AddWebClassOperation to generate either an
 * annotated or non annotated java class for an added web artifact. It shares
 * the NewWebClassDataModelProvider with the AddWebClassOperation to store the
 * appropriate properties required to generate the new web artifact.
 * 
 * @see AddWebClassOperation
 * @see NewWebClassDataModelProvider
 * 
 * In the annotated case, a WTPJetEmitter listener template is created and used
 * to generate the listener java class with the embedded annotated tags.
 * @see org.eclipse.jst.j2ee.internal.project.WTPJETEmitter
 * @see CreateWebClassTemplateModel
 * 
 * In the non annotated case, the same emitter is used to create the class with
 * the non annotated web artifact template so the annotated tags are omitted.
 * 
 * Subclasses may extend this operation to provide their own specific web
 * artifact java class generation. The execute method may be extended to do so.
 * Also, generateUsingTemplates is exposed.
 * 
 */
public abstract class NewWebClassOperation extends NewJavaEEArtifactClassOperation {

	/**
	 * XDoclet facet constants
	 */
	private static final String JST_WEB_XDOCLET_VERSION = "1.2.3"; //$NON-NLS-1$
	
	/**
	 * variable for the web plugin
	 */
	protected static final String WEB_PLUGIN = "WEB_PLUGIN"; //$NON-NLS-1$

	/**
	 * This is the constructor which should be used when creating a
	 * NewWebClassOperation. An instance of the NewWebClassDataModel should be
	 * passed in. This does not accept null parameter. It will not return null.
	 * 
	 * @see ArtifactEditProviderOperation#ArtifactEditProviderOperation(IDataModel)
	 * @see NewListenerClassDataModel
	 * 
	 * @param dataModel
	 * @return NewListenerClassOperation
	 */
	public NewWebClassOperation(IDataModel dataModel) {
		super(dataModel);
	}

	/**
	 * Subclasses may extend this method to provide their own template based
	 * creation of an annotated web artifact java class file. This implementation
	 * uses the creation of a CreateWebClassTemplateModel and the WTPJetEmitter
	 * to create the java class with the annotated tags. This method accepts
	 * null for monitor, it does not accept null for fragment. If annotations
	 * are not being used the tags will be omitted from the class.
	 * 
	 * @see CreateWebClassTemplateModel
	 * @see #generateTemplateSource(CreateWebClassTemplateModel, IProgressMonitor)
	 * 
	 * @param monitor
	 * @param fragment
	 * @throws CoreException
	 * @throws WFTWrappedException
	 */
	@Override
	protected void generateUsingTemplates(IProgressMonitor monitor, IPackageFragment fragment) throws WFTWrappedException, CoreException {
		// Create the web artifact template model
		CreateWebClassTemplateModel tempModel = createTemplateModel();
		IProject project = getTargetProject();
		String source;
		try {
			// generate the java source based on the template model
			source = generateTemplateSource(WebPlugin.getPlugin(), tempModel, getTemplateFile(), getTemplateImplementation(), monitor);
		} catch (Exception e) {
			throw new WFTWrappedException(e);
		}
		if (fragment != null) {
			// Create the java file
			String javaFileName = tempModel.getClassName() + DOT_JAVA;
			ICompilationUnit cu = fragment.getCompilationUnit(javaFileName);
			// Add the compilation unit to the java file
			if (cu == null || !cu.exists())
				cu = fragment.createCompilationUnit(javaFileName, source, true, monitor);
			IFile aFile = (IFile) cu.getResource();
			// Let the annotations controller process the annotated resource
			AnnotationsController controller = AnnotationsControllerManager.INSTANCE.getAnnotationsController(project);
			if (controller != null)
				controller.process(aFile);
		}
		
		// Add the xdoclet facet, if necessary, for xdoclet listener creation
		try {
			installXDocletFacetIfNecessary(monitor, project);
		} catch (Exception e) {
			throw new WFTWrappedException(e);
		}
	}

	/**
	 * This method is intended for internal use only. This will add an webdoclet facet to the project.
	 * 
	 * @throws CoreException 
	 * @throws ExecutionException 
	 */
	private void installXDocletFacet(IProgressMonitor monitor, IProject project) throws CoreException, ExecutionException {
		IFacetedProject facetedProject = ProjectFacetsManager.create(project);
		Set fixedFacets = facetedProject.getFixedProjectFacets();
		IDataModel dm = DataModelFactory.createDataModel(new FacetInstallDataModelProvider());
		dm.setProperty(IFacetDataModelProperties.FACET_ID, IJ2EEFacetConstants.DYNAMIC_WEB_XDOCLET);
		dm.setProperty(IFacetDataModelProperties.FACET_PROJECT_NAME, project.getName());
		dm.setProperty(IFacetDataModelProperties.FACET_VERSION_STR, JST_WEB_XDOCLET_VERSION);
		IDataModel fdm = DataModelFactory.createDataModel(new FacetProjectCreationDataModelProvider());
		fdm.setProperty(IFacetProjectCreationDataModelProperties.FACET_PROJECT_NAME, project.getName());

		FacetDataModelMap map = (FacetDataModelMap) fdm.getProperty(IFacetProjectCreationDataModelProperties.FACET_DM_MAP);
		map.add(dm);

		fdm.getDefaultOperation().execute(monitor, null);
		facetedProject.setFixedProjectFacets(fixedFacets);
	}
	
	/**
	 * This method is intended for internal use only. This will check to see if it needs to add an 
	 * webdoclet facet to the project.
	 * 
	 * @throws CoreException 
	 * @throws ExecutionException 
	 */
	private void installXDocletFacetIfNecessary(IProgressMonitor monitor, IProject project) throws CoreException, ExecutionException {

		// If not using annotations, ignore the xdoclet facet
		if (!model.getBooleanProperty(USE_ANNOTATIONS))
			return;
		
		// If an extended annotations processor is added, ignore the default xdoclet one
		Descriptor descriptor = AnnotationsControllerManager.INSTANCE.getDescriptor(getTargetComponent().getProject());
		if (descriptor != null)
			return;

		// Otherwise check and see if the xdoclet facet is on the project yet
		IFacetedProject facetedProject = ProjectFacetsManager.create(project);
		if (!facetedProject.hasProjectFacet(WebFacetUtils.WEB_XDOCLET_FACET))
			return;
			
		// Install xdoclet facet
		installXDocletFacet(monitor, project);
	}

	/**
	 * This method will create an instance of the CreateWebClassTemplateModel to
	 * be used in conjunction with the WTPJETEmitter. This method will not
	 * return null.
	 * 
	 * @see CreateWebClassTemplateModel
	 * @see NewWebClassOperation#generateUsingTemplates(IProgressMonitor,
	 *      IPackageFragment)
	 * 
	 * @return CreateWebClassTemplateModel
	 */
	protected abstract CreateWebClassTemplateModel createTemplateModel();

	/**
	 * This method will return the location of the template file that will be
	 * passed to the WTPJETEmitter to generate the template implementation
	 * class.
	 * 
	 * @return path to the template file. 
	 */
	protected abstract String getTemplateFile();

	/**
	 * This method will return an instance of the template implementation class
	 * that is statically compiled in the plugin. This instance can be used to
	 * generate the artifact's code without using the WTPJETEmitter.
	 * 
	 * @return an instance of the template implementation class. 
	 */
	protected abstract Object getTemplateImplementation();

	private IVirtualComponent getTargetComponent() {
		return ComponentCore.createComponent(getTargetProject());
	}
	
}
