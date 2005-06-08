package org.eclipse.jst.j2ee.application.internal.operations;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Properties;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.jem.util.emf.workbench.ProjectUtilities;
import org.eclipse.jem.util.logger.proxy.Logger;
import org.eclipse.jst.j2ee.application.Application;
import org.eclipse.jst.j2ee.application.Module;
import org.eclipse.jst.j2ee.application.WebModule;
import org.eclipse.jst.j2ee.componentcore.util.EARArtifactEdit;
import org.eclipse.jst.j2ee.internal.J2EEConstants;
import org.eclipse.jst.j2ee.internal.application.ApplicationPackage;
import org.eclipse.jst.j2ee.internal.modulecore.util.EARArtifactEditOperation;
import org.eclipse.wst.common.componentcore.ComponentCore;
import org.eclipse.wst.common.componentcore.internal.StructureEdit;
import org.eclipse.wst.common.componentcore.internal.WorkbenchComponent;
import org.eclipse.wst.common.componentcore.internal.impl.ModuleURIUtil;
import org.eclipse.wst.common.componentcore.internal.operation.ArtifactEditOperationDataModel;
import org.eclipse.wst.common.componentcore.internal.util.IModuleConstants;
import org.eclipse.wst.common.componentcore.resources.ComponentHandle;
import org.eclipse.wst.common.componentcore.resources.IVirtualComponent;
import org.eclipse.wst.common.componentcore.resources.IVirtualReference;

public class AddComponentToEnterpriseApplicationOperation extends EARArtifactEditOperation {
	public static final String metaInfFolderDeployPath = "/"; //$NON-NLS-1$

	public AddComponentToEnterpriseApplicationOperation(AddComponentToEnterpriseApplicationDataModel operationDataModel) {
		super(operationDataModel);
	}
	
	protected void execute(IProgressMonitor monitor) throws CoreException, InvocationTargetException, InterruptedException {
		addComponentToEnterpriseApplication(monitor);
	}

	private void addComponentToEnterpriseApplication(IProgressMonitor monitor) {
		// get ear component
		String earProjName = operationDataModel.getStringProperty(AddComponentToEnterpriseApplicationDataModel.EAR_PROJECT_NAME);
		IProject earProj = ProjectUtilities.getProject(earProjName);
		String earCompName = operationDataModel.getStringProperty(AddComponentToEnterpriseApplicationDataModel.EAR_MODULE_NAME);
		IVirtualComponent earComp = ComponentCore.createComponent(earProj, earCompName);
		// set up references
		AddComponentToEnterpriseApplicationDataModel dm = (AddComponentToEnterpriseApplicationDataModel)getOperationDataModel();
		IPath runtimePath = new Path(metaInfFolderDeployPath);
		List list = (List)dm.getProperty(AddComponentToEnterpriseApplicationDataModel.MODULE_LIST);
		int size = list.size();
		if (list != null && size > 0) {
			IVirtualReference[] refs = new IVirtualReference[size];
			for (int i = 0; i < size; i++) {
				IVirtualComponent refedComp = (IVirtualComponent)list.get(i);
				refs[i] = ComponentCore.createReference(earComp, refedComp);
				refs[i].setRuntimePath(runtimePath);
			}
			earComp.setReferences(refs);
		}
		
		updateEARDD(monitor);
	}

	protected void updateEARDD(IProgressMonitor monitor){	
		EARArtifactEdit earEdit = null;
       	try {
			String earProj = operationDataModel.getStringProperty(AddComponentToEnterpriseApplicationDataModel.EAR_PROJECT_NAME);
			IProject proj = ProjectUtilities.getProject(earProj);
			ComponentHandle handle = ComponentHandle.create(proj,operationDataModel.getStringProperty(AddComponentToEnterpriseApplicationDataModel.EAR_MODULE_NAME));
			earEdit = EARArtifactEdit.getEARArtifactEditForWrite(handle);
			if(earEdit != null){
				Application application = earEdit.getApplication();
				AddComponentToEnterpriseApplicationDataModel dm = (AddComponentToEnterpriseApplicationDataModel)getOperationDataModel();
				List list = (List)dm.getProperty(AddComponentToEnterpriseApplicationDataModel.MODULE_LIST);
				if (list != null && list.size() > 0) {
					for (int i = 0; i < list.size(); i++) {
						IVirtualComponent comp = (IVirtualComponent)list.get(i);
						addModule(application, comp);	
					}
				}
			}	
			earEdit.saveIfNecessary(monitor);
       	}
       	catch(Exception e){
       		Logger.getLogger().logError(e);
       	} finally {			
       		if(earEdit != null)
				earEdit.dispose();
       	}		
	}

	protected Module createNewModule(IVirtualComponent vc) {
		
		String type = vc.getComponentTypeId();
		if ( type.equals(IModuleConstants.JST_WEB_MODULE) ){
			return ((ApplicationPackage) EPackage.Registry.INSTANCE.getEPackage(ApplicationPackage.eNS_URI)).getApplicationFactory().createWebModule();
		}else if ( type.equals(IModuleConstants.JST_EJB_MODULE)) {
			return ((ApplicationPackage) EPackage.Registry.INSTANCE.getEPackage(ApplicationPackage.eNS_URI)).getApplicationFactory().createEjbModule();
		}else if ( type.equals(IModuleConstants.JST_APPCLIENT_MODULE)) {
			return ((ApplicationPackage) EPackage.Registry.INSTANCE.getEPackage(ApplicationPackage.eNS_URI)).getApplicationFactory().createJavaClientModule();
		}else if ( type.equals(IModuleConstants.JST_CONNECTOR_MODULE)) {
			return ((ApplicationPackage) EPackage.Registry.INSTANCE.getEPackage(ApplicationPackage.eNS_URI)).getApplicationFactory().createConnectorModule();
		}
		return null;
	}
	
	protected void addModule(Application application, IVirtualComponent vc) {
		Module m = createNewModule(vc);
		String name = vc.getName();
		String type = vc.getComponentTypeId();
		if ( type.equals(IModuleConstants.JST_WEB_MODULE) ){
			name += ".war"; //$NON-NLS-1$
		}else if ( type.equals(IModuleConstants.JST_EJB_MODULE)) {
			name += ".jar"; //$NON-NLS-1$
		}else if ( type.equals(IModuleConstants.JST_APPCLIENT_MODULE)) {
			name += ".jar"; //$NON-NLS-1$
		}else if ( type.equals(IModuleConstants.JST_CONNECTOR_MODULE)) {
			name += ".rar"; //$NON-NLS-1$
		}
		if( m!= null){
			m.setUri(name);
			if (m instanceof WebModule) {
				// set up context root
				Properties props = vc.getMetaProperties();
				String contextroot = props.getProperty(J2EEConstants.CONTEXTROOT);
				if (contextroot != null)
					((WebModule) m).setContextRoot(contextroot);
			}
			application.getModules().add(m);	
		}	
	}	
	
	private URI normalize(WorkbenchComponent aComponent, WorkbenchComponent aReferencedComponent) {   
		// same project doesn't matter
		if(StructureEdit.getContainingProject(aComponent) == StructureEdit.getContainingProject(aReferencedComponent)) 
			return aReferencedComponent.getHandle();
		// different project, fully qualify
		return ModuleURIUtil.fullyQualifyURI(aReferencedComponent);
							
	}

	public IProject getProject() {
		String projName = operationDataModel.getStringProperty(ArtifactEditOperationDataModel.PROJECT_NAME );
		return ProjectUtilities.getProject( projName );
	}

}
