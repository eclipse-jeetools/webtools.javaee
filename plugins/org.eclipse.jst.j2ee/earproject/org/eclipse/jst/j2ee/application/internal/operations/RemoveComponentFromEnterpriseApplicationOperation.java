package org.eclipse.jst.j2ee.application.internal.operations;

import java.util.List;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.jem.util.logger.proxy.Logger;
import org.eclipse.jst.j2ee.application.Application;
import org.eclipse.jst.j2ee.application.Module;
import org.eclipse.jst.j2ee.componentcore.util.EARArtifactEdit;
import org.eclipse.wst.common.componentcore.ComponentCore;
import org.eclipse.wst.common.componentcore.datamodel.properties.ICreateReferenceComponentsDataModelProperties;
import org.eclipse.wst.common.componentcore.internal.operation.RemoveReferenceComponentOperation;
import org.eclipse.wst.common.componentcore.internal.util.IModuleConstants;
import org.eclipse.wst.common.componentcore.resources.ComponentHandle;
import org.eclipse.wst.common.componentcore.resources.IVirtualComponent;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;

public class RemoveComponentFromEnterpriseApplicationOperation extends RemoveReferenceComponentOperation {


	public RemoveComponentFromEnterpriseApplicationOperation(IDataModel model) {
		super(model);
	}

	public IStatus execute(IProgressMonitor monitor, IAdaptable info) throws ExecutionException {
		super.execute(monitor, info);
		updateEARDD(monitor);
		return OK_STATUS;
	}


	protected void updateEARDD(IProgressMonitor monitor) {

		EARArtifactEdit earEdit = null;
		try {
			ComponentHandle handle = (ComponentHandle) model.getProperty(ICreateReferenceComponentsDataModelProperties.SOURCE_COMPONENT_HANDLE);
			earEdit = EARArtifactEdit.getEARArtifactEditForWrite(handle);
			if (earEdit != null) {
				Application application = earEdit.getApplication();
				List list = (List) model.getProperty(ICreateReferenceComponentsDataModelProperties.TARGET_COMPONENTS_HANDLE_LIST);
				if (list != null && list.size() > 0) {
					for (int i = 0; i < list.size(); i++) {
						ComponentHandle comphandle = (ComponentHandle) list.get(i);
						IVirtualComponent wc = ComponentCore.createComponent(comphandle.getProject(), comphandle.getName());
						removeModule(application, wc);
					}
				}
			}
			earEdit.saveIfNecessary(monitor);
		} catch (Exception e) {
			Logger.getLogger().logError(e);
		} finally {
			if (earEdit != null)
				earEdit.dispose();
		}
	}

	protected void removeModule(Application application, IVirtualComponent wc) {
		Application dd = application;

		String name = wc.getName();
		String type = wc.getComponentTypeId();

		if (type.equals(IModuleConstants.JST_WEB_MODULE)) {
			name += ".war"; //$NON-NLS-1$
		} else if (type.equals(IModuleConstants.JST_EJB_MODULE)) {
			name += ".jar"; //$NON-NLS-1$
		} else if (type.equals(IModuleConstants.JST_APPCLIENT_MODULE)) {
			name += ".jar"; //$NON-NLS-1$
		} else if (type.equals(IModuleConstants.JST_CONNECTOR_MODULE)) {
			name += ".rar"; //$NON-NLS-1$
		}
		Module existingModule = dd.getFirstModule(name);
		dd.getModules().remove(existingModule);

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
