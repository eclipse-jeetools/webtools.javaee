/***************************************************************************************************
 * Copyright (c) 2003, 2004 IBM Corporation and others. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: IBM Corporation - initial API and implementation
 **************************************************************************************************/
package org.eclipse.jst.j2ee.internal.ejb.archiveoperations;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.util.URI;
import org.eclipse.jem.util.emf.workbench.ProjectUtilities;
import org.eclipse.jem.util.logger.proxy.Logger;
import org.eclipse.jst.j2ee.application.internal.operations.AddComponentToEnterpriseApplicationDataModel;
import org.eclipse.jst.j2ee.application.internal.operations.AddComponentToEnterpriseApplicationOperation;
import org.eclipse.jst.j2ee.application.internal.operations.JavaUtilityComponentCreationOperation;
import org.eclipse.jst.j2ee.application.internal.operations.UpdateManifestDataModel;
import org.eclipse.jst.j2ee.application.internal.operations.UpdateManifestOperation;
import org.eclipse.jst.j2ee.ejb.internal.modulecore.util.EJBArtifactEdit;
import org.eclipse.jst.j2ee.internal.ejb.impl.EJBJarImpl;
import org.eclipse.wst.common.modulecore.ModuleCore;
import org.eclipse.wst.common.modulecore.ModuleCoreFactory;
import org.eclipse.wst.common.modulecore.ReferencedComponent;
import org.eclipse.wst.common.modulecore.WorkbenchComponent;
import org.eclipse.wst.common.modulecore.internal.util.IModuleConstants;
import org.eclipse.wst.common.modulecore.resources.IVirtualContainer;
import org.eclipse.wst.common.modulecore.resources.IVirtualFile;

public class EJBClientComponentCreationOperation extends JavaUtilityComponentCreationOperation {

	public static final String metaInfFolderDeployPath = "/"; //$NON-NLS-1$
	/**
	 * @param dataModel
	 */
	public EJBClientComponentCreationOperation(EJBClientComponentDataModel dataModel) {
		super(dataModel);
	}
	
	protected void execute(IProgressMonitor monitor) throws CoreException, InvocationTargetException, InterruptedException {
		super.execute(IModuleConstants.JST_UTILITY_MODULE, monitor);
		runAddToEAROperation(monitor);
		runAddToEJBOperation(monitor);
		modifyEJBModuleJarDependency(monitor);
		updateEJBDD(monitor);
	}	
	 
	protected void runAddToEAROperation(IProgressMonitor monitor) throws CoreException, InvocationTargetException, InterruptedException {
		ModuleCore core = null;
		
		EJBClientComponentDataModel dm = (EJBClientComponentDataModel)getOperationDataModel();
		try {
			core = ModuleCore.getModuleCoreForRead(dm.getProject());
			WorkbenchComponent wc = core.findWorkbenchModuleByDeployName(dm.getComponentDeployName());
			

			
			AddComponentToEnterpriseApplicationDataModel addComponentToEARDataModel = new AddComponentToEnterpriseApplicationDataModel();;
			
			addComponentToEARDataModel.setProperty(AddComponentToEnterpriseApplicationDataModel.PROJECT_NAME, dm.getProject().getName());				
			addComponentToEARDataModel.setProperty(AddComponentToEnterpriseApplicationDataModel.MODULE_NAME, dm.getComponentDeployName());
			addComponentToEARDataModel.setProperty(AddComponentToEnterpriseApplicationDataModel.EAR_MODULE_NAME, dm.getEARDeployName());
			
			List modulesList = new ArrayList();
			modulesList.add(wc);
			
			addComponentToEARDataModel.setProperty(AddComponentToEnterpriseApplicationDataModel.MODULE_LIST,modulesList);
			
			AddComponentToEnterpriseApplicationOperation addModuleOp = new AddComponentToEnterpriseApplicationOperation(addComponentToEARDataModel);
			addModuleOp.doRun(monitor);
		} finally {
			if(core != null)
				core.dispose();
		}
	}
	
