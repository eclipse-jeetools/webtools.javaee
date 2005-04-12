package org.eclipse.jst.j2ee.application.internal.operations;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.jst.j2ee.internal.common.J2EECommonMessages;
import org.eclipse.wst.common.frameworks.datamodel.AbstractDataModelProvider;
import org.eclipse.wst.common.frameworks.internal.plugin.WTPCommonPlugin;

public class FlexibleProjectCreationDataModelProvider  extends AbstractDataModelProvider 
		implements IFlexibleProjectCreationDataModelProperties {

	public FlexibleProjectCreationDataModelProvider() {
		super();
	}
	
	public String[] getPropertyNames() {
		return new String[] {PROJECT_NAME, PROJECT_LOCATION, NESTED_MODEL_PROJECT_CREATION
		};
	}
	
	public Object getDefaultProperty(String propertyName) {
		if (PROJECT_LOCATION.equals(propertyName)) {
			return getDefaultLocation();
		}
		return super.getDefaultProperty(propertyName);
	}
//	
//	protected boolean doSetProperty(String propertyName, Object propertyValue) {
//		if (PROJECT_NAME.equals(propertyName)) {
//		    projectDataModel.setProperty(ProjectCreationDataModel.PROJECT_NAME, propertyValue);
//			serverTargetDataModel.setProperty(J2EEProjectServerTargetDataModel.PROJECT_NAME, propertyValue);
//		}
//		return true;
//	}
	
	public IStatus validate(String propertyName) {
		if (PROJECT_NAME.equals(propertyName)) {
			return validateProjectName();
		} 
		return super.validate(propertyName);
	}
	
	private IStatus validateProjectName() {
		String projectName = getStringProperty(PROJECT_NAME);
		if (projectName != null && projectName.length() != 0) {
			IProject project = ResourcesPlugin.getWorkspace().getRoot().getProject(projectName);
			if (project != null && project.exists()) {
				String msg = J2EECommonMessages.getResourceString(J2EECommonMessages.ERR_PROJECT_NAME_EXISTS);
                return WTPCommonPlugin.createErrorStatus(msg);
			}
		}
		return WTPCommonPlugin.OK_STATUS;
	}

	private String getDefaultLocation() {
		IPath path = getRootLocation();
		String projectName = (String) getProperty(PROJECT_NAME);
		if (projectName != null)
			path = path.append(projectName);
		return path.toOSString();
	}

	private IPath getRootLocation() {
		return ResourcesPlugin.getWorkspace().getRoot().getLocation();
	}
//	
//	protected void initNestedModels() {
//		super.initNestedModels();
//		initProjectModel();
//		addNestedModel(NESTED_MODEL_PROJECT_CREATION, projectDataModel);
//
//		serverTargetDataModel = new J2EEProjectServerTargetDataModel();
//		addNestedModel(NESTED_MODEL_SERVER_TARGET, serverTargetDataModel);
//	}
	
//	protected void initProjectModel() {
//		projectDataModel = new ProjectCreationDataModel();
//	}
//	
//    public IDataModelOperation getDefaultOperation() {
//        return new FlexibleProjectCreationOperation(model);
//    }
}
