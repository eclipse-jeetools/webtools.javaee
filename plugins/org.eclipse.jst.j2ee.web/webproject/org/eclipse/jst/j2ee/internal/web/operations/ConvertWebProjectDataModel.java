/*
 * Created on Jun 28, 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package org.eclipse.jst.j2ee.internal.web.operations;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.jst.j2ee.internal.web.archive.operations.WebComponentCreationDataModel;
import org.eclipse.jst.j2ee.internal.web.archive.operations.WebComponentCreationOperation;
import org.eclipse.wst.common.frameworks.internal.operations.ProjectCreationDataModel;
import org.eclipse.wst.common.frameworks.internal.operations.WTPOperation;
import org.eclipse.wst.common.internal.emfworkbench.operation.EditModelOperationDataModel;

//TODO delete
/**
 * @deprecated
 *
 */
public class ConvertWebProjectDataModel extends WebComponentCreationDataModel
{
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ibm.iwt.webproject.operations.WebProjectCreationDataModel#getDefaultOperation()
	 */
	public WTPOperation getDefaultOperation()
	{
		// TODO Auto-generated method stub
		return new WebComponentCreationOperation(this);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ibm.etools.application.operations.J2EEModuleCreationDataModel#doValidateProperty(java.lang.String)
	 */
	protected IStatus doValidateProperty(String propertyName)
	{
		if( EditModelOperationDataModel.PROJECT_NAME.equals(propertyName) )
		{
			return ProjectCreationDataModel.validateProjectName((String) getProperty(PROJECT_NAME));
		}
		return super.doValidateProperty(propertyName);
	}
	
	/* (non-Javadoc)
	 * @see com.ibm.etools.application.operations.J2EEModuleCreationDataModel#initProjectModel()
	 */
	public void initProjectModel()
	{
		// use JavaProjectConverstionDataModel instead of JavaProjectCreationDataModel
		// to avoid validating project name and location
		//TODO Doe this still make sense??
		//setProjectDataModel(new JavaProjectCreationDataModel());
	}
}