	protected void runAddToEJBOperation(IProgressMonitor monitor)throws CoreException, InvocationTargetException, InterruptedException{

		EJBClientComponentDataModel dm = (EJBClientComponentDataModel)getOperationDataModel();
		
		ModuleCore moduleCore = null;
		try{
			moduleCore = ModuleCore.getModuleCoreForWrite(dm.getProject());
			WorkbenchComponent wc = moduleCore.findWorkbenchModuleByDeployName(dm.getComponentDeployName());
			WorkbenchComponent ejbComp = moduleCore.findWorkbenchModuleByDeployName(dm.getEJBDeployName());
			
			List list = new ArrayList();
			list.add(wc);
			
			URI runtimeURI = URI.createURI(metaInfFolderDeployPath);
		
			if (list != null && list.size() > 0) {
				for (int i = 0; i < list.size(); i++) {
					ReferencedComponent rc = ModuleCoreFactory.eINSTANCE.createReferencedComponent();
					WorkbenchComponent workbenchComp= (WorkbenchComponent)list.get(i);
					rc.setHandle(wc.getHandle());
					rc.setRuntimePath(runtimeURI);
					ejbComp.getReferencedComponents().add(rc);
				}
			}
			moduleCore.saveIfNecessary(monitor); 
		 }  finally {
		       if (null != moduleCore) {
		            moduleCore.dispose();
		       }
		 }				
	}
	private void modifyEJBModuleJarDependency(IProgressMonitor aMonitor) throws InvocationTargetException, InterruptedException {
		
		
		EJBClientComponentDataModel dm = (EJBClientComponentDataModel)getOperationDataModel();
		
		String ejbprojectName = dm.getEJBProjectName();		
		String ejbComponentName = dm.getEJBComponentName();
		String ejbComponentDeployName = dm.getEJBDeployName();

		String clientProjectName = dm.getProject().getName();
		String clientDeployName  = dm.getComponentDeployName();


		IProject ejbProject = ProjectUtilities.getProject( ejbprojectName );
		
		ModuleCore moduleCore = null;
		IFile manifestmf = null;
		try{
			moduleCore = ModuleCore.getModuleCoreForRead(ejbProject);
			WorkbenchComponent ejbwc = moduleCore.findWorkbenchModuleByDeployName(ejbComponentDeployName);
			ejbwc = moduleCore.findWorkbenchModuleByDeployName(ejbComponentDeployName);
			IVirtualContainer component = ModuleCore.create( ejbProject, ejbwc.getName());
			IVirtualFile vf = component.getFile( new Path("/META-INF/MANIFEST.MF"));
			manifestmf = vf.getRealFile();
		}finally {
		       if (null != moduleCore) {
	            moduleCore.dispose();
	       }
		}	
	
		if( clientProjectName.equals( ejbprojectName )){
			UpdateManifestDataModel updateManifestDataModel = new UpdateManifestDataModel();
			updateManifestDataModel.setProperty(UpdateManifestDataModel.PROJECT_NAME, ejbprojectName);
			updateManifestDataModel.setBooleanProperty(UpdateManifestDataModel.MERGE, false);
			updateManifestDataModel.setProperty(UpdateManifestDataModel.MANIFEST_FILE, manifestmf);
			updateManifestDataModel.setProperty(UpdateManifestDataModel.JAR_LIST, UpdateManifestDataModel.convertClasspathStringToList(clientDeployName) );
			
			UpdateManifestOperation op = new UpdateManifestOperation(updateManifestDataModel);		
			op.run(aMonitor);
		}else{
	        //JARDependencyDataModel dataModel = new JARDependencyDataModel();
	        //dataModel.setProperty(JARDependencyDataModel.PROJECT_NAME, ejbprojectName);
//	        dataModel.setProperty(JARDependencyDataModel.REFERENCED_PROJECT_NAME, clientprojectName);
//	        if (earNatures !=null && earNatures.length>0)
//	        	dataModel.setProperty(JARDependencyDataModel.EAR_PROJECT_NAME, earNatures[0].getProject().getName());
//	        dataModel.setIntProperty(JARDependencyDataModel.JAR_MANIPULATION_TYPE, JARDependencyDataModel.JAR_MANIPULATION_ADD);
//	        JARDependencyOperation op = new JARDependencyOperation(dataModel);
//	        op.run(createSubProgressMonitor(1));			
		}	
		

	}
	/**
	 * Update the deployment descriptor for the ejb project
	 */
	private void updateEJBDD(IProgressMonitor monitor) {
		
		EJBClientComponentDataModel dm = (EJBClientComponentDataModel)getOperationDataModel();
		
		String ejbprojectName = dm.getEJBProjectName();		
		IProject ejbProject = ProjectUtilities.getProject( ejbprojectName );	
		String ejbComponentDeployName = dm.getEJBDeployName();
		String clientDeployName  = dm.getComponentDeployName();
		
		ModuleCore moduleCore = null;

		try{
			moduleCore = ModuleCore.getModuleCoreForRead(ejbProject);
			WorkbenchComponent ejbwc = moduleCore.findWorkbenchModuleByDeployName(ejbComponentDeployName);
			ejbwc = moduleCore.findWorkbenchModuleByDeployName(ejbComponentDeployName);
			
            EJBArtifactEdit ejbEdit = null;
           	try{
           		ejbEdit = EJBArtifactEdit.getEJBArtifactEditForWrite(ejbwc);
           		if(ejbEdit != null) {
           			EJBJarImpl ejbres = (EJBJarImpl)ejbEdit.getDeploymentDescriptorRoot();
           			ejbres.setEjbClientJar(clientDeployName);
           			ejbEdit.saveIfNecessary(monitor);
           		}
           	}
           	catch(Exception e){
           		Logger.getLogger().logError(e);
           		e.printStackTrace();
           	} finally {
           		if(ejbEdit != null)
           			ejbEdit.dispose();
          	}
			
		}finally {
		       if (null != moduleCore) {
	            moduleCore.dispose();
	       }
		}	
	}	
}