/*******************************************************************************
 * Copyright (c) 2003, 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
/*
 * Created on Sep 29, 2003
 * 
 * To change the template for this generated file go to Window&gt;Preferences&gt;Java&gt;Code
 * Generation&gt;Code and Comments
 */
package org.eclipse.jst.j2ee.ejb.internal.plugin;

import org.eclipse.core.resources.IProject;
import org.eclipse.jst.j2ee.ejb.EJBJar;
import org.eclipse.jst.j2ee.ejb.componentcore.util.EJBArtifactEdit;
import org.eclipse.jst.j2ee.ejb.datamodel.properties.IEjbComponentCreationDataModelProperties;
import org.eclipse.jst.j2ee.ejb.internal.modulecore.util.EJBArtifactEditUtilities;
import org.eclipse.jst.j2ee.internal.archive.operations.ImportOption;
import org.eclipse.jst.j2ee.internal.ejb.archiveoperations.EjbClientProjectCreationDataModelProvider;
import org.eclipse.jst.j2ee.internal.ejb.archiveoperations.EjbClientProjectCreationOperation;
import org.eclipse.jst.j2ee.internal.ejb.archiveoperations.IEjbClientProjectCreationDataModelProperties;
import org.eclipse.jst.j2ee.internal.ejb.project.operations.EJBComponentImportDataModelProvider;
import org.eclipse.jst.j2ee.internal.ejb.project.operations.EjbFacetProjectCreationDataModelProvider;
import org.eclipse.jst.j2ee.internal.moduleextension.EarModuleExtensionImpl;
import org.eclipse.jst.j2ee.internal.moduleextension.EjbModuleExtension;
import org.eclipse.jst.j2ee.internal.project.IJ2EEProjectTypes;
import org.eclipse.wst.common.componentcore.ComponentCore;
import org.eclipse.wst.common.componentcore.resources.IVirtualComponent;
import org.eclipse.wst.common.frameworks.datamodel.DataModelFactory;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;
import org.eclipse.wst.common.frameworks.datamodel.IDataModelOperation;


public class EjbModuleExtensionImpl extends EarModuleExtensionImpl implements EjbModuleExtension {

	public EjbModuleExtensionImpl() {
		super();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.j2ee.internal.internal.moduleextension.EjbModuleExtension#initializeEjbReferencesToModule(org.eclipse.jst.j2ee.internal.internal.j2eeproject.J2EENature)
	 */
//	public void initializeEjbReferencesToModule(J2EENature nature, UpdateModuleReferencesInEARProjectCommand cmd) {
//		
//		IVirtualComponent[] comps = ComponentUtilities.getComponentsForProject(nature.getProject());
//		if (comps.length == 0)
//			return;
//		EJBArtifactEdit edit = EJBArtifactEdit.getEJBArtifactEditForRead(comps[0]);
//		boolean foundRef = false;
//		try {
//			EJBJar jar = edit.getEJBJar();
//			if (jar != null) {
//				List ejbs = jar.getEnterpriseBeans();
//				int size = ejbs.size();
//				EnterpriseBean ejb;
//				for (int i = 0; i < size; i++) {
//					ejb = (EnterpriseBean) ejbs.get(i);
//					foundRef = foundRef || cmd.initializeEjbReferencesToModule(ejb.getEjbRefs());
//					foundRef = foundRef || cmd.initializeEjbReferencesToModule(ejb.getEjbLocalRefs());
//				}
//			}
//			if (foundRef)
//				cmd.addNestedEditModel((J2EEEditModel)edit.getAdapter(ArtifactEditModel.class));
//		} finally {
//			if (edit != null)
//				edit.dispose();
//		}
//	}

	public EJBJar getEJBJar(IProject aProject) {
		
		IVirtualComponent comp = ComponentCore.createComponent(aProject);
		return EJBArtifactEditUtilities.getEJBJar(comp);
	}

	public IProject getDefinedEJBClientJARProject(IProject anEJBProject) {
		IVirtualComponent comp = ComponentCore.createComponent(anEJBProject);
		EJBArtifactEdit edit = null;
		IVirtualComponent clientComp = null;
		try {
			edit = EJBArtifactEdit.getEJBArtifactEditForRead(comp);
			clientComp = edit.getEJBClientJarModule();
		} finally {
			if (edit != null)
				edit.dispose();
		}
		if (clientComp == null)
			return null;
		return clientComp.getProject();
	}

	public IDataModelOperation createEJBClientJARProject(
			final String clientProjectName,
			final String srcFolderName,
			final String ejbProjectName,
			final String earProjectName,
			final org.eclipse.wst.common.project.facet.core.runtime.IRuntime runtime){
		
		IDataModel dm = DataModelFactory.createDataModel(new EjbClientProjectCreationDataModelProvider());
		dm.setProperty(IEjbClientProjectCreationDataModelProperties.PROJECT_NAME,
				clientProjectName );
		
		dm.setProperty(IEjbClientProjectCreationDataModelProperties.EJB_PROJECT_NAME,
				ejbProjectName );
		
		dm.setProperty(IEjbClientProjectCreationDataModelProperties.SOURCE_FOLDER,
				srcFolderName );
		
		dm.setProperty(IEjbClientProjectCreationDataModelProperties.EAR_PROJECT_NAME,
				earProjectName );
		
		dm.setProperty(IEjbClientProjectCreationDataModelProperties.RUNTIME,
				runtime );
		
		EjbClientProjectCreationOperation op = new EjbClientProjectCreationOperation(dm);
		return op;		
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.j2ee.internal.internal.moduleextension.EarModuleExtension#createProjectDataModel()
	 */
	public IDataModel createProjectDataModel() {
		//IDataModel model = DataModelFactory.createDataModel(new EjbComponentCreationDataModelProvider());

		// Added this property so Application Creation Wizard, will not create a
		// EJB client jar, when a EJB module is created.
		//model.setProperty(IEjbComponentCreationDataModelProperties.CREATE_CLIENT, Boolean.FALSE);

		// Override the default to not create a default session bean.
		// This is necessary when creating a default EJB project from an EAR project wizard.
		//model.setProperty(IEjbComponentCreationDataModelProperties.CREATE_DEFAULT_SESSION_BEAN, Boolean.FALSE);
		
		
		IDataModel model = DataModelFactory.createDataModel(new EjbFacetProjectCreationDataModelProvider());
		return model;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.j2ee.internal.internal.moduleextension.EarModuleExtension#createImportDataModel()
	 */
	public IDataModel createImportDataModel() {
		return DataModelFactory.createDataModel(new EJBComponentImportDataModelProvider());
	}


	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.j2ee.internal.internal.moduleextension.EarModuleExtension#createProjectCreationOperation(com.ibm.etools.archive.ear.operations.ImportOption)
	 */
	public IDataModelOperation  createProjectCreationOperation(ImportOption option) {
		if (option.getArchiveType() == IJ2EEProjectTypes.EJB_CLIENT) {
            IDataModel model = option.getModel();
			model.setProperty(IEjbComponentCreationDataModelProperties.CREATE_CLIENT, Boolean.TRUE);
			return createProjectCreationOperation(model);
		}
		return super.createProjectCreationOperation(option);
	}

}
