package org.eclipse.jst.j2ee.application.internal.operations;

import java.util.List;
import java.util.Properties;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.jem.util.logger.proxy.Logger;
import org.eclipse.jst.j2ee.application.Application;
import org.eclipse.jst.j2ee.application.ApplicationPackage;
import org.eclipse.jst.j2ee.application.Module;
import org.eclipse.jst.j2ee.application.WebModule;
import org.eclipse.jst.j2ee.componentcore.util.EARArtifactEdit;
import org.eclipse.jst.j2ee.internal.J2EEConstants;
import org.eclipse.jst.j2ee.internal.project.J2EEProjectUtilities;
import org.eclipse.wst.common.componentcore.datamodel.properties.ICreateReferenceComponentsDataModelProperties;
import org.eclipse.wst.common.componentcore.internal.ReferencedComponent;
import org.eclipse.wst.common.componentcore.internal.StructureEdit;
import org.eclipse.wst.common.componentcore.internal.WorkbenchComponent;
import org.eclipse.wst.common.componentcore.internal.operation.CreateReferenceComponentsOp;
import org.eclipse.wst.common.componentcore.resources.IVirtualComponent;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;

public class AddComponentToEnterpriseApplicationOp extends CreateReferenceComponentsOp {
	public static final String metaInfFolderDeployPath = "/"; //$NON-NLS-1$

	public AddComponentToEnterpriseApplicationOp(IDataModel model) {
		super(model);
	}

	public IStatus execute(IProgressMonitor monitor, IAdaptable info) throws ExecutionException {
		super.execute(monitor, info);
		updateEARDD(monitor);
		return OK_STATUS;
	}


	protected void updateEARDD(IProgressMonitor monitor) {

		EARArtifactEdit earEdit = null;
		StructureEdit se = null;
		try {
			IVirtualComponent sourceComp = (IVirtualComponent) model.getProperty(ICreateReferenceComponentsDataModelProperties.SOURCE_COMPONENT);
			earEdit = EARArtifactEdit.getEARArtifactEditForWrite(sourceComp.getProject());
			se = StructureEdit.getStructureEditForWrite(sourceComp.getProject());
			if (earEdit != null) {
				Application application = earEdit.getApplication();
				List list = (List) model.getProperty(ICreateReferenceComponentsDataModelProperties.TARGET_COMPONENT_LIST);
				if (list != null && list.size() > 0) {
					for (int i = 0; i < list.size(); i++) {
						StructureEdit compse = null;
						IVirtualComponent wc = (IVirtualComponent)list.get(i);
						WorkbenchComponent earwc = se.getComponent();
						try {
							compse = StructureEdit.getStructureEditForWrite(wc.getProject());
							WorkbenchComponent refwc = compse.getComponent();
							ReferencedComponent ref = se.findReferencedComponent(earwc,refwc);
							Module mod = addModule(application, wc);
							ref.setDependentObject(mod);
						} finally {
							if (compse != null) {
								compse.saveIfNecessary(monitor);
								compse.dispose();
							}
						}
					}
				}
			}
			se.saveIfNecessary(monitor);
			earEdit.saveIfNecessary(monitor);
		} catch (Exception e) {
			Logger.getLogger().logError(e);
		} finally {
			if (earEdit != null)
				earEdit.dispose();
			if (se != null)
				se.dispose();
		}
	}

	protected Module createNewModule(IVirtualComponent wc) {
		if (J2EEProjectUtilities.isDynamicWebProject(wc.getProject())) {
			return ((ApplicationPackage) EPackage.Registry.INSTANCE.getEPackage(ApplicationPackage.eNS_URI)).getApplicationFactory().createWebModule();
		} else if (J2EEProjectUtilities.isEJBProject(wc.getProject())) {
			return ((ApplicationPackage) EPackage.Registry.INSTANCE.getEPackage(ApplicationPackage.eNS_URI)).getApplicationFactory().createEjbModule();
		} else if (J2EEProjectUtilities.isApplicationClientProject(wc.getProject())) {
			return ((ApplicationPackage) EPackage.Registry.INSTANCE.getEPackage(ApplicationPackage.eNS_URI)).getApplicationFactory().createJavaClientModule();
		} else if (J2EEProjectUtilities.isJCAProject(wc.getProject())) {
			return ((ApplicationPackage) EPackage.Registry.INSTANCE.getEPackage(ApplicationPackage.eNS_URI)).getApplicationFactory().createConnectorModule();
		}
		return null;
	}

	protected Module addModule(Application application, IVirtualComponent wc) {
		Application dd = application;
		String name = wc.getName();
		
		if (J2EEProjectUtilities.isDynamicWebProject(wc.getProject())) {
			name += ".war"; //$NON-NLS-1$
		} else if (J2EEProjectUtilities.isEJBProject(wc.getProject())) {
			name += ".jar"; //$NON-NLS-1$
		} else if (J2EEProjectUtilities.isApplicationClientProject(wc.getProject())) {
			name += ".jar"; //$NON-NLS-1$
		} else if (J2EEProjectUtilities.isJCAProject(wc.getProject())) {
			name += ".rar"; //$NON-NLS-1$
		}
		Module existingModule = dd.getFirstModule(name);

		if (existingModule == null) {
			existingModule = createNewModule(wc);
			if (existingModule != null) {

				existingModule.setUri(name);
				if (existingModule instanceof WebModule) {


					Properties props = wc.getMetaProperties();
					String contextroot = ""; //$NON-NLS-1$
					if ((props != null) && (props.containsKey(J2EEConstants.CONTEXTROOT)))
						contextroot = props.getProperty(J2EEConstants.CONTEXTROOT);
					((WebModule) existingModule).setContextRoot(contextroot);
				}
				dd.getModules().add(existingModule);
			}
		}
		return existingModule;
	}

	public IStatus redo(IProgressMonitor monitor, IAdaptable info) throws ExecutionException {
		// TODO Auto-generated method stub
		return null;
	}

	public IStatus undo(IProgressMonitor monitor, IAdaptable info) throws ExecutionException {
		// TODO Auto-generated method stub
		return null;
	}

}
